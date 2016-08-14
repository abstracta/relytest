/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.forms;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import relytest.ui.common.CharterDto;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.plaf.ColorUIResource;
import relytest.interfaces.IConfigFormLoad;
import relytest.internationalization.LanguageController;
import relytest.internationalization.Texts;
import relytest.ui.Constants;
import relytest.ui.PropertiesMgr;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public final class MainForm extends javax.swing.JFrame implements IConfigFormLoad{

    private ConfigForm configFrm;
    private final LanguageController lCon;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        this.lCon = new LanguageController();
        initComponents();
        loadTimes();
        
        jTextFieldPath.setText(System.getProperty("user.dir"));
        loadLanguage();
        
    }

    private void loadLanguage() {        
        setTitle(lCon.getValue(Texts.MainForm_Title));
        jPanelCharter.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jPanelCharter)));
        jPanelDuration.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jPanelDuration)));       
        jButtonPath.setText("");
        jPanelWorkspace.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jButtonPath)));
        
        jButtonStart.setToolTipText(lCon.getValue(Texts.MainForm_startNewCharter));
        jButtonConfig.setToolTipText(lCon.getValue(Texts.MainForm_openConfigForm));
        jButtonPath.setToolTipText(lCon.getValue(Texts.OpenTheWorkspace));
    }
    
    @Override
    public void loadTimes() {
        PropertiesMgr p = new PropertiesMgr();       
        String value = p.getValue(Constants.KEY_SHORT_TIME);
        Integer i = Integer.parseInt(value);
        jToggleButtonShort.setText(lCon.getValue(Texts.MainForm_jToggleButtonShort)+" " + i + " Mins");
        i = Integer.parseInt(p.getValue(Constants.KEY_LONG_TIME));
        jToggleButtonLong.setText(lCon.getValue(Texts.MainForm_jToggleButtonLong)+" " + i + " Mins");
        i = Integer.parseInt(p.getValue(Constants.KEY_MEDIUM_TIME));
        jToggleButtonMedium.setText(lCon.getValue(Texts.MainForm_jToggleButtonMedium)+" " + i + " Mins");
    }

    private String getTotalTime() {
        PropertiesMgr p = new PropertiesMgr();
        String value;
        Integer i;
        if (jToggleButtonShort.isSelected()) {
            value = p.getValue(Constants.KEY_SHORT_TIME);
        } else if (jToggleButtonMedium.isSelected()) {
            value = p.getValue(Constants.KEY_MEDIUM_TIME);
        } else {
            value = p.getValue(Constants.KEY_LONG_TIME);
        }
        i = Integer.parseInt(value);
        return timeToHhMmSs(i);
    }

    private String timeToHhMmSs(Integer time) {
        if (time < 60) {
            return "0:" + time + ":00";
        } else {
            Integer hour = time / 60;
            Integer mins = time % 60;
            return hour + ":" + mins + ":00";
        }
    }
            
    private void start() {
        if (jTextFieldCharterName.getText().equals("")) {
            showMessageDialog(this, lCon.getValue(Texts.Msg_InsertNameCharter));
        } else {
            String charterName = jTextFieldCharterName.getText().replaceAll("[^a-zA-Z0-9.-]", "_");
            CharterDto dto = new CharterDto(jTextFieldCharterName.getText());
            dto.setCharterFileName(charterName);
            dto.setTotalTime(getTotalTime());
            dto.getDetails().getPlanification().setDuration(getTotalTime());
            
            MisionForm mision = new MisionForm(dto);
               
            mision.setTitle(lCon.getValue(Texts.MisionForm_Title)+ " "+ charterName);
            mision.setMainForm(this);
            mision.Start();
            mision.show();
            mision.pack();
            mision.setLocationRelativeTo(this);
            jTextFieldCharterName.setText("");
            this.setVisible(false);
        }                       
    }

    public boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }

    private void config() {
        configFrm = new ConfigForm(this);
        configFrm.pack();
        configFrm.setLocationRelativeTo(this);
        configFrm.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        durationButtonGroup = new javax.swing.ButtonGroup();
        jButtonConfig = new javax.swing.JButton();
        jPanelDuration = new javax.swing.JPanel();
        jToggleButtonShort = new javax.swing.JToggleButton();
        jToggleButtonMedium = new javax.swing.JToggleButton();
        jToggleButtonLong = new javax.swing.JToggleButton();
        jPanelCharter = new javax.swing.JPanel();
        jTextFieldCharterName = new javax.swing.JTextField();
        jToolBar = new javax.swing.JToolBar();
        jPanelWorkspace = new javax.swing.JPanel();
        jTextFieldPath = new javax.swing.JTextField();
        jButtonPath = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RelyTest");
        setBackground(new java.awt.Color(223, 223, 223));
        setForeground(new java.awt.Color(223, 223, 223));

        jButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Tool.png"))); // NOI18N
        jButtonConfig.setToolTipText("Opens the Configuration Window");
        jButtonConfig.setBorderPainted(false);
        jButtonConfig.setContentAreaFilled(false);
        jButtonConfig.setFocusPainted(false);
        jButtonConfig.setMaximumSize(new java.awt.Dimension(113, 23));
        jButtonConfig.setMinimumSize(new java.awt.Dimension(113, 23));
        jButtonConfig.setPreferredSize(new java.awt.Dimension(113, 23));
        jButtonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigActionPerformed(evt);
            }
        });

        jPanelDuration.setBackground(new java.awt.Color(223, 223, 223));
        jPanelDuration.setBorder(javax.swing.BorderFactory.createTitledBorder("Duration:"));

        jToggleButtonShort.setBackground(new java.awt.Color(69, 104, 143));
        durationButtonGroup.add(jToggleButtonShort);
        jToggleButtonShort.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonShort.setSelected(true);
        jToggleButtonShort.setText("Short (15min)");
        jToggleButtonShort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jToggleButtonShort.setFocusPainted(false);
        jToggleButtonShort.setMaximumSize(new java.awt.Dimension(113, 23));
        jToggleButtonShort.setMinimumSize(new java.awt.Dimension(113, 23));
        jToggleButtonShort.setPreferredSize(new java.awt.Dimension(113, 23));
        jToggleButtonShort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonShortActionPerformed(evt);
            }
        });

        jToggleButtonMedium.setBackground(new java.awt.Color(69, 104, 143));
        durationButtonGroup.add(jToggleButtonMedium);
        jToggleButtonMedium.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonMedium.setText("Medium (30min)");
        jToggleButtonMedium.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jToggleButtonMedium.setFocusPainted(false);
        jToggleButtonMedium.setMaximumSize(new java.awt.Dimension(113, 23));
        jToggleButtonMedium.setMinimumSize(new java.awt.Dimension(113, 23));
        jToggleButtonMedium.setPreferredSize(new java.awt.Dimension(113, 23));

        jToggleButtonLong.setBackground(new java.awt.Color(69, 104, 143));
        durationButtonGroup.add(jToggleButtonLong);
        jToggleButtonLong.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonLong.setText("Long (60min)");
        jToggleButtonLong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jToggleButtonLong.setFocusPainted(false);
        jToggleButtonLong.setMaximumSize(new java.awt.Dimension(113, 23));
        jToggleButtonLong.setMinimumSize(new java.awt.Dimension(113, 23));
        jToggleButtonLong.setPreferredSize(new java.awt.Dimension(113, 23));
        jToggleButtonLong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDurationLayout = new javax.swing.GroupLayout(jPanelDuration);
        jPanelDuration.setLayout(jPanelDurationLayout);
        jPanelDurationLayout.setHorizontalGroup(
            jPanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDurationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButtonShort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanelDurationLayout.setVerticalGroup(
            jPanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jToggleButtonMedium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButtonLong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButtonShort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCharter.setBackground(new java.awt.Color(223, 223, 223));
        jPanelCharter.setBorder(javax.swing.BorderFactory.createTitledBorder("Charter:"));
        jPanelCharter.setName("Charter"); // NOI18N

        jTextFieldCharterName.setToolTipText("What are you going to test?");
        jTextFieldCharterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCharterNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCharterLayout = new javax.swing.GroupLayout(jPanelCharter);
        jPanelCharter.setLayout(jPanelCharterLayout);
        jPanelCharterLayout.setHorizontalGroup(
            jPanelCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCharterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCharterName, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCharterLayout.setVerticalGroup(
            jPanelCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCharterLayout.createSequentialGroup()
                .addComponent(jTextFieldCharterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar.setFloatable(false);
        jToolBar.setRollover(true);

        jPanelWorkspace.setBackground(new java.awt.Color(223, 223, 223));

        jTextFieldPath.setEditable(false);
        jTextFieldPath.setText("Path");

        jButtonPath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/relytest/ui/forms/Folder.png"))); // NOI18N
        jButtonPath.setToolTipText("Take a look at your workspace");
        jButtonPath.setBorderPainted(false);
        jButtonPath.setContentAreaFilled(false);
        jButtonPath.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPath.setFocusable(false);
        jButtonPath.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPath.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelWorkspaceLayout = new javax.swing.GroupLayout(jPanelWorkspace);
        jPanelWorkspace.setLayout(jPanelWorkspaceLayout);
        jPanelWorkspaceLayout.setHorizontalGroup(
            jPanelWorkspaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWorkspaceLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTextFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonPath, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelWorkspaceLayout.setVerticalGroup(
            jPanelWorkspaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWorkspaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelWorkspaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Start.png"))); // NOI18N
        jButtonStart.setToolTipText("Starts a New Charter");
        jButtonStart.setFocusPainted(false);
        jButtonStart.setMaximumSize(new java.awt.Dimension(113, 23));
        jButtonStart.setMinimumSize(new java.awt.Dimension(113, 23));
        jButtonStart.setPreferredSize(new java.awt.Dimension(113, 23));
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(351, 351, 351)
                        .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelCharter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelWorkspace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanelCharter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanelDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelWorkspace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButtonShortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonShortActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jToggleButtonShortActionPerformed

    private void jToggleButtonLongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonLongActionPerformed

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        start();
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigActionPerformed
        config();
    }//GEN-LAST:event_jButtonConfigActionPerformed

    private void jTextFieldCharterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCharterNameActionPerformed
        // TODO add your handling code here:
        start();
    }//GEN-LAST:event_jTextFieldCharterNameActionPerformed

    private void jButtonPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPathActionPerformed

        try {
            File file = new File (System.getProperty("user.dir"));
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPathActionPerformed

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
            javax.swing.UIManager.put("activeCaption", new ColorUIResource(Color.red));
          
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup durationButtonGroup;
    private javax.swing.JButton jButtonConfig;
    private javax.swing.JButton jButtonPath;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JPanel jPanelCharter;
    private javax.swing.JPanel jPanelDuration;
    private javax.swing.JPanel jPanelWorkspace;
    private javax.swing.JTextField jTextFieldCharterName;
    private javax.swing.JTextField jTextFieldPath;
    private javax.swing.JToggleButton jToggleButtonLong;
    private javax.swing.JToggleButton jToggleButtonMedium;
    private javax.swing.JToggleButton jToggleButtonShort;
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables
}
