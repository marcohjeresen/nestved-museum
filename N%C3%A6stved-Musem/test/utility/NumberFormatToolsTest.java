/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class NumberFormatToolsTest {

    public NumberFormatToolsTest() {
    }

    /**
     * Test of getIntValue method, of class NumberFormatTools.
     */
    @Test
    public void testGetIntValue() {
        double price = 50;
        NumberFormatTools instance = new NumberFormatTools();
        int expResult = 5000;
        int result = instance.getIntValue(price);
        assertEquals("testGetIntValue har regnet forkert!", expResult, result);
    }

    /**
     * Test of getDoubleValue method, of class NumberFormatTools.
     */
    @Test
    public void testGetDoubleValue() {
        NumberFormatTools instance = new NumberFormatTools();
        int price = 5000;
        double expResult = 50;
        double result = instance.getDoubleValue(price);
        assertEquals("testGetDoubleValue har regnet forkert!", expResult, result, 0.0);
    }

    /**
     * Test of getIntSum method, of class NumberFormatTools.
     */
    @Test
    public void testGetIntSum() {
        NumberFormatTools instance = new NumberFormatTools();
        int intPrice = 5000;
        double doublePrice = 50.0;
        int expResult = 10000;
        int result = instance.getIntSum(intPrice, doublePrice);
        assertEquals("testGetIntSum har regnet forkert!", expResult, result);
    }

    /**
     * Test of getDoubleSum method, of class NumberFormatTools.
     */
    @Test
    public void testGetDoubleSum() {
        NumberFormatTools instance = new NumberFormatTools();
        int intPrice = 230;
        double doublePrice = 50.0;

        double expResult = 52.0;
        double result = instance.getDoubleSum(intPrice, doublePrice);
        assertEquals("testGetDoubleSum har regnet forkert!",expResult, result, 0.0);
    }

}
