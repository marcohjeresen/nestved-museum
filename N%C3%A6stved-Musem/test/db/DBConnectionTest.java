/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class DBConnectionTest {
    
    public DBConnectionTest() {
    }

    /**
     * Test of connect method, of class DBConnection.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        DBConnection instance = new DBConnection();
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConnected method, of class DBConnection.
     */
    @Test
    public void testIsConnected() {
        System.out.println("isConnected");
        DBConnection instance = new DBConnection();
        boolean expResult = false;
        boolean result = instance.isConnected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class DBConnection.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DBConnection expResult = null;
        DBConnection result = DBConnection.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of execute method, of class DBConnection.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String sql = "";
        DBConnection instance = new DBConnection();
        instance.execute(sql);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResult method, of class DBConnection.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        String sql = "";
        DBConnection instance = new DBConnection();
        ResultSet expResult = null;
        ResultSet result = instance.getResult(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class DBConnection.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        DBConnection instance = new DBConnection();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
