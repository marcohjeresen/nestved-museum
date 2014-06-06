
package model.controller;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.EventType;
import model.Product;
import model.ProductLine;
import model.TicketType;
import museum.MainView;
import utility.DateFormatTools;


public class StoreController {

    private static StoreController storeController;
    private ArrayList<Product> productsList;
    private ArrayList<Product> searchList;
    private ArrayList<TicketType> ticketTypesList;
    private ArrayList<EventType> eventTypesList;
    private ArrayList<Employee> employeesList;

    public StoreController() {

        productsList = new ArrayList<>();
        ticketTypesList = new ArrayList<>();
        eventTypesList = new ArrayList<>();
        employeesList = new ArrayList<>();
        getProductData();
        getEventData();
        getTicketData();
        getEmployeeData();

    }

    /**
     * Method, creates a singleton of StoreController if it doesn't exist already.
     * @return storeController.
     */
    public static StoreController getStoreController() {
        if (storeController == null) {
            storeController = new StoreController();
        }
        return storeController;
    }

    /**
     * Method, retrieves all the products from the database.
     */
    private void getProductData() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rse = db.getResult("select product_numberid, product_name, product_supplier, \n"
                    + "product_buyprice, product_saleprice_dk, \n"
                    + "product_saleprice_euro, product_discount, \n"
                    + "product_quantities, productgroup_id, productgroup_type\n"
                    + "from product, productgroup\n"
                    + "where product_groupid = productgroup_id\n"
                    + "order by product_quantities");
            while (rse.next()) {
                Product pd = new Product(rse.getInt("product_numberid"), rse.getString("product_name"),
                        rse.getString("product_supplier"), rse.getInt("product_buyprice"),
                        rse.getInt("product_saleprice_dk"), rse.getInt("product_saleprice_euro"),
                        rse.getInt("product_discount"), rse.getInt("product_quantities"),
                        rse.getInt("productgroup_id"), rse.getString("productgroup_type"));
                productsList.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println("model.controller - StoreController - getProductData() error" + ex.getLocalizedMessage());
        }
        db.close();
    }

    
    /**
     * Method, retrieves all the tickets from the database.
     */
    public void getTicketData() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM tickettype");
            while (rs.next()) {
                TicketType ticketType = new TicketType(rs.getInt("tickettype_id"), rs.getString("tickettype_type"), rs.getInt("tickettype_pricedk"), rs.getInt("tickettype_priceeuro"));
                ticketTypesList.add(ticketType);
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("model.controller - StoreController - getTicketData() error" + ex.getLocalizedMessage());
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Method, retrieves all the events from the database.
     */
    public void getEventData() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM eventtype order by eventtype_type");
            while (rs.next()) {
                EventType eventType = new EventType(rs.getInt("eventtype_id"), rs.getString("eventtype_type"), rs.getInt("eventtype_pricedk"),
                        rs.getInt("eventtype_priceeuro"));
                eventTypesList.add(eventType);
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("model.controller - StoreController - getEventData() error" + ex.getLocalizedMessage());
        }

    }

    /**
     * Method, retrieves all the employee data from the database.
     */
    public void getEmployeeData() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM employee");
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("employee_cpr"), rs.getString("employee_name"), rs.getString("employee_adresse"),
                        rs.getInt("employee_postzip"), rs.getString("employee_city"), rs.getInt("employee_password"));
                employeesList.add(employee);
            }
        } catch (SQLException ex) {
            System.out.println("Database connec: getEmployeeDate() fejl");
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM employeephone");
            while (rse.next()) {
                for (Employee employee : employeesList) {
                    if (employee.getCpr() == rse.getInt("employeephone_cpr")) {
                        employee.setPhoneList(rse.getInt("employeephone_phone"));
                    }
                }
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("model.controller - StoreController - getEmployeeData() error" + ex.getLocalizedMessage());
        }
    }

    public ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public ArrayList<TicketType> getTicketTypesList() {
        return ticketTypesList;
    }

    public ArrayList<EventType> getEventTypesList() {
        return eventTypesList;
    }

    
    /**
     * Method, checks the database if theres an event on the specific day.
     * @param date
     * @return dayEvent, as a String with all the informations about the event.
     */
    public String getDayEvent(String date) {
        String dayEvent = "";
        DateFormatTools dt = new DateFormatTools();
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * from eventline where eventline_date like '" + date + "_________'");
            while (rs.next()) {
                for (EventType eventType : eventTypesList) {
                    if (rs.getInt("eventline_eventtype_id") == eventType.getId()) {
                        dayEvent = dayEvent + "Sted: " + rs.getString("eventLine_place") + 
                                "\nTid: "+ dt.getTime(rs.getString("eventline_date"))+
                                "\nType: " + eventType.getType() + "\nKundeNummer: "+rs.getInt("eventline_customer_phone")+
                                "\nAntal Personer: "+ rs.getInt("eventline_quantities")+"\n\n";
                    }
                }

            }
        }catch(SQLException ex){
             System.out.println("model.controller - StoreController - getDayEvent() error" + ex.getLocalizedMessage());
        }

        return dayEvent;
    }
    
    /**
     * Method, checks the database for events on the specific date.
     * @param date
     * @return isthere, as a boolean.
     */
    public Boolean areThereEvent(String date) {
        boolean isthere = false;
        DBConnection db = new DBConnection();

        try {
            ResultSet rs = db.getResult("SELECT * from eventline where eventline_date like '" + date + "_________'");
            while (rs.next()) {
                isthere = true;
            }
        }catch(SQLException ex){
            System.out.println("model.controller - StoreController - arThereEvent() error" + ex.getLocalizedMessage());
        }
        db.close();
        return isthere;
        
    }

    /**
     * Method, changes the amount of products in the database.
     * @param saleProductList 
     */
    public void alterProductQuantities(ArrayList<ProductLine> saleProductList) {
        DBConnection db = new DBConnection();
        int quantities = 0;
        for (ProductLine salePL : saleProductList) {
            for (int i = 0; i < productsList.size(); i++) {
                if (salePL.getProduct().equals(productsList.get(i))) {
                    quantities = productsList.get(i).getQuantities() - salePL.getQuantities();
                    productsList.get(i).setQuantities(quantities);
                    db.execute("update product set product_quantities = " + quantities + " where product_numberid = " + productsList.get(i).getProductNumber() + ";");
                }
            }
        }
        db.close();
    }

    public void setEmployeesList(ArrayList<Employee> employeesList) {
        this.employeesList = employeesList;
    }

}
