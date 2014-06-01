
package model;

public class TicketLine {
    private int id;
    private TicketType ticketType;
    private Sale sale;
    private int quantities;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public TicketLine(int id, Sale sale, int quantities, TicketType ticketType) {
        this.id = id;
        this.sale = sale;
        this.quantities = quantities;
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }


    @Override
    public String toString() {
        String ticketLine = "";
            ticketLine = ticketLine + ticketType.toString();
        return ticketLine + "Quantities: " + quantities ;
    }
    
}
