/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

//import model.handler.SaleHandler;
import java.awt.Dimension;
import javax.swing.JButton;
import model.*;
import java.util.Calendar;
//import model.handler.*;

/**
 *
 * @author MarcoPc
 */
public class TicketView extends javax.swing.JPanel {

//    private SaleHandler saleHandler;
    private TicketType ticketType;
    private int plusAntal;

    /**
     * Creates new form TicketVeiw
     */
    public TicketView(TicketType ticketType1, int plusAntal) {
        setSize(new Dimension(300, 40));
        this.ticketType = ticketType1;
//        this.saleHandler = saleHandler;
        this.plusAntal = plusAntal;
        
        initComponents();
        settext();
    }

    public void settext() {

        if (plusAntal == 1) {
            jButton1.setText("+1");
            plusAntal = 1;
        } else if (plusAntal == 2) {
            jButton1.setText("+5");
            plusAntal = 5;
        } else if (plusAntal == 3) {
            jButton1.setText("+10");
            plusAntal = 10;
        }

    }

    public void addticket() {
//        String date = "";
//        Calendar cal = Calendar.getInstance();
//        date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
//        saleHandler.addTicketLineToSale(ticketType, plusAntal, date);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addticket();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}