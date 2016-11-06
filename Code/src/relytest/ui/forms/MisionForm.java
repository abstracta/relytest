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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import relytest.interfaces.IConfigFormLoad;
import relytest.interfaces.IScreenPrinter;
import relytest.interfaces.IWriter;
import relytest.internationalization.LanguageController;
import relytest.internationalization.Texts;
import relytest.ui.Constants;
import relytest.ui.PropertiesMgr;
import relytest.ui.common.CharterDto;
import relytest.ui.common.EnvironmentStats;
import relytest.ui.common.GroupNote;
import relytest.ui.common.Note;
import relytest.ui.common.Writer;

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
    //  private String paintApp = "mspaint.exe";
    private DefaultListModel model;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final IWriter writer = new Writer();
    private final Calendar calStart = Calendar.getInstance();

    private final Color defaultColor;
    private final LanguageController lCon = new LanguageController();

    private final java.awt.Color colorSelected = new java.awt.Color(223, 223, 223);
    private final java.awt.Color colorDeselected = new java.awt.Color(69, 104, 143);

    /**
     * Creates new form MisionForm
     *
     * @param dto
     */
    public MisionForm(CharterDto dto) {

        initComponents();
        model = new DefaultListModel();
        jListLog.setModel(model);

        charterDto = dto;
        charterDto.setFolderName(getDateNowToString() + "_Charter_" + dto.getCharterFileName());
        charterDto.setFolderNamePath(RunningPath + File.separator + charterDto.getFolderName());
        charterDto.setPicturePath(charterDto.getFolderNamePath() + File.separator + ScreenShotsDir + File.separator);
        createMisionFolders();
//        jTextFieldPath.setText(charterDto.getFolderName());

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

//        DefaultCaret caret = (DefaultCaret) jTextAreaLog.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //jButtonConfig.setVisible(false);
        loadLanguage();

        jTextAreaNote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int code = ke.getKeyCode();
                int modifiers = ke.getModifiers();
                if (code == KeyEvent.VK_ENTER && modifiers == KeyEvent.CTRL_MASK) {
                    if (jCheckBoxAddNoteShortcut.isSelected()) {
                        addNewNote();
                    }
                }
            }
        });

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTimeDisplay();
            }
        });

        setButtonsDefaultColor();
        this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
    }

    private void setButtonsDefaultColor() {
        switchImage(jtbBug);
        switchImage(jtbNote);
        switchImage(jtbProblem);
        switchImage(jtbRisk);
        switchImage(jtbToDo);
    }

    private void loadLanguage() {
        //jButtonPath.setText("");
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

//        paintApp = p.getValue(Constants.KEY_PAINT_APP);
    }

    private Boolean displayButton(PropertiesMgr p, String buttonName) {
        if (p.getValue(buttonName) != null) {
            return Boolean.valueOf(p.getValue(buttonName));
        }
        return true;
    }

    public void Start() {
        elapsedTimePassExpected = false;
        try {
            date = sdf.parse(charterDto.getTotalTime());
        } catch (ParseException ex) {
            Logger.getLogger(MisionForm.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                //  System.out.println("elapsedTimePassExpected "+sdf.format(date));
            } else {
                //   System.out.println("Not elapsedTimePassExpected "+sdf.format(date));
                cal.add(Calendar.SECOND, -1);
                date = cal.getTime();
                jButtonClock.setText(sdf.format(date));
                elapsedTimePassExpected = elapsedTimePassExpected || sdf.format(date).equals("00:00:00");
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

//    private void executePaint(String pictureName) {
//        try {
//            String[] params = new String[2];
//            params[0] = paintApp;
//            params[1] = pictureName;
//            Process p = new ProcessBuilder(params).start();
//            p.waitFor();
//        } catch (IOException | InterruptedException ex) {
//            Logger.getLogger(MisionForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private String print() {

       // CaptureScreen capture = new CaptureScreen();
        String pictureName = "";
        try {
            String now = getDateNowToString();

            pictureName = "Pic_" + now;
            //  IScreenPrinter printer = new ScreenPrinter();

            PropertiesMgr p = new PropertiesMgr();
            Boolean hide = Boolean.valueOf(p.getValue(Constants.KEY_HIDE_RELY_TEST));
            if (hide) {
                this.setVisible(false);
            }
            // String picFormat = printer.print(charterDto.getPicturePath() + pictureName);
            IScreenPrinter c = new CaptureScreen();
            String picFormat = c.print(charterDto.getPicturePath() + pictureName);

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
//        if (paintApp != null) {
        this.setAlwaysOnTop(false);
        String pic = print();
//
//        PropertiesMgr p = new PropertiesMgr();
//        Boolean open = Boolean.valueOf(p.getValue(Constants.KEY_OPEN_IMAGE_EDITOR));
//            if (open) {
//                executePaint(charterDto.getPicturePath() + pic);
//            }
        this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
        // }        
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

    private Note addNote(String label, String text, String timeStamp) {
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
        return note;
    }

    private void writeToLog(String label, String text) {
        boolean setStartTime = Constants.LABEL_SESSION_STARTED.equals(label);

        if (setStartTime) {
            charterDto.setStartTime(getDateNow());
        }
        String timeStamp = getDateNowToString();

        String newLogLine = timeStamp + "[" + label + "] " + text;
        writer.writeToFile(RunningPath + File.separator + charterDto.getFolderName() + File.separator + LogFile, newLogLine);

        Note newNote = addNote(label, text, timeStamp);

        model.addElement(newNote);
        jListLog.setSelectedIndex(model.getSize());
        jListLog.ensureIndexIsVisible(model.getSize());
        JScrollBar vertical = jScrollPane3.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

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
        jPanelLog = new javax.swing.JPanel();
        jPanelCommands = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonPicture = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jButtonClock = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListLog = new javax.swing.JList();
        jButtonStop = new javax.swing.JButton();
        jPanelNoteSelection = new javax.swing.JPanel();
        jPanelCategories = new javax.swing.JPanel();
        jtbBug = new javax.swing.JToggleButton();
        jtbNote = new javax.swing.JToggleButton();
        jtbProblem = new javax.swing.JToggleButton();
        jtbRisk = new javax.swing.JToggleButton();
        jtbToDo = new javax.swing.JToggleButton();
        jPanelNote = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jPanelOptions = new javax.swing.JPanel();
        jButtonHide = new javax.swing.JButton();
        jButtonPath = new javax.swing.JButton();
        jCheckBoxAlwaysOnTop = new javax.swing.JCheckBox();
        jCheckBoxAddNoteShortcut = new javax.swing.JCheckBox();
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

        jButtonClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/110/Chronometer.png"))); // NOI18N
        jButtonClock.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonClock.setBorderPainted(false);
        jButtonClock.setContentAreaFilled(false);
        jButtonClock.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonClock.setDefaultCapable(false);
        jButtonClock.setFocusPainted(false);
        jButtonClock.setFocusable(false);
        jButtonClock.setRolloverEnabled(false);

        jListLog.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListLogMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListLog);

        javax.swing.GroupLayout jPanelCommandsLayout = new javax.swing.GroupLayout(jPanelCommands);
        jPanelCommands.setLayout(jPanelCommandsLayout);
        jPanelCommandsLayout.setHorizontalGroup(
            jPanelCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommandsLayout.createSequentialGroup()
                .addComponent(jButtonClock, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
            .addGroup(jPanelCommandsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCommandsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanelCommandsLayout.setVerticalGroup(
            jPanelCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommandsLayout.createSequentialGroup()
                .addGroup(jPanelCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanelCommandsLayout.createSequentialGroup()
                        .addComponent(jButtonClock, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdd)))
                .addGap(0, 0, 0))
        );

        jButtonStop.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonStop.setText("End Session");
        jButtonStop.setToolTipText("Close the session and go to the session questionaire");
        jButtonStop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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

        javax.swing.GroupLayout jPanelLogLayout = new javax.swing.GroupLayout(jPanelLog);
        jPanelLog.setLayout(jPanelLogLayout);
        jPanelLogLayout.setHorizontalGroup(
            jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addComponent(jPanelCommands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLogLayout.setVerticalGroup(
            jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addComponent(jPanelCommands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
        );

        jButtonStop.getAccessibleContext().setAccessibleDescription("Close the session and go to the session questionaire");

        getContentPane().add(jPanelLog, java.awt.BorderLayout.CENTER);

        jPanelNoteSelection.setBackground(new java.awt.Color(223, 223, 223));

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
        jtbBug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbBugActionPerformed(evt);
            }
        });

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

        jPanelNote.setBackground(new java.awt.Color(223, 223, 223));

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jTextAreaNote.setToolTipText("Take your notes");
        jTextAreaNote.setAutoscrolls(false);
        jTextAreaNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextAreaNote.setMaximumSize(new java.awt.Dimension(164, 94));
        jTextAreaNote.setMinimumSize(new java.awt.Dimension(164, 94));
        jTextAreaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaNoteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaNote);

        jButtonHide.setText("<");
        jButtonHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHideActionPerformed(evt);
            }
        });

        jButtonPath.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButtonPath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/relytest/ui/forms/Folder.png"))); // NOI18N
        jButtonPath.setText("Open folder");
        jButtonPath.setToolTipText("Take a look at your workspace");
        jButtonPath.setBorderPainted(false);
        jButtonPath.setContentAreaFilled(false);
        jButtonPath.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPath.setFocusable(false);
        jButtonPath.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButtonPath.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathActionPerformed(evt);
            }
        });

        jCheckBoxAlwaysOnTop.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBoxAlwaysOnTop.setText("Always on top");
        jCheckBoxAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAlwaysOnTopActionPerformed(evt);
            }
        });

        jCheckBoxAddNoteShortcut.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBoxAddNoteShortcut.setSelected(true);
        jCheckBoxAddNoteShortcut.setText("Ctrl+Enter adds a note");

        javax.swing.GroupLayout jPanelOptionsLayout = new javax.swing.GroupLayout(jPanelOptions);
        jPanelOptions.setLayout(jPanelOptionsLayout);
        jPanelOptionsLayout.setHorizontalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButtonPath)
                .addGap(0, 0, 0)
                .addComponent(jCheckBoxAddNoteShortcut)
                .addGap(0, 0, 0)
                .addComponent(jCheckBoxAlwaysOnTop)
                .addGap(0, 0, 0)
                .addComponent(jButtonHide)
                .addGap(0, 0, 0))
        );
        jPanelOptionsLayout.setVerticalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonPath)
                    .addComponent(jCheckBoxAddNoteShortcut)
                    .addComponent(jCheckBoxAlwaysOnTop)
                    .addComponent(jButtonHide))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanelNoteLayout = new javax.swing.GroupLayout(jPanelNote);
        jPanelNote.setLayout(jPanelNoteLayout);
        jPanelNoteLayout.setHorizontalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0))
        );
        jPanelNoteLayout.setVerticalGroup(
            jPanelNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanelCategoriesLayout = new javax.swing.GroupLayout(jPanelCategories);
        jPanelCategories.setLayout(jPanelCategoriesLayout);
        jPanelCategoriesLayout.setHorizontalGroup(
            jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriesLayout.createSequentialGroup()
                .addGroup(jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtbRisk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbToDo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbBug, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbNote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbProblem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanelNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelCategoriesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jtbBug, jtbNote, jtbProblem, jtbRisk, jtbToDo});

        jPanelCategoriesLayout.setVerticalGroup(
            jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelCategoriesLayout.createSequentialGroup()
                        .addComponent(jtbNote, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jtbBug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jtbToDo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jtbRisk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jtbProblem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanelCategoriesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jtbBug, jtbNote, jtbProblem, jtbRisk, jtbToDo});

        javax.swing.GroupLayout jPanelNoteSelectionLayout = new javax.swing.GroupLayout(jPanelNoteSelection);
        jPanelNoteSelection.setLayout(jPanelNoteSelectionLayout);
        jPanelNoteSelectionLayout.setHorizontalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoteSelectionLayout.createSequentialGroup()
                .addComponent(jPanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanelNoteSelectionLayout.setVerticalGroup(
            jPanelNoteSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoteSelectionLayout.createSequentialGroup()
                .addComponent(jPanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(jPanelNoteSelection, java.awt.BorderLayout.LINE_START);

        jToolBar1.setBackground(new java.awt.Color(223, 223, 223));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleName("frameRelyTest");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setIconImage(new ImageIcon(getClass().getResource("RelyTest_logo.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    private final int WindowHeight = 193;
    private final int WindowWidthLong = 778;
    private final int WindowWidthShort = 540;
    private void jButtonHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHideActionPerformed
        // TODO add your handling code here:
        System.out.println("size"+this.getSize());
        if ("<".equals(jButtonHide.getText())) {
            jButtonHide.setText(">");
            this.setSize(WindowWidthShort, WindowHeight);
        } else {
            jButtonHide.setText("<");
            this.setSize(WindowWidthLong, WindowHeight);
        }
        jListLog.setVisible(!jListLog.isVisible());
        jScrollPane3.setVisible(!jScrollPane3.isVisible());
    }//GEN-LAST:event_jButtonHideActionPerformed

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

    private void jCheckBoxAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAlwaysOnTopActionPerformed
        // TODO add your handling code here:
        this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jCheckBoxAlwaysOnTopActionPerformed

    private void jListLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListLogMouseClicked
        // TODO add your handling code here:
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            System.out.println("index: " + index);
            Note noteSelected = (Note) model.getElementAt(index);
            if (noteSelected != null && noteSelected.getLabel()=="Picture Taken") {
                CaptureScreen c = new CaptureScreen();
                c.showPic(charterDto.getPicturePath() +noteSelected.getText());
            }
        }
    }//GEN-LAST:event_jListLogMouseClicked

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

    private void jTextAreaNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaNoteKeyReleased
        // TODO add your handling code here:
        jButtonAdd.setEnabled(!jTextAreaNote.getText().isEmpty());
    }//GEN-LAST:event_jTextAreaNoteKeyReleased

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        addNewNote();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPictureActionPerformed
        takePicture();
    }//GEN-LAST:event_jButtonPictureActionPerformed

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
            timer.stop();
            dispose();
        }
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void jtbProblemStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtbProblemStateChanged
        // TODO add your handling code here:
        switchImage(jtbProblem);
    }//GEN-LAST:event_jtbProblemStateChanged

    private void jtbRiskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtbRiskStateChanged
        // TODO add your handling code here:
        switchImage(jtbRisk);
    }//GEN-LAST:event_jtbRiskStateChanged

    private void jtbToDoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtbToDoStateChanged
        // TODO add your handling code here:
        switchImage(jtbToDo);
    }//GEN-LAST:event_jtbToDoStateChanged

    private void jtbBugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbBugActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbBugActionPerformed

    private void jtbBugItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbBugItemStateChanged
        // TODO add your handling code here:
        switchImage(jtbBug);
    }//GEN-LAST:event_jtbBugItemStateChanged

    private void jtbNoteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbNoteItemStateChanged
        // TODO add your handling code here:
        switchImage(jtbNote);
    }//GEN-LAST:event_jtbNoteItemStateChanged

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
    private javax.swing.JList jListLog;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCategories;
    private javax.swing.JPanel jPanelCommands;
    private javax.swing.JPanel jPanelLog;
    private javax.swing.JPanel jPanelNote;
    private javax.swing.JPanel jPanelNoteSelection;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JToolBar jToolBar1;
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
