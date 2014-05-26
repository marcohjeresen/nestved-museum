/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import model.Sale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class PrintHandlerTest {
    
    public PrintHandlerTest() {
    }

    /**
     * Test of kvitteringPrint method, of class PrintHandler.
     */
    @Test
    public void testKvitteringPrint() {
        System.out.println("kvitteringPrint");
        Sale sale = null;
        boolean discount = false;
        PrintHandler instance = new PrintHandler();
        instance.kvitteringPrint(sale, discount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cashReport method, of class PrintHandler.
     */
    @Test
    public void testCashReport() {
        System.out.println("cashReport");
        PrintHandler instance = new PrintHandler();
        instance.cashReport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawLines method, of class PrintHandler.
     */
    @Test
    public void testDrawLines() {
        System.out.println("drawLines");
        Graphics g = null;
        int page = 0;
        PrintHandler instance = new PrintHandler();
        instance.drawLines(g, page);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class PrintHandler.
     */
    @Test
    public void testPrint() throws Exception {
        System.out.println("print");
        Graphics g = null;
        PageFormat pf = null;
        int page = 0;
        PrintHandler instance = new PrintHandler();
        int expResult = 0;
        int result = instance.print(g, pf, page);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doPrint method, of class PrintHandler.
     */
    @Test
    public void testDoPrint() {
        System.out.println("doPrint");
        PrintHandler instance = new PrintHandler();
        instance.doPrint();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
