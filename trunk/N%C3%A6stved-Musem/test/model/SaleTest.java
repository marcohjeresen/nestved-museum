/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class SaleTest {
    
    public SaleTest() {
    }

    /**
     * Test of getId method, of class Sale.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Sale instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Sale.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Sale instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicketLine method, of class Sale.
     */
    @Test
    public void testGetTicketLine() {
        System.out.println("getTicketLine");
        Sale instance = null;
        ArrayList<TicketLine> expResult = null;
        ArrayList<TicketLine> result = instance.getTicketLine();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTicketLine method, of class Sale.
     */
    @Test
    public void testAddTicketLine() {
        System.out.println("addTicketLine");
        TicketType tt = null;
        int quantities = 0;
        Sale instance = null;
        instance.addTicketLine(tt, quantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventLine method, of class Sale.
     */
    @Test
    public void testGetEventLine() {
        System.out.println("getEventLine");
        Sale instance = null;
        ArrayList<EventLine> expResult = null;
        ArrayList<EventLine> result = instance.getEventLine();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEventLine method, of class Sale.
     */
    @Test
    public void testAddEventLine() {
        System.out.println("addEventLine");
        EventType et = null;
        int quantities = 0;
        int customer = 0;
        String place = "";
        Sale instance = null;
        instance.addEventLine(et, quantities, customer, place);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductLine method, of class Sale.
     */
    @Test
    public void testGetProductLine() {
        System.out.println("getProductLine");
        Sale instance = null;
        ArrayList<ProductLine> expResult = null;
        ArrayList<ProductLine> result = instance.getProductLine();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product p = null;
        Sale instance = null;
        instance.addProduct(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeProductLine method, of class Sale.
     */
    @Test
    public void testRemoveProductLine() {
        System.out.println("removeProductLine");
        Product p = null;
        Sale instance = null;
        instance.removeProductLine(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeEventLine method, of class Sale.
     */
    @Test
    public void testRemoveEventLine() {
        System.out.println("removeEventLine");
        EventType et = null;
        Sale instance = null;
        instance.removeEventLine(et);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTicketLine method, of class Sale.
     */
    @Test
    public void testRemoveTicketLine() {
        System.out.println("removeTicketLine");
        TicketType t = null;
        Sale instance = null;
        instance.removeTicketLine(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRemoveProductLineQuantities method, of class Sale.
     */
    @Test
    public void testAddRemoveProductLineQuantities() {
        System.out.println("addRemoveProductLineQuantities");
        Product p = null;
        int quantities = 0;
        Sale instance = null;
        instance.addRemoveProductLineQuantities(p, quantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRemoveTicketLineQuantities method, of class Sale.
     */
    @Test
    public void testAddRemoveTicketLineQuantities() {
        System.out.println("addRemoveTicketLineQuantities");
        TicketType tt = null;
        int quantities = 0;
        Sale instance = null;
        instance.addRemoveTicketLineQuantities(tt, quantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRemoveEventLineQuantities method, of class Sale.
     */
    @Test
    public void testAddRemoveEventLineQuantities() {
        System.out.println("addRemoveEventLineQuantities");
        EventType et = null;
        int quantities = 0;
        Sale instance = null;
        instance.addRemoveEventLineQuantities(et, quantities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentType method, of class Sale.
     */
    @Test
    public void testGetPaymentType() {
        System.out.println("getPaymentType");
        Sale instance = null;
        PaymentType expResult = null;
        PaymentType result = instance.getPaymentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentType method, of class Sale.
     */
    @Test
    public void testSetPaymentType() {
        System.out.println("setPaymentType");
        PaymentType paymentType = null;
        Sale instance = null;
        instance.setPaymentType(paymentType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployee method, of class Sale.
     */
    @Test
    public void testGetEmployee() {
        System.out.println("getEmployee");
        Sale instance = null;
        Employee expResult = null;
        Employee result = instance.getEmployee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmployee method, of class Sale.
     */
    @Test
    public void testSetEmployee() {
        System.out.println("setEmployee");
        Employee employee = null;
        Sale instance = null;
        instance.setEmployee(employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Sale.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Sale instance = null;
        String expResult = "";
        String result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Sale.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Sale instance = null;
        instance.setDate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvoice method, of class Sale.
     */
    @Test
    public void testGetInvoice() {
        System.out.println("getInvoice");
        Sale instance = null;
        Invoice expResult = null;
        Invoice result = instance.getInvoice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvoice method, of class Sale.
     */
    @Test
    public void testSetInvoice() {
        System.out.println("setInvoice");
        Invoice invoice = null;
        Sale instance = null;
        instance.setInvoice(invoice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndpriceDk method, of class Sale.
     */
    @Test
    public void testGetEndpriceDk() {
        System.out.println("getEndpriceDk");
        boolean discount = false;
        Sale instance = null;
        int expResult = 0;
        int result = instance.getEndpriceDk(discount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearSale method, of class Sale.
     */
    @Test
    public void testClearSale() {
        System.out.println("clearSale");
        Sale instance = null;
        instance.clearSale();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndprice method, of class Sale.
     */
    @Test
    public void testSetEndprice() {
        System.out.println("setEndprice");
        boolean discount = false;
        Sale instance = null;
        instance.setEndprice(discount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndpriceEuro method, of class Sale.
     */
    @Test
    public void testGetEndpriceEuro() {
        System.out.println("getEndpriceEuro");
        boolean discount = false;
        Sale instance = null;
        int expResult = 0;
        int result = instance.getEndpriceEuro(discount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Sale.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Sale instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
