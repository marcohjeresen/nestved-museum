/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class DateFormatToolsTest {
    
    public DateFormatToolsTest() {
    }

    /**
     * Test of getDateNowCal method, of class DateFormatTools.
     */
    @Test
    public void testGetDateNowCal() {
        System.out.println("getDateNowCal");
        DateFormatTools instance = new DateFormatTools();
        Calendar expResult = null;
        Calendar result = instance.getDateNowCal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateNowString method, of class DateFormatTools.
     */
    @Test
    public void testGetDateNowString() {
        System.out.println("getDateNowString");
        DateFormatTools instance = new DateFormatTools();
        String expResult = "";
        String result = instance.getDateNowString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateNowShortString method, of class DateFormatTools.
     */
    @Test
    public void testGetDateNowShortString() {
        System.out.println("getDateNowShortString");
        DateFormatTools instance = new DateFormatTools();
        String expResult = "";
        String result = instance.getDateNowShortString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateFromCal method, of class DateFormatTools.
     */
    @Test
    public void testGetDateFromCal() {
        System.out.println("getDateFromCal");
        Calendar calendar = null;
        DateFormatTools instance = new DateFormatTools();
        String expResult = "";
        String result = instance.getDateFromCal(calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShortDate method, of class DateFormatTools.
     */
    @Test
    public void testGetShortDate() throws Exception {
        System.out.println("getShortDate");
        String date = "";
        DateFormatTools instance = new DateFormatTools();
        Calendar expResult = null;
        Calendar result = instance.getShortDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShortDateFromCal method, of class DateFormatTools.
     */
    @Test
    public void testGetShortDateFromCal() {
        System.out.println("getShortDateFromCal");
        Calendar c = null;
        DateFormatTools instance = new DateFormatTools();
        String expResult = "";
        String result = instance.getShortDateFromCal(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateFromString method, of class DateFormatTools.
     */
    @Test
    public void testGetDateFromString() {
        System.out.println("getDateFromString");
        String date = "";
        DateFormatTools instance = new DateFormatTools();
        Calendar expResult = null;
        Calendar result = instance.getDateFromString(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextday method, of class DateFormatTools.
     */
    @Test
    public void testGetNextday() {
        System.out.println("getNextday");
        Calendar calendar = null;
        int days = 0;
        DateFormatTools instance = new DateFormatTools();
        Calendar expResult = null;
        Calendar result = instance.getNextday(calendar, days);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartDateFromString method, of class DateFormatTools.
     */
    @Test
    public void testGetStartDateFromString() {
        System.out.println("getStartDateFromString");
        String date = "";
        DateFormatTools instance = new DateFormatTools();
        Calendar expResult = null;
        Calendar result = instance.getStartDateFromString(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndDateFromString method, of class DateFormatTools.
     */
    @Test
    public void testGetEndDateFromString() {
        System.out.println("getEndDateFromString");
        String date = "";
        DateFormatTools instance = new DateFormatTools();
        Calendar expResult = null;
        Calendar result = instance.getEndDateFromString(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDay method, of class DateFormatTools.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        String date = "";
        DateFormatTools instance = new DateFormatTools();
        String expResult = "";
        String result = instance.getDay(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDayLetter method, of class DateFormatTools.
     */
    @Test
    public void testGetDayLetter() {
        System.out.println("getDayLetter");
        String date = "";
        DateFormatTools instance = new DateFormatTools();
        String expResult = "";
        String result = instance.getDayLetter(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
