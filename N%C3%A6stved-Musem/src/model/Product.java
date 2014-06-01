
package model;


public class Product extends ProductGroup{
    private int productNumber;
    private String name;
    private String supplier;
    private int buyPrice;
    private int priceDk;
    private int priceEuro;
    private int discount;
    private int quantities;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public Product(int productNumber, String name, String supplier, int buyPrice, int priceDk, int priceEuro, int discount, int quantities, int groupId, String groupType) {
        super(groupId, groupType);
        this.productNumber = productNumber;
        this.name = name;
        this.supplier = supplier;
        this.buyPrice = buyPrice;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
        this.discount = discount;
        this.quantities = quantities;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

   

    
    }

    
    
    




