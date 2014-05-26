/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class ProductGroupTest {
    
    public ProductGroupTest() {
    }

    /**
     * Test of getGroupId method, of class ProductGroup.
     */
    @Test
    public void testGetGroupId() {
        System.out.println("getGroupId");
        ProductGroup instance = null;
        int expResult = 0;
        int result = instance.getGroupId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupId method, of class ProductGroup.
     */
    @Test
    public void testSetGroupId() {
        System.out.println("setGroupId");
        int groupId = 0;
        ProductGroup instance = null;
        instance.setGroupId(groupId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupType method, of class ProductGroup.
     */
    @Test
    public void testGetGroupType() {
        System.out.println("getGroupType");
        ProductGroup instance = null;
        String expResult = "";
        String result = instance.getGroupType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupType method, of class ProductGroup.
     */
    @Test
    public void testSetGroupType() {
        System.out.println("setGroupType");
        String groupType = "";
        ProductGroup instance = null;
        instance.setGroupType(groupType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ProductGroup.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ProductGroup instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
