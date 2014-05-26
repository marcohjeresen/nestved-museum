/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.controller;

import model.CashRegister;
import model.DifferanceRegistre;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class MoneyControllerTest {
    
    public MoneyControllerTest() {
    }

    /**
     * Test of getMoneyController method, of class MoneyController.
     */
    @Test
    public void testGetMoneyController() {
        System.out.println("getMoneyController");
        MoneyController expResult = null;
        MoneyController result = MoneyController.getMoneyController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getIdFromData method, of class MoneyController.
     */
    @Test
    public void testGetIdFromData() {
        System.out.println("getIdFromData");
        MoneyController instance = new MoneyController();
        int expResult = 0;
        int result = instance.getIdFromData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStartingCashToDataBase method, of class MoneyController.
     */
    @Test
    public void testAddStartingCashToDataBase() {
        System.out.println("addStartingCashToDataBase");
        CashRegister register = null;
        MoneyController instance = new MoneyController();
        instance.addStartingCashToDataBase(register);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEndCashToDatabase method, of class MoneyController.
     */
    @Test
    public void testAddEndCashToDatabase() {
        System.out.println("addEndCashToDatabase");
        DifferanceRegistre dif = null;
        MoneyController instance = new MoneyController();
        instance.addEndCashToDatabase(dif);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
