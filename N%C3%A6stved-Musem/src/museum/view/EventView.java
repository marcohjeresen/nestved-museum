/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum.view;

import java.awt.Dimension;
import model.*;
import model.Handler.SaleHandler;
import model.Handler.StoreHandler;


/**
 *
 * @author MarcoPc
 */
public class EventView extends javax.swing.JPanel {

    private EventType eventType;
    private StoreHandler storeHandler;
    private SaleHandler saleHandler;
    private boolean customer;
    private boolean day;
    private boolean month;
    private boolean year;
    private boolean time;
    private boolean numberOfAdults;
    private boolean numberOfChildren;
    private boolean next;
    private String theNumber;

    /**
     * Creates new form EventView
     */
    public EventView(EventType eventType) {
        this.eventType = eventType;
        saleHandler = SaleHandler.getSaleHandler();
        setSize(new Dimension(238,410));
        initComponents();
        theNumber = "";
        customer = true;
        day = false;
        month = false;
        year = false;
        time = false;
        numberOfAdults = false;
        numberOfChildren = false;
        next = false;
        jButton11.setEnabled(false);
        jButton_ok.setEnabled(false);
        setPlace();
    }

    public void setPlace() {
        if (eventType.getId() == 3 || eventType.getId() == 4 || eventType.getId() == 7) {
            jTextField_sted.setText("");
        } else {
            jTextField_sted.setText("På museumet");
            jTextField_sted.setEditable(false);
        }
    }

    public void setText(String tal) {
        jButton_ok.setEnabled(false);
        theNumber = theNumber + tal;
        if (customer) {
            jTextField_kundenummer.setText(theNumber);
            if (theNumber.length() == 8) {
                customer = false;
                day = true;
                theNumber = "";
            }
        } else if (day) {
            jTextField_dag.setText(theNumber);
            if (theNumber.length() == 2) {
                day = false;
                month = true;
                theNumber = "";
            }
        } else if (month) {
            jTextField_måned.setText(theNumber);
            if (theNumber.length() == 2) {
                month = false;
                year = true;
                theNumber = "";
            }
        } else if (year) {
            jTextField_år.setText(theNumber);
            if (theNumber.length() == 4) {
                year = false;
                time = true;
                theNumber = "";
            }
        } else if (time) {
            jTextField_tid.setText(theNumber);
            if (theNumber.length() == 2) {
                theNumber = theNumber + ":";
                jTextField_tid.setText(theNumber);
            } else if (theNumber.length() == 5) {
                time = false;
                numberOfAdults = true;
                theNumber = "";
            }
        } else if (numberOfAdults) {
            jTextField_antalWok.setText(theNumber);
            jButton11.setEnabled(true);
            if (next) {
                numberOfAdults = false;
                numberOfChildren = true;
                theNumber = "";
                next = false;
            }
        } else if (numberOfChildren) {
            jTextField_antalBørn.setText(theNumber);
            jButton11.setEnabled(true);
            if (next) {
                numberOfChildren = false;
                theNumber = "";
                next = false;
            }
        }
        setOkbuttom();
    }

    public void changeText(String textfield) {
        jButton_ok.setEnabled(false);
        customer = false;
        day = false;
        month = false;
        year = false;
        time = false;
        numberOfAdults = false;
        numberOfChildren = false;
        next = false;
        theNumber = "";
        switch (textfield) {
            case "Kunde":
                customer = true;
                setText("");
                break;
            case "Dag":
                day = true;
                setText("");
                break;
            case "Måned":
                month = true;
                setText("");
                break;
            case "År":
                year = true;
                setText("");
                break;
            case "Tid":
                time = true;
                setText("");
                break;
            case "Antalvoksne":
                numberOfAdults = true;
                setText("");
                break;
            case "Antalbørn":
                numberOfChildren = true;
                setText("");
                break;
        }
    }
    public void setOkbuttom(){
        if (!jTextField_sted.getText().equals("") && !jTextField_kundenummer.getText().equals("")) {
            if (!jTextField_dag.getText().equals("") && !jTextField_måned.getText().equals("")) {
                if (!jTextField_år.getText().equals("") && !jTextField_tid.getText().equals("")) {
                    if (!jTextField_antalWok.getText().equals("") && !jTextField_antalBørn.getText().equals("")) {
                        jButton_ok.setEnabled(true);
                    }
                }
            }
        }
    }

