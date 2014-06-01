
package model.Handler;

import utility.DateFormatTools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import model.Employee;
import model.EventType;
import model.Product;
import model.TicketType;
import model.controller.StoreController;
import utility.Listeners;


public class StoreHandler {

    private static StoreHandler storeHandler;
    private StoreController controller;
    private Employee logEmployee;
    private boolean search;
    private Listeners listeners;
    private ArrayList<Product> chosenProduct;
    private TicketType chosenTicket;
    private EventType chosenEvent;
    private String event;

    /*
    Constructor, creates a new object of the class.
    */
    
    public StoreHandler() {
        controller = StoreController.getStoreController();
        listeners = Listeners.getList();
        search = false;
        chosenProduct = new ArrayList<>();
        chosenTicket = null;
        chosenEvent = null;
    }

    /*
    Method, creates a singleton of StoreHandler.
    */
    
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

    public boolean employeeLogin(int code) {
        boolean login = false;
        for (Employee employee : controller.getEmployeesList()) {
            if (employee.getPassword() == code) {
                logEmployee = employee;
                login = true;
            }
        }
        return login;
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

    public void setChosenProduct(String productGroup) {
        chosenProduct.removeAll(chosenProduct);
        for (Product product : controller.getProductsList()) {
            if (product.getGroupType().equals(productGroup)) {
                chosenProduct.add(product);
            }
        }
        listeners.notifyListeners("Chosen Product");
    }

    public void setSearchProduct(int productNumber) {
        chosenProduct.removeAll(chosenProduct);
        for (Product product : controller.getProductsList()) {
            if (product.getProductNumber() == productNumber) {
                chosenProduct.add(product);
            }
        }
        listeners.notifyListeners("Chosen Product");
    }

    public ArrayList<Product> getChosenProduct() {
        return chosenProduct;
    }

    public void setChosenTicket(String ticketType) {
        chosenTicket = null;
        for (TicketType ticketType1 : controller.getTicketTypesList()) {
            if (ticketType1.getType().equals(ticketType)) {
                chosenTicket = ticketType1;
            }
        }
        listeners.notifyListeners("Chosen Ticket");
    }

    public TicketType getChosenTicket() {
        return chosenTicket;
    }

    public void setChosenEvent(String eventType) {
        chosenEvent = null;
        for (EventType eventType1 : controller.getEventTypesList()) {
            if (eventType1.getType().equals(eventType)) {
                chosenEvent = eventType1;
            }
        }
        listeners.notifyListeners("Chosen Event");
    }

    public EventType getChosenEventType() {
        return chosenEvent;
    }

    public Boolean dayHaveEvent(Calendar c) {
        boolean isthere = false;
        DateFormatTools dft = new DateFormatTools();
        String date = dft.getShortDateFromCal(c);
        isthere = controller.areThereEvent(date);
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
