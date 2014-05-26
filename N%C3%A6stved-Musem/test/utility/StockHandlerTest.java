/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class StockHandlerTest {
    
    public StockHandlerTest() {
    }

    /**
     * Test of getStockHandler method, of class StockHandler.
     */
    @Test
    public void testGetStockHandler() {
        StockHandler expResult = StockHandler.getStockHandler();
        StockHandler result = null;
        if (result == null) {
            result = StockHandler.getStockHandler();

        }
        assertEquals("Failede at hente StockHandler", expResult, result);
    }


    
}
