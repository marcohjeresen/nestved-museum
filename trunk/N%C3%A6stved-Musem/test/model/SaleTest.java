/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import utility.NumberFormatTools;

/**
 *
 * @author Dennis
 */
public class SaleTest {

    public SaleTest() {
    }

    /**
     * Test of getEventLine method, of class Sale.
     */
    @Test
    public void testAddEvent() {
        System.out.println("getEventLine");
        EventType et = new EventType(1, "Foredrag", 50, 10);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addEventLine(et, 1, 88888888, "På museumet");
        EventType saleevent = null;
        for (EventLine eventLine : sale1.getEventLine()) {
            saleevent = eventLine.getEventtype();
        }

        assertEquals("Fejlede at adde event til listen", et, saleevent);
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddTicket() {
        System.out.println("getTicketLine");
        TicketType tt = new TicketType(1, "Voksen", 50, 10);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addTicketLine(tt, 1);
        TicketType saleticket = null;
        for (TicketLine ticketLine : sale1.getTicketLine()) {
            saleticket = ticketLine.getTicketType();
        }
        assertEquals("Fejlede at adde ticket til listen", tt, saleticket);
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product p = new Product(1, "En is", "Frisco", 50, 50, 50, 90, 25, 1, "Guf");
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addProduct(p);
        Product saleproduct = null;
        for (ProductLine productLine : sale1.getProductLine()) {
            saleproduct = productLine.getProduct();
        }
        assertEquals("Fejlede at adde product til listen", saleproduct, p);

    }

    /**
     * Test of getEndpriceDk method, of class Sale.
     */
    @Test
    public void testGetEndpriceDkWithoutDiscount() {
        System.out.println("getEndpriceDkWithoutDiscount");
        NumberFormatTools nft = new NumberFormatTools();
        Product p = new Product(1, "En is", "Frisco", 50, 5000, 50, 90, 25, 1, "Guf");
        EventType et = new EventType(1, "Foredrag", 5000, 10);
        TicketType tt = new TicketType(1, "Voksen", 5000, 10);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addProduct(p);
        sale1.addEventLine(et, 1, 88888888, "På museumet");
        sale1.addTicketLine(tt, 1);

        boolean discount = false;
        double expResult = nft.getDoubleValue(15000);
        double result = nft.getDoubleValue(sale1.getEndPriceDk(discount));

        assertEquals("Fejlede at hente prisen i danske kroner uden rabat", expResult, result, 0.0);

    }

    /**
     * Test of getEndpriceDk method, of class Sale.
     */
    @Test
    public void testGetEndpriceDkWithDiscount() {
        System.out.println("getEndpriceDkWithDiscount");
        NumberFormatTools nft = new NumberFormatTools();
        Product p = new Product(1, "En is", "Frisco", 50, 5000, 50, 90, 1, 1, "Guf");
        EventType et = new EventType(1, "Foredrag", 5000, 10);
        TicketType tt = new TicketType(1, "Voksen", 5000, 10);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addProduct(p);
        sale1.addEventLine(et, 1, 88888888, "På museumet");
        sale1.addTicketLine(tt, 1);

        boolean discount = true;
        double expResult = nft.getDoubleValue(13500);
        double result = nft.getDoubleValue(sale1.getEndPriceDk(discount));

        assertEquals("Fejlede at hente prisen i danske kroner med rabat", expResult, result, 0.0);

    }

    /**
     * Test of getEndpriceEuro method, of class Sale.
     */
    @Test
    public void testGetEndpriceEuroWithoutDiscount() {
        System.out.println("getEndpriceEuroWithoutDiscount");
        NumberFormatTools nft = new NumberFormatTools();
        Product p = new Product(1, "En is", "Frisco", 50, 5000, 5000, 90, 1, 1, "Guf");
        EventType et = new EventType(1, "Foredrag", 5000, 1000);
        TicketType tt = new TicketType(1, "Voksen", 5000, 1000);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addProduct(p);
        sale1.addEventLine(et, 1, 88888888, "På museumet");
        sale1.addTicketLine(tt, 1);

        boolean discount = false;
        double expResult = nft.getDoubleValue(7000);
        double result = nft.getDoubleValue(sale1.getEndPriceEuro(discount));

        assertEquals("Fejlede at hente prisen i euro uden rabat", expResult, result, 0.0);
    }

    
        /**
     * Test of getEndpriceEuro method, of class Sale.
     */
    @Test
    public void testGetEndpriceEuroWithDiscount() {
        System.out.println("getEndpriceEuroWithDiscount");
        NumberFormatTools nft = new NumberFormatTools();
        Product p = new Product(1, "En is", "Frisco", 50, 5000, 5000, 90, 1, 1, "Guf");
        EventType et = new EventType(1, "Foredrag", 5000, 1000);
        TicketType tt = new TicketType(1, "Voksen", 5000, 1000);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        sale1.addProduct(p);
        sale1.addEventLine(et, 1, 88888888, "På museumet");
        sale1.addTicketLine(tt, 1);

        boolean discount = true;
        double expResult = nft.getDoubleValue(6300);
        double result = nft.getDoubleValue(sale1.getEndPriceEuro(discount));

        assertEquals("Fejlede at hente prisen i euro uden rabat", expResult, result, 0.0);
    }
}
