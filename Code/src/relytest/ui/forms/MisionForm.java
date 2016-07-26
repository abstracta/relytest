/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.forms;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Desktop;
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
import javax.swing.JOptionPane;
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
import javax.swing.text.DefaultCaret;
import relytest.interfaces.IConfigFormLoad;
import relytest.internationalization.LanguageController;
import relytest.internationalization.Texts;
import relytest.ui.Constants;
import relytest.ui.HtmlPrinter;
import relytest.ui.PropertiesMgr;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class MisionForm extends javax.swing.JFrame implements IConfigFormLoad {

    private final CharterDto charterDto;
    private ArrayList<String> notesTypes = new ArrayList<>();
    private int selectedNoteType = 0;
    private static Timer timer;
    private static Date date;
    private MainForm mainForm;

    private final String NotesTypesFile = "NoteTypes.txt";
    private final String LogFile = "SessionLog.txt";
    private final String RunningPath = System.getProperty("user.dir");
    private final String ScreenShotsDir = "ScreenShots";
    private final String Summary = "Environment_Summary.txt";
    private static boolean paused = false;
    private String paintApp = "mspaint.exe";
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final IWriter writer = new Writer();
    private final Calendar calStart = Calendar.getInstance();

    private final Color defaultColor;
    private final LanguageController lCon = new LanguageController();

    /**
     * Creates new form MisionForm
     *
     * @param dto
     */
    public MisionForm(CharterDto dto) {
        initComponents();
        charterDto = dto;
        charterDto.setFolderName(getDateNow(false) + "_Charter_" + dto.getCharterFileName());
        charterDto.setFolderNamePath(RunningPath + File.separator + charterDto.getFolderName());
        charterDto.setPicturePath(charterDto.getFolderNamePath() + File.separator + ScreenShotsDir + File.separator);
        createMisionFolders();
        jTextFieldPath.setText(charterDto.getFolderName());

        EnvironmentStats e = new EnvironmentStats(charterDto.getDetails().getPlanification());
        e.setFile(charterDto.getFolderNamePath() + File.separator + Summary);
        e.start();
        defaultColor = jButtonPause.getBackground();

        loadProperties();
        initializeNotesGroup();
        writeToLog(Constants.LABEL_SESSION_STARTED, Constants.LABEL_SESSION_STARTED);
//        jLabelEventLog.setText(Constants.LABEL_SESSION_STARTED);

        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setWrapStyleWord(true);

        DefaultCaret caret = (DefaultCaret) jTextAreaLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jButtonConfig.setVisible(false);
        loadLanguage();
    }

    private void loadLanguage() {
        jButtonPath.setText(lCon.getValue(Texts.MainForm_jButtonPath));
    }

    @Override
    public void loadTimes() {

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
                    writeToLog(lCon.getValue(Texts.Msg_ElapsedTimePassExpectedTime), lCon.getValue(Texts.Msg_ElapsedTimePassExpectedTime));
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
        }
    }

    private void writePicTaken(String picName) {
        writeToLog(Constants.LABEL_PICTURE_TAKEN, picName);
    }

    private void writeNote() {
        if (!jTextAreaNote.getText().equals("")) {
            writeToLog(getSelectedNote(), jTextAreaNote.getText());
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

    private final ArrayList<Note> notesTaken = new ArrayList<>();
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
        Note note = new Note(++cont, text, timeStamp, label);
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
        String newLogLine = timeStamp + " > [" + label + "] " + text;
        writer.writeToFile(RunningPath + File.separator + charterDto.getFolderName() + File.separator + LogFile, newLogLine);
        jTextAreaLog.append(newLogLine + System.lineSeparator());
        addNote(label, text, timeStamp);
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
            for (GroupNote groupNote : groupNotes) {
                if (!groupNote.notes.isEmpty()) {
                    Gson gson = new Gson();
                    gson.toJson(groupNote, fwriter);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupNotes = new javax.swing.ButtonGroup();
        jPanelNoteSelection = new javax.swing.JPanel();
        jtbNote = new javax.swing.JToggleButton();
        jtbBug = new javax.swing.JToggleButton();
        jtbToDo = new javax.swing.JToggleButton();
        jtbRisk = new javax.swing.JToggleButton();
        jtbIssue = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        jPanelNote = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jButtonAdd = new javax.swing.JButton();
        jButtonPause = new javax.swing.JButton();
        jButtonPicture = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jButtonConfig = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonPath = new javax.swing.JButton();
        jTextFieldPath = new javax.swing.JTextField();

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoteSelectionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtbIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jtbRisk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jtbToDo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jtbBug, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jtbNote, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelNoteSelectionLayout.setVerticalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                .addComponent(jtbNote, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbBug, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbToDo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbRisk, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtbIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextAreaLog.setEditable(false);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jScrollPane2.setViewportView(jTextAreaLog);

        jPanelNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jButtonPicture.setMaximumSize(new java.awt.Dimension(109, 52));
        jButtonPicture.setMinimumSize(new java.awt.Dimension(109, 52));
        jButtonPicture.setOpaque(false);
        jButtonPicture.setPreferredSize(new java.awt.Dimension(109, 52));
        jButtonPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPictureActionPerformed(evt);
            }
        });

        jButtonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/stopCharter.png"))); // NOI18N
        jButtonStop.setToolTipText("Close the session");
        jButtonStop.setMaximumSize(new java.awt.Dimension(109, 52));
        jButtonStop.setMinimumSize(new java.awt.Dimension(109, 52));
        jButtonStop.setPreferredSize(new java.awt.Dimension(109, 52));
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        jButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/tool.png"))); // NOI18N
        jButtonConfig.setToolTipText("Opens the Configuration Window");
        jButtonConfig.setFocusPainted(false);
        jButtonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNoteLayout = new javax.swing.GroupLayout(jPanelNote);
        jPanelNote.setLayout(jPanelNoteLayout);
        jPanelNoteLayout.setHorizontalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButtonConfig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelNoteLayout.setVerticalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNoteLayout.createSequentialGroup()
                        .addComponent(jButtonPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConfig)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPause)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonPath.setText("Workspace:");
        jButtonPath.setFocusable(false);
        jButtonPath.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPath.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonPath);

        jTextFieldPath.setEditable(false);
        jTextFieldPath.setText("Path");
        jToolBar1.add(jTextFieldPath);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelNoteSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelNoteSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        long sec = (now.getTime() - calStart.getTime().getTime() + 1) / 1000;

        long min = sec / 60;
        String duration;
        if (min > 0) {
            duration = +min + " " + lCon.getValue(Texts.Unit_Min) + " : " + (sec - min * 60) + " " + lCon.getValue(Texts.Unit_Second);
            writeToLog(Constants.LABEL_SESSION_FINISHED, Constants.LABEL_SESSION_FINISHED + " - " + lCon.getValue(Texts.Duration) + ": " + duration + ".");
        } else {
            duration = sec + " " + lCon.getValue(Texts.Unit_Second);
            writeToLog(Constants.LABEL_SESSION_FINISHED, Constants.LABEL_SESSION_FINISHED + " - " + lCon.getValue(Texts.Duration) + ": " + duration + ".");
        }
        PropertiesMgr p = new PropertiesMgr();
        String tester = p.getValue(Constants.KEY_NAME);
        charterDto.getDetails().getPlanification().setTester(tester);
        charterDto.getDetails().getExecution().setRealDuration(duration);

    }

    private void printCloseChart() {
        lastWriteToLog();
        printNotes();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        PropertiesMgr p = new PropertiesMgr();
        Boolean confirm = Boolean.valueOf(p.getValue(Constants.KEY_CONFIRM_EXIT_RELYTEST));
        int dialogResult = JOptionPane.NO_OPTION;
        if (confirm) {
            dialogResult = JOptionPane.showConfirmDialog(this, lCon.getValue(Texts.Question_Exit_RelyTest), lCon.getValue(Texts.Exit_RelyTest), JOptionPane.YES_NO_OPTION);
        }
        if (!confirm || dialogResult == JOptionPane.YES_OPTION) {
            printCloseChart();
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//cancel
        }
    }//GEN-LAST:event_formWindowClosing

    private void jtbNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbNoteActionPerformed

    private void jButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPauseActionPerformed
        paused = !paused;
        if (paused) {
            jButtonPause.setText(Constants.LABEL_PAUSED + "...");
            jButtonPause.setToolTipText("Continue the session");
            writeToLog(Constants.LABEL_PAUSED, Constants.LABEL_PAUSED);
            enableAllControls(false);
        } else {
            jButtonPause.setBackground(defaultColor);
            writeToLog(Constants.LABEL_CONTINUE, Constants.LABEL_CONTINUE);
            jButtonPause.setToolTipText(lCon.getValue(Texts.PauseSession));
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
        PropertiesMgr p = new PropertiesMgr();
        Boolean confirm = Boolean.valueOf(p.getValue(Constants.KEY_CONFIRM_STOP_CHARTER));
        int dialogResult = JOptionPane.NO_OPTION;
        if (confirm) {
            dialogResult = JOptionPane.showConfirmDialog(this, lCon.getValue(Texts.Question_Exit_Session), lCon.getValue(Texts.Exit_Session), JOptionPane.YES_NO_OPTION);
        }
        if (!confirm || dialogResult == JOptionPane.YES_OPTION) {
            charterDto.setGroupNotes(groupNotes);
            charterDto.setNotesTaken(notesTaken);
            charterDto.setPathHtml(RunningPath + File.separator + charterDto.getFolderName() + File.separator + "RelyTest_Charter_Report.html");
            printCloseChart();
            QuestionnaireForm qForm = new QuestionnaireForm(charterDto);
            qForm.setMainForm(mainForm);
            qForm.pack();
            qForm.setLocationRelativeTo(this);
            qForm.setVisible(true);
            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void jButtonConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigActionPerformed
        // TODO add your handling code here:
        ConfigForm configFrm = new ConfigForm(this);
        configFrm.pack();
        configFrm.setLocationRelativeTo(this);
        configFrm.setVisible(true);
    }//GEN-LAST:event_jButtonConfigActionPerformed

    private void jButtonPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPathActionPerformed

        try {
            if (Desktop.isDesktopSupported()) {
                File file = new File(charterDto.getFolderName());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
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
                new MisionForm(new CharterDto("No name")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupNotes;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonConfig;
    private javax.swing.JButton jButtonPath;
    private javax.swing.JButton jButtonPause;
    private javax.swing.JButton jButtonPicture;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JPanel jPanelNote;
    private javax.swing.JPanel jPanelNoteSelection;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldPath;
    private javax.swing.JToolBar jToolBar1;
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
