/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class ConfigForm extends javax.swing.JFrame {

    private static MainForm _mainForm;
    private PropertiesMgr p = new PropertiesMgr();

    /**
     * Creates new form ConfigForm
     * @param mainForm
     */
    public ConfigForm(MainForm mainForm) {
        initComponents();
        _mainForm = mainForm;
        loadValues();
        jButtonLaF.setVisible(false);
    }

    private void loadValues() {
       
        String name = p.getValue(Constants.Key_Name);
        Boolean open = Boolean.valueOf(p.getValue(Constants.Key_OpenImageEditor));
        jCheckBoxOpenImageEditor.setSelected(open);
        jTextFieldName.setText(name);
        Boolean hide = Boolean.valueOf(p.getValue(Constants.Key_HideRelyTest));
        jCheckBoxHideRelyTest.setSelected(hide);
        
        String value = p.getValue(Constants.Key_ShortTime);
        Integer i = Integer.parseInt(value);
        jSpinnerShort.setValue(i);
         i = Integer.parseInt( p.getValue(Constants.Key_LongTime));
         jSpinnerLong.setValue(i);
    }

    private void save() {
        if (jTextFieldName.getText().equals("")) {
            showMessageDialog(this, "Please insert the name of the user.");
        } else {
           
            p.setValue(Constants.Key_Name, jTextFieldName.getText());
            Boolean open = jCheckBoxOpenImageEditor.isSelected();
            p.setValue(Constants.Key_OpenImageEditor, open.toString());
            Boolean hideRelyTest = jCheckBoxHideRelyTest.isSelected();
            p.setValue(Constants.Key_HideRelyTest, hideRelyTest.toString());
        
            p.setValue(Constants.Key_ShortTime,  jSpinnerShort.getValue().toString());
            p.setValue(Constants.Key_LongTime,  jSpinnerLong.getValue().toString());
            this.setVisible(false);
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

        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jCheckBoxOpenImageEditor = new javax.swing.JCheckBox();
        jCheckBoxHideRelyTest = new javax.swing.JCheckBox();
        jButtonLaF = new javax.swing.JButton();
        jLabelShortTimeValue = new javax.swing.JLabel();
        jLabelLongTimeValue = new javax.swing.JLabel();
        jSpinnerShort = new javax.swing.JSpinner();
        jSpinnerLong = new javax.swing.JSpinner();

        setTitle("RelyTest - Configuration");
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(400, 184));
        setMinimumSize(new java.awt.Dimension(400, 184));
        setName("frameConfiguration"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jLabelName.setText("Name:");

        jCheckBoxOpenImageEditor.setText("Open image editor after a picture is taken");

        jCheckBoxHideRelyTest.setText("Hide RelyTest when taking a picture");

        jButtonLaF.setText("Look & Feel");
        jButtonLaF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLaFActionPerformed(evt);
            }
        });

        jLabelShortTimeValue.setText("Short Time value");

        jLabelLongTimeValue.setText("Long Time value");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonLaF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSave))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxHideRelyTest)
                            .addComponent(jCheckBoxOpenImageEditor)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelShortTimeValue)
                                    .addComponent(jLabelLongTimeValue))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinnerShort, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(jSpinnerLong))))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelName)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jCheckBoxOpenImageEditor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxHideRelyTest)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelShortTimeValue))
                    .addComponent(jSpinnerShort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLongTimeValue)
                    .addComponent(jSpinnerLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonLaF))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonLaFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLaFActionPerformed
        // TODO add your handling code here:
        LookAndFeelForm laf = new LookAndFeelForm();
              laf.setLocationRelativeTo(null);  
              this.setVisible(false);
              laf.setVisible(true);
    }//GEN-LAST:event_jButtonLaFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ConfigForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConfigForm(_mainForm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLaF;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxHideRelyTest;
    private javax.swing.JCheckBox jCheckBoxOpenImageEditor;
    private javax.swing.JLabel jLabelLongTimeValue;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelShortTimeValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinnerLong;
    private javax.swing.JSpinner jSpinnerShort;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