    public void addEventLine() {
        String place = jTextField_sted.getText();
        if (!place.equals("") && place.length() > 4) {
            String date = jTextField_år.getText() + "-" + jTextField_måned.getText() + "-" + jTextField_dag.getText() + " " + jTextField_tid.getText() + ":00";
            int amount = Integer.parseInt(jTextField_antalBørn.getText()) + Integer.parseInt(jTextField_antalWok.getText());
            int customerNumber = Integer.parseInt(jTextField_kundenummer.getText());
            saleHandler.getCurrentSale().addEventLine(eventType, amount, customerNumber, place, date);
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

        jTextField6 = new javax.swing.JTextField();
        jTextField_sted = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_dag = new javax.swing.JTextField();
        jTextField_måned = new javax.swing.JTextField();
        jTextField_år = new javax.swing.JTextField();
        jTextField_tid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_antalWok = new javax.swing.JTextField();
        jTextField_antalBørn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
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
        jButton_ok = new javax.swing.JButton();
        jButton_fortryd = new javax.swing.JButton();
        jTextField_kundenummer = new javax.swing.JTextField();

        jTextField6.setText("jTextField6");

        jLabel1.setText("Event Afholdnings Sted:");

        jLabel2.setText("Kunde Tlf Nummer:");

        jTextField_dag.setEditable(false);
        jTextField_dag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_dagMouseClicked(evt);
            }
        });

        jTextField_måned.setEditable(false);
        jTextField_måned.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_månedMouseClicked(evt);
            }
        });
        jTextField_måned.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_månedActionPerformed(evt);
            }
        });

        jTextField_år.setEditable(false);
        jTextField_år.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_årMouseClicked(evt);
            }
        });

        jTextField_tid.setEditable(false);
        jTextField_tid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_tidMouseClicked(evt);
            }
        });

        jLabel3.setText("Dag:            Måned:      År:              Tid:");

        jLabel4.setText("Antal Voksne:");

        jTextField_antalWok.setEditable(false);
        jTextField_antalWok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_antalWokMouseClicked(evt);
            }
        });

        jTextField_antalBørn.setEditable(false);
        jTextField_antalBørn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_antalBørnMouseClicked(evt);
            }
        });
        jTextField_antalBørn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_antalBørnActionPerformed(evt);
            }
        });

        jLabel5.setText("Antal Børn:");

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("0");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("->");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton_ok.setText("Ok");
        jButton_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okActionPerformed(evt);
            }
        });

        jButton_fortryd.setText("Fortyd");
        jButton_fortryd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_fortrydActionPerformed(evt);
            }
        });

        jTextField_kundenummer.setEditable(false);
        jTextField_kundenummer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_kundenummerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_antalWok, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_antalBørn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_dag, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_måned, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_år, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_tid))
                    .addComponent(jTextField_sted)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton_fortryd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_kundenummer)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jTextField_sted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_kundenummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_dag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_måned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_år, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_antalWok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_antalBørn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_fortryd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_månedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_månedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_månedActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        next = true;
        setText("");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setText("1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setText("2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setText("3");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setText("4");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setText("5");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        setText("6");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        setText("7");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        setText("8");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        setText("9");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        setText("0");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okActionPerformed
        addEventLine();
    }//GEN-LAST:event_jButton_okActionPerformed

    private void jButton_fortrydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_fortrydActionPerformed
        theNumber = "";
        setText("");
    }//GEN-LAST:event_jButton_fortrydActionPerformed

    private void jTextField_dagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_dagMouseClicked
        changeText("Dag");
    }//GEN-LAST:event_jTextField_dagMouseClicked

    private void jTextField_månedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_månedMouseClicked
        changeText("Måned");
    }//GEN-LAST:event_jTextField_månedMouseClicked

    private void jTextField_årMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_årMouseClicked
        changeText("År");
    }//GEN-LAST:event_jTextField_årMouseClicked

    private void jTextField_tidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_tidMouseClicked
        changeText("Tid");
    }//GEN-LAST:event_jTextField_tidMouseClicked

    private void jTextField_antalWokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_antalWokMouseClicked
        changeText("Antalvoksne");
    }//GEN-LAST:event_jTextField_antalWokMouseClicked

    private void jTextField_antalBørnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_antalBørnMouseClicked
        changeText("Antalbørn");
    }//GEN-LAST:event_jTextField_antalBørnMouseClicked

    private void jTextField_antalBørnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_antalBørnActionPerformed
//         TODO add your handling code here:
    }//GEN-LAST:event_jTextField_antalBørnActionPerformed

    private void jTextField_kundenummerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_kundenummerMouseClicked
        changeText("Kunde");
    }//GEN-LAST:event_jTextField_kundenummerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_fortryd;
    private javax.swing.JButton jButton_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField_antalBørn;
    private javax.swing.JTextField jTextField_antalWok;
    private javax.swing.JTextField jTextField_dag;
    private javax.swing.JTextField jTextField_kundenummer;
    private javax.swing.JTextField jTextField_måned;
    private javax.swing.JTextField jTextField_sted;
    private javax.swing.JTextField jTextField_tid;
    private javax.swing.JTextField jTextField_år;
    // End of variables declaration//GEN-END:variables
}
