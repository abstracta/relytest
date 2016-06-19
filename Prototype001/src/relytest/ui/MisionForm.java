/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import relytest.interfaces.IScreenPrinter;
import relytest.interfaces.IWriter;
import relytest.ui.common.EnvironmentStats;
import relytest.ui.common.ScreenPrinter;
import relytest.ui.common.Writer;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class MisionForm extends javax.swing.JFrame {

    private String misionName;
    private String charterName;
    private String sesionName = "";
    private ArrayList<String> notesTypes = new ArrayList<>();
    private int selectedNoteType = 0;
    private String totalTime;
    private static Timer timer;
    private static Date date;

    private final String PaintApp = "mspaint.exe";
    private final String NotesTypesFile = "NoteTypes.txt";
    private final String SubDir = "\\";
    private final String LogFile = "SessionLog.txt";
    private final String RunningPath = System.getProperty("user.dir");
    private final String ScreenShotsDir = "ScreenShots";
    private final String Summary = "Environment_Summary.txt";
    private static boolean paused = false;

    private String picturePath = "";
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final IWriter writer = new Writer();
    private final Calendar calStart = Calendar.getInstance();

    private final Color defaultColor;

    /**
     * Creates new form MisionForm
     *
     */
    public MisionForm(String newCharterName) {
        initComponents();
        sesionName = getDateNow()+"_Charter_"+newCharterName;
        charterName=newCharterName;
        picturePath = RunningPath + SubDir + sesionName + SubDir + ScreenShotsDir + SubDir;
        createMisionFolders();

        writeToLog("Session Started", "");

        EnvironmentStats e = new EnvironmentStats();
        e.setFile(RunningPath + SubDir + sesionName + SubDir + Summary);
        e.start();

        defaultColor = jButtonPause.getBackground();
    }

    public void Start() {
        try {
            date = sdf.parse(totalTime);
        } catch (ParseException ex) {
            Logger.getLogger(MisionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTimeDisplay();
            }
        });
        timer.start();
    }

    private boolean elapsedTimePassExpected = false;

    public void UpdateTimeDisplay() {
        if (!paused) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            if (elapsedTimePassExpected) {
                cal.add(Calendar.SECOND, +1);
                jButtonPause.setBackground(Color.RED);
                date = cal.getTime();
                jButtonPause.setText("-" + sdf.format(date));
            } else {
                cal.add(Calendar.SECOND, -1);
                date = cal.getTime();
                jButtonPause.setText(sdf.format(date));
                elapsedTimePassExpected = sdf.format(date).equals("00:00:00");
                if (elapsedTimePassExpected) {
                    writeToLog("Elapsed Time Pass Expected", "The elapsed time passed the expected time for the session.");
                }
            }

        } else {
            ChangeBackgroundColor();
        }
    }

    private void ChangeBackgroundColor() {
        if (defaultColor.equals(jButtonPause.getBackground())) {
            jButtonPause.setBackground(Color.YELLOW);
        } else {
            jButtonPause.setBackground(defaultColor);
        }
    }

    private void createMisionFolders() {
        new File(RunningPath + SubDir + sesionName).mkdirs();
        new File(RunningPath + SubDir + sesionName + SubDir + ScreenShotsDir).mkdirs();
    }

    private void executePaint(String pictureName) {
        try {
            String[] params = new String[2];

            params[0] = PaintApp;
            params[1] = pictureName;
            Process p = new ProcessBuilder(params).start();

            p.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MisionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String print() {
        String pictureName = "";
        try {
            String now = getDateNow();

            pictureName = "Pic_" + now;
            IScreenPrinter printer = new ScreenPrinter();

            PropertiesMgr p = new PropertiesMgr();
            Boolean hide = Boolean.valueOf(p.getValue(Constants.Key_HideRelyTest));
            if (hide) {
                this.setVisible(false);
            }
            String picFormat = printer.print(picturePath + pictureName);
            if (hide) {
                this.setVisible(true);
            }
            pictureName += picFormat;
            writePicTaken(pictureName);
        } catch (Exception e) {
        }
        return pictureName;
    }

    private void takePicture() {
        String pic = print();

        PropertiesMgr p = new PropertiesMgr();
        Boolean open = Boolean.valueOf(p.getValue(Constants.Key_OpenImageEditor));
        if (open) {
            executePaint(picturePath + pic);
        }
    }

    private void writePicTaken(String picName) {
        writeToLog("Picture Taken", picName);
    }

    private void writeNote() {
        if (!jTextAreaNote.getText().equals("")) {
            writeToLog(getSelectedNote(), jTextAreaNote.getText());
        }
    }

    private String getSelectedNote() {
        String str;
        if (jtbBug.isSelected()) {
            str = "BUG";
        } else if (jtbNote.isSelected()) {
            str = "NOTE";
        } else if (jtbToDo.isSelected()) {
            str = "ToDo";
        } else if (jtbIssue.isSelected()) {
            str = "Issue";
        } else {
            str = "Risk";
        }
        return str;
    }

    private void writeToLog(String label, String text) {
        writer.writeToFile(RunningPath + SubDir + sesionName + SubDir + LogFile, getDateNow() + " > [" + label + "] " + text);
    }

    private String getDateNow() {
        Date dt = new Date();
        calStart.setTime(dt);
        DateFormat dateFormat = new SimpleDateFormat(Constants.DateTimeFormat);
        return dateFormat.format(dt);
    }

    private void readXmlFile() throws ParserConfigurationException, SAXException, IOException {
        File file = new File(RunningPath + SubDir + NotesTypesFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList list = document.getElementsByTagName("item");
        for (int i = 0; i < list.getLength(); i++) {
            String usr = document.getElementsByTagName("item").item(i).getTextContent();
            notesTypes.add(usr);
        }
    }

//    private void loadNotes() {
//        try {
//            readXmlFile();
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//        }
//        if (notesTypes.isEmpty()) {
//            notesTypes.add("Note");
//            notesTypes.add("Bug");
//            notesTypes.add("To do");
//            notesTypes.add("Improvement");
//        }
//    }

//    private void getNextNote() {
//        if (!notesTypes.isEmpty()) {
//            selectedNoteType++;
//            if (notesTypes.size() <= selectedNoteType) {
//                selectedNoteType = 0;
//            }
////            jLabelNoteType.setText(notesTypes.get(selectedNoteType));
//        }
//    }

//    private void getPreviousNote() {
//        if (!notesTypes.isEmpty()) {
//            selectedNoteType--;
//            if (0 > selectedNoteType) {
//                selectedNoteType = notesTypes.size() - 1;
//            }
////            jLabelNoteType.setText(notesTypes.get(selectedNoteType));
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupNotes = new javax.swing.ButtonGroup();
        jButtonPause = new javax.swing.JButton();
        jPanelNote = new javax.swing.JPanel();
        jPanelNoteSelection = new javax.swing.JPanel();
        jtbNote = new javax.swing.JToggleButton();
        jtbBug = new javax.swing.JToggleButton();
        jtbToDo = new javax.swing.JToggleButton();
        jtbRisk = new javax.swing.JToggleButton();
        jtbIssue = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jButtonAdd = new javax.swing.JButton();
        jButtonPicture = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RelyTest");
        setName("frameRelyTest"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonPause.setText("Pause");
        jButtonPause.setFocusPainted(false);
        jButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPauseActionPerformed(evt);
            }
        });

        jPanelNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelNoteSelection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtbNote.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbNote);
        jtbNote.setSelected(true);
        jtbNote.setText("Note");
        jtbNote.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbNote.setMinimumSize(new java.awt.Dimension(97, 23));
        jtbNote.setPreferredSize(new java.awt.Dimension(80, 23));
        jtbNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbNoteActionPerformed(evt);
            }
        });

        jtbBug.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbBug);
        jtbBug.setText("Bug");
        jtbBug.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbBug.setMinimumSize(new java.awt.Dimension(97, 23));
        jtbBug.setPreferredSize(new java.awt.Dimension(80, 23));

        jtbToDo.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbToDo);
        jtbToDo.setText("To do");
        jtbToDo.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbToDo.setPreferredSize(new java.awt.Dimension(80, 23));

        jtbRisk.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbRisk);
        jtbRisk.setText("Risk");
        jtbRisk.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbRisk.setMinimumSize(new java.awt.Dimension(59, 23));
        jtbRisk.setPreferredSize(new java.awt.Dimension(80, 23));

        jtbIssue.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbIssue);
        jtbIssue.setText("Issue");
        jtbIssue.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbIssue.setMinimumSize(new java.awt.Dimension(59, 23));
        jtbIssue.setPreferredSize(new java.awt.Dimension(80, 23));

        javax.swing.GroupLayout jPanelNoteSelectionLayout = new javax.swing.GroupLayout(jPanelNoteSelection);
        jPanelNoteSelection.setLayout(jPanelNoteSelectionLayout);
        jPanelNoteSelectionLayout.setHorizontalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbBug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbToDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbRisk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbIssue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNoteSelectionLayout.setVerticalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbNote, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addComponent(jtbBug, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtbToDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtbRisk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtbIssue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNote);

        jButtonAdd.setText("Add");
        jButtonAdd.setFocusPainted(false);
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNoteLayout = new javax.swing.GroupLayout(jPanelNote);
        jPanelNote.setLayout(jPanelNoteLayout);
        jPanelNoteLayout.setHorizontalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelNoteLayout.createSequentialGroup()
                        .addComponent(jPanelNoteSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelNoteLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAdd)))
                .addContainerGap())
        );
        jPanelNoteLayout.setVerticalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addComponent(jPanelNoteSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButtonPicture.setText("Take Picture");
        jButtonPicture.setFocusPainted(false);
        jButtonPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPictureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonPicture)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPause, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("frameRelyTest");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPictureActionPerformed
        takePicture();
    }//GEN-LAST:event_jButtonPictureActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        writeNote();
        jTextAreaNote.setText("");
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

        Date now = new Date();

        long sec = (now.getTime() - calStart.getTime().getTime()) / 1000;

        long min = sec / 60;
        if (min > 0) {
            writeToLog("Session Finished", "Duration: " + min + " min : " + (sec - min * 60) + " sec.");
        } else {
            writeToLog("Session Finished", "Duration: " + sec + " sec.");
        }

    }//GEN-LAST:event_formWindowClosing

    private void jtbNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbNoteActionPerformed

    private void jButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPauseActionPerformed
        paused = !paused;
        if (paused) {
            jButtonPause.setText("Paused...");
            writeToLog("Paused", "");
        } else {
            jButtonPause.setBackground(defaultColor);
            writeToLog("Continue", "");
        }
    }//GEN-LAST:event_jButtonPauseActionPerformed

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
            java.util.logging.Logger.getLogger(MisionForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MisionForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MisionForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MisionForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String noCharter ="No charter";
                new MisionForm(noCharter).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupNotes;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonPause;
    private javax.swing.JButton jButtonPicture;
    private javax.swing.JPanel jPanelNote;
    private javax.swing.JPanel jPanelNoteSelection;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JToggleButton jtbBug;
    private javax.swing.JToggleButton jtbIssue;
    private javax.swing.JToggleButton jtbNote;
    private javax.swing.JToggleButton jtbRisk;
    private javax.swing.JToggleButton jtbToDo;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the totalTime
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime the totalTime to set
     */
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * @return the misionName
     */
    public String getMisionName() {
        return misionName;
    }

    /**
     * @param misionName the misionName to set
     */
    public void setMisionName(String misionName) {
        this.misionName = misionName;
    }
    
    public String getCharterName(){
        return charterName;
    }
    public void setCharterName(String charterName){
        this.charterName=charterName;
    }
}
