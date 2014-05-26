/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.controller;

import java.util.ArrayList;
import model.Employee;
import model.EventType;
import model.Product;
import model.ProductLine;
import model.TicketType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class StoreControllerTest {
    
    public StoreControllerTest() {
    }

    /**
     * Test of getStoreController method, of class StoreController.
     */
    @Test
    public void testGetStoreController() {
        System.out.println("getStoreController");
        StoreController expResult = null;
        StoreController result = StoreController.getStoreController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicketData method, of class StoreController.
     */
    @Test
    public void testGetTicketData() {
        System.out.println("getTicketData");
        StoreController instance = new StoreController();
        instance.getTicketData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventData method, of class StoreController.
     */
    @Test
    public void testGetEventData() {
        System.out.println("getEventData");
        StoreController instance = new StoreController();
        instance.getEventData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeData method, of class StoreController.
     */
    @Test
    public void testGetEmployeeData() {
        System.out.println("getEmployeeData");
        StoreController instance = new StoreController();
        instance.getEmployeeData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeesList method, of class StoreController.
     */
    @Test
    public void testGetEmployeesList() {
        System.out.println("getEmployeesList");
        StoreController instance = new StoreController();
        ArrayList<Employee> expResult = null;
        ArrayList<Employee> result = instance.getEmployeesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductsList method, of class StoreController.
     */
    @Test
    public void testGetProductsList() {
        System.out.println("getProductsList");
        StoreController instance = new StoreController();
        ArrayList<Product> expResult = null;
        ArrayList<Product> result = instance.getProductsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicketTypesList method, of class StoreController.
     */
    @Test
    public void testGetTicketTypesList() {
        System.out.println("getTicketTypesList");
        StoreController instance = new StoreController();
        ArrayList<TicketType> expResult = null;
        ArrayList<TicketType> result = instance.getTicketTypesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventTypesList method, of class StoreController.
     */
    @Test
    public void testGetEventTypesList() {
        System.out.println("getEventTypesList");
        StoreController instance = new StoreController();
        ArrayList<EventType> expResult = null;
        ArrayList<EventType> result = instance.getEventTypesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDayEvent method, of class StoreController.
     */
    @Test
    public void testGetDayEvent() {
        System.out.println("getDayEvent");
        String date = "";
        StoreController instance = new StoreController();
        String expResult = "";
        String result = instance.getDayEvent(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of arThereEvent method, of class StoreController.
     */
    @Test
    public void testArThereEvent() {
        System.out.println("arThereEvent");
        String date = "";
        StoreController instance = new StoreController();
        Boolean expResult = null;
        Boolean result = instance.arThereEvent(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterProductQuantities method, of class StoreController.
     */
    @Test
    public void testAlterProductQuantities() {
        System.out.println("alterProductQuantities");
        ArrayList<ProductLine> saleProductList = null;
        StoreController instance = new StoreController();
        instance.alterProductQuantities(saleProductList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
