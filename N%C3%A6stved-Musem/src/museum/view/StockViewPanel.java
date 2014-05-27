/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

import utility.StockLine;



/**
 *
 * @author MarcoPc
 */
public class StockViewPanel extends javax.swing.JPanel {

    /**
     * Creates new form StockView
     */
    public StockViewPanel(StockLine stockLine) {
        initComponents();
        setSize(790, 16);
        if (!stockLine.getProductBuyPrice().equals("KøbsPris")) {
            int priceeee = Integer.parseInt(stockLine.getProductBuyPrice());
            double price = priceeee / 100;
            jLabel_buyp.setText(price + "");
        }else{
            jLabel_buyp.setText(stockLine.getProductBuyPrice());
        }
        jLabel_numb.setText(stockLine.getProductNumber());
        jLabel_title.setText(stockLine.getProductName());
        jLabel_sup.setText(stockLine.getProductSupply());
        jLabel_qun.setText(stockLine.getProductQuantities());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_numb = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_sup = new javax.swing.JLabel();
        jLabel_buyp = new javax.swing.JLabel();
        jLabel_qun = new javax.swing.JLabel();

        jLabel_numb.setText("ProduktNummer:");

        jLabel_title.setText("Title:");

        jLabel_sup.setText("Suplere:");

        jLabel_buyp.setText("BuyPrice:");

        jLabel_qun.setText("auantitits");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_numb, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_title, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_buyp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_qun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_numb)
                .addComponent(jLabel_title)
                .addComponent(jLabel_sup)
                .addComponent(jLabel_buyp)
                .addComponent(jLabel_qun))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_buyp;
    private javax.swing.JLabel jLabel_numb;
    private javax.swing.JLabel jLabel_qun;
    private javax.swing.JLabel jLabel_sup;
    private javax.swing.JLabel jLabel_title;
    // End of variables declaration//GEN-END:variables
}
