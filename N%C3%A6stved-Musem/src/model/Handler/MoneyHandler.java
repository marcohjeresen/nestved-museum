/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Handler;
import model.*;
import Util.*;
import java.util.Calendar;
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
                if (curency == "DK") {
                    cashRegister.setAmountDk(amountDk + amount);
                }else if (curency == "EURO")
                    cashRegister.setAmountEuro(amountEuro + amount);
                break;
            case "-":
                if (curency == "Dk") {
                    cashRegister.setAmountDk(amountDk - amount);
                }else if (curency == "Euro")
                    cashRegister.setAmountEuro(amountEuro - amount);
                break;
        }
        
      
    }
    
     public void endCashregister(int DkIndTheBox, int EuroIndTheBox, Employee employee){
        int differanceDk =  DkIndTheBox - cashRegister.getAmountDk();
        int differanceEuro = EuroIndTheBox - cashRegister.getAmountEuro() ;
        Calendar cal = Calendar.getInstance();
        String dato = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        DifferanceRegistre differanceRegistre = new DifferanceRegistre(cashRegister.getId(), employee, DkIndTheBox, EuroIndTheBox,cashRegister.getAmountDk(), cashRegister.getAmountEuro(),  differanceDk, differanceEuro, dato);
        moneyController.addEndCashToDatabase(differanceRegistre);
        
        cashRegister = null;
        
    }
    
}
