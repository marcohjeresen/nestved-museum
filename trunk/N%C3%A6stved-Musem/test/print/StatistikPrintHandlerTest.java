/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class StatistikPrintHandlerTest {
    
    public StatistikPrintHandlerTest() {
    }

    /**
     * Test of doPrint method, of class StatistikPrintHandler.
     */
    @Test
    public void testDoPrint() {
        System.out.println("doPrint");
        StatistikPrintHandler instance = null;
        instance.doPrint();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class StatistikPrintHandler.
     */
    @Test
    public void testPrint() throws Exception {
        System.out.println("print");
        Graphics g = null;
        PageFormat pf = null;
        int page = 0;
        StatistikPrintHandler instance = null;
        int expResult = 0;
        int result = instance.print(g, pf, page);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
