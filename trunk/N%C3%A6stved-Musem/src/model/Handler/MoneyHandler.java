/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;

import util.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CashRegister;
import model.DifferanceRegistre;
import model.Employee;
import model.controller.MoneyController;

/**
 *
 * @author MarcoPc
 */
public class MoneyHandler {
    private static MoneyHandler moneyHandler;
    private CashRegister cashRegister;
    private DateFormatTools dateFormatTools;
    private MoneyController moneyController;
    private StoreHandler storeHandler;

    public MoneyHandler()  {
        dateFormatTools = new DateFormatTools();
        moneyController = MoneyController.getMoneyController();
        storeHandler = StoreHandler.storeHandler();
    }
    
    public static MoneyHandler getMoneyHandler() {
        if (moneyHandler == null) {
            moneyHandler = new MoneyHandler();
        }
        return moneyHandler;
    }
    
    public void setCashRegistre(int dk, int euro) {
        try {
            String date = dateFormatTools.getDateNowString();
            int id = moneyController.getIdFromData();
            cashRegister = new CashRegister(id,date, dk, euro, storeHandler.getLogEmployee());
            moneyController.addStartingCashToDataBase(cashRegister);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void endRegistre(){
        cashRegister = null;
    }
    
    public boolean cashRegistre(){
        boolean isthere = false;
        if (cashRegister == null) {
            isthere = false;
        }else if (cashRegister != null) {
            isthere = true;
        }
        return isthere;
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }
    
    public void addCashAmount(String type, String curency ,int amount){
        int amountDk = cashRegister.getAmountDk();
        int amountEuro = cashRegister.getAmountEuro();
        switch (type) {
            case "+":
        switch (curency) {
            case "DK":
                cashRegister.setAmountDk(amountDk + amount);
                break;
            case "EURO":
                cashRegister.setAmountEuro(amountEuro + amount);
                break;
        }
                break;
            case "-":
        switch (curency) {
            case "Dk":
                cashRegister.setAmountDk(amountDk - amount);
                break;
            case "Euro":
                cashRegister.setAmountEuro(amountEuro - amount);
                break;
        }
                break;
        }
    }
    
     public void endCashregister(int DkIndTheBox, int EuroIndTheBox, Employee employee)  {
        try {
            int differanceDk =  DkIndTheBox - cashRegister.getAmountDk();
            int differanceEuro = EuroIndTheBox - cashRegister.getAmountEuro() ;
            Calendar cal = Calendar.getInstance();
            String dato = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
            DifferanceRegistre differanceRegistre = new DifferanceRegistre(cashRegister.getId(), employee, DkIndTheBox, EuroIndTheBox,cashRegister.getAmountDk(), cashRegister.getAmountEuro(),  differanceDk, differanceEuro, dato);
            moneyController.addEndCashToDatabase(differanceRegistre);
            cashRegister = null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
