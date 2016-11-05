/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.forms;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.plaf.ColorUIResource;
import relytest.common.dto.Charter;
import relytest.interfaces.IConfigFormLoad;
import relytest.internationalization.LanguageController;
import relytest.internationalization.Texts;
import relytest.ui.Constants;
import relytest.ui.PropertiesMgr;
import relytest.ui.common.CharterDto;
import relytest.web.client.CharterClient;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public final class MainForm extends javax.swing.JFrame implements IConfigFormLoad {

    private ConfigForm configFrm;
    private final LanguageController lCon;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        this.lCon = new LanguageController();
        initComponents();
        loadTimes();

//        jTextFieldPath.setText(System.getProperty("user.dir"));
        loadLanguage();

        PropertiesMgr p = new PropertiesMgr();
        if (Boolean.valueOf(p.getValue(Constants.KEY_CONNECT_TO_SERVER))) {
            CharterClient cc = new CharterClient();
            try {
                List<Charter> charters = cc.getCharters();
                if (charters != null) {
                    for (Charter charter : charters) {
                        jComboBoxCharters.addItem(charter);
                    }
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not connect to the server.", "Relytest - Server not available", JOptionPane.INFORMATION_MESSAGE);
                jComboBoxCharters.setEnabled(false);
                //Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadLanguage() {
        setTitle(lCon.getValue(Texts.MainForm_Title));
        jPanelCharter.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jPanelCharter)));
        jPanelDuration.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jPanelDuration)));
      // jButtonPath.setText("");
