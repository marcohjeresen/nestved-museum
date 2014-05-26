/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import java.awt.event.ActionListener;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class ListenersTest {
    
    public ListenersTest() {
    }

    /**
     * Test of getList method, of class Listeners.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        Listeners expResult = null;
        Listeners result = Listeners.getList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addListener method, of class Listeners.
     */
    @Test
    public void testAddListener() {
        System.out.println("addListener");
        ActionListener listener = null;
        Listeners instance = null;
        instance.addListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyListeners method, of class Listeners.
     */
    @Test
    public void testNotifyListeners() {
        System.out.println("notifyListeners");
        String event = "";
        Listeners instance = null;
        instance.notifyListeners(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
