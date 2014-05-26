/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import model.Employee;
import model.EventType;
import model.Product;
import model.TicketType;
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
     * Test of storeHandler method, of class StoreHandler.
     */
    @Test
    public void testStoreHandler() {
        System.out.println("storeHandler");
        StoreHandler expResult = null;
        StoreHandler result = StoreHandler.storeHandler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogEmployee method, of class StoreHandler.
     */
    @Test
    public void testGetLogEmployee() {
        System.out.println("getLogEmployee");
        StoreHandler instance = new StoreHandler();
        Employee expResult = null;
        Employee result = instance.getLogEmployee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logOutEmployee method, of class StoreHandler.
     */
    @Test
    public void testLogOutEmployee() {
        System.out.println("logOutEmployee");
        StoreHandler instance = new StoreHandler();
        instance.logOutEmployee();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of employeeLogin method, of class StoreHandler.
     */
    @Test
    public void testEmployeeLogin() {
        System.out.println("employeeLogin");
        int kode = 0;
        StoreHandler instance = new StoreHandler();
        boolean expResult = false;
        boolean result = instance.employeeLogin(kode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductGroups method, of class StoreHandler.
     */
    @Test
    public void testGetProductGroups() {
        System.out.println("getProductGroups");
        StoreHandler instance = new StoreHandler();
        HashSet<String> expResult = null;
        HashSet<String> result = instance.getProductGroups();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductList method, of class StoreHandler.
     */
    @Test
    public void testGetProductList() {
        System.out.println("getProductList");
        StoreHandler instance = new StoreHandler();
        ArrayList<Product> expResult = null;
        ArrayList<Product> result = instance.getProductList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicketType method, of class StoreHandler.
     */
    @Test
    public void testGetTicketType() {
        System.out.println("getTicketType");
        StoreHandler instance = new StoreHandler();
        ArrayList<TicketType> expResult = null;
        ArrayList<TicketType> result = instance.getTicketType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventType method, of class StoreHandler.
     */
    @Test
    public void testGetEventType() {
        System.out.println("getEventType");
        StoreHandler instance = new StoreHandler();
        ArrayList<EventType> expResult = null;
        ArrayList<EventType> result = instance.getEventType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class StoreHandler.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        StoreHandler instance = new StoreHandler();
        boolean expResult = false;
        boolean result = instance.search();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChoosenProduct method, of class StoreHandler.
     */
    @Test
    public void testSetChoosenProduct() {
        System.out.println("setChoosenProduct");
        String productGroup = "";
        StoreHandler instance = new StoreHandler();
        instance.setChoosenProduct(productGroup);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSearchProduct method, of class StoreHandler.
     */
    @Test
    public void testSetSearchProduct() {
        System.out.println("setSearchProduct");
        int productNumber = 0;
        StoreHandler instance = new StoreHandler();
        instance.setSearchProduct(productNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChoosenProduct method, of class StoreHandler.
     */
    @Test
    public void testGetChoosenProduct() {
        System.out.println("getChoosenProduct");
        StoreHandler instance = new StoreHandler();
        ArrayList<Product> expResult = null;
        ArrayList<Product> result = instance.getChoosenProduct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChoosenTicket method, of class StoreHandler.
     */
    @Test
    public void testSetChoosenTicket() {
        System.out.println("setChoosenTicket");
        String ticketType = "";
        StoreHandler instance = new StoreHandler();
        instance.setChoosenTicket(ticketType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChoosenTicket method, of class StoreHandler.
     */
    @Test
    public void testGetChoosenTicket() {
        System.out.println("getChoosenTicket");
        StoreHandler instance = new StoreHandler();
        TicketType expResult = null;
        TicketType result = instance.getChoosenTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChoosenEvent method, of class StoreHandler.
     */
    @Test
    public void testSetChoosenEvent() {
        System.out.println("setChoosenEvent");
        String eventType = "";
        StoreHandler instance = new StoreHandler();
        instance.setChoosenEvent(eventType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChoosenEventType method, of class StoreHandler.
     */
    @Test
    public void testGetChoosenEventType() {
        System.out.println("getChoosenEventType");
        StoreHandler instance = new StoreHandler();
        EventType expResult = null;
        EventType result = instance.getChoosenEventType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dayHaveEvent method, of class StoreHandler.
     */
    @Test
    public void testDayHaveEvent() {
        System.out.println("dayHaveEvent");
        Calendar c = null;
        StoreHandler instance = new StoreHandler();
        Boolean expResult = null;
        Boolean result = instance.dayHaveEvent(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetDateToCalender method, of class StoreHandler.
     */
    @Test
    public void testSetDateToCalender() {
        System.out.println("SetDateToCalender");
        Calendar c = null;
        StoreHandler instance = new StoreHandler();
        instance.SetDateToCalender(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDayEvent method, of class StoreHandler.
     */
    @Test
    public void testGetDayEvent() {
        System.out.println("getDayEvent");
        StoreHandler instance = new StoreHandler();
        String expResult = "";
        String result = instance.getDayEvent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
