
package print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import museum.view.StatistikView;




public class StatistikPrintHandler implements Printable {

    private StatistikView statistikView;

    
    /**
     * Constructor, creates a new object of the class.
     * @param statistikView 
     */
    public StatistikPrintHandler(StatistikView statistikView) {
        this.statistikView = statistikView;
    }

    /**
     * Method, Checks if you are available to print and asks if you want to print.
     */
    public void doPrint() {
        PrinterJob job = PrinterJob.getPrinterJob();
        Printable doc = this;
        job.setPrintable(doc);
        boolean accept = job.printDialog();
        if (accept) {
            try {
                job.print();
            } catch (PrinterException ex) {
                System.out.println("print - StatistikPrintHandler - doPrint(): Printer Error :" + ex.getLocalizedMessage());
            }
        } else {
                System.out.println("print - StatistikPrintHandler - doPrint(): Printer didnt print:");
        }
    }

    
    /**
     * Method, retrieves the information from statistikView and prints it.
     * @param g
     * @param pf
     * @param page
     * @return printResult, as an int. PAGE_EXISTS or NO_SUCH_PAGE
     * @throws PrinterException 
     */
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        int printResult;

        if (page == 0) {
            Graphics2D g2d = (Graphics2D) g;
            double x0 = pf.getImageableX();
            double y0 = pf.getImageableY();
            g2d.translate(x0, y0);
            statistikView.drawLines(g2d, page);
            printResult = PAGE_EXISTS;
        } else {
            printResult = NO_SUCH_PAGE;
        }
        return printResult;
    }

}
