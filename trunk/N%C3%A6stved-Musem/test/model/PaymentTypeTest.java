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
public class PaymentTypeTest {
    
    public PaymentTypeTest() {
    }

    /**
     * Test of getId method, of class PaymentType.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PaymentType instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PaymentType.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        PaymentType instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class PaymentType.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        PaymentType instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class PaymentType.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        PaymentType instance = null;
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFee method, of class PaymentType.
     */
    @Test
    public void testGetFee() {
        System.out.println("getFee");
        PaymentType instance = null;
        int expResult = 0;
        int result = instance.getFee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFee method, of class PaymentType.
     */
    @Test
    public void testSetFee() {
        System.out.println("setFee");
        int fee = 0;
        PaymentType instance = null;
        instance.setFee(fee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class PaymentType.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PaymentType instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
