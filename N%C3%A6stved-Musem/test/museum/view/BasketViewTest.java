/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

import model.Employee;
import model.PaymentType;
import model.Sale;
import model.TicketLine;
import model.TicketType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class BasketViewTest {

    public BasketViewTest() {
    }

    /**
     * Test of addQuantities method, of class BasketView.
     */
    @Test
    public void testAddQuantities() {
        System.out.println("addQuantities");
        TicketType tt = new TicketType(1, "Voksen", 5000, 10);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        TicketLine tl = new TicketLine(1, sale1, 1, tt);
        
        sale1.addTicketLine(tt, 1);
        sale1.addRemoveTicketLineQuantities(tt, 1);
        
        int expResult = 2;
        int result = 0;
        
        for (TicketLine ticketLine : sale1.getTicketLine()) {
            result = ticketLine.getQuantities();
        }
        assertEquals("Den har ikke tilføjet korrekt.",expResult,result );

    }

    /**
     * Test of removeQuantities method, of class BasketView.
     */
    @Test
    public void testRemoveQuantities() {
        System.out.println("removeQuantities");
        TicketType tt = new TicketType(1, "Voksen", 5000, 10);
        PaymentType pt = new PaymentType(1, "Kontant", 0);
        Employee employee = new Employee(1234561234, "Karl", "Parkvej 105", 4700, "Næstved", 1435);
        Sale sale1 = new Sale(1, pt, employee, "2014-05-27 12:22:00");
        TicketLine tl = new TicketLine(1, sale1, 1, tt);
        
        sale1.addTicketLine(tt, 3);
        sale1.addRemoveTicketLineQuantities(tt, -2);
        
        int expResult = 1;
        int result = 0;
        
        for (TicketLine ticketLine : sale1.getTicketLine()) {
            result = ticketLine.getQuantities();
        }
        assertEquals("Den har ikke fjernet ticketlines korrekt.",expResult,result );
    }
}
