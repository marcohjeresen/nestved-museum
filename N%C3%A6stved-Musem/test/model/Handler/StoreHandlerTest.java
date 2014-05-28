/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Handler;

import model.Product;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class StoreHandlerTest {

    public StoreHandlerTest() {
    }

    /**
     * Test of employeeLogin method, of class StoreHandler.
     */
    @Test
    public void testEmployeeLogin() {
        System.out.println("employeeLogin");
        StoreHandler storehandler = StoreHandler.storeHandler();
        int kode = 1421;
        boolean expResult = true;
        boolean result = storehandler.employeeLogin(kode);
        assertEquals("Testen fejlede at logge brugeren ind.", expResult, result);
    }

    /**
     * Her tester vi om en forkert bruger kan logge ind i systemet
     */
    @Test
    public void testWrongEmployeeLogin() {
        System.out.println("WrongEmployeeLogin");
        StoreHandler storehandler = StoreHandler.storeHandler();
        int kode = 1521;
        boolean expResult = false;
        boolean result = storehandler.employeeLogin(kode);
        assertEquals("Testen fejlede fordi brugeren kunne godt logge ind uden gyldig kodeord.", expResult, result);
    }

    /**
     * Test of setSearchProduct method, of class StoreHandler.
     */
    @Test
    public void testCorrectSearchProduct() {
        System.out.println("testCorrectSearchProduct");
        StoreHandler storehandler = StoreHandler.storeHandler();
        int productNumber = 1035;
        storehandler.setSearchProduct(productNumber);
        String expResult = "Bornholmsk Værkstedskeramik";
        String result = "";
        for (Product product : storehandler.getChosenProduct()) {
            result = product.getName();
        }

        assertEquals("Testen fejlede at finde det ønskede produkt.", expResult, result);
    }

    
        /**
     * Test of setSearchProduct method, of class StoreHandler.
     */
    @Test
    public void testWrongSearchProduct() {
        System.out.println("testWrongSearchProduct");
        StoreHandler storehandler = StoreHandler.storeHandler();
        int productNumber = 1000;
        storehandler.setSearchProduct(productNumber);
        boolean expResult = false;
        boolean result = true;
        if(storehandler.getChosenProduct().isEmpty()){
            result = false;
        }else{
            result = true;
        }
        assertEquals("Testen fejlede, den fandt et produkt.", expResult, result);
    }
}
