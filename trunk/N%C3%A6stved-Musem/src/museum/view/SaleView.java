/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

import Util.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import model.EventLine;
import model.EventType;
import model.Handler.MoneyHandler;
import model.Handler.SaleHandler;
import model.Handler.StoreHandler;
import model.Product;
import model.ProductGroup;
import model.ProductLine;
import model.TicketLine;
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
    private NumberFormatTools numberFormatTools;
     private String modtaget;
    private String dkOrEuro;
    private ArrayList<String> modtag;
    private double penge;
    private Timer timer;
    private boolean discount;

    /**
     * Creates new form SaleView
     */
    public SaleView() {
        storeHandler = StoreHandler.storeHandler();
        saleHandler = SaleHandler.getSaleHandler();
        moneyHandler = MoneyHandler.getMoneyHandler();
        listeners = Listeners.getList();
        numberFormatTools = NumberFormatTools.getTools();
        initComponents();
        listeners.addListener(this);
        setSize(new Dimension(1008, 691));
        CardLayout cl = (CardLayout) getLayout();
        cl.addLayoutComponent(jP_Sale, "sale");
        cl.addLayoutComponent(jP_EndSale, "endsale");
        cl.show(this, "sale");
        jP_basketf.setPreferredSize(new Dimension(450, 200));
        jButton_betal.setEnabled(false);
        discount = false;
        modtaget = "";
//        setBounds(0, 0, 400, 470);
        jLabel_melding.setText("");
        
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
                x = 65;
                y = 50;
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
            for (TicketLine ticketLine : saleHandler.getCurrentSale().getTicketLine()) {
                BasketView bv = new BasketView(null, null, ticketLine);
                bv.setLocation(x, y);
                y += bv.getHeight() + 5;
                jP_basketf.add(bv);
                bv.setVisible(true);
                jP_basketf.revalidate();
            }
        }
        if (!saleHandler.getCurrentSale().getEventLine().isEmpty()) {
            for (EventLine eventLine : saleHandler.getCurrentSale().getEventLine()) {
                BasketView bv = new BasketView(null, eventLine, null);
                bv.setLocation(x, y);
                y += bv.getHeight() + 5;
                jP_basketf.add(bv);
                bv.setVisible(true);
                jP_basketf.revalidate();
            }
        }
        jP_basketf.setPreferredSize(new Dimension(HEIGHT, y));
    }
    
    public void setPrice(){
        double moneyDk = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceDk(false));
        double moneyEuro = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceEuro(false));
        jL_BasketPriceDk.setText(""+moneyDk);
        jL_BasketPriceEuro.setText(""+moneyEuro);
    }
    
    public void showCashReg(){
        double moneyDk = numberFormatTools.getDoubleValue(moneyHandler.getCashRegister().getAmountDk());
        double moneyEuro = numberFormatTools.getDoubleValue(moneyHandler.getCashRegister().getAmountEuro());
        jL_dkAmount.setText(""+moneyDk);
        jL_euroAmount.setText(""+moneyEuro);
    }
    
    public void Museumscard() {
        discount = jCheckBox_rabat.isSelected();
        setPaymentAmount();
    }

    public void setPaymentAmount() {

        if (jCheckBox_danske.isSelected()) {
            double priceDk = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceDk(discount));
            jTextField_beløb.setText("DK: " + priceDk);
            penge = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceDk(discount));
        } else if (jCheckBox_euro.isSelected()) {
            double priceEuro = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceEuro(discount));
            jTextField_beløb.setText("EURO: " + priceEuro);
            penge = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceEuro(discount));
        }

    }

    public void setModtagetBeløb() {
        modtaget = "";
        if (!modtag.isEmpty()) {
            for (String string : modtag) {
                modtaget = modtaget + string;
                jButton_betal.setEnabled(true);
            }
        }

        jTextField_modtaget.setText(modtaget);
    }

    public void endSale() throws SQLException {
        boolean beløbGodkent = false;
        double modtagetTilBetaling;
        double retur = 0;
        int payid = 1;
        try {
            modtagetTilBetaling = Double.parseDouble(modtaget);
            if (penge <= modtagetTilBetaling) {
                beløbGodkent = true;
                if (jCheckBox_danske.isSelected()) {
                    retur = modtagetTilBetaling - penge;
                    jTextField_returBeløb.setText("Retur DK: " + retur);

                } else if (jCheckBox_euro.isSelected()) {
                    retur = modtagetTilBetaling - penge;
                    jTextField_returBeløb.setText("Retur Euro: " + retur);
                }

//                storeController.alterProductQuantities(sale.getProductLine(), storeHandler.getProductsList());

//                saleHandler.endSale(sale, discount);
                penge = penge;
                int money = (int) (penge * 100);
//                moneyHandler.addCashAmount("+", dkOrEuro, money);

                listeners.notifyListeners("End Sale");
                jButton_betal.setEnabled(false);
                jButton_fortryd.setEnabled(false);
                timer.start();
                if (jCheckBox_kvit.isSelected()) {
//                    printHandler.kvitteringPrint(sale, discount);
                }
            } else {
                jLabel_melding.setText("Beløb Ikke Nok!!");
            }

        } catch (NumberFormatException ex) {
            jLabel_melding.setText("Tjek indtastede beløb");
        }

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
        jButton4 = new javax.swing.JButton();
        jCheckBox_danske = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jButton_fortryd = new javax.swing.JButton();
        jTextField_returBeløb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCheckBox_euro = new javax.swing.JCheckBox();
        jButton9 = new javax.swing.JButton();
        jCheckBox_kvit = new javax.swing.JCheckBox();
        jLabel_melding = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jCheckBox_rabat = new javax.swing.JCheckBox();
        jTextField_modtaget = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton_betal = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_beløb = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();

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
        jB_endSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_endSaleActionPerformed(evt);
            }
        });

        jB_emtyBasket.setText("Tøm Kurv:");
        jB_emtyBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_emtyBasketActionPerformed(evt);
            }
        });

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

        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox_danske.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_danske.setText("Danske Kroner");
        jCheckBox_danske.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_danskeActionPerformed(evt);
            }
        });

        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton_fortryd.setText("Fortryd Indtastning:");
        jButton_fortryd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_fortrydActionPerformed(evt);
            }
        });

        jTextField_returBeløb.setEditable(false);

        jLabel2.setText("Beløb Til Betaling:");

        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox_euro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_euro.setText("Euro");
        jCheckBox_euro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_euroActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jCheckBox_kvit.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_kvit.setText("Kvitering");

        jLabel_melding.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_melding.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_melding.setText("jLabel5");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Vælg Betalings Type:");

        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jCheckBox_rabat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_rabat.setText("MuseumsKort");
        jCheckBox_rabat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_rabatActionPerformed(evt);
            }
        });

        jTextField_modtaget.setEditable(false);

        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setText("0");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton_betal.setText("Betal");
        jButton_betal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_betalActionPerformed(evt);
            }
        });

        jButton11.setText(".");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel5.setText("Penge Retur:");

        jLabel6.setText("Beløb Modtaget:");

        jTextField_beløb.setEditable(false);

        jButton12.setText("Gå Tilbage:");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_EndSaleLayout = new javax.swing.GroupLayout(jP_EndSale);
        jP_EndSale.setLayout(jP_EndSaleLayout);
        jP_EndSaleLayout.setHorizontalGroup(
            jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                        .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addComponent(jTextField_returBeløb)
                            .addComponent(jCheckBox_danske)
                            .addComponent(jTextField_beløb, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jCheckBox_euro, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_modtaget)
                                            .addComponent(jButton_fortryd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton_betal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel_melding, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(338, 338, 338))
                            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox_kvit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                        .addComponent(jCheckBox_rabat)
                        .addGap(88, 88, 88))))
            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jP_EndSaleLayout.setVerticalGroup(
            jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_EndSaleLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jCheckBox_rabat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                        .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_euro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox_danske))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_beløb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_modtaget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(jTextField_returBeløb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_kvit))
                .addGap(6, 6, 6)
                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_melding))
                .addGap(6, 6, 6)
                .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                        .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jP_EndSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jP_EndSaleLayout.createSequentialGroup()
                        .addComponent(jButton_fortryd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_betal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(81, 81, 81)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void jB_emtyBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_emtyBasketActionPerformed
        saleHandler.getCurrentSale().clearSale();
    }//GEN-LAST:event_jB_emtyBasketActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modtag.add("4");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox_danskeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_danskeActionPerformed
        if (jCheckBox_danske.isSelected()) {
            jCheckBox_euro.setSelected(false);
            dkOrEuro = "DK";
        }
        setPaymentAmount();
    }//GEN-LAST:event_jCheckBox_danskeActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        modtag.add("7");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton_fortrydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_fortrydActionPerformed
        modtag.remove(modtag.size() - 1);
        setModtagetBeløb();
    }//GEN-LAST:event_jButton_fortrydActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modtag.add("2");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modtag.add("3");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modtag.add("1");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox_euroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_euroActionPerformed
        if (jCheckBox_euro.isSelected()) {
            jCheckBox_danske.setSelected(false);
            dkOrEuro = "EURO";
        }
        setPaymentAmount();
    }//GEN-LAST:event_jCheckBox_euroActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        modtag.add("9");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        modtag.add("6");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCheckBox_rabatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_rabatActionPerformed

        if (jCheckBox_rabat.isSelected()) {

        }
        setPaymentAmount();
    }//GEN-LAST:event_jCheckBox_rabatActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        modtag.add("8");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        modtag.add("0");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modtag.add("5");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton_betalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_betalActionPerformed
        try {
            endSale();
        } catch (SQLException ex) {
            Logger.getLogger(SaleView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_betalActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        modtag.add(".");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        CardLayout cl = (CardLayout) getLayout();
                cl.show(this, "sale");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jB_endSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_endSaleActionPerformed
        CardLayout cl = (CardLayout) getLayout();
                cl.show(this, "endsale");
    }//GEN-LAST:event_jB_endSaleActionPerformed


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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_betal;
    private javax.swing.JButton jButton_fortryd;
    private javax.swing.JCheckBox jCheckBox_danske;
    private javax.swing.JCheckBox jCheckBox_euro;
    private javax.swing.JCheckBox jCheckBox_kvit;
    private javax.swing.JCheckBox jCheckBox_rabat;
    private javax.swing.JLabel jL_BasketPriceDk;
    private javax.swing.JLabel jL_BasketPriceEuro;
    private javax.swing.JLabel jL_EmployeeName;
    private javax.swing.JLabel jL_dkAmount;
    private javax.swing.JLabel jL_euroAmount;
    private javax.swing.JLabel jL_userName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_melding;
    private javax.swing.JPanel jP_EndSale;
    private javax.swing.JPanel jP_Sale;
    private javax.swing.JPanel jP_basket;
    private javax.swing.JPanel jP_basketf;
    private javax.swing.JPanel jP_product;
    private javax.swing.JPanel jP_totalPrice;
    private javax.swing.JPanel jP_type;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_beløb;
    private javax.swing.JTextField jTextField_modtaget;
    private javax.swing.JTextField jTextField_returBeløb;
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
            case "Basket Change":
                fillBasket();
                setPrice();

            default:
        }

    }
}
