/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author MarcoPc
 */
public class NumberFormatTools {
    private static NumberFormatTools numberFormatTools;
    private int priceInt;
    private double priceDouble;

    /**
     * Constructor, creates a new object of the class.
     */
    private NumberFormatTools() {
    }
    
    /**
     * Method, creates a singleton of NumberFormatTools if it doesn't exist already..
     * @return numberFormatTools.
     */
    public static NumberFormatTools getTools(){
        if (numberFormatTools == null) {
            numberFormatTools = new NumberFormatTools();
        }
        return numberFormatTools;
    }
    
    public int getIntValue(double price){
        priceDouble = price;
        priceInt = (int) (priceDouble * 100);
        return priceInt;
    }
    
    public double getDoubleValue(int price){
        priceDouble = price;
        priceDouble = priceDouble / 100;
        return priceDouble;
    }
    
    public int getIntSum(int intPrice, double doublePrice){
        priceInt = (int) (doublePrice * 100);
        priceInt = priceInt + intPrice;
        return priceInt;
    }
    
    public double getDoubleSum(int intPrice, double doublePrice){
        priceInt = (int) (doublePrice * 100);
        priceInt = priceInt + intPrice;
        priceDouble = priceInt / 100;
        return priceDouble;
    }
    
}
