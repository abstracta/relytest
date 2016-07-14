/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
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
import relytest.interfaces.IPrinter;
import relytest.interfaces.IScreenPrinter;
import relytest.interfaces.IWriter;
import relytest.ui.common.CharterDto;
import relytest.ui.common.EnvironmentStats;
import relytest.ui.common.GroupNote;
import relytest.ui.common.Note;
import relytest.ui.common.ScreenPrinter;
import relytest.ui.common.Writer;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class MisionForm extends javax.swing.JFrame {

    private CharterDto charterDto;
    
    //private String misionName;
 //   private String charterName;
   // private String folderName = "";
    private ArrayList<String> notesTypes = new ArrayList<>();
    private int selectedNoteType = 0;
//    private String totalTime;
    private static Timer timer;
    private static Date date;
    private MainForm mainForm;

    private final String NotesTypesFile = "NoteTypes.txt";
    private final String LogFile = "SessionLog.txt";
    private final String RunningPath = System.getProperty("user.dir");
    private final String ScreenShotsDir = "ScreenShots";
    private final String Summary = "Environment_Summary.txt";
    private static boolean paused = false;

    //private String picturePath = "";
    private String paintApp = "mspaint.exe";
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final IWriter writer = new Writer();
    private final Calendar calStart = Calendar.getInstance();

    private final Color defaultColor;

    /**
     * Creates new form MisionForm
     *
     * @param newCharterName
     */
    public MisionForm(CharterDto dto) {
        initComponents();
        charterDto=dto;
        charterDto.setFolderName( getDateNow(false) + "_Charter_" + dto.getCharterFileName());
        charterDto.setFolderNamePath(RunningPath + File.separator + charterDto.getFolderName());
        charterDto.setPicturePath( charterDto.getFolderNamePath() + File.separator + ScreenShotsDir + File.separator);
        createMisionFolders();

        EnvironmentStats e = new EnvironmentStats();
        e.setFile(charterDto.getFolderNamePath()  + File.separator + Summary);
        e.start();
        defaultColor = jButtonPause.getBackground();

        loadProperties();
        initializeNotesGroup();
        writeToLog(Constants.LABEL_SESSION_STARTED, Constants.LABEL_SESSION_STARTED);
        jLabelEventLog.setText(Constants.LABEL_SESSION_STARTED);

        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setWrapStyleWord(true);
    }

    private void loadProperties() {
        PropertiesMgr p = new PropertiesMgr();
        jtbNote.setVisible(displayButton(p, Constants.KEY_BUTTON_NOTE));
        jtbBug.setVisible(displayButton(p, Constants.KEY_BUTTON_BUG));
        jtbIssue.setVisible(displayButton(p, Constants.KEY_BUTTON_PROBLEM));
        jtbRisk.setVisible(displayButton(p, Constants.KEY_BUTTON_RISK));
        jtbToDo.setVisible(displayButton(p, Constants.KEY_BUTTON_TODO));

        paintApp = p.getValue(Constants.KEY_PAINT_APP);
    }

    private Boolean displayButton(PropertiesMgr p, String buttonName) {
        if (p.getValue(buttonName) != null) {
            return Boolean.valueOf(p.getValue(buttonName));
        }
        return true;
    }

    public void Start() {
        try {
            date = sdf.parse(charterDto.getTotalTime());
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
                    writeToLog(Constants.LABEL_ELAPSED_TIME_PASS_EXPECTED_TIME, "The elapsed time passed the expected time for the session.");
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
        new File(RunningPath + File.separator + charterDto.getFolderName()).mkdirs();
        new File(RunningPath + File.separator + charterDto.getFolderName() + File.separator + ScreenShotsDir).mkdirs();
    }

    private void executePaint(String pictureName) {
        try {
            String[] params = new String[2];
            params[0] = paintApp;
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
            String now = getDateNow(false);

            pictureName = "Pic_" + now;
            IScreenPrinter printer = new ScreenPrinter();

            PropertiesMgr p = new PropertiesMgr();
            Boolean hide = Boolean.valueOf(p.getValue(Constants.KEY_HIDE_RELY_TEST));
            if (hide) {
                this.setVisible(false);
            }
            String picFormat = printer.print(charterDto.getPicturePath() + pictureName);
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
        if (paintApp != null) {
            String pic = print();

            PropertiesMgr p = new PropertiesMgr();
            Boolean open = Boolean.valueOf(p.getValue(Constants.KEY_OPEN_IMAGE_EDITOR));
            if (open) {
                executePaint(charterDto.getPicturePath() + pic);
            }
            jLabelEventLog.setText(Constants.LABEL_PICTURE_TAKEN);
        }
    }

    private void writePicTaken(String picName) {
        writeToLog(Constants.LABEL_PICTURE_TAKEN, picName);
    }

    private void writeNote() {
        if (!jTextAreaNote.getText().equals("")) {
            writeToLog(getSelectedNote(), jTextAreaNote.getText());
            jLabelEventLog.setText("Your " + getSelectedNote() + " has been added.");
        }
    }

    private String getSelectedNote() {
        String str;
        if (jtbBug.isSelected()) {
            str = Constants.LABEL_BUG;
        } else if (jtbNote.isSelected()) {
            str = Constants.LABEL_NOTE;
        } else if (jtbToDo.isSelected()) {
            str = Constants.LABEL_ToDo;
        } else if (jtbIssue.isSelected()) {
            str = Constants.LABEL_PROBLEM;
        } else {
            str = Constants.LABEL_RISK;
        }
        return str;
    }

    private ArrayList<Note> notesTaken = new ArrayList<>();
    GroupNote[] groupNotes = new GroupNote[7];

    private void initializeNotesGroup() {
        for (int x = 0; x < groupNotes.length; x++) {
            switch (x) {
                case 0:
                    groupNotes[x] = new GroupNote(Constants.LABEL_BUG);
                    break;
                case 1:
                    groupNotes[x] = new GroupNote(Constants.LABEL_NOTE);
                    break;
                case 2:
                    groupNotes[x] = new GroupNote(Constants.LABEL_ToDo);
                    break;
                case 3:
                    groupNotes[x] = new GroupNote(Constants.LABEL_PROBLEM);
                    break;
                case 4:
                    groupNotes[x] = new GroupNote(Constants.LABEL_RISK);
                    break;
                case 5:
                    groupNotes[x] = new GroupNote(Constants.LABEL_PICTURE_TAKEN);
                    break;
                default:
                    groupNotes[x] = new GroupNote(Constants.LABEL_EVENT);
                    break;

            }
        }
    }

    private long cont = 0;

    private void addNote(String label, String text, String timeStamp) {
        Note note = new Note(++cont, text, timeStamp);
        notesTaken.add(note);
        switch (label) {
            case Constants.LABEL_BUG:
                groupNotes[0].addNote(note);
                PropertiesMgr p = new PropertiesMgr();
                Boolean takePic = Boolean.valueOf(p.getValue(Constants.KEY_TAKE_PICTURE_AFTER_BUG));
                if (takePic) {
                    takePicture();
                }
                break;
            case Constants.LABEL_NOTE:
                groupNotes[1].addNote(note);
                break;
            case Constants.LABEL_ToDo:
                groupNotes[2].addNote(note);
                break;
            case Constants.LABEL_PROBLEM:
                groupNotes[3].addNote(note);
                break;
            case Constants.LABEL_RISK:
                groupNotes[4].addNote(note);
                break;
            case Constants.LABEL_PICTURE_TAKEN:
                groupNotes[5].addNote(note);
                break;
            default:
                groupNotes[6].addNote(note);
                break;
        }
    }

    private void writeToLog(String label, String text) {
        boolean setStartTime = Constants.LABEL_SESSION_STARTED.equals(label);
        String timeStamp = getDateNow(setStartTime);
        writer.writeToFile(RunningPath + File.separator + charterDto.getFolderName() + File.separator + LogFile, timeStamp + " > [" + label + "] " + text);
        addNote(label, text, timeStamp);
    }

    private void printHtml() {
        IPrinter printer = new HtmlPrinter();
        charterDto.setGroupNotes(groupNotes);
        charterDto.setPathHtml(RunningPath + File.separator + charterDto.getFolderName() + File.separator + "RelyTest_Charter_Report.html");

        printer.print(charterDto);
    }

    private void printNotes() {
        printJsonLog();
        printJsonGroupNotes();
    }

    private void printJsonLog() {
        Gson gson = new Gson();
        //2. Convert object to JSON string and save into a file directly
        try (FileWriter fwriter = new FileWriter(RunningPath + File.separator + charterDto.getFolderName() + File.separator + "log.json", false)) {
            gson.toJson(notesTaken, fwriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printJsonGroupNotes() {
        try (FileWriter fwriter = new FileWriter(RunningPath + File.separator + charterDto.getFolderName() + File.separator + "notes.json", true)) {
            for (int i = 0; i < groupNotes.length; i++) {
                if (!groupNotes[i].notes.isEmpty()) {
                    Gson gson = new Gson();
                    gson.toJson(groupNotes[i], fwriter);
                    fwriter.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDateNow(boolean setStartTime) {
        Date dt = new Date();
        if (setStartTime) {
            calStart.setTime(dt);
        }
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        return dateFormat.format(dt);
    }

    private void readXmlFile() throws ParserConfigurationException, SAXException, IOException {
        File file = new File(RunningPath + File.separator + NotesTypesFile);
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
        jPanel1 = new javax.swing.JPanel();
        jLabelEventLog = new javax.swing.JLabel();
        jButtonPause = new javax.swing.JButton();
        jButtonPicture = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RelyTest");
        setName("frameRelyTest"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelNoteSelection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtbNote.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbNote);
        jtbNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/note.png"))); // NOI18N
        jtbNote.setSelected(true);
        jtbNote.setToolTipText("Select the Note label");
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
        jtbBug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bug.png"))); // NOI18N
        jtbBug.setToolTipText("Select the Bug label");
        jtbBug.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbBug.setMinimumSize(new java.awt.Dimension(97, 23));
        jtbBug.setPreferredSize(new java.awt.Dimension(80, 23));

        jtbToDo.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbToDo);
        jtbToDo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ToDo.png"))); // NOI18N
        jtbToDo.setToolTipText("Select the ToDo label");
        jtbToDo.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbToDo.setPreferredSize(new java.awt.Dimension(80, 23));

        jtbRisk.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbRisk);
        jtbRisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/risk.png"))); // NOI18N
        jtbRisk.setToolTipText("Select the Risk label");
        jtbRisk.setMaximumSize(new java.awt.Dimension(97, 23));
        jtbRisk.setMinimumSize(new java.awt.Dimension(59, 23));
        jtbRisk.setPreferredSize(new java.awt.Dimension(80, 23));

        jtbIssue.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroupNotes.add(jtbIssue);
        jtbIssue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/error.png"))); // NOI18N
        jtbIssue.setToolTipText("Select the Problem label");
        jtbIssue.setMaximumSize(new java.awt.Dimension(97, 23));
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
            .addComponent(jtbNote, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(jtbBug, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtbToDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtbRisk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtbIssue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jTextAreaNote.setAutoscrolls(false);
        jTextAreaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaNoteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaNote);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Add.png"))); // NOI18N
        jButtonAdd.setToolTipText("Add the note to your notes collection");
        jButtonAdd.setEnabled(false);
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
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabelEventLog.setText("jLabel1");

        jButtonPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/timer.png"))); // NOI18N
        jButtonPause.setText("Pause");
        jButtonPause.setToolTipText("Pause the session");
        jButtonPause.setFocusPainted(false);
        jButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPauseActionPerformed(evt);
            }
        });

        jButtonPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Camara.png"))); // NOI18N
        jButtonPicture.setToolTipText("Take a Picture");
        jButtonPicture.setFocusPainted(false);
        jButtonPicture.setOpaque(false);
        jButtonPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPictureActionPerformed(evt);
            }
        });

        jButtonStop.setText("Stop");
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonPause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelEventLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jButtonStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonPause)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelEventLog))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jButtonAdd.setEnabled(false);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void lastWriteToLog() {
        Date now = new Date();

        long sec = (now.getTime() - calStart.getTime().getTime()) / 1000;

        long min = sec / 60;
        if (min > 0) {
            writeToLog(Constants.LABEL_SESSION_FINISHED, Constants.LABEL_SESSION_FINISHED + " - Duration: " + min + " min : " + (sec - min * 60) + " sec.");
        } else {
            writeToLog(Constants.LABEL_SESSION_FINISHED, Constants.LABEL_SESSION_FINISHED + " - Duration: " + sec + " sec.");
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

        lastWriteToLog();
        printNotes();

    }//GEN-LAST:event_formWindowClosing

    private void jtbNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbNoteActionPerformed

    private void jButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPauseActionPerformed
        paused = !paused;
        jLabelEventLog.setText("");
        if (paused) {
            jButtonPause.setText(Constants.LABEL_PAUSED + "...");
            jButtonPause.setToolTipText("Continue the session");
            writeToLog(Constants.LABEL_PAUSED, Constants.LABEL_PAUSED);
            enableAllControls(false);
        } else {
            jButtonPause.setBackground(defaultColor);
            writeToLog(Constants.LABEL_CONTINUE, Constants.LABEL_CONTINUE);
            jButtonPause.setToolTipText("Pause the session");
            enableAllControls(true);
        }
    }//GEN-LAST:event_jButtonPauseActionPerformed

    private void enableAllControls(boolean enable) {
        jButtonAdd.setEnabled(enable && !jTextAreaNote.getText().isEmpty());
        jButtonPicture.setEnabled(enable);
        jTextAreaNote.setEnabled(enable);
        jtbBug.setEnabled(enable);
        jtbIssue.setEnabled(enable);
        jtbNote.setEnabled(enable);
        jtbRisk.setEnabled(enable);
        jtbToDo.setEnabled(enable);
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowClosed

    private void jTextAreaNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaNoteKeyReleased
        // TODO add your handling code here:
        jButtonAdd.setEnabled(!jTextAreaNote.getText().isEmpty());
    }//GEN-LAST:event_jTextAreaNoteKeyReleased

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        // TODO add your handling code here:
        lastWriteToLog();
        printNotes();
        printHtml();

        mainForm.setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButtonStopActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MisionForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {              
                new MisionForm(new CharterDto()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupNotes;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonPause;
    private javax.swing.JButton jButtonPicture;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabelEventLog;
    private javax.swing.JPanel jPanel1;
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
