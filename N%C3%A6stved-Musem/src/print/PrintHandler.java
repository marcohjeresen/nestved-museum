/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import Util.DateFormatTools;
import Util.Line;
import db.DBConnection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EventLine;
import model.ProductLine;
import model.*;
import model.TicketLine;
import model.Handler.MoneyHandler;
import model.Handler.SaleHandler;
import model.Handler.StoreHandler;
import model.controller.StoreController;
import museum.MainView;

/**
 *
 * @author MarcoPc
 */
public class PrintHandler implements Printable {

    public static final int LINES_PER_PAGE = 23;
    private ArrayList<Line> lines;
    ArrayList<ProductLine> productLines;
    ArrayList<TicketLine> ticketLines;
    ArrayList<EventLine> eventLines;
    private int xCord;
    private SaleHandler saleHandler;
    private StoreHandler storeHandler;
    private StoreController storeController;
    private MoneyHandler moneyHandler;
    private DateFormatTools dateFormatTools;

    public PrintHandler() throws SQLException, ClassNotFoundException {
        saleHandler = SaleHandler.getSaleHandler();
        storeHandler = StoreHandler.storeHandler();
        moneyHandler = MoneyHandler.getMoneyHandler();
        storeController = StoreController.getStoreController();
        dateFormatTools = new DateFormatTools();
        lines = new ArrayList<>();
        productLines = new ArrayList<>();
        ticketLines = new ArrayList<>();
        eventLines = new ArrayList<>();
        xCord = 10;
    }

    public void kvitteringPrint(Sale sale, boolean discount) {
        lines.removeAll(lines);
        Line startLine = new Line("Næstved Museum", 0, 0);
        Line startLine2 = new Line("En afdeling af Museum Sydøstdanmark", 0, 0);
        Line emty = new Line("", 0, 0);
        lines.add(startLine);
        lines.add(startLine2);
        lines.add(emty);
        int priceDk = 0;
        int priceEuro = 0;
        xCord = 27;

        if (!sale.getProductLine().isEmpty()) {
            Line produ = new Line("Produkter: ", 0, 0);
            lines.add(produ);
            lines.add(emty);
            for (ProductLine productLine : sale.getProductLine()) {
                priceDk = productLine.getProduct().getPriceDk() * productLine.getQuantities();
                priceEuro = productLine.getProduct().getPriceEuro() * productLine.getQuantities();
                Line product = new Line(productLine.getProduct().getName() + "", 0, 0);
                Line productPrice = new Line("Antal: " + productLine.getQuantities(), priceDk, priceEuro);
                lines.add(product);
                lines.add(productPrice);
            }
            lines.add(emty);
        }
        if (!sale.getEventLine().isEmpty()) {
            Line event = new Line("Event: ", 0, 0);
            lines.add(event);
            lines.add(emty);
            for (EventLine eventLine : sale.getEventLine()) {
                priceDk = eventLine.getEventlinePriceDk();
                priceEuro = eventLine.getEventlineEuro();
                Line eventLineText = new Line(eventLine.getEventtype().getType() + "", 0, 0);
                Line eventLinePlace = new Line(eventLine.getPlace() + "", 0, 0);
                Line eventLinePrice = new Line("Antal: " + eventLine.getQuantities(), priceDk, priceEuro);
                lines.add(eventLineText);
                lines.add(eventLinePlace);
                lines.add(eventLinePrice);
            }
            lines.add(emty);
        }
        if (!sale.getTicketLine().isEmpty()) {
            Line ticket = new Line("Ticket: ", 0, 0);
            lines.add(ticket);
            lines.add(emty);
            for (TicketLine ticketLine : sale.getTicketLine()) {
                priceDk = ticketLine.getTicketType().getPriceDk() * ticketLine.getQuantities();
                priceEuro = ticketLine.getTicketType().getPriceEuro() * ticketLine.getQuantities();
                Line ticketLineText = new Line(ticketLine.getTicketType().getType(), 0, 0);
                Line ticketPrice = new Line("Antal: " + ticketLine.getQuantities(), priceDk, priceEuro);
                lines.add(ticketLineText);
                lines.add(ticketPrice);
            }
            lines.add(emty);
        }
        Line totelPrice = new Line("Total Pris: ", sale.getEndpriceDk(discount), sale.getEndpriceEuro(discount));
        lines.add(totelPrice);
        Line employ = new Line("Du Bliv Betjent Af:", 0, 0);
        Line employ2 = new Line(sale.getEmployee().getName(), 0, 0);
        Line end = new Line("Tak For Besøget Og På Gensyn!", 0, 0);
        lines.add(employ);
        lines.add(employ2);
        lines.add(end);

        doPrint();
    }

