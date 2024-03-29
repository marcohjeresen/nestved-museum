
package model.controller;

import utility.DateFormatTools;
import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.EventLine;
import model.Invoice;
import model.PaymentType;
import model.ProductLine;
import model.Sale;
import model.TicketLine;


public class SaleController {

    private static SaleController saleController;
    private ArrayList<PaymentType> paymentTypesList;
    private DateFormatTools dateFormatTools;

    /**
     * Constructor, creates a new object of the class.
     */
    private SaleController() {
        paymentTypesList = new ArrayList<>();
        dateFormatTools = new DateFormatTools();
    }

    /**
     * Method, creates a singleton of SaleController if it doesn't exist already.
     * @return saleController.
     */
    public static SaleController controller() {
        if (saleController == null) {
            saleController = new SaleController();
        }
        return saleController;
    }

    /**
     * Method, changes the different payment methods in the system.
     */
    public void getPaymentData() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM paymenttype");
            while (rs.next()) {
                PaymentType paymentType = new PaymentType(rs.getInt("paymenttype_id"), rs.getString("paymenttype_type"), rs.getInt("paymenttype_fee"));
                paymentTypesList.add(paymentType);
            }
            db.close();
        } catch (SQLException ex) {
            System.out.println("model.controller - SaleController - getPaymentDate(): sql problems " + ex.getLocalizedMessage());

        }
    }

    
    /**
     * Method, ends the current sale by adding all the sale information to the database.
     * @param sale1
     * @param discount 
     */
    public void endSale(Sale sale1, boolean discount) {
        DBConnection db = new DBConnection();
        int invoiceId = 0;
        int saleId = 0;
        int productLineId = 0;
        int eventLineId = 0;
        int ticketLineId = 0;
        ResultSet rs;
        try {
            rs = db.getResult("select max(sale_id) from sale;");
            while (rs.next()) {
                saleId = rs.getInt("max(sale_id)");
            }
            rs = db.getResult("select max(invoice_id) from invoice;");
            while (rs.next()) {
                invoiceId = rs.getInt("max(invoice_id)");
            }
            rs = db.getResult("select max(productline_id) from productline");
            while (rs.next()) {
                productLineId = rs.getInt("max(productline_id)");
            }
            rs = db.getResult("select max(eventline_id) from eventline");
            while (rs.next()) {
                eventLineId = rs.getInt("max(eventline_id)");
            }
            rs = db.getResult("select max(ticketline_id) from ticketline");
            while (rs.next()) {
                ticketLineId = rs.getInt("max(ticketline_id)");
            }
        } catch (SQLException ex) {
            System.out.println("model.controller - SaleController - endSale(): sql problems " + ex.getLocalizedMessage());
        }
        String dato = dateFormatTools.getDateNowString();
        invoiceId = invoiceId + 1;
        Invoice invoice = new Invoice(invoiceId, dato, sale1.getEndPriceDk(discount), sale1.getEndPriceEuro(discount), 1);
        sale1.setInvoice(invoice);
        sale1.setDate();
        saleId = saleId + 1;
        int count = 1;
        db.execute("insert into sale values(" + saleId + "," + "1" + "," + sale1.getEmployee().getCpr() + ",'" + sale1.getDate() + "')");
        if (!sale1.getProductLine().isEmpty()) {
            for (ProductLine productLine : sale1.getProductLine()) {
                productLineId = productLineId + count;
                db.execute("insert into productline values(" + productLineId + "," + productLine.getProduct().getProductNumber() + "," + saleId + "," + productLine.getQuantities() + ")");
                count++;
            }
        }
        if (!sale1.getEventLine().isEmpty()) {
            count = 1;
            for (EventLine eventLine : sale1.getEventLine()) {
                eventLineId = eventLineId + count;
                db.execute("insert into eventline values (" + eventLineId + "," + eventLine.getEventtype().getId() + "," + saleId + "," + eventLine.getQuantities() + ",'" + eventLine.getDate() + "'," + eventLine.getCustomer() + ",'" + eventLine.getPlace() + "')");
                count++;
            }
        }
        if (!sale1.getTicketLine().isEmpty()) {
            count = 1;
            for (TicketLine ticketLine : sale1.getTicketLine()) {
                ticketLineId = ticketLineId + count;
                db.execute("insert into ticketline values(" + ticketLineId + "," + ticketLine.getTicketType().getId() + "," + saleId + "," + ticketLine.getQuantities() + ")");
                count++;
            }
        }
        if (sale1.getInvoice() != null) {
            
            db.execute("insert into invoice values (" + sale1.getInvoice().getId() + "," + saleId + ",'" + dato + "'," + sale1.getInvoice().getPriceDk() + "," + sale1.getInvoice().getPriceEuro() + "," + sale1.getInvoice().getInvoiceStatus() + ")");
        }
        db.close();
    }
}
