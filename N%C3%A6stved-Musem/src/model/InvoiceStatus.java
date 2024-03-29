

package model;

public class InvoiceStatus {
    private int id;
    private String type;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public InvoiceStatus(int id, String type) {
        this.id = id;
        this.type = type;
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

    @Override
    public String toString() {
        return "id: " + id + " type: " + type;
    }
    
}
