/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;

import model.CashRegister;
import model.Employee;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class MoneyHandlerTest {
    
    public MoneyHandlerTest() {
    }

    /**
     * Test of getMoneyHandler method, of class MoneyHandler.
     */
    @Test
    public void testGetMoneyHandler() {
        System.out.println("getMoneyHandler");
        MoneyHandler expResult = null;
        MoneyHandler result = MoneyHandler.getMoneyHandler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCashRegistre method, of class MoneyHandler.
     */
    @Test
    public void testSetCashRegistre() {
        System.out.println("setCashRegistre");
        int dk = 0;
        int euro = 0;
        MoneyHandler instance = new MoneyHandler();
        instance.setCashRegistre(dk, euro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endRegistre method, of class MoneyHandler.
     */
    @Test
    public void testEndRegistre() {
        System.out.println("endRegistre");
        MoneyHandler instance = new MoneyHandler();
        instance.endRegistre();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cashRegistre method, of class MoneyHandler.
     */
    @Test
    public void testCashRegistre() {
        System.out.println("cashRegistre");
        MoneyHandler instance = new MoneyHandler();
        boolean expResult = false;
        boolean result = instance.cashRegistre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCashRegister method, of class MoneyHandler.
     */
    @Test
    public void testGetCashRegister() {
        System.out.println("getCashRegister");
        MoneyHandler instance = new MoneyHandler();
        CashRegister expResult = null;
        CashRegister result = instance.getCashRegister();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCashAmount method, of class MoneyHandler.
     */
    @Test
    public void testAddCashAmount() {
        System.out.println("addCashAmount");
        String type = "";
        String curency = "";
        int amount = 0;
        MoneyHandler instance = new MoneyHandler();
        instance.addCashAmount(type, curency, amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endCashregister method, of class MoneyHandler.
     */
    @Test
    public void testEndCashregister() {
        System.out.println("endCashregister");
        int DkIndTheBox = 0;
        int EuroIndTheBox = 0;
        Employee employee = null;
        MoneyHandler instance = new MoneyHandler();
        instance.endCashregister(DkIndTheBox, EuroIndTheBox, employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
