
package model;


public class ProductLine {
    private int id;
    private Product product;
    private Sale sale;
    private int quantities;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public ProductLine(int id, Sale sale, Product product1, int quantities) {
        this.id = id;
        this.sale = sale;
        this.product = product1; 
        this.quantities = quantities;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale saleId) {
        this.sale = saleId;
    }

    @Override
    public String toString() {
        String productLine = "Sale Number: "+sale.getId()+"\n";
            productLine = productLine + product.toString()+ "\n";
        return productLine;
    }
    
    
   
    
}
