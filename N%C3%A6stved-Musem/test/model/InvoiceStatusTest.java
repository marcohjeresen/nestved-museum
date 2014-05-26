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
public class InvoiceStatusTest {
    
    public InvoiceStatusTest() {
    }

    /**
     * Test of getId method, of class InvoiceStatus.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        InvoiceStatus instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class InvoiceStatus.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        InvoiceStatus instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class InvoiceStatus.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        InvoiceStatus instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class InvoiceStatus.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        InvoiceStatus instance = null;
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class InvoiceStatus.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        InvoiceStatus instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
