/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

import Util.Listeners;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.EventType;
import model.Handler.MoneyHandler;
import model.Handler.SaleHandler;
import model.Handler.StoreHandler;
import model.Product;
import model.ProductGroup;
import model.ProductLine;
import model.TicketType;

/**
 *
 * @author markh_000
 */
public class SaleView extends javax.swing.JPanel implements ActionListener {

    private StoreHandler storeHandler;
    private SaleHandler saleHandler;
    private MoneyHandler moneyHandler;
    private Listeners listeners;

    /**
     * Creates new form SaleView
     */
    public SaleView() {
        storeHandler = StoreHandler.storeHandler();
        saleHandler = SaleHandler.getSaleHandler();
        moneyHandler = MoneyHandler.getMoneyHandler();
        listeners = Listeners.getList();
        initComponents();
        listeners.addListener(this);
        setSize(new Dimension(1008, 691));
        CardLayout cl = (CardLayout) getLayout();
        cl.addLayoutComponent(jP_Sale, "sale");
        cl.addLayoutComponent(jP_EndSale, "endsale");
        cl.show(this, "sale");
        jP_basketf.setPreferredSize(new Dimension(450, 200));
        setPrice();

    }

    public void showEmployee() {
        jL_userName.setText("Medarbejder: ");
        if (storeHandler.getLogEmployee() != null) {
            jL_EmployeeName.setText(storeHandler.getLogEmployee().getName());
        }
    }

    public void createGroupPanel(String Type) {
        int count = 0;
        ArrayList<GroupsPanel> specGroupsList = new ArrayList<>();
        int x = 15;
        int y = 25;
        int height = 0;
        int width = 0;
        jP_type.removeAll();
        repaint();
        switch (Type) {
            case "Product":
                for (String productGroup : storeHandler.getProductGroups()) {
                    GroupsPanel groupsPanel = new GroupsPanel(productGroup, null, null);
                    if (count == 5) {
                        x = 15;
                        y = groupsPanel.getHeight() + 30;
                    }
                    groupsPanel.setLocation(x, y);
                    jP_type.add(groupsPanel);
                    jP_type.revalidate();
                    x += groupsPanel.getWidth() + 5;
                    groupsPanel.setVisible(true);
                    height = groupsPanel.getHeight();
                    width = groupsPanel.getWidth();
                    count++;
                }
                break;
            case "Ticket":

                for (TicketType ticketType : storeHandler.getTicketType()) {
                    GroupsPanel groupsPanel = new GroupsPanel(null, ticketType, null);
                    if (count == 4) {
                        x = 15;
                        y = groupsPanel.getHeight() + 30;
                    }
                    groupsPanel.setLocation(x, y);
                    jP_type.add(groupsPanel);
                    jP_type.revalidate();
                    x += groupsPanel.getWidth() + 5;
                    groupsPanel.setVisible(true);
                    height = groupsPanel.getHeight();
                    width = groupsPanel.getWidth();
                    count++;
                }
                break;
            case "Event":

                for (EventType eventType : storeHandler.getEventType()) {
                    GroupsPanel groupsPanel = new GroupsPanel(null, null, eventType);
                    if (count == 4) {
                        x = 15;
                        y = groupsPanel.getHeight() + 30;
                    } else if (count == 8) {
                        x = 15;
                        y = 2 * groupsPanel.getHeight() + 35;
                    }
                    groupsPanel.setLocation(x, y);
                    jP_type.add(groupsPanel);
                    jP_type.revalidate();
                    x += groupsPanel.getWidth() + 5;
                    groupsPanel.setVisible(true);
                    height = groupsPanel.getHeight();
                    width = groupsPanel.getWidth();
                    count++;
                }
                break;
        }

    }

    public void setChoosenType(String type, boolean search) {
        jP_product.removeAll();
        jP_product.repaint();
        int count = 0;
        int y = 25;
        int x = 15;
        switch (type) {
            case "Product":
                jP_product.removeAll();
                if (search) {

                } else {
                    for (Product product : storeHandler.getChoosenProduct()) {
                        ProductView pw = new ProductView(product);
                        pw.setLocation(x, y);
                        jP_product.add(pw);
                        jP_product.revalidate();
                        y += pw.getHeight() + 5;
                        pw.setVisible(true);
                    }
                }
                break;
            case "Ticket":
                jP_product.removeAll();
                TicketView tv1 = new TicketView(storeHandler.getChoosenTicket(), 1);
                TicketView tv2 = new TicketView(storeHandler.getChoosenTicket(), 2);
                TicketView tv3 = new TicketView(storeHandler.getChoosenTicket(), 3);
                tv1.setLocation(x, y);
                y += tv1.getHeight() + 5;
                tv2.setLocation(x, y);
                y += tv2.getHeight() + 5;
                tv3.setLocation(x, y);
                jP_product.add(tv1);
                jP_product.add(tv2);
                jP_product.add(tv3);
                jP_product.revalidate();
                break;
            case "Event":
                jP_product.removeAll();
                EventView ev = new EventView(storeHandler.getChoosenEventType());
                ev.setLocation(x, y);
                jP_product.add(ev);
                ev.setVisible(true);
                jP_product.revalidate();
                break;
            default:
        }
    }

