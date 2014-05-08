/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.DateFormatTools;
import Util.Listeners;
import java.util.ArrayList;
import java.util.Date;
import sun.security.ssl.SSLContextImpl;

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
    private int endpriceDk;
    private int endpriceEuro;

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

    public void addTicketLine(TicketLine tl) {
        boolean isthere = false;
        for (TicketLine ticketLine1 : ticketLine) {
            if (ticketLine1.getTicketType().equals(tl.getTicketType())) {
                int quantitis = ticketLine1.getQuantities();
                ticketLine1.setQuantities(quantitis + 1);
                isthere = true;
            }
        }
        if (!isthere) {
            ticketLine.add(tl);
        }
        listeners.notifyListeners("Basket Chance");
    }

    public ArrayList<EventLine> getEventLine() {
        return eventLine;
    }

    public void addEventLine(EventLine el) {
        boolean isthere = false;
        for (EventLine el1 : eventLine) {
            if (el1.getEventtype().equals(el.getEventtype())) {
                int quantitis = el1.getQuantities();
                el1.setQuantities(quantitis + el.getQuantities());
                isthere = true;
            }
        }
        if (!isthere) {
            eventLine.add(el);
        }
        listeners.notifyListeners("Basket Chance");
    }

    public ArrayList<ProductLine> getProductLine() {
        return productLine;
    }

    public void addProduct(Product p) {
        boolean isthere = false;
        for (ProductLine pl : productLine) {
            if (pl.getProduct().equals(p)) {
                int quantitis = pl.getQuantities();
                pl.setQuantities(quantitis + 1);
                isthere = true;
            }
        }
        if (!isthere) {
            ProductLine pl = new ProductLine(0, this, p, 1);
            productLine.add(pl);
        }
        listeners.notifyListeners("Basket Chance");
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

    public int getEndpriceDk(boolean discount) {
        setEndprice(discount);
        return endpriceDk;
    }

    public void clearSale() {
        ticketLine.removeAll(ticketLine);
        eventLine.removeAll(eventLine);
        productLine.removeAll(productLine);
        employee = null;
        invoice = null;
        date = null;
        paymentType = null;
        id = 0;
        listeners.notifyListeners("Sale Cleard");
    }

    public void setEndprice(boolean discount) {
        double price = 0;
        endpriceDk = 0;
        endpriceEuro = 0;
        if (!productLine.isEmpty()) {
            for (ProductLine productline : productLine) {
                if (discount) {
                    price = (productline.getProduct().getPriceDk() / 100) * productline.getProduct().getDiscount();
                    price = price * productline.getQuantities();
                    endpriceDk = (int) (endpriceDk + price);

                    price = (productline.getProduct().getPriceEuro() / 100) * productline.getProduct().getDiscount();
                    price = price * productline.getQuantities();
                    endpriceEuro = (int) (endpriceEuro + price);
                } else {
                    price = productline.getProduct().getPriceDk() * productline.getQuantities();
                    endpriceDk = (int) (endpriceDk + price);
                    price = productline.getProduct().getPriceEuro() * productline.getQuantities();
                    endpriceEuro = (int) (endpriceEuro + price);

                }
            }
        }
        if (!eventLine.isEmpty()) {
            for (EventLine eventLine1 : eventLine) {

                price = eventLine1.getEventlinePriceDk();
                endpriceDk = (int) (endpriceDk + price);
                price = eventLine1.getEventlineEuro();
                endpriceEuro = (int) (endpriceEuro + price);
            }
        }
        if (!ticketLine.isEmpty()) {
            for (TicketLine ticketLine1 : ticketLine) {
                price = ticketLine1.getTicketType().getPriceDk() * ticketLine1.getQuantities();
                endpriceDk = (int) (endpriceDk + price);
                price = ticketLine1.getTicketType().getPriceEuro() * ticketLine1.getQuantities();
                endpriceEuro = (int) (endpriceEuro + price);
            }
        }

    }

    public int getEndpriceEuro(boolean discount) {
        setEndprice(discount);
        return endpriceEuro;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", paymentType=" + paymentType + ", employee=" + employee + ", date=" + date + ", ticketLine=" + ticketLine + ", eventLine=" + eventLine + ", productLine=" + productLine + ", invoice=" + invoice + ", endpriceDk=" + endpriceDk + ", endpriceEuro=" + endpriceEuro + '}';
    }

}
