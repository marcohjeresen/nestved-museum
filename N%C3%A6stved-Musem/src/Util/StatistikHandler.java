/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Handler.SaleHandler;
import museum.MainView;

/**
 *
 * @author MarcoPc
 */
public class StatistikHandler {
    private static StatistikHandler statistikHandler;
    private ArrayList<Line> lineList;
    private DateFormatTools dateFormat;
    private SaleHandler saleHandler;

    private StatistikHandler() throws SQLException {
        lineList = new ArrayList<>();
        dateFormat = new DateFormatTools();
        saleHandler = SaleHandler.getSaleHandler();
    }

    public static StatistikHandler getStatistikHandler() throws SQLException {
        if (statistikHandler == null) {
            statistikHandler = new StatistikHandler();
        }
        return statistikHandler;
    }

    public ArrayList<Line> getWeekStat(String date) {
        lineList.removeAll(lineList);
        Calendar StringDate = dateFormat.getStartDateFromString(date);
        Calendar fromDate = StringDate;
        int count = 7;
        for (int i = 0; i < count; i++) {
            if (i != 0) {
                fromDate = dateFormat.getNextday(dateFormat.getDateFromString(date), i);
            }
            String datefrom = dateFormat.getShortDateFromCal(fromDate);
            DBConnection db = new DBConnection();
            try {
                ResultSet rse = db.getResult("select ticketline_quantities, tickettype_type,sale_date, sale_id "
                        + "from ticketline, tickettype, sale "
                        + "where sale_date like '" + datefrom + "_________' and ticketline_sale_id = sale_id "
                        + "and ticketline_tickettype_id = tickettype_id");
                while (rse.next()) {
                    boolean erder = false;
                    if (!lineList.isEmpty()) {
                        for (int j = 0; j < lineList.size(); j++) {
                            if (lineList.get(j).getTicketDate() == datefrom) {
                                erder = false;
                                switch (rse.getString("tickettype_type")) {
                                    case "Voksenbillet, over 18 år": {
                                        erder = true;
                                        int quantities = lineList.get(j).getTkAdultQu();
                                        lineList.get(j).setTkAdultQu(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                    case "Børnebillet, under 18 år": {
                                        erder = true;
                                        int quantities = lineList.get(j).getTkKidsQu();
                                        lineList.get(j).setTkKidsQu(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                    case "Gruppebillet, min. 10 personer": {
                                        erder = true;
                                        int quantities = lineList.get(j).getTkAGroupQu();
                                        lineList.get(j).setTkAGroupQu(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                    case "Gratister (Museumskort, foregning, ovs)": {
                                        erder = true;
                                        int quantities = lineList.get(j).getTkFreeQu();
                                        lineList.get(j).setTkFreeQu(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                }
                            }
                        }
                        if (!erder) {
                            Line l = new Line("", 0, 0);
                            switch (rse.getString("tickettype_type")) {
                                case "Voksenbillet, over 18 år": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTkAdultQu(quantities);
                                    break;
                                }
                                case "Børnebillet, under 18 år": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTkKidsQu(quantities);
                                    break;
                                }
                                case "Gruppebillet, min. 10 personer": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTkAGroupQu(quantities);
                                    break;
                                }
                                case "Gratister (Museumskort, foregning, ovs)": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTkFreeQu(quantities);
                                    break;
                                }
                            }
                            l.setTicketDate(datefrom);
                            lineList.add(l);
                        }
                    } else {
                        Line l = new Line("", 0, 0);
                        switch (rse.getString("tickettype_type")) {
                            case "Voksenbillet, over 18 år": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTkAdultQu(quantities);
                                break;
                            }
                            case "Børnebillet, under 18 år": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTkKidsQu(quantities);
                                break;
                            }
                            case "Gruppebillet, min. 10 personer": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTkAGroupQu(quantities);
                                break;
                            }
                            case "Gratister (Museumskort, foregning, ovs)": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTkFreeQu(quantities);
                                break;
                            }
                        }
                        l.setTicketDate(datefrom);
                        lineList.add(l);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Database connec: getProductDate() fejl");
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ResultSet rs = db.getResult("select eventline_quantities, eventline_sale_id, sale_date "
                        + "from eventline, sale "
                        + "where sale_date like '" + datefrom + "_________' and eventline_sale_id = sale_id");
                while (rs.next()) {
                    boolean erder = false;
                    for (int j = 0; j < lineList.size(); j++) {
                        if (lineList.get(j).getTicketDate() == datefrom) {
                            int sold = lineList.get(i).getEvSold();
                            int quintities = lineList.get(i).getEvQuantities();
                            lineList.get(i).setEvQuantities(quintities + rs.getInt("eventline_quantities"));
                            lineList.get(i).setEvSold(sold + 1);
                            erder = true;
                        }
                    }
                    if (!erder) {
                        Line l = new Line("", 0, 0);
                        l.setEvQuantities(rs.getInt("eventline_quantities"));
                        l.setEvSold(1);
                        l.setTicketDate(datefrom);
                        lineList.add(l);
                    }
                }
            } catch (SQLException ex) {

            }
            try {
                db.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatistikHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lineList;
    }
    
}