    public void cashReport() throws ClassNotFoundException, SQLException {
        Line empty = new Line("", 0, -1);
        String date = dateFormatTools.getDateNowShortString();
        System.out.println(date);
        DBConnection db = new DBConnection();
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<ProductLine> prodLineList = new ArrayList<>();
        ArrayList<EventLine> eventLineList = new ArrayList<>();
        ArrayList<TicketLine> ticketLineList = new ArrayList<>();
        boolean isthere = false;
        try {
            ResultSet rs = db.getResult("SELECT sale_id from sale where sale_date like '" + date + "_________'");
            while (rs.next()) {
                System.out.println(rs.getInt("sale_id"));
                idList.add(rs.getInt("sale_id"));
            }
            for (Integer integer : idList) {
                rs = db.getResult("select * from productline where productline_sale_id = " + integer + "");
                while (rs.next()) {
                    for (Product product : storeController.getProductsList()) {
                        if (product.getProductNumber() == rs.getInt("productline_product_id")) {
                            for (int i = 0; i < prodLineList.size(); i++) {
                                if (prodLineList.get(i).getProduct().getProductNumber() == rs.getInt("productline_product_id")) {
                                    isthere = true;
                                    int quantities = prodLineList.get(i).getQuantities();
                                    prodLineList.get(i).setQuantities(quantities + rs.getInt("productline_quantities"));
                                }
                            }
                            if (!isthere) {
                                ProductLine pl = new ProductLine(0, null, product, rs.getInt("productline_quantities"));
                                prodLineList.add(pl);
                            }
                        }
                    }
                }
            }
            isthere = false;
            for (Integer integer : idList) {
                rs = db.getResult("select * from eventline where eventline_sale_id = " + integer + "");
                while (rs.next()) {
                    for (EventType eventType : storeController.getEventTypesList()) {
                        if (eventType.getId() == rs.getInt("eventline_eventtype_id")) {
                            for (int i = 0; i < eventLineList.size(); i++) {
                                if (eventLineList.get(i).getEventtype().getId() == rs.getInt("eventline_eventtype_id")) {
                                    isthere = true;
                                    int quantities = eventLineList.get(i).getQuantities();
                                    eventLineList.get(i).setQuantities(quantities + rs.getInt("eventline_quantities"));
                                }
                            }
                            if (!isthere) {
                                EventLine et = new EventLine(0, eventType, null, rs.getInt("eventline_quantities"), "", 0, "");
                                eventLineList.add(et);
                            }
                        }
                    }
                }
            }
            isthere = false;
            for (Integer integer : idList) {
                rs = db.getResult("select * from ticketline where ticketline_sale_id = " + integer + "");
                while (rs.next()) {
                    for (TicketType ticket : storeController.getTicketTypesList()) {
                        if (ticket.getId() == rs.getInt("ticketline_tickettype_id")) {
                            for (int i = 0; i < ticketLineList.size(); i++) {
                                if (ticketLineList.get(i).getTicketType().getId() == rs.getInt("ticketline_tickettype_id")) {
                                    isthere = true;
                                    int quantities = ticketLineList.get(i).getQuantities();
                                    ticketLineList.get(i).setQuantities(quantities + rs.getInt("ticketline_quantities"));
                                }
                            }
                            if (!isthere) {
                                TicketLine tl = new TicketLine(0, null, rs.getInt("ticketline_quantities"), ticket);
                                ticketLineList.add(tl);
                            }
                        }
                    }
                }
            }

            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }

        lines.removeAll(lines);
        Line pro = new Line("Produkter: ", 0, -1);
        lines.add(pro);
        int totalProd = 0;
        for (ProductLine productLine : prodLineList) {
            String title = "" + productLine.getProduct().getProductNumber() + "  " + productLine.getProduct().getName();
            Line l = new Line(title, productLine.getQuantities(), -1);
            lines.add(l);
            totalProd = totalProd + productLine.getQuantities();
        }
        Line tic = new Line("Billetter: ", 0, -1);
        lines.add(tic);
        int totalTic = 0;
        for (TicketLine ticketLine : ticketLineList) {
            String title = "" + ticketLine.getTicketType().getId() + " " + ticketLine.getTicketType().getType();
            Line l = new Line(title, ticketLine.getQuantities(), -1);
            lines.add(l);
            totalTic = totalTic + ticketLine.getQuantities();
        }
        Line evt = new Line("Event: ", 0, -1);
        lines.add(evt);
        int totalEvt = 0;
        for (EventLine eventLine : eventLineList) {
            String title = "" + eventLine.getEventtype().getId() + " " + eventLine.getEventtype().getType();
            Line l = new Line(title, eventLine.getQuantities(), -1);
            lines.add(l);
            totalEvt = totalEvt + eventLine.getQuantities();
        }
        Line t = new Line("-------------------------------------------------------", 0, -1);
        Line tot = new Line("Alt I Alt Salg: ", 0, -1);
        Line totalp = new Line("Product:    Antal I Alt: " + totalProd + "    Antal Forskelige: " + productLines.size(), 0, -1);
        Line totalt = new Line("Billetter:    Antal I Alt: " + totalTic + "    Antal Forskelige: " + ticketLines.size(), 0, -1);
        Line totale = new Line("Event:    Antal I Alt: " + totalEvt + "    Antal Forskelige: " + eventLines.size(), 0, -1);

        lines.add(t);
        lines.add(tot);
        lines.add(totalp);
        lines.add(totalt);
        lines.add(totale);
        lines.add(t);
        Line cash = new Line("Kasse Opgørelse:", 0, -1);
        lines.add(cash);
        db = new DBConnection();
        try {
            ResultSet rs = db.getResult("select *  from cashregistercontent , differance "
                    + "where starting_date like '" + date + "_________' and starting_id = differance_id");
            while (rs.next()) {
                for (Employee employee : storeController.getEmployeesList()) {
                    if (employee.getCpr() == rs.getInt("starting_employee_cpr")) {
                        Line ll = new Line("Kasse Start:   Dato: " + rs.getString("starting_date"), 0, -1);
                        double dk = rs.getInt("starting_amount_dk") / 100;
                        double euro = rs.getInt("starting_amount_euro") / 100;
                        Line l = new Line("Medarbejder: " + storeHandler.getLogEmployee().getName() + " DK I Kassen: " + dk + " EURO I Kassen: " + euro, 0, -1);
                        lines.add(ll);
                        lines.add(l);
                    }
                    if (employee.getCpr() == rs.getInt("differance_employeecpr")) {
                        DifferanceRegistre d = new DifferanceRegistre(rs.getInt("differance_id"), employee, rs.getInt("differance_currentcashdk"), rs.getInt("differance_currentcasheuro"),
                                rs.getInt("differance_expecteddk"), rs.getInt("differance_expectedeuro"), rs.getInt("differance_differancedk"), rs.getInt("differance_differanceeuro"), rs.getString("differance_date"));
                        Line lll = new Line("Kasse Slut:   Dato: " + d.getDate(), 0, -1);
                        double fDk = d.getExpectedDk() / 100;
                        double fEuro = d.getExpectedEuro() / 100;
                        double SDk = d.getCurrentCashDk() / 100;
                        double sEuro = d.getCurrentCahsEuro() / 100;
                        Line llll = new Line("" + d.getEmployee().getName() + "  ForvDk: " + fDk + " ForvEuro: " + fEuro
                                + " SlutDk: " + SDk + " SlutEuro: " + sEuro, 0, -1);
                        double DDk = d.getDifferanceDk() / 100;
                        double DEuro = d.getDifferanceEuro() / 100;
                        Line dif = new Line("Difference Type: " + " DifDk: " + DDk + " DifEuro: " + DEuro, 0, -1);
                        lines.add(lll);
                        lines.add(llll);
                        lines.add(dif);
                        lines.add(empty);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        doPrint();
    }

    public void drawLines(Graphics g, int page) {
        g.setColor(new Color(150, 190, 255));
        g.fillRect(5, 10, 10 * xCord, 75);
        g.setColor(new Color(50, 90, 155));
        g.drawRect(5, 10, 10 * xCord, 75);
        g.setColor(Color.BLACK);
        Calendar cal = Calendar.getInstance();
        g.drawString(cal.getTime().toString(), 5, 80);

        int lineCount = 0;
        for (int i = page * LINES_PER_PAGE; i < (page + 1) * LINES_PER_PAGE; i++) {
            if (i < lines.size()) {
                int yCoord = 120 + 20 * (i - page * LINES_PER_PAGE);
                if (lines.get(i).getPriceDk() != 0) {
                    if (lines.get(i).getPriceEuro() == -1) {
                        g.drawString(lines.get(i).getText(), 10, yCoord);
                        int priceDk = (lines.get(i).getPriceDk());
                        g.drawString(priceDk + "", 9 * xCord, yCoord);
                    } else if (lines.get(i).getPriceEuro() == -2) {
                        g.drawString(lines.get(i).getProductNumber(), 10, yCoord);
                        g.drawString(lines.get(i).getProductTitle(), 70, yCoord);
                        g.drawString(lines.get(i).getProductSupplier(), 260, yCoord);
                        if (!"KøbsPris".equals(lines.get(i).getProductBuyPrice())) {
                            int priceeee = Integer.parseInt(lines.get(i).getProductBuyPrice());
                            double price = priceeee / 100;
                            g.drawString(price + "", 370, yCoord);
                        } else {
                            g.drawString(lines.get(i).getProductBuyPrice(), 370, yCoord);
                        }
                        g.drawString(lines.get(i).getProductQuantities(), 450, yCoord);
                    }
                } else {
                    g.drawString(lines.get(i).getText(), 10, yCoord);
                    double priceDk = (lines.get(i).getPriceDk() / 100);
                    g.drawString(priceDk + " kr", 9 * xCord, yCoord);
                }
                lineCount++;
            }
        }
        g.drawString("Side " + (page + 1), 10, 175 + lineCount * 20);
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        int printResult;
        if (page > lines.size() / LINES_PER_PAGE) {
            printResult = NO_SUCH_PAGE;
        } else {
            // Find øverste venstre hjørne i det printbare område
            // Forskyd g2d, så (0,0) svarer til øverste venstre hjørne
            Graphics2D g2d = (Graphics2D) g;
            double x0 = pf.getImageableX();
            double y0 = pf.getImageableY();
            g2d.translate(x0, y0);
            drawLines(g2d, page);
            printResult = PAGE_EXISTS;
        }
        return printResult;
    }

    public void doPrint() {
        PrinterJob job = PrinterJob.getPrinterJob();
        Printable doc = this;
        job.setPrintable(doc);
        boolean accept = job.printDialog();
        if (accept) {
            try {
                job.print();
            } catch (PrinterException ex) {
                System.out.println("printer problemmer");
            }
        }
    }
}
//    