    public void fillBasket() {
        int x = 3;
        int y = 5;
        jP_basketf.removeAll();
        jP_basketf.repaint();
        if (!saleHandler.getCurrentSale().getProductLine().isEmpty()) {
            for (ProductLine pl : saleHandler.getCurrentSale().getProductLine()) {
                BasketView bv = new BasketView(pl, null, null);
                bv.setLocation(x, y);
                y += bv.getHeight() + 5;
                jP_basketf.add(bv);
                bv.setVisible(true);
                jP_basketf.revalidate();
            }
        }
        if (!saleHandler.getCurrentSale().getTicketLine().isEmpty()) {
            
        }
        if (!saleHandler.getCurrentSale().getEventLine().isEmpty()) {
            
        }
        
        jP_basketf.setPreferredSize(new Dimension(HEIGHT, y));

    }
    
    public void setPrice(){
        jL_BasketPriceDk.setText(""+saleHandler.getCurrentSale().getEndpriceDk(false));
        jL_BasketPriceEuro.setText(""+saleHandler.getCurrentSale().getEndpriceEuro(false));
    }
    
    public void showCashReg(){
        jL_dkAmount.setText(""+moneyHandler.getCashRegister().getAmountDk());
        jL_euroAmount.setText(""+moneyHandler.getCashRegister().getAmountEuro());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_Sale = new javax.swing.JPanel();
        jP_type = new javax.swing.JPanel();
        jP_basket = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_basketf = new javax.swing.JPanel();
        jP_totalPrice = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jL_BasketPriceDk = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jL_BasketPriceEuro = new javax.swing.JLabel();
        jB_endSale = new javax.swing.JButton();
        jB_emtyBasket = new javax.swing.JButton();
        jB_showUtilSide = new javax.swing.JButton();
        jP_product = new javax.swing.JPanel();
        jB_seachProdukt = new javax.swing.JButton();
        jB_showProduct = new javax.swing.JButton();
        jB_showTicket = new javax.swing.JButton();
        jB_shoeEvent = new javax.swing.JButton();
        jL_dkAmount = new javax.swing.JLabel();
        jL_euroAmount = new javax.swing.JLabel();
        jB_logOut = new javax.swing.JButton();
        jB_closeRegisstre = new javax.swing.JButton();
        jL_userName = new javax.swing.JLabel();
        jL_EmployeeName = new javax.swing.JLabel();
        jP_EndSale = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        jP_Sale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jP_SaleFocusGained(evt);
            }
        });

        jP_type.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Typer:"));

        javax.swing.GroupLayout jP_typeLayout = new javax.swing.GroupLayout(jP_type);
        jP_type.setLayout(jP_typeLayout);
        jP_typeLayout.setHorizontalGroup(
            jP_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jP_typeLayout.setVerticalGroup(
            jP_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        jP_basket.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Kurv:"));

        javax.swing.GroupLayout jP_basketfLayout = new javax.swing.GroupLayout(jP_basketf);
        jP_basketf.setLayout(jP_basketfLayout);
        jP_basketfLayout.setHorizontalGroup(
            jP_basketfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );
        jP_basketfLayout.setVerticalGroup(
            jP_basketfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jP_basketf);

        javax.swing.GroupLayout jP_basketLayout = new javax.swing.GroupLayout(jP_basket);
        jP_basket.setLayout(jP_basketLayout);
        jP_basketLayout.setHorizontalGroup(
            jP_basketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jP_basketLayout.setVerticalGroup(
            jP_basketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jP_totalPrice.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Total Pris:"));

        jLabel1.setText("Danske Kroner:");

        jL_BasketPriceDk.setText("9672872872872");

        jLabel3.setText("EURO:");

        jL_BasketPriceEuro.setText("5247228723872872");

        javax.swing.GroupLayout jP_totalPriceLayout = new javax.swing.GroupLayout(jP_totalPrice);
        jP_totalPrice.setLayout(jP_totalPriceLayout);
        jP_totalPriceLayout.setHorizontalGroup(
            jP_totalPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_totalPriceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL_BasketPriceDk, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_BasketPriceEuro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_totalPriceLayout.setVerticalGroup(
            jP_totalPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_totalPriceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_totalPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jL_BasketPriceDk)
                    .addComponent(jLabel3)
                    .addComponent(jL_BasketPriceEuro))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jB_endSale.setText("Betal:");

        jB_emtyBasket.setText("Tøm Kurv:");

        jB_showUtilSide.setText("Statestik/Lager");

        jP_product.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Produkt:"));

        javax.swing.GroupLayout jP_productLayout = new javax.swing.GroupLayout(jP_product);
        jP_product.setLayout(jP_productLayout);
        jP_productLayout.setHorizontalGroup(
            jP_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );
        jP_productLayout.setVerticalGroup(
            jP_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jB_seachProdukt.setText("Søg Efter Produkt:");

        jB_showProduct.setText("Vis Produkt Grupper:");
        jB_showProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_showProductActionPerformed(evt);
            }
        });

        jB_showTicket.setText("Vis Billet Typer:");
        jB_showTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_showTicketActionPerformed(evt);
            }
        });

        jB_shoeEvent.setText("Vis Event Typer:");
        jB_shoeEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_shoeEventActionPerformed(evt);
            }
        });

        jL_dkAmount.setText("jLabel1");

        jL_euroAmount.setText("jLabel2");

        jB_logOut.setText("Log Ud:");

        jB_closeRegisstre.setText("Luk Kassen:");

        jL_userName.setText("jLabel3");

        jL_EmployeeName.setText("jLabel5");

        javax.swing.GroupLayout jP_SaleLayout = new javax.swing.GroupLayout(jP_Sale);
        jP_Sale.setLayout(jP_SaleLayout);
        jP_SaleLayout.setHorizontalGroup(
            jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_SaleLayout.createSequentialGroup()
                .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_SaleLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jB_seachProdukt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jB_showProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jB_showTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jB_shoeEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jL_dkAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jL_euroAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jP_SaleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jL_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jL_EmployeeName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_SaleLayout.createSequentialGroup()
                        .addComponent(jB_logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jB_closeRegisstre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jP_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jP_SaleLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jB_showUtilSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(jB_emtyBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jB_endSale, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(jP_totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jP_basket, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jP_SaleLayout.setVerticalGroup(
            jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_SaleLayout.createSequentialGroup()
                .addComponent(jP_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_SaleLayout.createSequentialGroup()
                        .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jP_SaleLayout.createSequentialGroup()
                                .addComponent(jP_basket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jP_totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jP_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addGroup(jP_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jB_endSale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(jB_logOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jB_closeRegisstre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jP_SaleLayout.createSequentialGroup()
                                .addComponent(jL_userName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jL_EmployeeName))
                            .addComponent(jB_emtyBasket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jB_showUtilSide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(jP_SaleLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jB_seachProdukt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jB_showProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jB_showTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jB_shoeEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jL_dkAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jL_euroAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        add(jP_Sale, "card2");

        javax.swing.GroupLayout jP_EndSaleLayout = new javax.swing.GroupLayout(jP_EndSale);
        jP_EndSale.setLayout(jP_EndSaleLayout);
        jP_EndSaleLayout.setHorizontalGroup(
            jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
        );
        jP_EndSaleLayout.setVerticalGroup(
            jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );

        add(jP_EndSale, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void jB_showProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_showProductActionPerformed
        createGroupPanel("Product");
    }//GEN-LAST:event_jB_showProductActionPerformed

    private void jP_SaleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jP_SaleFocusGained

    }//GEN-LAST:event_jP_SaleFocusGained

    private void jB_showTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_showTicketActionPerformed
        createGroupPanel("Ticket");
    }//GEN-LAST:event_jB_showTicketActionPerformed

    private void jB_shoeEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_shoeEventActionPerformed
        createGroupPanel("Event");
    }//GEN-LAST:event_jB_shoeEventActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_closeRegisstre;
    private javax.swing.JButton jB_emtyBasket;
    private javax.swing.JButton jB_endSale;
    private javax.swing.JButton jB_logOut;
    private javax.swing.JButton jB_seachProdukt;
    private javax.swing.JButton jB_shoeEvent;
    private javax.swing.JButton jB_showProduct;
    private javax.swing.JButton jB_showTicket;
    private javax.swing.JButton jB_showUtilSide;
    private javax.swing.JLabel jL_BasketPriceDk;
    private javax.swing.JLabel jL_BasketPriceEuro;
    private javax.swing.JLabel jL_EmployeeName;
    private javax.swing.JLabel jL_dkAmount;
    private javax.swing.JLabel jL_euroAmount;
    private javax.swing.JLabel jL_userName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jP_EndSale;
    private javax.swing.JPanel jP_Sale;
    private javax.swing.JPanel jP_basket;
    private javax.swing.JPanel jP_basketf;
    private javax.swing.JPanel jP_product;
    private javax.swing.JPanel jP_totalPrice;
    private javax.swing.JPanel jP_type;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Choosen Product":
                setChoosenType("Product", false);
                break;
            case "Choosen Ticket":
                setChoosenType("Ticket", false);
                break;
            case "Choosen Event":
                setChoosenType("Event", false);
                break;
            case "Basket Chance":
                fillBasket();
                setPrice();

            default:
        }

    }
}
