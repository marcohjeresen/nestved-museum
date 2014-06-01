
package model;

public class EventType {
    private int id;
    private String type;
    private int priceDk;
    private int priceEuro;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public EventType(int id, String type, int priceDk, int priceEuro) {
        this.id = id;
        this.type = type;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "EventType{" + "id=" + id + ", type=" + type + ", priceDk=" + priceDk + ", priceEuro=" + priceEuro + '}';
    }

  

    
    
}
