/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author MarcoPc
 */
public class StoreController {

    private static StoreController storeController;
    private ArrayList<Product> productsList;
    private ArrayList<Product> searchList;
    private ArrayList<TicketType> ticketTypesList;
    private ArrayList<EventType> eventTypesList;
    private ArrayList<Employee> employeesList;

    public StoreController() throws SQLException {

        productsList = new ArrayList<>();
        ticketTypesList = new ArrayList<>();
        eventTypesList = new ArrayList<>();
        employeesList = new ArrayList<>();
        getProductData();
        getEventData();
        getTicketData();
        getEmployeeData();

    }

    public static StoreController getStoreController() throws SQLException {
        if (storeController == null) {
            storeController = new StoreController();
        }
        return storeController;
    }

    private void getProductData() throws SQLException {
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
            System.out.println("Database connec: getProductDate() fejl");
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
    }

    public void getTicketData() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM tickettype");
            while (rs.next()) {
                TicketType ticketType = new TicketType(rs.getInt("tickettype_id"), rs.getString("tickettype_type"), rs.getInt("tickettype_pricedk"), rs.getInt("tickettype_priceeuro"));
                ticketTypesList.add(ticketType);
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("Database connec: getTicketDate() fejl");
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getEventData() throws SQLException {
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
            System.out.println("Database connec: getEventDate() fejl");
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getEmployeeData() throws SQLException {
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
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
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

    public void alterProductQuantities(ArrayList<ProductLine> saleProductList) throws SQLException {
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

}
