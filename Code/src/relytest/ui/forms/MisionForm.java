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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
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
import relytest.ui.PropertiesMgr;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class MisionForm extends javax.swing.JFrame implements IConfigFormLoad {

    private final CharterDto charterDto;
    private final ArrayList<String> notesTypes = new ArrayList<>();
    private final int selectedNoteType = 0;
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
    
    private final java.awt.Color colorSelected =new java.awt.Color(223, 223, 223);
    private final java.awt.Color colorDeselected = new java.awt.Color(69, 104, 143);

    /**
     * Creates new form MisionForm
     *
     * @param dto
     */
    public MisionForm(CharterDto dto) {
        initComponents();
        charterDto = dto;
        charterDto.setFolderName(getDateNowToString() + "_Charter_" + dto.getCharterFileName());
        charterDto.setFolderNamePath(RunningPath + File.separator + charterDto.getFolderName());
        charterDto.setPicturePath(charterDto.getFolderNamePath() + File.separator + ScreenShotsDir + File.separator);
        createMisionFolders();
        jTextFieldPath.setText(charterDto.getFolderName());

        EnvironmentStats e = new EnvironmentStats(charterDto.getDetails().getPlanification());
        e.setFile(charterDto.getFolderNamePath() + File.separator + Summary);
        e.start();
        defaultColor = jButtonClock.getBackground();

        loadProperties();
        initializeNotesGroup();
        writeToLog(Constants.LABEL_SESSION_STARTED, Constants.LABEL_SESSION_STARTED);
//        jLabelEventLog.setText(Constants.LABEL_SESSION_STARTED);

        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setWrapStyleWord(true);

        DefaultCaret caret = (DefaultCaret) jTextAreaLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //jButtonConfig.setVisible(false);
        loadLanguage();

        jTextAreaNote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int code = ke.getKeyCode();
                int modifiers = ke.getModifiers();
                if (code == KeyEvent.VK_ENTER && modifiers == KeyEvent.CTRL_MASK) {
                    if(jCheckBoxAddNoteShortcut.isSelected()){
                         addNewNote();
                    }                   
                }
            }
        });
        
       setButtonsDefaultColor();
       this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
    }
    
    private void setButtonsDefaultColor(){
         switchImage(jtbBug) ;
         switchImage(jtbNote) ;
         switchImage(jtbProblem) ;
         switchImage(jtbRisk) ;
         switchImage(jtbToDo) ;
    }

    private void loadLanguage() {
        jButtonPath.setText("");
        //jPanelWorkspace.setBorder(javax.swing.BorderFactory.createTitledBorder(lCon.getValue(Texts.MainForm_jButtonPath)));
        jCheckBoxAlwaysOnTop.setText(lCon.getValue(Texts.MisionForm_AlwaysOnTop));
        jCheckBoxAddNoteShortcut.setText(lCon.getValue(Texts.MisionForm_Shortcut));
        jButtonPath.setToolTipText(lCon.getValue(Texts.OpenTheWorkspace));
    }

    @Override
    public void loadTimes() {
    }

    private void loadProperties() {
        PropertiesMgr p = new PropertiesMgr();
        jtbNote.setVisible(displayButton(p, Constants.KEY_BUTTON_NOTE));
        jtbBug.setVisible(displayButton(p, Constants.KEY_BUTTON_BUG));
        jtbProblem.setVisible(displayButton(p, Constants.KEY_BUTTON_PROBLEM));
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
                jButtonClock.setBackground(Color.RED);
                date = cal.getTime();
                jButtonClock.setText("-" + sdf.format(date));
            } else {
                cal.add(Calendar.SECOND, -1);
                date = cal.getTime();
                jButtonClock.setText(sdf.format(date));
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
        if (defaultColor.equals(jButtonClock.getBackground())) {
            jButtonClock.setBackground(Color.YELLOW);
        } else {
            jButtonClock.setBackground(defaultColor);
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
            String now = getDateNowToString();

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
            this.setAlwaysOnTop(false);
            String pic = print();

            PropertiesMgr p = new PropertiesMgr();
            Boolean open = Boolean.valueOf(p.getValue(Constants.KEY_OPEN_IMAGE_EDITOR));
            if (open) {
                executePaint(charterDto.getPicturePath() + pic);
            }
             this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
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
        } else if (jtbProblem.isSelected()) {
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
        
        if(setStartTime){
            charterDto.setStartTime(getDateNow());
        }
        String timeStamp = getDateNowToString();
        jTextAreaLog.append(" > [" + label + "] " + text + System.lineSeparator());
        String newLogLine = timeStamp + " > [" + label + "] " + text;
        writer.writeToFile(RunningPath + File.separator + charterDto.getFolderName() + File.separator + LogFile, newLogLine);

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

    private Calendar getDateNow() {
        Date dt = new Date();
     
            calStart.setTime(dt);
             
        return calStart;
    }
    
    private String getDateNowToString() {
        Date dt = new Date();
//        if (setStartTime) {
//            calStart.setTime(dt);
//        }
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
        jPanel1 = new javax.swing.JPanel();
        jPanelNoteSelection = new javax.swing.JPanel();
        jtbNote = new javax.swing.JToggleButton();
        jtbBug = new javax.swing.JToggleButton();
        jtbToDo = new javax.swing.JToggleButton();
        jtbRisk = new javax.swing.JToggleButton();
        jtbProblem = new javax.swing.JToggleButton();
        jPanelNote = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        jButtonStop = new javax.swing.JButton();
        jButtonPicture = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jButtonClock = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jButtonHide = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jButtonPath = new javax.swing.JButton();
        jTextFieldPath = new javax.swing.JTextField();
        jCheckBoxAddNoteShortcut = new javax.swing.JCheckBox();
        jCheckBoxAlwaysOnTop = new javax.swing.JCheckBox();
        jToolBar1 = new javax.swing.JToolBar();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RelyTest");
        setBackground(new java.awt.Color(16, 36, 65));
        setForeground(new java.awt.Color(16, 36, 65));
        setName("frameRelyTest"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelNoteSelection.setBackground(new java.awt.Color(223, 223, 223));

        jtbNote.setBackground(new java.awt.Color(69, 104, 143));
        buttonGroupNotes.add(jtbNote);
        jtbNote.setForeground(new java.awt.Color(255, 255, 255));
        jtbNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Note.png"))); // NOI18N
        jtbNote.setSelected(true);
        jtbNote.setToolTipText("Select the Note label");
        jtbNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jtbNote.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbNote.setMaximumSize(new java.awt.Dimension(80, 23));
        jtbNote.setMinimumSize(new java.awt.Dimension(80, 23));
        jtbNote.setName("jtbNote"); // NOI18N
        jtbNote.setPreferredSize(new java.awt.Dimension(80, 23));
        jtbNote.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jtbNoteItemStateChanged(evt);
            }
        });

        jtbBug.setBackground(new java.awt.Color(69, 104, 143));
        buttonGroupNotes.add(jtbBug);
        jtbBug.setForeground(new java.awt.Color(255, 255, 255));
        jtbBug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Bug.png"))); // NOI18N
        jtbBug.setToolTipText("Select the Bug label");
        jtbBug.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jtbBug.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbBug.setMaximumSize(new java.awt.Dimension(80, 23));
        jtbBug.setMinimumSize(new java.awt.Dimension(80, 23));
        jtbBug.setName("jtbBug"); // NOI18N
        jtbBug.setPreferredSize(new java.awt.Dimension(80, 23));
        jtbBug.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jtbBugItemStateChanged(evt);
            }
        });

        jtbToDo.setBackground(new java.awt.Color(69, 104, 143));
        buttonGroupNotes.add(jtbToDo);
        jtbToDo.setForeground(new java.awt.Color(255, 255, 255));
        jtbToDo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ToDo.png"))); // NOI18N
        jtbToDo.setToolTipText("Select the ToDo label");
        jtbToDo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jtbToDo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbToDo.setMaximumSize(new java.awt.Dimension(80, 23));
        jtbToDo.setMinimumSize(new java.awt.Dimension(80, 23));
        jtbToDo.setName("jtbToDo"); // NOI18N
        jtbToDo.setPreferredSize(new java.awt.Dimension(80, 23));
        jtbToDo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtbToDoStateChanged(evt);
            }
        });

        jtbRisk.setBackground(new java.awt.Color(69, 104, 143));
        buttonGroupNotes.add(jtbRisk);
        jtbRisk.setForeground(new java.awt.Color(255, 255, 255));
        jtbRisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Risk.png"))); // NOI18N
        jtbRisk.setToolTipText("Select the Risk label");
        jtbRisk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jtbRisk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbRisk.setMaximumSize(new java.awt.Dimension(80, 23));
        jtbRisk.setMinimumSize(new java.awt.Dimension(80, 23));
        jtbRisk.setName("jtbRisk"); // NOI18N
        jtbRisk.setPreferredSize(new java.awt.Dimension(80, 23));
        jtbRisk.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtbRiskStateChanged(evt);
            }
        });

        jtbProblem.setBackground(new java.awt.Color(69, 104, 143));
        buttonGroupNotes.add(jtbProblem);
        jtbProblem.setForeground(new java.awt.Color(255, 255, 255));
        jtbProblem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Problem.png"))); // NOI18N
        jtbProblem.setToolTipText("Select the Problem label");
        jtbProblem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jtbProblem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbProblem.setMaximumSize(new java.awt.Dimension(80, 23));
        jtbProblem.setMinimumSize(new java.awt.Dimension(80, 23));
        jtbProblem.setName("jtbProblem"); // NOI18N
        jtbProblem.setPreferredSize(new java.awt.Dimension(80, 23));
        jtbProblem.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtbProblemStateChanged(evt);
            }
        });

        jPanelNote.setBackground(new java.awt.Color(223, 223, 223));

        jTextAreaLog.setEditable(false);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jTextAreaLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jScrollPane2.setViewportView(jTextAreaLog);

        jButtonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/120/Stop.png"))); // NOI18N
        jButtonStop.setToolTipText("Close the session");
        jButtonStop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jButtonStop.setBorderPainted(false);
        jButtonStop.setContentAreaFilled(false);
        jButtonStop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonStop.setMaximumSize(new java.awt.Dimension(109, 52));
        jButtonStop.setMinimumSize(new java.awt.Dimension(109, 52));
        jButtonStop.setPreferredSize(new java.awt.Dimension(109, 52));
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        jButtonPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Prt. Sc..png"))); // NOI18N
        jButtonPicture.setToolTipText("Take a Picture");
        jButtonPicture.setBorderPainted(false);
        jButtonPicture.setContentAreaFilled(false);
        jButtonPicture.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPicture.setFocusPainted(false);
        jButtonPicture.setMaximumSize(new java.awt.Dimension(109, 52));
        jButtonPicture.setMinimumSize(new java.awt.Dimension(109, 52));
        jButtonPicture.setPreferredSize(new java.awt.Dimension(109, 52));
        jButtonPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPictureActionPerformed(evt);
            }
        });

        jButtonAdd.setBackground(new java.awt.Color(69, 104, 143));
        jButtonAdd.setForeground(new java.awt.Color(16, 36, 65));
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Add.png"))); // NOI18N
        jButtonAdd.setToolTipText("Add the note to your notes collection");
        jButtonAdd.setBorder(null);
        jButtonAdd.setBorderPainted(false);
        jButtonAdd.setContentAreaFilled(false);
        jButtonAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonAdd.setEnabled(false);
        jButtonAdd.setFocusPainted(false);
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jTextAreaNote.setToolTipText("Take your notes");
        jTextAreaNote.setAutoscrolls(false);
        jTextAreaNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jTextAreaNote.setMaximumSize(new java.awt.Dimension(164, 94));
        jTextAreaNote.setMinimumSize(new java.awt.Dimension(164, 94));
        jTextAreaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaNoteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaNote);

        jButtonClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/110/Chronometer.png"))); // NOI18N
        jButtonClock.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonClock.setBorderPainted(false);
        jButtonClock.setContentAreaFilled(false);
        jButtonClock.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonClock.setDefaultCapable(false);
        jButtonClock.setFocusPainted(false);
        jButtonClock.setFocusable(false);
        jButtonClock.setRolloverEnabled(false);

        jButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/120/Pause.png"))); // NOI18N
        jButtonPlay.setToolTipText("Pause the session");
        jButtonPlay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 36, 65)));
        jButtonPlay.setBorderPainted(false);
        jButtonPlay.setContentAreaFilled(false);
        jButtonPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPlay.setMaximumSize(new java.awt.Dimension(109, 52));
        jButtonPlay.setMinimumSize(new java.awt.Dimension(109, 52));
        jButtonPlay.setPreferredSize(new java.awt.Dimension(109, 52));
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        jButtonHide.setText("<");
        jButtonHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNoteLayout = new javax.swing.GroupLayout(jPanelNote);
        jPanelNote.setLayout(jPanelNoteLayout);
        jPanelNoteLayout.setHorizontalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButtonHide, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNoteLayout.createSequentialGroup()
                        .addComponent(jButtonClock, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoteLayout.createSequentialGroup()
                        .addComponent(jButtonPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanelNoteLayout.setVerticalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNoteLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jButtonAdd))
                    .addGroup(jPanelNoteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonClock, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNoteLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonHide)
                        .addContainerGap())))
        );

        jToolBar2.setRollover(true);

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
        jToolBar2.add(jButtonPath);

        jTextFieldPath.setEditable(false);
        jTextFieldPath.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextFieldPath.setText("Path");
        jToolBar2.add(jTextFieldPath);

        jCheckBoxAddNoteShortcut.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBoxAddNoteShortcut.setSelected(true);
        jCheckBoxAddNoteShortcut.setText("Ctrl+Enter adds a note");

        jCheckBoxAlwaysOnTop.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBoxAlwaysOnTop.setSelected(true);
        jCheckBoxAlwaysOnTop.setText("Always on top");
        jCheckBoxAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAlwaysOnTopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNoteSelectionLayout = new javax.swing.GroupLayout(jPanelNoteSelection);
        jPanelNoteSelection.setLayout(jPanelNoteSelectionLayout);
        jPanelNoteSelectionLayout.setHorizontalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtbRisk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbToDo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbBug, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbNote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbProblem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBoxAddNoteShortcut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxAlwaysOnTop))
                    .addComponent(jPanelNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelNoteSelectionLayout.setVerticalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                        .addComponent(jtbNote, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jtbBug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbToDo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbRisk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbProblem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxAddNoteShortcut)
                    .addComponent(jCheckBoxAlwaysOnTop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanelNoteSelection, java.awt.BorderLayout.CENTER);

        jToolBar1.setBackground(new java.awt.Color(223, 223, 223));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleName("frameRelyTest");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPictureActionPerformed
        takePicture();
    }//GEN-LAST:event_jButtonPictureActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        addNewNote();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void addNewNote() {
        if (jButtonAdd.isEnabled()) {
            writeNote();
            jTextAreaNote.setText("");
            jButtonAdd.setEnabled(false);
        }
    }

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

    private void enableAllControls(boolean enable) {
        jButtonAdd.setEnabled(enable && !jTextAreaNote.getText().isEmpty());
        jButtonPicture.setEnabled(enable);
        jTextAreaNote.setEnabled(enable);
        jtbBug.setEnabled(enable);
        jtbProblem.setEnabled(enable);
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

    private void jtbNoteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbNoteItemStateChanged
        // TODO add your handling code here:
        switchImage(jtbNote);
    }//GEN-LAST:event_jtbNoteItemStateChanged

    private void jtbBugItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbBugItemStateChanged
        // TODO add your handling code here:
        switchImage(jtbBug);
    }//GEN-LAST:event_jtbBugItemStateChanged

    private void jtbToDoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtbToDoStateChanged
        // TODO add your handling code here:
        switchImage(jtbToDo);
    }//GEN-LAST:event_jtbToDoStateChanged

    private void jtbRiskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtbRiskStateChanged
        // TODO add your handling code here:
        switchImage(jtbRisk);
    }//GEN-LAST:event_jtbRiskStateChanged

    private void jtbProblemStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtbProblemStateChanged
        // TODO add your handling code here:
        switchImage(jtbProblem);
    }//GEN-LAST:event_jtbProblemStateChanged

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        // TODO add your handling code here:
        paused = !paused;
        if (paused) {
            //jButtonPause.setText(Constants.LABEL_PAUSED + "...");
            jButtonPlay.setToolTipText(lCon.getValue(Texts.ContinueSession));
            writeToLog(Constants.LABEL_PAUSED, Constants.LABEL_PAUSED);
            enableAllControls(false);
            jButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Play.png")));
        } else {
          //  jButtonClock.setBackground(defaultColor);
            writeToLog(Constants.LABEL_CONTINUE, Constants.LABEL_CONTINUE);
            jButtonPlay.setToolTipText(lCon.getValue(Texts.PauseSession));
            enableAllControls(true);
            jButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Pause.png")));
        }
    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jButtonHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHideActionPerformed
        // TODO add your handling code here:
        System.out.println("size"+this.getSize());
        if("<".equals(jButtonHide.getText())){
            jButtonHide.setText(">");
            this.setSize(600, 258);
        }else{
            jButtonHide.setText("<");
            this.setSize(911, 258);
        }
        jTextAreaLog.setVisible(!jTextAreaLog.isVisible());
        jScrollPane2.setVisible(!jScrollPane2.isVisible());
    }//GEN-LAST:event_jButtonHideActionPerformed

    private void jCheckBoxAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAlwaysOnTopActionPerformed
        // TODO add your handling code here:
        this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jCheckBoxAlwaysOnTopActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
    }//GEN-LAST:event_formWindowOpened
       
    private void switchImage(JToggleButton button) {
        if (!button.isSelected()) {
            button.setBackground(colorSelected);
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/" + button.getName() + "Selected.png")));
        } else {
            button.setBackground(colorDeselected);
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/" + button.getName() + "Deselected.png")));
        }
    }

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
    private javax.swing.JButton jButtonClock;
    private javax.swing.JButton jButtonHide;
    private javax.swing.JButton jButtonPath;
    private javax.swing.JButton jButtonPicture;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JCheckBox jCheckBoxAddNoteShortcut;
    private javax.swing.JCheckBox jCheckBoxAlwaysOnTop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelNote;
    private javax.swing.JPanel jPanelNoteSelection;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldPath;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToggleButton jtbBug;
    private javax.swing.JToggleButton jtbNote;
    private javax.swing.JToggleButton jtbProblem;
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
