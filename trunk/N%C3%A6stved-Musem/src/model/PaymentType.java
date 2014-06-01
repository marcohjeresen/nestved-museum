

package model;


public class PaymentType {
    private int id;
    private String type;
    private int fee;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public PaymentType(int id, String type, int fee) {
        this.id = id;
        this.type = type;
        this.fee = fee;
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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "id: " + id + " type: " + type + " fee: " + fee;
    }
    
}
