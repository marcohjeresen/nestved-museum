/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author davs
 */
public class Line {
    private String text;
    private int priceDk;
    private int priceEuro;
    private String productNumber;
    private String productTitle;
    private String productSupplier;
    private String productBuyPrice;
    private String productQuantities;
    private int day;
    private int sum;
    private int id;
    private int ticketAdultQuantities;
    private int ticketChildenQuantities;
    private int ticketFreeQuantities;
    private int ticketGroupQuantities;
    private int eventQuantities;
    private int eventSold;
    private String ticketDate;

    public Line(String text, int priceDk, int priceEuro) {
        this.text = text;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriceDk() {
        return priceDk;
    }

    public void setPriceDk(int priceDk) {
        this.priceDk = priceDk;
    }

    public int getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(int priceEuro) {
        this.priceEuro = priceEuro;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductBuyPrice() {
        return productBuyPrice;
    }

    public void setProductBuyPrice(String productBuyPrice) {
        this.productBuyPrice = productBuyPrice;
    }

    public String getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(String productQuantities) {
        this.productQuantities = productQuantities;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketAdultQuantities() {
        return ticketAdultQuantities;
    }

    public void setTicketAdultQuantities(int ticketAdultQuantities) {
        this.ticketAdultQuantities = ticketAdultQuantities;
    }

    public int getTicketChildenQuantities() {
        return ticketChildenQuantities;
    }

    public void setTicketChildenQuantities(int ticketChildenQuantities) {
        this.ticketChildenQuantities = ticketChildenQuantities;
    }

    public int getTicketFreeQuantities() {
        return ticketFreeQuantities;
    }

    public void setTicketFreeQuantities(int ticketFreeQuantities) {
        this.ticketFreeQuantities = ticketFreeQuantities;
    }

    public int getTicketGroupQuantities() {
        return ticketGroupQuantities;
    }

    public void setTicketGroupQuantities(int ticketGroupQuantities) {
        this.ticketGroupQuantities = ticketGroupQuantities;
    }

    public int getEventQuantities() {
        return eventQuantities;
    }

    public void setEventQuantities(int eventQuantities) {
        this.eventQuantities = eventQuantities;
    }

    public int getEventSold() {
        return eventSold;
    }

    public void setEventSold(int eventSold) {
        this.eventSold = eventSold;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }



    @Override
    public String toString() {
        String text = "";
        if (priceDk == 0) {
           text = "text: " + text + " priceDk: " + priceDk + " priceEuro: " + priceEuro;
        }
        return text;
    }


    
}
