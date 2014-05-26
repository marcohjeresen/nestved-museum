/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class ProductLineTest {
    
    public ProductLineTest() {
    }

    /**
     * Test of getQuantities method, of class ProductLine.
     */
    @Test
    public void testGetQuantities() {
        System.out.println("getQuantities");
        ProductLine instance = null;
        int expResult = 0;
        int result = instance.getQuantities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantities method, of class ProductLine.
     */
    @Test
    public void testSetQuantities() {
        System.out.println("setQuantities");
        int quantities = 0;
        ProductLine instance = null;
        instance.setQuantities(quantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class ProductLine.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ProductLine instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class ProductLine.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        ProductLine instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProduct method, of class ProductLine.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        ProductLine instance = null;
        Product expResult = null;
        Product result = instance.getProduct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSale method, of class ProductLine.
     */
    @Test
    public void testGetSale() {
        System.out.println("getSale");
        ProductLine instance = null;
        Sale expResult = null;
        Sale result = instance.getSale();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSale method, of class ProductLine.
     */
    @Test
    public void testSetSale() {
        System.out.println("setSale");
        Sale saleId = null;
        ProductLine instance = null;
        instance.setSale(saleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ProductLine.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ProductLine instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
