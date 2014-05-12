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
import model.controller.SaleController;
import model.controller.StoreController;

/**
 *
 * @author markh_000
 */
public class SaleView extends javax.swing.JPanel implements ActionListener {

    private StoreHandler storeHandler;
    private StoreController storeController;
    private SaleHandler saleHandler;
    private SaleController saleController;
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
    public SaleView() throws SQLException {
        storeHandler = StoreHandler.storeHandler();
        storeController = StoreController.getStoreController();
        saleHandler = SaleHandler.getSaleHandler();
        saleController = SaleController.controller();
        moneyHandler = MoneyHandler.getMoneyHandler();
        listeners = Listeners.getList();
        numberFormatTools = NumberFormatTools.getTools();
        initComponents();
        listeners.addListener(this);
        setSize(new Dimension(1008, 691));
        CardLayout cl = (CardLayout) getLayout();
        cl.addLayoutComponent(jP_Sale, "sale");
        cl.addLayoutComponent(jP_endSale, "endsale");
        cl.show(this, "sale");
        jP_basketf.setPreferredSize(new Dimension(450, 200));
        jButton_saleEnd.setEnabled(false);
        discount = false;
        modtaget = "";
        modtag = new ArrayList<>();
        jLabel_endError.setText("");
        jB_endSale.setEnabled(false);
        setPrice();

        timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
clearAll();
                showPage("sale");
                
                timer.stop();
            }
        });
    }
    
    public void showPage(String page){
        CardLayout cl = (CardLayout) getLayout();
                cl.show(this, page);
    }

    public void clearList() {
        modtag.removeAll(modtag);
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
                jB_endSale.setEnabled(true);
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
                jB_endSale.setEnabled(true);
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
                jB_endSale.setEnabled(true);
            }
        }
        jP_basketf.setPreferredSize(new Dimension(HEIGHT, y));
    }

    public void setPrice() {
        double moneyDk = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceDk(false));
        double moneyEuro = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceEuro(false));
        jL_BasketPriceDk.setText("" + moneyDk);
        jL_BasketPriceEuro.setText("" + moneyEuro);
    }

    public void showCashReg() {
        double moneyDk = numberFormatTools.getDoubleValue(moneyHandler.getCashRegister().getAmountDk());
        double moneyEuro = numberFormatTools.getDoubleValue(moneyHandler.getCashRegister().getAmountEuro());
        jL_dkAmount.setText("" + moneyDk);
        jL_euroAmount.setText("" + moneyEuro);
    }

    public void Museumscard() {
        discount = jToggleButton_member.isSelected();
        setPaymentAmount();
    }

    public void setPaymentAmount() {

        if (jToggleButton_dk.isSelected()) {
            double priceDk = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceDk(discount));
            jTextField_endprice.setText("DK: " + priceDk);
            penge = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceDk(discount));
        } else if (jToggleButton_euro.isSelected()) {
            double priceEuro = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceEuro(discount));
            jTextField_endprice.setText("EURO: " + priceEuro);
            penge = numberFormatTools.getDoubleValue(saleHandler.getCurrentSale().getEndpriceEuro(discount));
        } else {
            jTextField_endprice.setText("" + 0);
        }

    }

    public void setModtagetBeløb() {
        modtaget = "";
        if (!modtag.isEmpty()) {
            for (String string : modtag) {
                modtaget = modtaget + string;
                jButton_saleEnd.setEnabled(true);
            }
        }

        jTextField_payamount.setText(modtaget);
    }

    public void clearAll() {
        setPaymentAmount();
        showCashReg();
        setPrice();
        fillBasket();
        jP_product.removeAll();
        jP_type.removeAll();
        modtag.removeAll(modtag);
        modtaget = "";
        jB_endSale.setEnabled(false);
        setModtagetBeløb();
        jTextField_payback.setText("");
        

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
                if (jToggleButton_dk.isSelected()) {
                    int amount1 = (int) (modtagetTilBetaling * 100);
                    int amount2 = (int) (penge * 100);
                    retur = amount1 - amount2;
                    retur = retur / 100;
                    jTextField_payback.setText("Retur DK: " + retur);
                    dkOrEuro = "DK";

                } else if (jToggleButton_euro.isSelected()) {
                    int amount1 = (int) (modtagetTilBetaling * 100);
                    int amount2 = (int) (penge * 100);
                    retur = amount1 - amount2;
                    retur = retur / 100;
                    jTextField_payback.setText("Retur Euro: " + retur);
                    dkOrEuro = "EURO";
                }
                saleHandler.getCurrentSale().setEmployee(storeHandler.getLogEmployee());
                storeController.alterProductQuantities(saleHandler.getCurrentSale().getProductLine());
                saleController.endSale(saleHandler.getCurrentSale(), discount);

                jLabel_endError.setText("Betaling Godkendt:");
                int money = (int) (penge * 100);
                moneyHandler.addCashAmount("+", dkOrEuro, money);

                listeners.notifyListeners("End Sale");
                jButton_saleEnd.setEnabled(false);

                
                if (!jToggleButton_noKvit.isSelected()) {
//                    printHandler.kvitteringPrint(sale, discount);
                }
                saleHandler.newSale();
                timer.start();
            } else {
                jLabel_endError.setText("Beløb Ikke Nok!!");
            }

        } catch (NumberFormatException ex) {
            jLabel_endError.setText("Tjek indtastede beløb");
        }
        revalidate();
        repaint();

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
        jP_endSale = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton_dk = new javax.swing.JToggleButton();
        jToggleButton_euro = new javax.swing.JToggleButton();
        jToggleButton_member = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField_endprice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_payamount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_payback = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jToggleButton_noKvit = new javax.swing.JToggleButton();
        jButton_saleEnd = new javax.swing.JButton();
        jLabel_endError = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();

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
        jB_logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_logOutActionPerformed(evt);
            }
        });

        jB_closeRegisstre.setText("Luk Kassen:");
        jB_closeRegisstre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeRegisstreActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Betalings Type:");

        jToggleButton_dk.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jToggleButton_dk.setText("Danske Kroner:");
        jToggleButton_dk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_dkActionPerformed(evt);
            }
        });

        jToggleButton_euro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jToggleButton_euro.setText("Euro:");
        jToggleButton_euro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_euroActionPerformed(evt);
            }
        });

        jToggleButton_member.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jToggleButton_member.setText("Medlemskort:");
        jToggleButton_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_memberActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Beløb Til Betaling:");

        jTextField_endprice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Beløb Modtaget:");

        jTextField_payamount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_payamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_payamountActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Beløb Retur:");

        jTextField_payback.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("0");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setText(".");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setText("<-");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jToggleButton_noKvit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jToggleButton_noKvit.setText("Ingen Kvittering:");

        jButton_saleEnd.setText("GodKend:");
        jButton_saleEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saleEndActionPerformed(evt);
            }
        });

        jLabel_endError.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_endError.setText("jLabel7");

        jButton13.setText("Tilbage:");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_endSaleLayout = new javax.swing.GroupLayout(jP_endSale);
        jP_endSale.setLayout(jP_endSaleLayout);
        jP_endSaleLayout.setHorizontalGroup(
            jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_endSaleLayout.createSequentialGroup()
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_endSaleLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jP_endSaleLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton_noKvit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jP_endSaleLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_endError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jP_endSaleLayout.createSequentialGroup()
                                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField_payback, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleButton_member, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_endprice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleButton_dk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_endSaleLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jTextField_payamount, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jP_endSaleLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jToggleButton_euro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jP_endSaleLayout.createSequentialGroup()
                                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jP_endSaleLayout.createSequentialGroup()
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jP_endSaleLayout.createSequentialGroup()
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton_saleEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jP_endSaleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(372, Short.MAX_VALUE))
        );
        jP_endSaleLayout.setVerticalGroup(
            jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_endSaleLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton_euro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_dk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_member, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_endprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_payamount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_payback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_endSaleLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jToggleButton_noKvit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_endError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_endSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_saleEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jP_endSale, "card3");
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

    private void jB_endSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_endSaleActionPerformed
        showPage("endsale");
        jToggleButton_dk.setSelected(true);
        setPaymentAmount();
    }//GEN-LAST:event_jB_endSaleActionPerformed

    private void jToggleButton_euroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_euroActionPerformed
        jToggleButton_dk.setSelected(false);
        setPaymentAmount();
    }//GEN-LAST:event_jToggleButton_euroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modtag.add("1");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modtag.add("2");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modtag.add("3");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modtag.add("4");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modtag.add("5");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        modtag.add("6");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        modtag.add("7");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        modtag.add("8");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        modtag.add("9");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        modtag.add("0");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        modtag.add(".");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        modtag.remove(modtag.size() - 1);
        setModtagetBeløb();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jToggleButton_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_memberActionPerformed
        Museumscard();
    }//GEN-LAST:event_jToggleButton_memberActionPerformed

    private void jToggleButton_dkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_dkActionPerformed
        jToggleButton_euro.setSelected(false);
        setPaymentAmount();
    }//GEN-LAST:event_jToggleButton_dkActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        showPage("sale");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton_saleEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saleEndActionPerformed
        try {
            endSale();
        } catch (SQLException ex) {
            Logger.getLogger(SaleView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_saleEndActionPerformed

    private void jTextField_payamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_payamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_payamountActionPerformed

    private void jB_logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_logOutActionPerformed
      storeHandler.logOutEmployee();
        listeners.notifyListeners("LogOut");
    }//GEN-LAST:event_jB_logOutActionPerformed

    private void jB_closeRegisstreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeRegisstreActionPerformed
        
        listeners.notifyListeners("EndCashAndDay");
    }//GEN-LAST:event_jB_closeRegisstreActionPerformed


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
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_saleEnd;
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
    private javax.swing.JLabel jLabel_endError;
    private javax.swing.JPanel jP_Sale;
    private javax.swing.JPanel jP_basket;
    private javax.swing.JPanel jP_basketf;
    private javax.swing.JPanel jP_endSale;
    private javax.swing.JPanel jP_product;
    private javax.swing.JPanel jP_totalPrice;
    private javax.swing.JPanel jP_type;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_endprice;
    private javax.swing.JTextField jTextField_payamount;
    private javax.swing.JTextField jTextField_payback;
    private javax.swing.JToggleButton jToggleButton_dk;
    private javax.swing.JToggleButton jToggleButton_euro;
    private javax.swing.JToggleButton jToggleButton_member;
    private javax.swing.JToggleButton jToggleButton_noKvit;
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
