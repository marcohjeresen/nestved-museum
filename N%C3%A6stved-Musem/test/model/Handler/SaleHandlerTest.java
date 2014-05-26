/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;

import model.Sale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class SaleHandlerTest {
    
    public SaleHandlerTest() {
    }

    /**
     * Test of getSaleHandler method, of class SaleHandler.
     */
    @Test
    public void testGetSaleHandler() {
        System.out.println("getSaleHandler");
        SaleHandler expResult = null;
        SaleHandler result = SaleHandler.getSaleHandler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newSale method, of class SaleHandler.
     */
    @Test
    public void testNewSale() {
        System.out.println("newSale");
        SaleHandler instance = new SaleHandler();
        instance.newSale();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentSale method, of class SaleHandler.
     */
    @Test
    public void testGetCurrentSale() {
        System.out.println("getCurrentSale");
        SaleHandler instance = new SaleHandler();
        Sale expResult = null;
        Sale result = instance.getCurrentSale();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearSale method, of class SaleHandler.
     */
    @Test
    public void testClearSale() {
        System.out.println("clearSale");
        SaleHandler instance = new SaleHandler();
        instance.clearSale();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
