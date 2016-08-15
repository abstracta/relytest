/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package relytest.ui.forms;

import static javax.swing.JOptionPane.showMessageDialog;
import relytest.interfaces.IConfigFormLoad;
import relytest.internationalization.LanguageController;
import relytest.internationalization.Texts;
import relytest.ui.Constants;
import relytest.ui.PropertiesMgr;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class ConfigForm extends javax.swing.JFrame {

    private static IConfigFormLoad _loadConfigForm;
    private final PropertiesMgr p;

    /**
     * Creates new form ConfigForm
     *
     * @param loadForm
     */
    public ConfigForm(IConfigFormLoad loadForm) {
        this.p = new PropertiesMgr();
        initComponents();
        _loadConfigForm = loadForm;
        loadValues();
        loadLanguage();
        jButtonLaF.setVisible(false);
    }

    private void loadLanguage() {
        LanguageController lCon = new LanguageController();
        jLabelName.setText(lCon.getValue(Texts.ConfigForm_jLabelName));
        jCheckBoxOpenImageEditor.setText(lCon.getValue(Texts.ConfigForm_jCheckBoxOpenImageEditor));
        jCheckBoxHideRelyTest.setText(lCon.getValue(Texts.ConfigForm_jCheckBoxHideRelyTest));
        jCheckBoxTakePicAfterBug.setText(lCon.getValue(Texts.ConfigForm_jCheckBoxTakePicAfterBug));
        jCheckBoxLaunchBrowserAfterCharterEnds.setText(lCon.getValue(Texts.ConfigForm_jCheckBoxLaunchBrowserAfterCharterEnds));
        jCheckBoxConfirmStopCharter.setText(lCon.getValue(Texts.ConfigForm_jCheckBoxConfirmStopCharter));
        jLabelShortTimeValue.setText(lCon.getValue(Texts.ConfigForm_jLabelShortTimeValue));
        jLabelMediumTimeValue.setText(lCon.getValue(Texts.ConfigForm_jLabelMediumTimeValue));
        jLabelLongTimeValue.setText(lCon.getValue(Texts.ConfigForm_jLabelLongTimeValue));
        jButtonSave.setText(lCon.getValue(Texts.ConfigForm_jButtonSave));
        jCheckBoxConfirmExitRelyTest.setText(lCon.getValue(Texts.ConfigForm_jCheckBoxConfirmExitRelyTest));
        setTitle(lCon.getValue(Texts.ConfigForm_Title));
    }

    private void loadValues() {

        String name = p.getValue(Constants.KEY_NAME);
        Boolean open = Boolean.valueOf(p.getValue(Constants.KEY_OPEN_IMAGE_EDITOR));
        jCheckBoxOpenImageEditor.setSelected(open);
        jTextFieldName.setText(name);
        Boolean hide = Boolean.valueOf(p.getValue(Constants.KEY_HIDE_RELY_TEST));
        jCheckBoxHideRelyTest.setSelected(hide);

        Boolean takePic = Boolean.valueOf(p.getValue(Constants.KEY_TAKE_PICTURE_AFTER_BUG));
        jCheckBoxTakePicAfterBug.setSelected(takePic);

        Boolean confirm = Boolean.valueOf(p.getValue(Constants.KEY_CONFIRM_STOP_CHARTER));
        jCheckBoxConfirmStopCharter.setSelected(confirm);

        confirm = Boolean.valueOf(p.getValue(Constants.KEY_CONFIRM_EXIT_RELYTEST));
        jCheckBoxConfirmExitRelyTest.setSelected(confirm);

        Boolean launchBrowser = Boolean.valueOf(p.getValue(Constants.KEY_LAUNCH_BROWSER));
        jCheckBoxLaunchBrowserAfterCharterEnds.setSelected(launchBrowser);

        String value = p.getValue(Constants.KEY_SHORT_TIME);
        Integer i = Integer.parseInt(value);
        jSpinnerShort.setValue(i);
        i = Integer.parseInt(p.getValue(Constants.KEY_LONG_TIME));
        jSpinnerLong.setValue(i);

        i = Integer.parseInt(p.getValue(Constants.KEY_MEDIUM_TIME));
        jSpinnerMedium.setValue(i);
    }

    private void save() {
        if (jTextFieldName.getText().equals("")) {
            showMessageDialog(this, "Please insert the name of the user.");
        } else {

            p.setValue(Constants.KEY_NAME, jTextFieldName.getText());
            Boolean open = jCheckBoxOpenImageEditor.isSelected();
            p.setValue(Constants.KEY_OPEN_IMAGE_EDITOR, open.toString());
            Boolean hideRelyTest = jCheckBoxHideRelyTest.isSelected();
            p.setValue(Constants.KEY_HIDE_RELY_TEST, hideRelyTest.toString());

            Boolean takePic = jCheckBoxTakePicAfterBug.isSelected();
            p.setValue(Constants.KEY_TAKE_PICTURE_AFTER_BUG, takePic.toString());

            Boolean confirm = jCheckBoxConfirmStopCharter.isSelected();
            p.setValue(Constants.KEY_CONFIRM_STOP_CHARTER, confirm.toString());

            confirm = jCheckBoxConfirmExitRelyTest.isSelected();
            p.setValue(Constants.KEY_CONFIRM_EXIT_RELYTEST, confirm.toString());

            Boolean launchBrowser = jCheckBoxLaunchBrowserAfterCharterEnds.isSelected();
            p.setValue(Constants.KEY_LAUNCH_BROWSER, launchBrowser.toString());

            p.setValue(Constants.KEY_SHORT_TIME, jSpinnerShort.getValue().toString());
            p.setValue(Constants.KEY_LONG_TIME, jSpinnerLong.getValue().toString());
            p.setValue(Constants.KEY_MEDIUM_TIME, jSpinnerMedium.getValue().toString());

            _loadConfigForm.loadTimes();
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
        jLabelMediumTimeValue = new javax.swing.JLabel();
        jSpinnerMedium = new javax.swing.JSpinner();
        jCheckBoxTakePicAfterBug = new javax.swing.JCheckBox();
        jCheckBoxConfirmStopCharter = new javax.swing.JCheckBox();
        jCheckBoxConfirmExitRelyTest = new javax.swing.JCheckBox();
        jCheckBoxLaunchBrowserAfterCharterEnds = new javax.swing.JCheckBox();

        setTitle("RelyTest - Configuration");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(449, 379));
        setName("frameConfiguration"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(223, 223, 223));
        jPanel1.setMaximumSize(new java.awt.Dimension(440, 368));
        jPanel1.setMinimumSize(new java.awt.Dimension(440, 368));

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jLabelName.setText("Name:");

        jCheckBoxOpenImageEditor.setBackground(new java.awt.Color(223, 223, 223));
        jCheckBoxOpenImageEditor.setText("Open image editor after a picture is taken");

        jCheckBoxHideRelyTest.setBackground(new java.awt.Color(223, 223, 223));
        jCheckBoxHideRelyTest.setText("Hide RelyTest when taking a picture");

        jButtonLaF.setText("Look & Feel");
        jButtonLaF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLaFActionPerformed(evt);
            }
        });

        jLabelShortTimeValue.setText("Short Time value");

        jLabelLongTimeValue.setText("Long Time value");

        jSpinnerShort.setModel(new javax.swing.SpinnerNumberModel(15, 1, 99, 1));

        jSpinnerLong.setModel(new javax.swing.SpinnerNumberModel(30, 1, 199, 1));

        jLabelMediumTimeValue.setText("Medium Time value");

        jSpinnerMedium.setModel(new javax.swing.SpinnerNumberModel(30, 1, 199, 1));

        jCheckBoxTakePicAfterBug.setBackground(new java.awt.Color(223, 223, 223));
        jCheckBoxTakePicAfterBug.setText("Take picture after adding a Bug");

        jCheckBoxConfirmStopCharter.setBackground(new java.awt.Color(223, 223, 223));
        jCheckBoxConfirmStopCharter.setText("Confirm to Stop Charter");

        jCheckBoxConfirmExitRelyTest.setBackground(new java.awt.Color(223, 223, 223));
        jCheckBoxConfirmExitRelyTest.setText("Confirm to Finish RelyTest");

        jCheckBoxLaunchBrowserAfterCharterEnds.setBackground(new java.awt.Color(223, 223, 223));
        jCheckBoxLaunchBrowserAfterCharterEnds.setText("Launch Browser At End");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonLaF)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxHideRelyTest)
                            .addComponent(jCheckBoxOpenImageEditor)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelShortTimeValue)
                                    .addComponent(jLabelLongTimeValue)
                                    .addComponent(jLabelMediumTimeValue))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinnerShort)
                                    .addComponent(jSpinnerMedium)
                                    .addComponent(jSpinnerLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jCheckBoxTakePicAfterBug))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxConfirmStopCharter)
                            .addComponent(jCheckBoxLaunchBrowserAfterCharterEnds)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonSave)
                                .addComponent(jCheckBoxConfirmExitRelyTest)))))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxOpenImageEditor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxHideRelyTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxTakePicAfterBug))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jCheckBoxLaunchBrowserAfterCharterEnds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxConfirmStopCharter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxConfirmExitRelyTest)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelShortTimeValue)
                    .addComponent(jSpinnerShort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMediumTimeValue)
                    .addComponent(jSpinnerMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLongTimeValue)
                    .addComponent(jSpinnerLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                new ConfigForm(_loadConfigForm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLaF;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxConfirmExitRelyTest;
    private javax.swing.JCheckBox jCheckBoxConfirmStopCharter;
    private javax.swing.JCheckBox jCheckBoxHideRelyTest;
    private javax.swing.JCheckBox jCheckBoxLaunchBrowserAfterCharterEnds;
    private javax.swing.JCheckBox jCheckBoxOpenImageEditor;
    private javax.swing.JCheckBox jCheckBoxTakePicAfterBug;
    private javax.swing.JLabel jLabelLongTimeValue;
    private javax.swing.JLabel jLabelMediumTimeValue;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelShortTimeValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinnerLong;
    private javax.swing.JSpinner jSpinnerMedium;
    private javax.swing.JSpinner jSpinnerShort;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
