/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.Product;
import model.controller.StoreController;

/**
 *
 * @author MarcoPc
 */
public class StoreHandler {

    private static StoreHandler storeHandler;
    private StoreController controller;
    private Employee logEmployee;
    
    public StoreHandler() {
        try {
            controller = StoreController.getStoreController();
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Employee getLogEmployee() {
        return logEmployee;
    }
    
    public static StoreHandler storeHandler() {
        if (storeHandler == null) {
            storeHandler = new StoreHandler();
        }
        return storeHandler;
    }
    
    public boolean employeeLogin(int kode) {
        boolean logon = false;
        for (Employee employee : controller.getEmployeesList()) {
            if (employee.getPassword() == kode) {
                logEmployee = employee;
                logon = true;
            }
        }
        return logon;
    }
    
    public HashSet getProductGroups() {
        HashSet<String> ProductGroups = new HashSet<>();
        for (Product product : controller.getProductsList()) {
            ProductGroups.add(product.getGroupType());       
    }
        
    return ProductGroups;
}
}
