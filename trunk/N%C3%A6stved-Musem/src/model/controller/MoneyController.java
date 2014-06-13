package model.controller;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CashRegister;
import model.DifferanceRegistre;

public class MoneyController {

    private static MoneyController moneyController;

    
    /**
     * Constructor, creates a new object of the class.
     */
    private MoneyController() {
    }

    /**
     * Method, creates a singleton of MoneyController if it doesn't exist already.
     * @return moneyController.
     */
    public static MoneyController getMoneyController() {
        if (moneyController == null) {
            moneyController = new MoneyController();
        }
        return moneyController;
    }

    
    /**
     * Method, checks for the next avaible ID for cashregister from the database
     * @return 
     */
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

    
    /**
     * Method, sends the start amount of the cashregister to the database.
     * @param register 
     */
    public void addStartingCashToDataBase(CashRegister register) {
        DBConnection db = new DBConnection();
        db.execute("insert into cashregistercontent values (" + register.getId() + ",'" + register.getDate() + "'," + register.getAmountDk() + "," + register.getAmountEuro() + "," + register.getEmployee().getCpr() + ")");
        db.close();
    }

    /**
     * Method, sends the end amount of the cashregister to the database.
     * @param dif 
     */
    public void addEndCashToDatabase(DifferanceRegistre dif) {
        DBConnection db = new DBConnection();
        db.execute("insert into differance values ('" + dif.getId() + "'," + dif.getEmployee().getCpr() + ""
                + "," + dif.getCurrentCashDk() + "," + dif.getCurrentCashEuro() + "," + dif.getExpectedDk() + "," + dif.getExpectedEuro() + ""
                + "," + dif.getDifferanceDk() + "," + dif.getDifferanceEuro() + ",'" + dif.getDate() + "')");
        db.close();
    }

}
