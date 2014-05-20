/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;

import model.Sale;

/**
 *
 * @author markh_000
 */
public class SaleHandler {
    private static SaleHandler saleHandler;
    private Sale currentSale;

    public SaleHandler() {
        currentSale = new Sale(0, null, null, null);
    }
    
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
