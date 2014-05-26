/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package museum.view;

import java.awt.event.ActionEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class UtilViewTest {
    
    public UtilViewTest() {
    }

    /**
     * Test of showPage method, of class UtilView.
     */
    @Test
    public void testShowPage() {
        System.out.println("showPage");
        String page = "";
        UtilView instance = new UtilView();
        instance.showPage(page);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDaysOfMounthCalendar method, of class UtilView.
     */
    @Test
    public void testGetDaysOfMounthCalendar() {
        System.out.println("getDaysOfMounthCalendar");
        int mounht = 0;
        UtilView instance = new UtilView();
        instance.getDaysOfMounthCalendar(mounht);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStockView method, of class UtilView.
     */
    @Test
    public void testGetStockView() {
        System.out.println("getStockView");
        UtilView instance = new UtilView();
        instance.getStockView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parsDate method, of class UtilView.
     */
    @Test
    public void testParsDate() {
        System.out.println("parsDate");
        UtilView instance = new UtilView();
        String expResult = "";
        String result = instance.parsDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ShowStat method, of class UtilView.
     */
    @Test
    public void testShowStat() {
        System.out.println("ShowStat");
        UtilView instance = new UtilView();
        instance.ShowStat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class UtilView.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent ae = null;
        UtilView instance = new UtilView();
        instance.actionPerformed(ae);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
