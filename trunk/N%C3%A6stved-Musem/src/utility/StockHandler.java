
package utility;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Handler.*;

public class StockHandler {

    private static StockHandler StockHandler;
    private StoreHandler storeHandler;
    private ArrayList<StockLine> StockList;

    
    /**
     * Constructor, creates a new object of the class.
     */
    private StockHandler() {
        storeHandler = StoreHandler.storeHandler();
        StockList = new ArrayList<>();
    }

    /**
     * Method, creates a singleton of StockHandler if it doesn't exist already.
     * @return StockHandler.
     */
    public static StockHandler getStockHandler() {
        if (StockHandler == null) {
            StockHandler = new StockHandler();
        }
        return StockHandler;
    }
/**
 * Method, retrieves all the products from the database and sorts them.
 * @return Arraylist<StockLine> that contains all the products.
 */
    public ArrayList<StockLine> getStockList() {
        StockList.removeAll(StockList);
        StockLine st = new StockLine("Nummer", "Navn", "Leverandør ", "KøbsPris", "Antal");
        StockList.add(st);
        DBConnection db = new DBConnection();
        try {
            ResultSet rse = db.getResult("SELECT * FROM product order by product_quantities");
            while (rse.next()) {
                StockLine l = new StockLine("" + rse.getInt("product_numberid"), rse.getString("product_name"), rse.getString("product_supplier"), "" + rse.getInt("product_buyprice"), "" + rse.getInt("product_quantities"));
                StockList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("utility - StockHandler - getStockList(): sql Error :" + ex.getLocalizedMessage());
        }
        return StockList;
    }
}
