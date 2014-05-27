/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utility.DateFormatTools;
import utility.Listeners;
import java.util.ArrayList;

/**
 *
 * @author markh_000
 */
public class Sale {

    private Listeners listeners;
    private DateFormatTools dateTools;
    private int id;
    private PaymentType paymentType;
    private Employee employee;
    private String date;
    private ArrayList<TicketLine> ticketLine;
    private ArrayList<EventLine> eventLine;
    private ArrayList<ProductLine> productLine;
    private Invoice invoice;
    private int endPriceDk;
    private int endPriceEuro;

    public Sale(int id, PaymentType paymentType, Employee employee, String date) {
        this.id = id;
        this.paymentType = paymentType;
        this.employee = employee;
        this.date = date;
        ticketLine = new ArrayList<>();
        eventLine = new ArrayList<>();
        productLine = new ArrayList<>();
        dateTools = new DateFormatTools();
        listeners = Listeners.getList();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TicketLine> getTicketLine() {
        return ticketLine;
    }

    public void addTicketLine(TicketType tt, int quantities) {
        boolean isthere = false;

        if (!ticketLine.isEmpty()) {
            for (int i = 0; i < ticketLine.size(); i++) {
                if (ticketLine.get(i).getTicketType().equals(tt)) {
                    int amount = ticketLine.get(i).getQuantities();
                    ticketLine.get(i).setQuantities(amount + quantities);
                    isthere = true;
                }
            }
            if (!isthere) {
                if ("Gruppebillet, min. 10 personer".equals(tt.getType())) {
                    quantities = 10;
                }
                TicketLine tl = new TicketLine(0, this, quantities, tt);
                ticketLine.add(tl);
            }
        } else {
            if ("Gruppebillet, min. 10 personer".equals(tt.getType())) {
                quantities = 10;
            }
            TicketLine tl = new TicketLine(0, this, quantities, tt);
            ticketLine.add(tl);
        }
        listeners.notifyListeners("Basket Change");
    }

    public ArrayList<EventLine> getEventLine() {
        return eventLine;
    }

    public void addEventLine(EventType et, int quantities, int customer, String place) {
        boolean isthere = false;
        if (!eventLine.isEmpty()) {
            for (int i = 0; i < eventLine.size(); i++) {
                if (eventLine.get(i).getEventtype().equals(et)) {
                    int amount = eventLine.get(i).getQuantities();
                    eventLine.get(i).setQuantities(amount + quantities);
                    isthere = true;
                }
            }
            if (!isthere) {
                EventLine el = new EventLine(0, et, this, quantities, dateTools.getDateNowString(), customer, place);
                eventLine.add(el);
            }
        } else {
            EventLine el = new EventLine(0, et, this, quantities, dateTools.getDateNowString(), customer, place);
            eventLine.add(el);
        }
        listeners.notifyListeners("Basket Change");
    }

    public ArrayList<ProductLine> getProductLine() {
        return productLine;
    }

    public void addProduct(Product p) {
        boolean isthere = false;
        if (p.getQuantities() >= 1) {
            if (!productLine.isEmpty()) {
                for (int i = 0; i < productLine.size(); i++) {
                    if (productLine.get(i).getProduct().equals(p)) {
                        int amount = productLine.get(i).getQuantities();
                        if (p.getQuantities() > amount) {
                            productLine.get(i).setQuantities(amount + 1);
                            isthere = true;
                        } else {
                            isthere = true;
                        }
                    }
                }
                if (!isthere) {
                    ProductLine pl = new ProductLine(0, this, p, 1);
                    productLine.add(pl);
                }
            } else {
                ProductLine pl = new ProductLine(0, this, p, 1);
                productLine.add(pl);
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public void removeProductLine(Product p) {
        for (int i = 0; i < productLine.size(); i++) {
            if (productLine.get(i).getProduct().equals(p)) {
                productLine.remove(i);
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public void removeEventLine(EventType et) {
        for (int i = 0; i < eventLine.size(); i++) {
            if (eventLine.get(i).getEventtype().equals(et)) {
                eventLine.remove(i);
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public void removeTicketLine(TicketType t) {
        for (int i = 0; i < ticketLine.size(); i++) {
            if (ticketLine.get(i).getTicketType().equals(t)) {
                ticketLine.remove(i);
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public void addRemoveProductLineQuantities(Product p, int quantities) {
        for (int i = 0; i < productLine.size(); i++) {
            if (productLine.get(i).getProduct().equals(p)) {
                int amount = productLine.get(i).getQuantities();
                if (p.getQuantities() > amount) {
                    productLine.get(i).setQuantities(amount + quantities);
                    if (productLine.get(i).getQuantities() == 0) {
                        productLine.remove(i);
                    }
                }
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public void addRemoveTicketLineQuantities(TicketType tt, int quantities) {
        for (int i = 0; i < ticketLine.size(); i++) {
            if (ticketLine.get(i).getTicketType().equals(tt)) {
                int amount = ticketLine.get(i).getQuantities();
                ticketLine.get(i).setQuantities(amount + quantities);
                if (ticketLine.get(i).getQuantities() == 0) {
                    ticketLine.remove(i);
                }
                if ("Gruppebillet, min. 10 personer".equals(tt.getType())) {
                    if (ticketLine.get(i).getQuantities() < 10) {
                      ticketLine.remove(i);  
                    }
                    
                }
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public void addRemoveEventLineQuantities(EventType et, int quantities) {
        for (int i = 0; i < eventLine.size(); i++) {
            if (eventLine.get(i).getEventtype().equals(et)) {
                int amount = eventLine.get(i).getQuantities();
                eventLine.get(i).setQuantities(amount + quantities);
                if (eventLine.get(i).getQuantities() == 0) {
                    eventLine.remove(i);
                }
            }
        }
        listeners.notifyListeners("Basket Change");
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = dateTools.getDateNowString();
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getEndPriceDk(boolean discount) {
        setEndPrice(discount);
        return endPriceDk;
    }

    public void clearSale() {
        ticketLine.removeAll(ticketLine);
        eventLine.removeAll(eventLine);
        productLine.removeAll(productLine);
        invoice = null;
        paymentType = null;
        id = 0;
        listeners.notifyListeners("Basket Change");
    }

    public void setEndPrice(boolean discount) {
        int price = 0;
        endPriceDk = 0;
        endPriceEuro = 0;
        if (!productLine.isEmpty()) {
            for (ProductLine productline : productLine) {
                if (discount) {
                    price = productline.getProduct().getPriceDk() / 100;
                    price = price * productline.getProduct().getDiscount();
                    price = price * productline.getQuantities();
                    endPriceDk = (int) (endPriceDk + price);
                    price = productline.getProduct().getPriceEuro() * productline.getProduct().getDiscount();
                    price = price / 100;
                    price = price * productline.getQuantities();
                    endPriceEuro = (int) (endPriceEuro + price);
                } else {
                    price = productline.getProduct().getPriceDk() * productline.getQuantities();
                    endPriceDk = (int) (endPriceDk + price);
                    price = productline.getProduct().getPriceEuro() * productline.getQuantities();
                    endPriceEuro = (int) (endPriceEuro + price);
                }
            }
        }
        if (!eventLine.isEmpty()) {
            for (EventLine eventLine1 : eventLine) {
                if (discount) {
                    price = eventLine1.getEventlinePriceDk() * 90;
                    price = price / 100;
                    endPriceDk = (int) (endPriceDk + price);
                    price = eventLine1.getEventlineEuro() / 100;
                    price = price * 90;
                    endPriceEuro = (int) (endPriceEuro + price);

                } else {
                    price = eventLine1.getEventlinePriceDk();
                    endPriceDk = (int) (endPriceDk + price);
                    price = eventLine1.getEventlineEuro();
                    endPriceEuro = (int) (endPriceEuro + price);
                }
            }
        }
        if (!ticketLine.isEmpty()) {
            for (TicketLine ticketLine1 : ticketLine) {
                if (discount) {
                    price = ticketLine1.getTicketType().getPriceDk() * 90;
                    price = price / 100;
                    price = price * ticketLine1.getQuantities();
                    endPriceDk = (int) (endPriceDk + price);
                    price = ticketLine1.getTicketType().getPriceEuro() * 90;
                    price = price / 100;
                    price = price * ticketLine1.getQuantities();
                    endPriceEuro = (int) (endPriceEuro + price);
                } else {
                    price = ticketLine1.getTicketType().getPriceDk() * ticketLine1.getQuantities();
                    endPriceDk = (int) (endPriceDk + price);
                    price = ticketLine1.getTicketType().getPriceEuro() * ticketLine1.getQuantities();
                    endPriceEuro = (int) (endPriceEuro + price);
                }
            }
        }
    }

    public int getEndPriceEuro(boolean discount) {
        setEndPrice(discount);
        return endPriceEuro;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", paymentType=" + paymentType + ", employee=" + employee + ", date=" + date + ", ticketLine=" + ticketLine + ", eventLine=" + eventLine + ", productLine=" + productLine + ", invoice=" + invoice + ", endpriceDk=" + endPriceDk + ", endpriceEuro=" + endPriceEuro + '}';
    }

}
