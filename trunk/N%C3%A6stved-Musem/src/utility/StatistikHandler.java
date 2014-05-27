/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author MarcoPc
 */
public class StatistikHandler {
    private static StatistikHandler statistikHandler;
    private ArrayList<Line> lineList;
    private DateFormatTools dateFormat;
    

    private StatistikHandler() {
        lineList = new ArrayList<>();
        dateFormat = new DateFormatTools();
        
    }

    public static StatistikHandler getStatistikHandler() {
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
                    boolean isThere = false;
                    if (!lineList.isEmpty()) {
                        for (int j = 0; j < lineList.size(); j++) {
                            if (lineList.get(j).getTicketDate().equals(datefrom)) {
                                isThere = false;
                                switch (rse.getString("tickettype_type")) {
                                    case "Voksenbillet, over 18 år": {
                                        isThere = true;
                                        int quantities = lineList.get(j).getTicketAdultQuantities();
                                        lineList.get(j).setTicketAdultQuantities(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                    case "Børnebillet, under 18 år": {
                                        isThere = true;
                                        int quantities = lineList.get(j).getTicketChildenQuantities();
                                        lineList.get(j).setTicketChildenQuantities(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                    case "Gruppebillet, min. 10 personer": {
                                        isThere = true;
                                        int quantities = lineList.get(j).getTicketGroupQuantities();
                                        lineList.get(j).setTicketGroupQuantities(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                    case "Gratister (Museumskort, foregning, ovs)": {
                                        isThere = true;
                                        int quantities = lineList.get(j).getTicketFreeQuantities();
                                        lineList.get(j).setTicketFreeQuantities(quantities + rse.getInt("ticketline_quantities"));
                                        break;
                                    }
                                }
                            }
                        }
                        if (!isThere) {
                            Line l = new Line("", 0, 0);
                            switch (rse.getString("tickettype_type")) {
                                case "Voksenbillet, over 18 år": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTicketAdultQuantities(quantities);
                                    break;
                                }
                                case "Børnebillet, under 18 år": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTicketChildenQuantities(quantities);
                                    break;
                                }
                                case "Gruppebillet, min. 10 personer": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTicketGroupQuantities(quantities);
                                    break;
                                }
                                case "Gratister (Museumskort, foregning, ovs)": {
                                    int quantities = rse.getInt("ticketline_quantities");
                                    l.setTicketFreeQuantities(quantities);
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
                                l.setTicketAdultQuantities(quantities);
                                break;
                            }
                            case "Børnebillet, under 18 år": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTicketChildenQuantities(quantities);
                                break;
                            }
                            case "Gruppebillet, min. 10 personer": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTicketGroupQuantities(quantities);
                                break;
                            }
                            case "Gratister (Museumskort, foregning, ovs)": {
                                int quantities = rse.getInt("ticketline_quantities");
                                l.setTicketFreeQuantities(quantities);
                                break;
                            }
                        }
                        l.setTicketDate(datefrom);
                        lineList.add(l);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("utility - StatistikHandler - getWeekStat(): sql Error :"+ ex.getLocalizedMessage());
            }
            try {
                ResultSet rs = db.getResult("select eventline_quantities, eventline_sale_id, sale_date "
                        + "from eventline, sale "
                        + "where sale_date like '" + datefrom + "_________' and eventline_sale_id = sale_id");
                while (rs.next()) {
                    boolean erder = false;
                    for (int j = 0; j < lineList.size(); j++) {
                        if (lineList.get(j).getTicketDate().equals(datefrom)) {
                            int sold = lineList.get(j).getEventSold();
                            int quintities = lineList.get(j).getEventQuantities();
                            lineList.get(j).setEventQuantities(quintities + rs.getInt("eventline_quantities"));
                            lineList.get(j).setEventSold(sold + 1);
                            erder = true;
                        }
                    }
                    if (!erder) {
                        Line l = new Line("", 0, 0);
                        l.setEventQuantities(rs.getInt("eventline_quantities"));
                        l.setEventSold(1);
                        l.setTicketDate(datefrom);
                        lineList.add(l);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("utility - StatistikHandler - getWeekStat(): sql Error :"+ ex.getLocalizedMessage());
            }
            db.close();
        }
        return lineList;
    }
    
}
