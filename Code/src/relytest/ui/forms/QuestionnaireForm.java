/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.forms;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ButtonGroup;
import static javax.swing.JOptionPane.showMessageDialog;
import relytest.interfaces.IPrinter;
import relytest.internationalization.LanguageController;
import relytest.internationalization.Texts;
import relytest.ui.HtmlPrinter;
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
    private final LanguageController lCon = new LanguageController();

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

        jLabelBugReport.setText("Time researching and reporting bugs (%" + jSliderBugReport.getValue() + "):");
        jLabelTesting.setText("Time Testing (%" + jSliderTesting.getValue() + "):");
        jLabelConfig.setText("Time spent on configuration (%" + jSliderConfiguration.getValue() + "):");

        loadLanguage();
    }

    private void loadLanguage() {
        jButtonSave.setText(lCon.getValue(Texts.Save));
        jButtonSaveAndOpen.setText(lCon.getValue(Texts.SaveAndOpenReport));
        jLabelFocusCharterTip.setText(lCon.getValue(Texts.TimeOfSessionFocusedOnCharter) + ": " + charterDto.getName());
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
        jRadioButtonFeelNoAnswer = new javax.swing.JRadioButton();
        jPanelNavegability = new javax.swing.JPanel();
        jRadioButtonNavegabilityBad = new javax.swing.JRadioButton();
        jRadioButtonNavegabilityGood = new javax.swing.JRadioButton();
        jRadioButtonNavegabilityExcelent = new javax.swing.JRadioButton();
        jRadioButtonNavNoAnswer = new javax.swing.JRadioButton();
        jPanelConfigTime = new javax.swing.JPanel();
        jSliderConfiguration = new javax.swing.JSlider();
        jLabelConfig = new javax.swing.JLabel();
        jLabelBugReport = new javax.swing.JLabel();
        jSliderBugReport = new javax.swing.JSlider();
        jLabelTesting = new javax.swing.JLabel();
        jSliderTesting = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonSaveAndOpen = new javax.swing.JButton();
        jPanelBrowser = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneBrowser = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jSliderFocusOnCharter.setValue(0);
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

        jRadioButtonFeelGood.setText("Good");

        jRadioButtonFeelExcelent.setText("Excelent");

        jRadioButtonFeelNoAnswer.setSelected(true);
        jRadioButtonFeelNoAnswer.setText("No Answer");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonFeelNoAnswer)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelFeelUsingTheAppLayout.setVerticalGroup(
            jPanelFeelUsingTheAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFeelUsingTheAppLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFeelUsingTheAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonFeelBad)
                    .addComponent(jRadioButtonFeelGood)
                    .addComponent(jRadioButtonFeelExcelent)
                    .addComponent(jRadioButtonFeelNoAnswer)))
        );

        jPanelNavegability.setBorder(javax.swing.BorderFactory.createTitledBorder("How about the navegability?"));

        jRadioButtonNavegabilityBad.setText("Bad");

        jRadioButtonNavegabilityGood.setText("Good");

        jRadioButtonNavegabilityExcelent.setText("Excelent");

        jRadioButtonNavNoAnswer.setSelected(true);
        jRadioButtonNavNoAnswer.setText("No Answer");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonNavNoAnswer)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelNavegabilityLayout.setVerticalGroup(
            jPanelNavegabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegabilityLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelNavegabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonNavegabilityBad)
                    .addComponent(jRadioButtonNavegabilityGood)
                    .addComponent(jRadioButtonNavegabilityExcelent)
                    .addComponent(jRadioButtonNavNoAnswer)))
        );

        jPanelConfigTime.setBorder(javax.swing.BorderFactory.createTitledBorder("How did you spent your time?"));

        jSliderConfiguration.setMajorTickSpacing(10);
        jSliderConfiguration.setMinorTickSpacing(10);
        jSliderConfiguration.setPaintLabels(true);
        jSliderConfiguration.setPaintTicks(true);
        jSliderConfiguration.setValue(0);
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
        jSliderBugReport.setValue(0);
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
        jSliderTesting.setValue(0);
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

        jButtonSaveAndOpen.setText("Save and Open Report");
        jButtonSaveAndOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAndOpenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSaveAndOpen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonSaveAndOpen))
                .addContainerGap())
        );

        jPanelBrowser.setBorder(javax.swing.BorderFactory.createTitledBorder("Browser used?"));

        jTextPaneBrowser.setText("<No browser used>");
        jTextPaneBrowser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextPaneBrowserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPaneBrowser);

        javax.swing.GroupLayout jPanelBrowserLayout = new javax.swing.GroupLayout(jPanelBrowser);
        jPanelBrowser.setLayout(jPanelBrowserLayout);
        jPanelBrowserLayout.setHorizontalGroup(
            jPanelBrowserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBrowserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelBrowserLayout.setVerticalGroup(
            jPanelBrowserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBrowserLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanelBrowser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelFocusOnCharter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void startBrowser() {
        StartBrowser st = new StartBrowser();
        st.start(charterDto.getPathHtml());
    }

    private void printHtml() {
        IPrinter printer = new HtmlPrinter();
        printer.print(charterDto);
    }
private int totalTimeSum=0;
    private boolean isTimeSumCorrect(boolean showMessageDialog) {
        totalTimeSum = jSliderConfiguration.getValue() + jSliderBugReport.getValue() + jSliderTesting.getValue();
        boolean ok = totalTimeSum == 100;
        if (!ok && showMessageDialog) {
            showMessageDialog(this, "The sum of: "
                    + System.lineSeparator() + "- Time spent configuration, "
                    + System.lineSeparator() + "- Time spent reporting bugs and"
                    + System.lineSeparator() + "- Time spent testing"
                    + System.lineSeparator() + "should be 100. But the actual value is: " + totalTimeSum);
        }
        return ok;
    }

    private void saveQuestionnaire() {
        QuestionnaireDto qDto = new QuestionnaireDto();
        qDto.setFocusOnCharter(jSliderFocusOnCharter.getValue());

        qDto.setTimeOnTesting(jSliderTesting.getValue());
        qDto.setTimeOnConfiguration(jSliderConfiguration.getValue());
        qDto.setTimeOnBugReport(jSliderBugReport.getValue());

        charterDto.getDetails().getMetrics().setFocusOnCharter("" + jSliderFocusOnCharter.getValue());
        charterDto.getDetails().getMetrics().setTimeSpentConfiguration("" + jSliderConfiguration.getValue());
        charterDto.getDetails().getMetrics().setTimeSpentReportingBugs("" + jSliderBugReport.getValue());
        charterDto.getDetails().getMetrics().setTimeSpentTesting("" + jSliderTesting.getValue());
        charterDto.getDetails().getPlanification().setBrowser(jTextPaneBrowser.getText());
        String feeling;
        if (jRadioButtonFeelBad.isSelected()) {
            feeling = lCon.getValue(Texts.Bad);
        } else if (jRadioButtonFeelGood.isSelected()) {
            feeling = lCon.getValue(Texts.Good);
        } else if (jRadioButtonFeelExcelent.isSelected()){
            feeling = lCon.getValue(Texts.Excelent);
        }else{
            feeling = lCon.getValue(Texts.NoAnswer);
        }
        qDto.setFeelUsingRelyTest(feeling);
        String nav;
        if (jRadioButtonNavegabilityBad.isSelected()) {
            nav = lCon.getValue(Texts.Bad);
        } else if (jRadioButtonNavegabilityGood.isSelected()) {
            nav = lCon.getValue(Texts.Good);
        } else if (jRadioButtonNavegabilityExcelent.isSelected()){
            nav = lCon.getValue(Texts.Excelent);
        }else{
            nav = lCon.getValue(Texts.NoAnswer);
        }
        qDto.setNavegability(nav);

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
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:     
        if (isTimeSumCorrect(true)) {
            saveQuestionnaire();
            printHtml();
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jSliderFocusOnCharterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderFocusOnCharterStateChanged
        jPanelFocusOnCharter.setBorder(javax.swing.BorderFactory.createTitledBorder("Focus on the Charter (" + jSliderFocusOnCharter.getValue() + " %): "));
    }//GEN-LAST:event_jSliderFocusOnCharterStateChanged

    private void jSliderConfigurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderConfigurationStateChanged
         updateTimeJlabels();
    }//GEN-LAST:event_jSliderConfigurationStateChanged

    private void jSliderBugReportStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderBugReportStateChanged
        updateTimeJlabels();
    }//GEN-LAST:event_jSliderBugReportStateChanged

    private void updateTimeJlabels(){
        if (isTimeSumCorrect(false)) {
            jLabelTesting.setText("Time Testing (%" + jSliderTesting.getValue() + "):");
            jLabelBugReport.setText("Time researching and reporting bugs (%" + jSliderBugReport.getValue() + "):");
            jLabelConfig.setText("Time spent on configuration (%" + jSliderConfiguration.getValue() + "):");
            jPanelConfigTime.setBorder(javax.swing.BorderFactory.createTitledBorder("How did you spent your time? (Sum = "+totalTimeSum+" %)"));
        } else {
            jLabelTesting.setText("<html>Time Testing <font color='red'>(%" + jSliderTesting.getValue() + "</font>):</html>");
             jLabelBugReport.setText("<html>Time researching and reporting bugs <font color='red'>(%" + jSliderBugReport.getValue() + "</font>):</html>");
            jLabelConfig.setText("<html>Time spent on configuration <font color='red'>(%" + jSliderConfiguration.getValue() + "</font>):</html>");
            jPanelConfigTime.setBorder(javax.swing.BorderFactory.createTitledBorder("<html>How did you spent your time? (<font color='red'>Sum = "+totalTimeSum+" %</font>)</html>"));
        }
    }
    
    private void jSliderTestingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderTestingStateChanged
        updateTimeJlabels();
    }//GEN-LAST:event_jSliderTestingStateChanged

    private void jSliderConfigurationPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSliderConfigurationPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jSliderConfigurationPropertyChange


    private void jButtonSaveAndOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAndOpenActionPerformed
        if (isTimeSumCorrect(true)) {
            saveQuestionnaire();
            printHtml();
            startBrowser();
        }
    }//GEN-LAST:event_jButtonSaveAndOpenActionPerformed

    private void jTextPaneBrowserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextPaneBrowserMouseClicked
        // TODO add your handling code here:
        if (jTextPaneBrowser.getText().equals("<No browser used>")) {
            jTextPaneBrowser.setText("");
        }
    }//GEN-LAST:event_jTextPaneBrowserMouseClicked

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
                new QuestionnaireForm(new CharterDto("no name")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAndOpen;
    private javax.swing.JLabel jLabelBugReport;
    private javax.swing.JLabel jLabelConfig;
    private javax.swing.JLabel jLabelFocusCharterTip;
    private javax.swing.JLabel jLabelTesting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBrowser;
    private javax.swing.JPanel jPanelConfigTime;
    private javax.swing.JPanel jPanelFeelUsingTheApp;
    private javax.swing.JPanel jPanelFocusOnCharter;
    private javax.swing.JPanel jPanelNavegability;
    private javax.swing.JRadioButton jRadioButtonFeelBad;
    private javax.swing.JRadioButton jRadioButtonFeelExcelent;
    private javax.swing.JRadioButton jRadioButtonFeelGood;
    private javax.swing.JRadioButton jRadioButtonFeelNoAnswer;
    private javax.swing.JRadioButton jRadioButtonNavNoAnswer;
    private javax.swing.JRadioButton jRadioButtonNavegabilityBad;
    private javax.swing.JRadioButton jRadioButtonNavegabilityExcelent;
    private javax.swing.JRadioButton jRadioButtonNavegabilityGood;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSliderBugReport;
    private javax.swing.JSlider jSliderConfiguration;
    private javax.swing.JSlider jSliderFocusOnCharter;
    private javax.swing.JSlider jSliderTesting;
    private javax.swing.JTextPane jTextPaneBrowser;
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
