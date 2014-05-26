/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.controller;

import model.Sale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class SaleControllerTest {
    
    public SaleControllerTest() {
    }

    /**
     * Test of controller method, of class SaleController.
     */
    @Test
    public void testController() {
        System.out.println("controller");
        SaleController expResult = null;
        SaleController result = SaleController.controller();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentData method, of class SaleController.
     */
    @Test
    public void testGetPaymentData() {
        System.out.println("getPaymentData");
        SaleController instance = new SaleController();
        instance.getPaymentData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endSale method, of class SaleController.
     */
    @Test
    public void testEndSale() {
        System.out.println("endSale");
        Sale sale1 = null;
        boolean discount = false;
        SaleController instance = new SaleController();
        instance.endSale(sale1, discount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
