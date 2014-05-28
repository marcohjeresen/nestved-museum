/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docTools;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Product;
import utility.DateFormatTools;
import utility.Line;
import utility.StockLine;

/**
 *
 * @author MarcoPc
 */
public class DocHandler {

    private ArrayList<Line> lineList;

    public DocHandler(ArrayList<Line> lineList) {
        this.lineList = lineList;
        DateFormatTools dt = new DateFormatTools();

        
        try {
            String filename = "CashReport-"+dt.getDateNowShortString()+".txt";
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(file);
            for (Line product : lineList) {
                String proString = "";
                if (product.getPriceDk() == 0) {
                   proString = product.getText();
                }else{
                    proString = product.getText() + "," + product.getPriceDk();
                }
                pw.println(proString);
            }
            pw.close();

        } catch (IOException ex) {
            System.out.println("Det var ikke muligt at skrive til filen: " + ex.getMessage());
        }

    }

}
