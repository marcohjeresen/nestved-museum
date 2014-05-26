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
public class TicketLineTest {
    
    public TicketLineTest() {
    }

    /**
     * Test of getId method, of class TicketLine.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TicketLine instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class TicketLine.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        TicketLine instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicketType method, of class TicketLine.
     */
    @Test
    public void testGetTicketType() {
        System.out.println("getTicketType");
        TicketLine instance = null;
        TicketType expResult = null;
        TicketType result = instance.getTicketType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTicketType method, of class TicketLine.
     */
    @Test
    public void testSetTicketType() {
        System.out.println("setTicketType");
        TicketType ticketType = null;
        TicketLine instance = null;
        instance.setTicketType(ticketType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSale method, of class TicketLine.
     */
    @Test
    public void testGetSale() {
        System.out.println("getSale");
        TicketLine instance = null;
        Sale expResult = null;
        Sale result = instance.getSale();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSale method, of class TicketLine.
     */
    @Test
    public void testSetSale() {
        System.out.println("setSale");
        Sale sale = null;
        TicketLine instance = null;
        instance.setSale(sale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantities method, of class TicketLine.
     */
    @Test
    public void testGetQuantities() {
        System.out.println("getQuantities");
        TicketLine instance = null;
        int expResult = 0;
        int result = instance.getQuantities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantities method, of class TicketLine.
     */
    @Test
    public void testSetQuantities() {
        System.out.println("setQuantities");
        int quantities = 0;
        TicketLine instance = null;
        instance.setQuantities(quantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TicketLine.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TicketLine instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
