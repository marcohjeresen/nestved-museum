/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package museum.view;

import java.awt.Graphics;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import utility.Line;

/**
 *
 * @author Dennis
 */
public class StatestikViewTest {
    
    public StatestikViewTest() {
    }

    /**
     * Test of getStatView method, of class StatestikView.
     */
    @Test
    public void testGetStatView() {
        System.out.println("getStatView");
        StatestikView expResult = null;
        StatestikView result = StatestikView.getStatView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStat method, of class StatestikView.
     */
    @Test
    public void testSetStat() {
        System.out.println("setStat");
        ArrayList<Line> lineList = null;
        String date = "";
        StatestikView instance = null;
        instance.setStat(lineList, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawLines method, of class StatestikView.
     */
    @Test
    public void testDrawLines() {
        System.out.println("drawLines");
        Graphics g = null;
        int page = 0;
        StatestikView instance = null;
        instance.drawLines(g, page);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
