/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

import java.awt.CardLayout;
import model.Handler.*;
import utility.Listeners;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import print.PrintHandler;

public class Logon extends javax.swing.JPanel implements ActionListener {

    private String code;
    private StoreHandler storeHandler;
    private MoneyHandler moneyHandler;
    private PrintHandler printHandler;
    private Listeners listeners;
    private boolean dkCash;
    private boolean euroCash;
    private String dk;
    private String euro;
    private double dkc;
    private double euc;

    /**
     * Creates new form Logon
     */
    public Logon() {
        storeHandler = StoreHandler.storeHandler();
        moneyHandler = MoneyHandler.getMoneyHandler();
        listeners = Listeners.getList();
        printHandler = new PrintHandler();

        initComponents();
        code = "0";
        dk = "0";
        euro = "0";
        dkCash = true;
        euroCash = false;
        settextfield();
        setCashRegistreText();
        setSize(new Dimension(1008, 691));
        CardLayout cl = (CardLayout) getLayout();
        cl.addLayoutComponent(jP_logon, "Logon");
        cl.addLayoutComponent(jP_CashRegistre, "CashReg");
        cl.show(this, "Logon");
        code = "1421";
        settextfield();
    }

    public void showPage(String page) {
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, page);
    }

    public void setCode(String tal) {
        if (code == "0") {
            code = tal;
        } else {
            code = code + tal;
        }
        settextfield();
    }

    public void settextfield() {
        jTextField_kode.setText("Kode: " + code);
    }

    public boolean openOrClose() {
        boolean open = moneyHandler.cashRegister();
        return open;
    }

    public void setcash(String tal) {
        if (dkCash) {
            dk = dk + tal;
        } else if (!dkCash) {
            euro = euro + tal;
            euroCash = true;
        }
        setText();
        setCashRegistreText();
    }

    public void setText() {
        dkc = Double.parseDouble(dk);
        euc = Double.parseDouble(euro);
        if (dkCash) {
            jTextField_DkCash.setText("Total: " + dkc);
        } else if (!dkCash) {
            jTextField_Eurocash.setText("Total: " + euc);
        }
    }

    public void endReg() {
        if (dkCash) {
            if (dkc != 0) {
                dkCash = false;
                setText();
            }
        } else if (!dkCash && euroCash) {
            if (euc != 0) {
                int dkMoney = (int) (dkc * 100);
                int euroMoney = (int) (euc * 100);
                setCashRegistreText();
                if (!openOrClose()) {
                    dkMoney = (int) (dkc * 100);
                    euroMoney = (int) (euc * 100);
                    moneyHandler.setCashRegistre(dkMoney, euroMoney);
                    listeners.notifyListeners("LogAndCashOk");
                } else {
                    moneyHandler.endCashRegister(dkMoney, euroMoney, storeHandler.getLogEmployee());
                    if (jCheckBox_kvit.isSelected()) {
                        printHandler.cashReport();
                    }
                    storeHandler.logOutEmployee();
                    listeners.notifyListeners("LogOut");
                }
            }
        }
    }

    public void setCashRegistreText() {
        if (openOrClose()) {
            jLabel_overskrift.setText("Luk Kassen");
            double dkReg = moneyHandler.getCashRegister().getAmountDk() / 100;
            double euroReg = moneyHandler.getCashRegister().getAmountEuro() / 100;
            double difDk = (dkc * 100) - moneyHandler.getCashRegister().getAmountDk();
            double difeuro = (euc * 100) - moneyHandler.getCashRegister().getAmountEuro();
            difDk = difDk / 100;
            difeuro = difeuro / 100;
            jTextField_dkRegistre.setText("" + dkReg);
            jTextField_EuroRegistre.setText("" + euroReg);
            jTextField_diffDk.setText("" + difDk);
            jTextField_diffEuro.setText("" + difeuro);
            jButton_end.setText("Luk Kassen");
            jCheckBox_kvit.setVisible(true);
            jTextField_dkRegistre.setVisible(true);
            jLabel_regCashEuro.setVisible(true);
            jTextField_EuroRegistre.setVisible(true);
            jLabel_rigCash.setVisible(true);
            jTextField_diffDk.setVisible(true);
            jLabel_diff.setVisible(true);
            jTextField_diffEuro.setVisible(true);
            jLabel_diffEoru.setVisible(true);
            jButton_return.setVisible(true);
        } else {
            jLabel_overskrift.setText("Åben Kassen");
            jButton_end.setText("Åben Kassen");
           jButton_return.setVisible(false);
            jCheckBox_kvit.setSelected(false);
            jTextField_dkRegistre.setVisible(false);
            jLabel_regCashEuro.setVisible(false);
            jTextField_EuroRegistre.setVisible(false);
            jLabel_rigCash.setVisible(false);
            jTextField_diffDk.setVisible(false);
            jLabel_diff.setVisible(false);
            jTextField_diffEuro.setVisible(false);
            jLabel_diffEoru.setVisible(false);
            jCheckBox_kvit.setVisible(false);
        }
        repaint();
    }

    public void clearCashPage() {
        jTextField_DkCash.setText("");
        jTextField_EuroRegistre.setText("");
        jTextField_Eurocash.setText("");
        jTextField_diffDk.setText("");
        jTextField_diffEuro.setText("");
        jTextField_dkRegistre.setText("");
        jTextField_kode.setText("");
        dk = "0";
        euro = "0";
        dkCash = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_logon = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField_kode = new javax.swing.JTextField();
        jButton_2 = new javax.swing.JButton();
        jButton_1 = new javax.swing.JButton();
        jButton_9 = new javax.swing.JButton();
        jButton_5 = new javax.swing.JButton();
        jButton_3 = new javax.swing.JButton();
        jButton_4 = new javax.swing.JButton();
        jButton_6 = new javax.swing.JButton();
        jButton_7 = new javax.swing.JButton();
        jButton_8 = new javax.swing.JButton();
        jButton_0 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton_Logon = new javax.swing.JButton();
        jP_CashRegistre = new javax.swing.JPanel();
        jTextField_DkCash = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel_regCashEuro = new javax.swing.JLabel();
        jTextField_EuroRegistre = new javax.swing.JTextField();
        jLabel_diff = new javax.swing.JLabel();
        jLabel_diffEoru = new javax.swing.JLabel();
        jTextField_diffDk = new javax.swing.JTextField();
        jTextField_diffEuro = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton_end = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jCheckBox_kvit = new javax.swing.JCheckBox();
        jTextField_Eurocash = new javax.swing.JTextField();
        jTextField_dkRegistre = new javax.swing.JTextField();
        jLabel_rigCash = new javax.swing.JLabel();
        jLabel_overskrift = new javax.swing.JLabel();
        jButton_return = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        jP_logon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Log På Systemmet. Skriv Kode", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        jTextField_kode.setEditable(false);
        jTextField_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_kodeActionPerformed(evt);
            }
        });

        jButton_2.setText("2");
        jButton_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_2ActionPerformed(evt);
            }
        });

        jButton_1.setText("1");
        jButton_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_1ActionPerformed(evt);
            }
        });

        jButton_9.setText("9");
        jButton_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_9ActionPerformed(evt);
            }
        });

        jButton_5.setText("5");
        jButton_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_5ActionPerformed(evt);
            }
        });

        jButton_3.setText("3");
        jButton_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_3ActionPerformed(evt);
            }
        });

        jButton_4.setText("4");
        jButton_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_4ActionPerformed(evt);
            }
        });

        jButton_6.setText("6");
        jButton_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_6ActionPerformed(evt);
            }
        });

        jButton_7.setText("7");
        jButton_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_7ActionPerformed(evt);
            }
        });

        jButton_8.setText("8");
        jButton_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_8ActionPerformed(evt);
            }
        });

        jButton_0.setText("0");
        jButton_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_0ActionPerformed(evt);
            }
        });

        jButton1.setText("Ryd Felt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_Logon.setText("Godkend");
        jButton_Logon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_0, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton_8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Logon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_kode))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_0, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Logon, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jP_logonLayout = new javax.swing.GroupLayout(jP_logon);
        jP_logon.setLayout(jP_logonLayout);
        jP_logonLayout.setHorizontalGroup(
            jP_logonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_logonLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(429, Short.MAX_VALUE))
        );
        jP_logonLayout.setVerticalGroup(
            jP_logonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_logonLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        add(jP_logon, "card2");

        jTextField_DkCash.setEditable(false);
        jTextField_DkCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_DkCashMouseClicked(evt);
            }
        });
        jTextField_DkCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_DkCashActionPerformed(evt);
            }
        });

        jLabel2.setText("Euro I Kassen:");

        jLabel1.setText("Dk I Kassen:");

        jButton11.setText(".");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setText("0");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("2");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton12.setText("3");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel_regCashEuro.setText("Kassens Indhold Euro");

        jTextField_EuroRegistre.setEditable(false);

        jLabel_diff.setText("Differencen Dk");

        jLabel_diffEoru.setText("Differencen Euro");

        jTextField_diffDk.setEditable(false);

        jTextField_diffEuro.setEditable(false);

        jButton13.setText("1");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton_end.setText("Luk Kassen");
        jButton_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_endActionPerformed(evt);
            }
        });

        jButton14.setText("->");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jCheckBox_kvit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jCheckBox_kvit.setText("Udskriv Rapport");
        jCheckBox_kvit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_kvitActionPerformed(evt);
            }
        });

        jTextField_Eurocash.setEditable(false);
        jTextField_Eurocash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_EurocashMouseClicked(evt);
            }
        });
        jTextField_Eurocash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_EurocashActionPerformed(evt);
            }
        });

        jTextField_dkRegistre.setEditable(false);

        jLabel_rigCash.setText("Kassens Indhold Dk");

        jLabel_overskrift.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel_overskrift.setText("jLabel7");

        jButton_return.setText("Tilbage");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_CashRegistreLayout = new javax.swing.GroupLayout(jP_CashRegistre);
        jP_CashRegistre.setLayout(jP_CashRegistreLayout);
        jP_CashRegistreLayout.setHorizontalGroup(
            jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                            .addGap(244, 244, 244)
                            .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                    .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_DkCash, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField_Eurocash, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                            .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGap(18, 18, 18)
                            .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                                    .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField_diffDk)
                                        .addComponent(jLabel_diff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_dkRegistre, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_rigCash, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel_regCashEuro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_EuroRegistre)
                                        .addComponent(jTextField_diffEuro)
                                        .addComponent(jLabel_diffEoru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jCheckBox_kvit, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                            .addGap(362, 362, 362)
                            .addComponent(jButton_end, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_CashRegistreLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel_overskrift, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_return, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(306, 306, 306))
        );
        jP_CashRegistreLayout.setVerticalGroup(
            jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel_overskrift, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_rigCash)
                            .addComponent(jLabel_regCashEuro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_dkRegistre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_EuroRegistre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_diff)
                            .addComponent(jLabel_diffEoru))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_diffDk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_diffEuro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(jCheckBox_kvit))
                    .addGroup(jP_CashRegistreLayout.createSequentialGroup()
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_DkCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Eurocash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_CashRegistreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton_end, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jButton_return, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jP_CashRegistre, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_2ActionPerformed
        setCode("2");
    }//GEN-LAST:event_jButton_2ActionPerformed

    private void jButton_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_1ActionPerformed
        setCode("1");
    }//GEN-LAST:event_jButton_1ActionPerformed

    private void jButton_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_9ActionPerformed
        setCode("9");
    }//GEN-LAST:event_jButton_9ActionPerformed

    private void jButton_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_5ActionPerformed
        setCode("5");
    }//GEN-LAST:event_jButton_5ActionPerformed

    private void jButton_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_3ActionPerformed
        setCode("3");
    }//GEN-LAST:event_jButton_3ActionPerformed

    private void jButton_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_4ActionPerformed
        setCode("4");
    }//GEN-LAST:event_jButton_4ActionPerformed

    private void jButton_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_6ActionPerformed
        setCode("6");
    }//GEN-LAST:event_jButton_6ActionPerformed

    private void jButton_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_7ActionPerformed
        setCode("7");
    }//GEN-LAST:event_jButton_7ActionPerformed

    private void jButton_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_8ActionPerformed
        setCode("8");
    }//GEN-LAST:event_jButton_8ActionPerformed

    private void jButton_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_0ActionPerformed
        setCode("0");
    }//GEN-LAST:event_jButton_0ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        code = "0";
        settextfield();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_LogonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LogonActionPerformed
        int numberCode = Integer.parseInt(code);
        code = "0";
        settextfield();
        clearCashPage();
        if (storeHandler.employeeLogin(numberCode)) {
            if (!moneyHandler.cashRegister()) {
                showPage("CashReg");
                setText();
            } else {
                listeners.notifyListeners("LogAndCashOk");
            }
        } else {
            jTextField_kode.setText("Forkert Kode!!");
        }
    }//GEN-LAST:event_jButton_LogonActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        setcash(".");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        setcash("0");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        setcash("9");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        setcash("8");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        setcash("7");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        setcash("6");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setcash("4");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setcash("5");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setcash("2");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        setcash("3");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        setcash("1");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_endActionPerformed
        endReg();
        setCashRegistreText();
    }//GEN-LAST:event_jButton_endActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        endReg();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_kodeActionPerformed

    private void jTextField_DkCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_DkCashActionPerformed

    }//GEN-LAST:event_jTextField_DkCashActionPerformed

    private void jTextField_EurocashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_EurocashActionPerformed

    }//GEN-LAST:event_jTextField_EurocashActionPerformed

    private void jTextField_DkCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_DkCashMouseClicked
        dkCash = true;
        dk = "0";
    }//GEN-LAST:event_jTextField_DkCashMouseClicked

    private void jTextField_EurocashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_EurocashMouseClicked
        dkCash = false;
        euro = "0";
    }//GEN-LAST:event_jTextField_EurocashMouseClicked

    private void jCheckBox_kvitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_kvitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_kvitActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        listeners.notifyListeners("Show Sale");
    }//GEN-LAST:event_jButton_returnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_0;
    private javax.swing.JButton jButton_1;
    private javax.swing.JButton jButton_2;
    private javax.swing.JButton jButton_3;
    private javax.swing.JButton jButton_4;
    private javax.swing.JButton jButton_5;
    private javax.swing.JButton jButton_6;
    private javax.swing.JButton jButton_7;
    private javax.swing.JButton jButton_8;
    private javax.swing.JButton jButton_9;
    private javax.swing.JButton jButton_Logon;
    private javax.swing.JButton jButton_end;
    private javax.swing.JButton jButton_return;
    private javax.swing.JCheckBox jCheckBox_kvit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_diff;
    private javax.swing.JLabel jLabel_diffEoru;
    private javax.swing.JLabel jLabel_overskrift;
    private javax.swing.JLabel jLabel_regCashEuro;
    private javax.swing.JLabel jLabel_rigCash;
    private javax.swing.JPanel jP_CashRegistre;
    private javax.swing.JPanel jP_logon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_DkCash;
    private javax.swing.JTextField jTextField_EuroRegistre;
    private javax.swing.JTextField jTextField_Eurocash;
    private javax.swing.JTextField jTextField_diffDk;
    private javax.swing.JTextField jTextField_diffEuro;
    private javax.swing.JTextField jTextField_dkRegistre;
    private javax.swing.JTextField jTextField_kode;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "LogOut":
                showPage("Logon");

                break;
            case "Log":

                break;
        }
    }
}
