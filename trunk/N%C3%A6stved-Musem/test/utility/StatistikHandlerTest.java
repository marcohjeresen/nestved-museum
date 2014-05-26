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
public class StatistikHandlerTest {

    public StatistikHandlerTest() {
    }

    /**
     *Her tester vi StatistikHandler opretter en singleton hvis den ikke allerede eksistere.
     */
    @Test
    public void testGetStatistikHandler() {
        StatistikHandler expResult = StatistikHandler.getStatistikHandler();
        StatistikHandler result = null;
        if (result == null) {
            result = StatistikHandler.getStatistikHandler();

        }
        assertEquals("Failede at hente StatistikHandler", expResult, result);

    }

}
