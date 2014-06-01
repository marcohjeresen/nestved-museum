
package docTools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import utility.DateFormatTools;
import utility.Line;


public class DocHandler {

    private ArrayList<Line> lineList;
    
    /**
     * Constructor, creates a new object of the class.
     * The constructor is initialized with a lineList, which the
     * information is retrieved from.
     * Creates a file with the current date and information from lineList.
     * @throws IOException
     */

    public DocHandler(ArrayList<Line> lineList) {
        this.lineList = lineList;
        DateFormatTools dt = new DateFormatTools();
        
        try {
            String filename = "CashReport-" + dt.getDateNowShortString() + ".txt";
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(file);
            for (Line product : lineList) {
                String proString = "";
                if (product.getPriceDk() == 0) {
                    proString = product.getText();
                } else {
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
