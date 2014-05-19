/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CashRegister;
import model.DifferanceRegistre;
import model.Employee;
import museum.MainView;

/**
 *
 * @author MarcoPc
 */
public class MoneyController {

    private static MoneyController moneyController;

    public MoneyController() {
    }

    public static MoneyController getMoneyController() {
        if (moneyController == null) {
            moneyController = new MoneyController();
        }
        return moneyController;
    }

    public int getIdFromData() throws ClassNotFoundException, SQLException {
        DBConnection db = new DBConnection();
        int id = 0;
        try {
            ResultSet rs = db.getResult("select max(starting_id) from cashregistercontent");
            while (rs.next()) {
                id = rs.getInt("max(starting_id)");
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        id = id + 1;
        return id;
    }

    public void addStartingCashToDataBase(CashRegister register) throws ClassNotFoundException, SQLException {
        DBConnection db = new DBConnection();
        try {
            db.execute("insert into cashregistercontent values (" + register.getId() + ",'" + register.getDate() + "'," + register.getAmountDk() + "," + register.getAmountEuro() + "," + register.getEmployee().getCpr() + ")");
            System.out.println("det virker.,... klap klap");
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addEndCashToDatabase(DifferanceRegistre dif) throws ClassNotFoundException, SQLException{
        DBConnection db = new DBConnection();
        try {
            db.execute("insert into differance values ('"+dif.getId()+"',"+dif.getEmployee().getCpr()+""
                    + ","+dif.getCurrentCashDk()+","+dif.getCurrentCahsEuro()+","+dif.getExpectedDk()+","+dif.getExpectedEuro()+""
                    + ","+dif.getDifferanceDk()+","+dif.getDifferanceEuro()+",'"+dif.getDate()+"')");
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
