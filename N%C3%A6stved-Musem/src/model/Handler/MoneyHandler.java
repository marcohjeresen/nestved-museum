/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;
import model.*;
import Util.*;
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

    public MoneyHandler() {
        dateFormatTools = new DateFormatTools();
        moneyController = MoneyController.getMoneyController();
        storeHandler = StoreHandler.storeHandler();
        
    }
    
    public static MoneyHandler getMoneyHandler(){
        if (moneyHandler == null) {
            moneyHandler = new MoneyHandler();
        }
        return moneyHandler;
    }
    
    public void setCashRegistre(int dk, int euro){
        
        String date = dateFormatTools.getDateNowString();
        int id = moneyController.getIdFromData();
        
        cashRegister = new CashRegister(id,date, dk, euro, storeHandler.getLogEmployee());
        moneyController.addStartingCashToDataBase(cashRegister);
        
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
    
}
