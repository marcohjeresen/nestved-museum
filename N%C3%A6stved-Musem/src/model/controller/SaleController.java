/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PaymentType;
import museum.MainView;

/**
 *
 * @author MarcoPc
 */
public class SaleController {

    private static SaleController saleController;
    private ArrayList<PaymentType> paymentTypesList;

    public SaleController() {
        paymentTypesList = new ArrayList<>();
    }

    public static SaleController controller() {
        if (saleController == null) {
            saleController = new SaleController();
        }
        return saleController;
    }

    public void getPaymentData() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM paymenttype");
            while (rs.next()) {
                PaymentType paymentType = new PaymentType(rs.getInt("paymenttype_id"), rs.getString("paymenttype_type"), rs.getInt("paymenttype_fee"));
                paymentTypesList.add(paymentType);
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("Database connec: getPaymentDate() fejl");
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
