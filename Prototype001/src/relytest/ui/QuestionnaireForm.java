/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ButtonGroup;
import relytest.ui.common.CharterDto;
import relytest.ui.common.QuestionnaireDto;
import relytest.ui.common.StartBrowser;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class QuestionnaireForm extends javax.swing.JFrame {

    /**
     * Creates new form QuestionnaireForm
     */
    private MainForm mainForm;
    private final CharterDto charterDto;

    public QuestionnaireForm(CharterDto aCharterDto) {
        initComponents();
        jSliderFocusOnCharter.setMajorTickSpacing(10);
        jSliderFocusOnCharter.setMinorTickSpacing(10);
        jSliderFocusOnCharter.setPaintTicks(true);
        jSliderFocusOnCharter.setPaintLabels(true);
        
        jSliderConfiguration.setMajorTickSpacing(10);
        jSliderConfiguration.setMinorTickSpacing(10);
        jSliderConfiguration.setPaintTicks(true);
        jSliderConfiguration.setPaintLabels(true);
        
        jSliderBugReport.setMajorTickSpacing(10);
        jSliderBugReport.setMinorTickSpacing(10);
        jSliderBugReport.setPaintTicks(true);
        jSliderBugReport.setPaintLabels(true);
        
        jSliderTesting.setMajorTickSpacing(10);
        jSliderTesting.setMinorTickSpacing(10);
        jSliderTesting.setPaintTicks(true);
        jSliderTesting.setPaintLabels(true);
        
        charterDto = aCharterDto;
        
        ButtonGroup groupFeel = new ButtonGroup();
    groupFeel.add(jRadioButtonFeelBad);
    groupFeel.add(jRadioButtonFeelExcelent);
    groupFeel.add(jRadioButtonFeelGood);
        ButtonGroup groupNav = new ButtonGroup();
    groupNav.add(jRadioButtonNavegabilityBad);
    groupNav.add(jRadioButtonNavegabilityExcelent);
    groupNav.add(jRadioButtonNavegabilityGood);
    
    jLabelFocusCharterTip.setText("Time of the session that was focused on the Charter: "+charterDto.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFocusOnCharter = new javax.swing.JPanel();
        jSliderFocusOnCharter = new javax.swing.JSlider();
        jLabelFocusCharterTip = new javax.swing.JLabel();
        jPanelFeelUsingTheApp = new javax.swing.JPanel();
        jRadioButtonFeelBad = new javax.swing.JRadioButton();
        jRadioButtonFeelGood = new javax.swing.JRadioButton();
        jRadioButtonFeelExcelent = new javax.swing.JRadioButton();
        jPanelNavegability = new javax.swing.JPanel();
        jRadioButtonNavegabilityBad = new javax.swing.JRadioButton();
        jRadioButtonNavegabilityGood = new javax.swing.JRadioButton();
        jRadioButtonNavegabilityExcelent = new javax.swing.JRadioButton();
        jPanelConfigTime = new javax.swing.JPanel();
        jSliderConfiguration = new javax.swing.JSlider();
        jLabelConfig = new javax.swing.JLabel();
        jLabelBugReport = new javax.swing.JLabel();
        jSliderBugReport = new javax.swing.JSlider();
        jLabelTesting = new javax.swing.JLabel();
        jSliderTesting = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(451, 544));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelFocusOnCharter.setBorder(javax.swing.BorderFactory.createTitledBorder("Focus on the Charter:"));

        jSliderFocusOnCharter.setMajorTickSpacing(10);
        jSliderFocusOnCharter.setMinorTickSpacing(10);
        jSliderFocusOnCharter.setPaintLabels(true);
        jSliderFocusOnCharter.setPaintTicks(true);
        jSliderFocusOnCharter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderFocusOnCharterStateChanged(evt);
            }
        });

        jLabelFocusCharterTip.setText("Time of the session that was focused on the Charter.");

        javax.swing.GroupLayout jPanelFocusOnCharterLayout = new javax.swing.GroupLayout(jPanelFocusOnCharter);
        jPanelFocusOnCharter.setLayout(jPanelFocusOnCharterLayout);
        jPanelFocusOnCharterLayout.setHorizontalGroup(
            jPanelFocusOnCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFocusOnCharterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFocusOnCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderFocusOnCharter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFocusCharterTip, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelFocusOnCharterLayout.setVerticalGroup(
            jPanelFocusOnCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFocusOnCharterLayout.createSequentialGroup()
                .addComponent(jSliderFocusOnCharter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelFocusCharterTip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelFeelUsingTheApp.setBorder(javax.swing.BorderFactory.createTitledBorder("How do you feel using RelyTest?"));

        jRadioButtonFeelBad.setText("Bad");

        jRadioButtonFeelGood.setSelected(true);
        jRadioButtonFeelGood.setText("Good");

        jRadioButtonFeelExcelent.setText("Excelent");

        javax.swing.GroupLayout jPanelFeelUsingTheAppLayout = new javax.swing.GroupLayout(jPanelFeelUsingTheApp);
        jPanelFeelUsingTheApp.setLayout(jPanelFeelUsingTheAppLayout);
        jPanelFeelUsingTheAppLayout.setHorizontalGroup(
            jPanelFeelUsingTheAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFeelUsingTheAppLayout.createSequentialGroup()
                .addComponent(jRadioButtonFeelBad)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonFeelGood)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonFeelExcelent)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelFeelUsingTheAppLayout.setVerticalGroup(
            jPanelFeelUsingTheAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFeelUsingTheAppLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFeelUsingTheAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonFeelBad)
                    .addComponent(jRadioButtonFeelGood)
                    .addComponent(jRadioButtonFeelExcelent)))
        );

        jPanelNavegability.setBorder(javax.swing.BorderFactory.createTitledBorder("How about the navegability?"));

        jRadioButtonNavegabilityBad.setText("Bad");

        jRadioButtonNavegabilityGood.setSelected(true);
        jRadioButtonNavegabilityGood.setText("Good");

        jRadioButtonNavegabilityExcelent.setText("Excelent");

        javax.swing.GroupLayout jPanelNavegabilityLayout = new javax.swing.GroupLayout(jPanelNavegability);
        jPanelNavegability.setLayout(jPanelNavegabilityLayout);
        jPanelNavegabilityLayout.setHorizontalGroup(
            jPanelNavegabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegabilityLayout.createSequentialGroup()
                .addComponent(jRadioButtonNavegabilityBad)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonNavegabilityGood)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonNavegabilityExcelent)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelNavegabilityLayout.setVerticalGroup(
            jPanelNavegabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegabilityLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelNavegabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonNavegabilityBad)
                    .addComponent(jRadioButtonNavegabilityGood)
                    .addComponent(jRadioButtonNavegabilityExcelent)))
        );

        jPanelConfigTime.setBorder(javax.swing.BorderFactory.createTitledBorder("How did you spent your time?"));

        jSliderConfiguration.setMajorTickSpacing(10);
        jSliderConfiguration.setMinorTickSpacing(10);
        jSliderConfiguration.setPaintLabels(true);
        jSliderConfiguration.setPaintTicks(true);
        jSliderConfiguration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderConfigurationStateChanged(evt);
            }
        });
        jSliderConfiguration.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSliderConfigurationPropertyChange(evt);
            }
        });

        jLabelConfig.setText("Time spent on configuration (%):");

        jLabelBugReport.setText("Time researching and reporting bugs (%):");

        jSliderBugReport.setMajorTickSpacing(10);
        jSliderBugReport.setMinorTickSpacing(10);
        jSliderBugReport.setPaintLabels(true);
        jSliderBugReport.setPaintTicks(true);
        jSliderBugReport.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderBugReportStateChanged(evt);
            }
        });

        jLabelTesting.setText("Time Testing (%):");

        jSliderTesting.setMajorTickSpacing(10);
        jSliderTesting.setMinorTickSpacing(10);
        jSliderTesting.setPaintLabels(true);
        jSliderTesting.setPaintTicks(true);
        jSliderTesting.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderTestingStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelConfigTimeLayout = new javax.swing.GroupLayout(jPanelConfigTime);
        jPanelConfigTime.setLayout(jPanelConfigTimeLayout);
        jPanelConfigTimeLayout.setHorizontalGroup(
            jPanelConfigTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelBugReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelConfigTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConfigTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSliderBugReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSliderTesting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTesting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelConfigTimeLayout.setVerticalGroup(
            jPanelConfigTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigTimeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSliderConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBugReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSliderBugReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTesting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSliderTesting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFocusOnCharter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelFeelUsingTheApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelNavegability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelConfigTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelFocusOnCharter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFeelUsingTheApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelNavegability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConfigTime, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelFocusOnCharter.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        mainForm.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        QuestionnaireDto qDto = new QuestionnaireDto();
        qDto.setFocusOnCharter(jSliderFocusOnCharter.getValue());
        
        qDto.setTimeOnTesting(jSliderTesting.getValue());
        qDto.setTimeOnConfiguration(jSliderConfiguration.getValue());
        qDto.setTimeOnBugReport(jSliderBugReport.getValue());
        
        String feeling;
        if (jRadioButtonFeelBad.isSelected()) {
            feeling = "Bad";
        } else if (jRadioButtonFeelGood.isSelected()) {
            feeling = "Good";
        } else {
            feeling = "Excelent";
        }
        qDto.setFeelUsingRelyTest(feeling);
        String nav;
        if (jRadioButtonNavegabilityBad.isSelected()) {
            nav = "Bad";
        } else if (jRadioButtonNavegabilityGood.isSelected()) {
            nav = "Good";
        } else {
            nav = "Excelent";
        }
        qDto.setNavegability(nav);

        StartBrowser st = new StartBrowser();
        st.start(charterDto.getPathHtml());
        
        Gson gson = new Gson();
        //2. Convert object to JSON string and save into a file directly
        try (FileWriter fwriter = new FileWriter(charterDto.getFolderName() + File.separator + "Questionnaire.json", false)) {
            gson.toJson(qDto, fwriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainForm.pack();
        mainForm.setLocationRelativeTo(this);
        mainForm.setVisible(true);
            setVisible(false);
            dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jSliderFocusOnCharterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderFocusOnCharterStateChanged
        jPanelFocusOnCharter.setBorder(javax.swing.BorderFactory.createTitledBorder("Focus on the Charter ("+jSliderFocusOnCharter.getValue()+" %): "));
    }//GEN-LAST:event_jSliderFocusOnCharterStateChanged

    private void jSliderConfigurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderConfigurationStateChanged
        // TODO add your handling code here:
        jLabelConfig.setText("Time spent on configuration (%"+jSliderConfiguration.getValue()+"):");
    }//GEN-LAST:event_jSliderConfigurationStateChanged

    private void jSliderBugReportStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderBugReportStateChanged
        // TODO add your handling code here:
        jLabelBugReport.setText("Time researching and reporting bugs (%"+jSliderBugReport.getValue()+"):");
    }//GEN-LAST:event_jSliderBugReportStateChanged

    private void jSliderTestingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderTestingStateChanged
        // TODO add your handling code here:
        jLabelTesting.setText("Time Testing (%"+jSliderTesting.getValue()+"):");
    }//GEN-LAST:event_jSliderTestingStateChanged

    private void jSliderConfigurationPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSliderConfigurationPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jSliderConfigurationPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuestionnaireForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionnaireForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionnaireForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionnaireForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuestionnaireForm(new CharterDto()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelBugReport;
    private javax.swing.JLabel jLabelConfig;
    private javax.swing.JLabel jLabelFocusCharterTip;
    private javax.swing.JLabel jLabelTesting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelConfigTime;
    private javax.swing.JPanel jPanelFeelUsingTheApp;
    private javax.swing.JPanel jPanelFocusOnCharter;
    private javax.swing.JPanel jPanelNavegability;
    private javax.swing.JRadioButton jRadioButtonFeelBad;
    private javax.swing.JRadioButton jRadioButtonFeelExcelent;
    private javax.swing.JRadioButton jRadioButtonFeelGood;
    private javax.swing.JRadioButton jRadioButtonNavegabilityBad;
    private javax.swing.JRadioButton jRadioButtonNavegabilityExcelent;
    private javax.swing.JRadioButton jRadioButtonNavegabilityGood;
    private javax.swing.JSlider jSliderBugReport;
    private javax.swing.JSlider jSliderConfiguration;
    private javax.swing.JSlider jSliderFocusOnCharter;
    private javax.swing.JSlider jSliderTesting;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the mainForm
     */
    public MainForm getMainForm() {
        return mainForm;
    }

    /**
     * @param mainForm the mainForm to set
     */
    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }
}
