/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum;

import utility.Listeners;
import db.DBConnection;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import museum.view.*;

/**
 *
 * @author markh_000
 */
public class MainPanel extends javax.swing.JPanel implements ActionListener {

    private Listeners listeners;
    private SaleView sv;
    private Logon logon;
    private UtilView utilView;

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        listeners = Listeners.getList();
        initComponents();
        listeners.addListener(this);
        setSize(new Dimension(1008, 691));
        connect();
    }

    public void connect() {
        DBConnection db = null;
        jP_Log.removeAll();
        repaint();
        CardLayout cl = (CardLayout) getLayout();
        cl.addLayoutComponent(jP_Log, "Logon");
        cl.addLayoutComponent(jP_Sale, "Sale");
        cl.addLayoutComponent(jP_Util, "Util");
        cl.show(this, "Logon");
        db = new DBConnection();
        if (db.isConnected()) {
            logon = new Logon();
            jP_Log.add(logon);
            logon.setVisible(true);
            sv = new SaleView();
            jP_Sale.add(sv);
            sv.setVisible(true);
            utilView = new UtilView();
            jP_Util.add(utilView);
            utilView.setVisible(true);
        } else {
            DbError dbError = new DbError(listeners);
            jP_Log.add(dbError);
            dbError.setVisible(true);
        }
    }

    public void showPage(String page) {
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, page);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_Log = new javax.swing.JPanel();
        jP_Sale = new javax.swing.JPanel();
        jP_Util = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jP_LogLayout = new javax.swing.GroupLayout(jP_Log);
        jP_Log.setLayout(jP_LogLayout);
        jP_LogLayout.setHorizontalGroup(
            jP_LogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jP_LogLayout.setVerticalGroup(
            jP_LogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        add(jP_Log, "card2");

        javax.swing.GroupLayout jP_SaleLayout = new javax.swing.GroupLayout(jP_Sale);
        jP_Sale.setLayout(jP_SaleLayout);
        jP_SaleLayout.setHorizontalGroup(
            jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jP_SaleLayout.setVerticalGroup(
            jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        add(jP_Sale, "card3");

        javax.swing.GroupLayout jP_UtilLayout = new javax.swing.GroupLayout(jP_Util);
        jP_Util.setLayout(jP_UtilLayout);
        jP_UtilLayout.setHorizontalGroup(
            jP_UtilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jP_UtilLayout.setVerticalGroup(
            jP_UtilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        add(jP_Util, "card4");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_Log;
    private javax.swing.JPanel jP_Sale;
    private javax.swing.JPanel jP_Util;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "LogAndCashOk":
                showPage("Sale");
                sv.showEmployee();
                sv.showCashReg();
                break;
            case "LogOut":
                showPage("Logon");
                logon.showPage("Logon");
                break;
            case "EndCashAndDay":
                showPage("Logon");
                logon.clearCashPage();
                logon.openOrClose();
                logon.showPage("CashReg");
                break;
            case "Database Retry":
                connect();
                break;
            case "Show Util":
                showPage("Util");
                break;
            case "Show Sale":
                showPage("Sale");
                break;
        }
    }
}
