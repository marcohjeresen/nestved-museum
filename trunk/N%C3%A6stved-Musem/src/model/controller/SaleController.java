/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import Util.DateFormatTools;
import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EventLine;
import model.Invoice;
import model.InvoiceStatus;
import model.PaymentType;
import model.ProductLine;
import model.Sale;
import model.TicketLine;
import museum.MainView;

/**
 *
 * @author MarcoPc
 */
public class SaleController {

    private static SaleController saleController;
    private ArrayList<PaymentType> paymentTypesList;
    private DateFormatTools dateFormatTools;

    public SaleController() {
        paymentTypesList = new ArrayList<>();
        dateFormatTools = new DateFormatTools();
    }

    public static SaleController controller() {
        if (saleController == null) {
            saleController = new SaleController();
        }
        return saleController;
    }

    public void getPaymentData() throws ClassNotFoundException, SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM paymenttype");
            while (rs.next()) {
                PaymentType paymentType = new PaymentType(rs.getInt("paymenttype_id"), rs.getString("paymenttype_type"), rs.getInt("paymenttype_fee"));
                paymentTypesList.add(paymentType);
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("Database connec: getPaymentDate() fejl");
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void endSale(Sale sale1, boolean discount) throws ClassNotFoundException, SQLException {
        DBConnection db = new DBConnection();
        int invoiceId = 0;
        int saleId = 0;
        int productLineId = 0;
        int eventLineId = 0;
        int ticketLineId = 0;

        ResultSet rs;
        try {
            rs = db.getResult("select max(sale_id) from sale;");
            while (rs.next()) {
                saleId = rs.getInt("max(sale_id)");
            }

            rs = db.getResult("select max(invoice_id) from invoice;");
            while (rs.next()) {
                invoiceId = rs.getInt("max(invoice_id)");
            }

            rs = db.getResult("select max(productline_id) from productline");
            while (rs.next()) {
                productLineId = rs.getInt("max(productline_id)");
            }
            
            rs = db.getResult("select max(eventline_id) from eventline");
            while (rs.next()) {
                eventLineId = rs.getInt("max(eventline_id)");
            }
            
            rs = db.getResult("select max(ticketline_id) from ticketline");
            while (rs.next()) {
                ticketLineId = rs.getInt("max(ticketline_id)");
            }

//            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String dato = dateFormatTools.getDateNowString();
        invoiceId = invoiceId + 1;
        Invoice invoice = new Invoice(invoiceId, dato, sale1.getEndpriceDk(discount), sale1.getEndpriceEuro(discount), 1);

        sale1.setInvoice(invoice);

        sale1.setDate();
        saleId = saleId + 1;
        try {
            int count = 1;
            db.execute("insert into sale values(" + saleId + "," + "1" + "," + sale1.getEmployee().getCpr() + ",'" + sale1.getDate() + "')");
           
            if (!sale1.getProductLine().isEmpty()) {
                for (ProductLine productLine : sale1.getProductLine()) {
                    productLineId = productLineId + count;
                    db.execute("insert into productline values(" + productLineId + "," + productLine.getProduct().getProductNumber() + "," + saleId + "," + productLine.getQuantities() + ")");
                    count++;
                }
            }
            if (!sale1.getEventLine().isEmpty()) {
                count = 1;
                for (EventLine eventLine : sale1.getEventLine()) {
                    eventLineId = eventLineId + count;
                    db.execute("insert into eventline values (" + eventLineId + "," + eventLine.getEventtype().getId() + "," + saleId + "," + eventLine.getQuantities() + ",'" + eventLine.getDate() + "'," + eventLine.getCustomer() + ",'" + eventLine.getPlace() + "')");
                    count++;
                }
            }
            if (!sale1.getTicketLine().isEmpty()) {
                count = 1;
                for (TicketLine ticketLine : sale1.getTicketLine()) {
                    ticketLineId = ticketLineId + count;
                    db.execute("insert into ticketline values(" + ticketLineId + "," + ticketLine.getTicketType().getId() + "," + saleId + "," + ticketLine.getQuantities() + ")");
                    count++;
                }
            }
            if (sale1.getInvoice() != null) {

                db.execute("insert into invoice values (" + sale1.getInvoice().getId() + "," + saleId + ",'" + dato + "'," + sale1.getInvoice().getPriceDk() + "," + sale1.getInvoice().getPriceEuro() + "," + sale1.getInvoice().getInvoiceStatus() + ")");
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("SaleController: endSale: fejl: " + ex.getLocalizedMessage());
        }
    }

}
