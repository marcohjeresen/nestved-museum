

package model.Handler;

import model.Sale;


public class SaleHandler {
    private static SaleHandler saleHandler;
    private Sale currentSale;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public SaleHandler() {
        currentSale = new Sale(0, null, null, null);
    }
    
    /**
    * Method, creates a singleton of SaleHandler.
    */
    
    public static SaleHandler getSaleHandler(){
        if (saleHandler == null) {
            saleHandler = new SaleHandler();
        }
        return saleHandler;
    }
    
    public void newSale(){
        currentSale = new Sale(0, null, null, null);
    }
    
    public Sale getCurrentSale(){
        return currentSale;
    }
    
    public void clearSale(){
        currentSale.clearSale();
    }
    
    
}
