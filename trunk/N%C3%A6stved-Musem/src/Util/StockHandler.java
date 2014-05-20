/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import model.Handler.*;

/**
 *
 * @author markh_000
 */
public class StockHandler {

    private static StockHandler StockHandler;
    private StoreHandler storeHandler;

    private ArrayList<StockLine> StockList;

    private StockHandler() {
        storeHandler = StoreHandler.storeHandler();
        StockList = new ArrayList<>();
    }

    public static StockHandler getStockHandler() {
        if (StockHandler == null) {
            StockHandler = new StockHandler();
        }
        return StockHandler;
    }

    public void setvalgte(String type, String typeNavn) {

    }

    public ArrayList<StockLine> getStockList() {
        StockList.removeAll(StockList);
        StockLine st = new StockLine("Nummer", "Navn", "Suplier", "KøbsPris", "Antal");
        StockList.add(st);
        DBConnection db = new DBConnection();
        try {
            ResultSet rse = db.getResult("SELECT * FROM product order by product_quantities");
            while (rse.next()) {
                StockLine l = new StockLine("" + rse.getInt("product_numberid"), rse.getString("product_name"), rse.getString("product_supplier"), "" + rse.getInt("product_buyprice"), "" + rse.getInt("product_quantities"));
                StockList.add(l);
            }
        } catch (SQLException ex) {

        }

        return StockList;
    }

    public void print() {

    }
}
