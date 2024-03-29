
package utility;


public class StockLine {
private String productNumber;
private String productName;
private String productSupply;
private String productBuyPrice;
private String productQuantities;


/**
 * Constructor, creates a new object of the class.
*/
    public StockLine(String producNumber, String producName, String producSuppl, String producBuyPrice, String producQuantitis) {
        this.productNumber = producNumber;
        this.productName = producName;
        this.productSupply = producSuppl;
        this.productBuyPrice = producBuyPrice;
        this.productQuantities = producQuantitis;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSupply() {
        return productSupply;
    }

    public void setProductSupply(String productSupply) {
        this.productSupply = productSupply;
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


}
