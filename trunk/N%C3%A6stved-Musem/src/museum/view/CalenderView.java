/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package museum.view;

import utility.Listeners;
import model.Handler.StoreHandler;
import static java.awt.Color.RED;
import java.util.Calendar;


/**
 *
 * @author MarcoPc
 */
public class CalenderView extends javax.swing.JPanel {
private int day;
private int mounth;
private int year;
private String x;
private StoreHandler storeHandler;
private Calendar calendar;
private Listeners listeners;
    /**
     * Creates new form CalenderView
     * @param day

     * @param x
     */
    public CalenderView(int day, int month, int year, String x) {
        this.day = day;
        this.mounth = month;
        this.year = year;
        this.x = x;
        this.storeHandler = StoreHandler.storeHandler();
        
        this.listeners = Listeners.getList();
        initComponents();
        setSize(60, 60);
        setText();
        calendar = Calendar.getInstance();
        calendar.set(year, month, day, 00, 00, 00);
    }
    
    public void setText(){
        if (x == "x") {
            jButton1.setForeground(RED);
            jButton1.setText(day + x);
        }else
        jButton1.setText(day+"");
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
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        storeHandler.SetDateToCalender(calendar);
        listeners.notifyListeners("Update Cal");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}