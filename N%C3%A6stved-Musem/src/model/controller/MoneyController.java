/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CashRegister;
import model.DifferanceRegistre;

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

    public int getIdFromData() {
        DBConnection db = new DBConnection();
        int id = 0;
        try {
            ResultSet rs = db.getResult("select max(starting_id) from cashregistercontent");
            while (rs.next()) {
                id = rs.getInt("max(starting_id)");
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("model.controller - MoneyController - getIdFromDate(): sql problems " + ex.getLocalizedMessage());
        }
        id = id + 1;
        return id;
    }

    public void addStartingCashToDataBase(CashRegister register) {
        DBConnection db = new DBConnection();
        db.execute("insert into cashregistercontent values (" + register.getId() + ",'" + register.getDate() + "'," + register.getAmountDk() + "," + register.getAmountEuro() + "," + register.getEmployee().getCpr() + ")");
        db.close();
    }
    
    public void addEndCashToDatabase(DifferanceRegistre dif) {
        DBConnection db = new DBConnection();
        db.execute("insert into differance values ('"+dif.getId()+"',"+dif.getEmployee().getCpr()+""
                + ","+dif.getCurrentCashDk()+","+dif.getCurrentCashEuro()+","+dif.getExpectedDk()+","+dif.getExpectedEuro()+""
                + ","+dif.getDifferanceDk()+","+dif.getDifferanceEuro()+",'"+dif.getDate()+"')");
        db.close();
    }

}