//        jPanelWorkspace.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jButtonPath)));

        jButtonStart.setToolTipText(lCon.getValue(Texts.MainForm_startNewCharter));
        jButtonConfig.setToolTipText(lCon.getValue(Texts.MainForm_openConfigForm));
        jButtonPath.setToolTipText(lCon.getValue(Texts.OpenTheWorkspace));
    }

    @Override
    public void loadTimes() {
        PropertiesMgr p = new PropertiesMgr();
        String value = p.getValue(Constants.KEY_SHORT_TIME);
        Integer i = Integer.parseInt(value);
        jRadioButtonShort.setText(lCon.getValue(Texts.MainForm_jToggleButtonShort) + " (" + i + "' )");
        i = Integer.parseInt(p.getValue(Constants.KEY_LONG_TIME));
        jRadioButtonLong.setText(lCon.getValue(Texts.MainForm_jToggleButtonLong) + " (" + i + "' )");
        i = Integer.parseInt(p.getValue(Constants.KEY_MEDIUM_TIME));
        jRadioButtonMedium.setText(lCon.getValue(Texts.MainForm_jToggleButtonMedium) + " (" + i + "' )");
    }

    private String getTotalTime() {
        PropertiesMgr p = new PropertiesMgr();
        String value;
        Integer i;
        if (jRadioButtonShort.isSelected()) {
            value = p.getValue(Constants.KEY_SHORT_TIME);
        } else if (jRadioButtonMedium.isSelected()) {
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

            mision.setTitle(lCon.getValue(Texts.MisionForm_Title) + " " + dto.getName());
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
        jPanelMain = new javax.swing.JPanel();
        jPanelCharter = new javax.swing.JPanel();
        jTextFieldCharterName = new javax.swing.JTextField();
        jComboBoxCharters = new javax.swing.JComboBox();
        jPanelDuration = new javax.swing.JPanel();
        jRadioButtonShort = new javax.swing.JRadioButton();
        jRadioButtonMedium = new javax.swing.JRadioButton();
        jRadioButtonLong = new javax.swing.JRadioButton();
        jButtonStart = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonConfig = new javax.swing.JButton();
        jButtonPath = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RelyTest");
        setBackground(new java.awt.Color(223, 223, 223));
        setForeground(new java.awt.Color(223, 223, 223));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelCharter.setBackground(new java.awt.Color(223, 223, 223));
        jPanelCharter.setBorder(javax.swing.BorderFactory.createTitledBorder("Charter:"));
        jPanelCharter.setName("Charter"); // NOI18N

        jTextFieldCharterName.setToolTipText("What are you going to test?");
        jTextFieldCharterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCharterNameActionPerformed(evt);
            }
        });

        jComboBoxCharters.setEnabled(false);
        jComboBoxCharters.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxChartersItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelCharterLayout = new javax.swing.GroupLayout(jPanelCharter);
        jPanelCharter.setLayout(jPanelCharterLayout);
        jPanelCharterLayout.setHorizontalGroup(
            jPanelCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCharterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCharterName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxCharters, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCharterLayout.setVerticalGroup(
            jPanelCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCharterLayout.createSequentialGroup()
                .addGroup(jPanelCharterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCharterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCharters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDuration.setBackground(new java.awt.Color(223, 223, 223));
        jPanelDuration.setBorder(javax.swing.BorderFactory.createTitledBorder("Duration:"));

        jRadioButtonShort.setSelected(true);
        jRadioButtonShort.setText("S");
        jRadioButtonShort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonShortActionPerformed(evt);
            }
        });

        jRadioButtonMedium.setText("M");
        jRadioButtonMedium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMediumActionPerformed(evt);
            }
        });

        jRadioButtonLong.setText("L");
        jRadioButtonLong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDurationLayout = new javax.swing.GroupLayout(jPanelDuration);
        jPanelDuration.setLayout(jPanelDurationLayout);
        jPanelDurationLayout.setHorizontalGroup(
            jPanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDurationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonShort, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonLong, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDurationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButtonLong, jRadioButtonMedium, jRadioButtonShort});

        jPanelDurationLayout.setVerticalGroup(
            jPanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDurationLayout.createSequentialGroup()
                .addGroup(jPanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonShort)
                    .addComponent(jRadioButtonMedium)
                    .addComponent(jRadioButtonLong))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanelDurationLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButtonLong, jRadioButtonMedium, jRadioButtonShort});

        jButtonStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Start.png"))); // NOI18N
        jButtonStart.setToolTipText("Starts a New Charter");
        jButtonStart.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonStart.setFocusPainted(false);
        jButtonStart.setMaximumSize(new java.awt.Dimension(123, 30));
        jButtonStart.setMinimumSize(new java.awt.Dimension(123, 30));
        jButtonStart.setPreferredSize(new java.awt.Dimension(123, 30));
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Tool.png"))); // NOI18N
        jButtonConfig.setToolTipText("Opens the Configuration Window");
        jButtonConfig.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonConfig.setBorderPainted(false);
        jButtonConfig.setContentAreaFilled(false);
        jButtonConfig.setFocusPainted(false);
        jButtonConfig.setMaximumSize(new java.awt.Dimension(123, 30));
        jButtonConfig.setMinimumSize(new java.awt.Dimension(123, 30));
        jButtonConfig.setPreferredSize(new java.awt.Dimension(123, 30));
        jButtonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigActionPerformed(evt);
            }
        });

        jButtonPath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/relytest/ui/forms/Folder.png"))); // NOI18N
        jButtonPath.setText("Open folder");
        jButtonPath.setToolTipText("Take a look at your workspace");
        jButtonPath.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonPath.setBorderPainted(false);
        jButtonPath.setContentAreaFilled(false);
        jButtonPath.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPath.setFocusable(false);
        jButtonPath.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButtonPath.setMaximumSize(new java.awt.Dimension(123, 30));
        jButtonPath.setMinimumSize(new java.awt.Dimension(123, 30));
        jButtonPath.setPreferredSize(new java.awt.Dimension(123, 30));
        jButtonPath.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButtonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonConfig, jButtonPath});

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jPanelCharter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanelDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelCharter, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanelDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            File file = new File(System.getProperty("user.dir"));
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPathActionPerformed

    private void jComboBoxChartersItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxChartersItemStateChanged
        Object obj = jComboBoxCharters.getSelectedItem();
        if (obj != null) {
            Charter charter = (Charter) obj;
            jTextFieldCharterName.setText(charter.toString());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxChartersItemStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setIconImage(new ImageIcon(getClass().getResource("RelyTest_logo.png")).getImage());
      //  this.update(null);
        
    }//GEN-LAST:event_formWindowOpened

    private void jRadioButtonMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMediumActionPerformed
        // TODO add your handling code here:
        jRadioButtonShort.setSelected(false);
        jRadioButtonLong.setSelected(false);
    }//GEN-LAST:event_jRadioButtonMediumActionPerformed

    private void jRadioButtonLongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLongActionPerformed
        // TODO add your handling code here:
        jRadioButtonShort.setSelected(false);
        jRadioButtonMedium.setSelected(false);
    }//GEN-LAST:event_jRadioButtonLongActionPerformed

    private void jRadioButtonShortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonShortActionPerformed
        // TODO add your handling code here:
        jRadioButtonMedium.setSelected(false);
        jRadioButtonLong.setSelected(false);
    }//GEN-LAST:event_jRadioButtonShortActionPerformed

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
    private javax.swing.JComboBox jComboBoxCharters;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCharter;
    private javax.swing.JPanel jPanelDuration;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JRadioButton jRadioButtonLong;
    private javax.swing.JRadioButton jRadioButtonMedium;
    private javax.swing.JRadioButton jRadioButtonShort;
    private javax.swing.JTextField jTextFieldCharterName;
    // End of variables declaration//GEN-END:variables
}
