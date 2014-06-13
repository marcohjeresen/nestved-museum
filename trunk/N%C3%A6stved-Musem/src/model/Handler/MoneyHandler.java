
package model.Handler;

import utility.DateFormatTools;
import java.util.Calendar;
import model.CashRegister;
import model.DifferanceRegistre;
import model.Employee;
import model.controller.MoneyController;

public class MoneyHandler {

    private static MoneyHandler moneyHandler;
    private CashRegister cashRegister;
    private DateFormatTools dateFormatTools;
    private MoneyController moneyController;
    private StoreHandler storeHandler;

    /**
    * Constructor, creates a new object of the class.
    */
    
    private MoneyHandler() {
        dateFormatTools = new DateFormatTools();
        moneyController = MoneyController.getMoneyController();
        storeHandler = StoreHandler.storeHandler();
    }

    /**
    * Method, creates a singleton of MoneyHandler.
    */
    public static MoneyHandler getMoneyHandler() {
        if (moneyHandler == null) {
            moneyHandler = new MoneyHandler();
        }
        return moneyHandler;
    }

    
    /**
     * Method, used to set the cashregistre and sends information to the database.
     * @param dk
     * @param euro 
     */
    public void setCashRegistre(int dk, int euro) {
        String date = dateFormatTools.getDateNowString();
        int id = moneyController.getIdFromData();
        cashRegister = new CashRegister(id, date, dk, euro, storeHandler.getLogEmployee());
        moneyController.addStartingCashToDataBase(cashRegister);

    }

    public void endRegister() {
        cashRegister = null;
    }

    
    /**
     * Method,checks if the cashRegister is true or false.
     * @return isthere as true or false depending on if cashRegister has been set.
     */
    public boolean cashRegister() {
        boolean isthere = false;
        if (cashRegister == null) {
            isthere = false;
        } else if (cashRegister != null) {
            isthere = true;
        }
        return isthere;
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }

    
    /**
     * Method, this method adds or removes money from the current cashRegister.
     * @param type
     * @param curency
     * @param amount 
     */
    public void addCashAmount(String type, String curency, int amount) {
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

    
    /**
     * Method, it calculates the difference and sends the information to the database
     * and then empties the cashRegister.
     * @param dkInTheBox
     * @param euroInTheBox
     * @param employee 
     */
    public void endCashRegister(int dkInTheBox, int euroInTheBox, Employee employee) {
        int differanceDk = dkInTheBox - cashRegister.getAmountDk();
        int differanceEuro = euroInTheBox - cashRegister.getAmountEuro();
        Calendar cal = Calendar.getInstance();
        String dato = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        DifferanceRegistre differanceRegistre = new DifferanceRegistre(cashRegister.getId(), employee, dkInTheBox, euroInTheBox, cashRegister.getAmountDk(), cashRegister.getAmountEuro(), differanceDk, differanceEuro, dato);
        moneyController.addEndCashToDatabase(differanceRegistre);
        cashRegister = null;
    }

}
