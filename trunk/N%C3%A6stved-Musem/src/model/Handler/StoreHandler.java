/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Handler;

import utility.DateFormatTools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.EventType;
import model.Product;
import model.TicketType;
import model.controller.StoreController;
import utility.Listeners;

/**
 *
 * @author MarcoPc
 */
public class StoreHandler {

    private static StoreHandler storeHandler;
    private StoreController controller;
    private Employee logEmployee;
    private boolean search;
    private Listeners listeners;
    private ArrayList<Product> choosenProduct;
    private TicketType choosenTicket;
    private EventType choosenEvent;
    private String event;

    public StoreHandler() {
        try {
            controller = StoreController.getStoreController();
            listeners = Listeners.getList();
            search = false;
            choosenProduct = new ArrayList<>();
            choosenTicket = null;
            choosenEvent = null;
        } catch (SQLException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static StoreHandler storeHandler() {
        if (storeHandler == null) {
            storeHandler = new StoreHandler();
        }
        return storeHandler;
    }

    public Employee getLogEmployee() {
        return logEmployee;
    }

    public void logOutEmployee() {
        logEmployee = null;
    }

    public boolean employeeLogin(int kode) {
        boolean logon = false;
        for (Employee employee : controller.getEmployeesList()) {
            if (employee.getPassword() == kode) {
                logEmployee = employee;
                logon = true;
            }
        }
        return logon;
    }

    public HashSet<String> getProductGroups() {
        HashSet<String> ProductGroups = new HashSet<>();
        for (Product product : controller.getProductsList()) {
            ProductGroups.add(product.getGroupType());
        }
        return ProductGroups;
    }

    public ArrayList<Product> getProductList() {
        return controller.getProductsList();
    }

    public ArrayList<TicketType> getTicketType() {
        return controller.getTicketTypesList();
    }

    public ArrayList<EventType> getEventType() {
        return controller.getEventTypesList();
    }

    public boolean search() {
        return search;
    }

    public void setChoosenProduct(String productGroup) {
        choosenProduct.removeAll(choosenProduct);
        for (Product product : controller.getProductsList()) {
            if (product.getGroupType().equals(productGroup)) {
                choosenProduct.add(product);
            }
        }
        listeners.notifyListeners("Choosen Product");
    }

    public void setSearchProduct(int productNumber) {
        choosenProduct.removeAll(choosenProduct);
        for (Product product : controller.getProductsList()) {
            if (product.getProductNumber() == productNumber) {
                choosenProduct.add(product);
            }
        }
        listeners.notifyListeners("Choosen Product");
    }

    public ArrayList<Product> getChoosenProduct() {
        return choosenProduct;
    }

    public void setChoosenTicket(String ticketType) {
        choosenTicket = null;
        for (TicketType ticketType1 : controller.getTicketTypesList()) {
            if (ticketType1.getType().equals(ticketType)) {
                choosenTicket = ticketType1;
            }
        }
        listeners.notifyListeners("Choosen Ticket");
    }

    public TicketType getChoosenTicket() {
        return choosenTicket;
    }

    public void setChoosenEvent(String eventType) {
        choosenEvent = null;
        for (EventType eventType1 : controller.getEventTypesList()) {
            if (eventType1.getType().equals(eventType)) {
                choosenEvent = eventType1;
            }
        }
        listeners.notifyListeners("Choosen Event");
    }

    public EventType getChoosenEventType() {
        return choosenEvent;
    }

    public Boolean dayHaveEvent(Calendar c) {
        boolean isthere = false;
        try {
            DateFormatTools dft = new DateFormatTools();
            String date = dft.getShortDateFromCal(c);
            isthere = controller.arThereEvent(date);
        } catch (SQLException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isthere;
    }

    public void SetDateToCalender(Calendar c) {
        DateFormatTools dft = new DateFormatTools();
        event = controller.getDayEvent(dft.getShortDateFromCal(c));
    }

    public String getDayEvent() {
        return event;
    }

}
