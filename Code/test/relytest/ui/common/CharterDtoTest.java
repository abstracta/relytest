/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.common;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class CharterDtoTest {

    public CharterDtoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class CharterDto.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        CharterDto instance = new CharterDto("Test name");
        String expResult = "Test name";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class CharterDto.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Test name2";
        CharterDto instance = new CharterDto("Test name");
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getGroupNotes method, of class CharterDto.
     */
    @Test
    public void testGetGroupNotes() {
        System.out.println("getGroupNotes");
        CharterDto instance = new CharterDto("Test name");
        GroupNote[] expResult = {new GroupNote("S1"), new GroupNote("S2")};

        instance.setGroupNotes(expResult);

        GroupNote[] result = instance.getGroupNotes();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupNotes method, of class CharterDto.
     */
    @Test
    public void testSetGroupNotes() {
        System.out.println("setGroupNotes");
        CharterDto instance = new CharterDto("Test name");
        GroupNote[] expResult = {new GroupNote("S1"), new GroupNote("S2"), new GroupNote("S1"), new GroupNote("S2"), new GroupNote("S1"), new GroupNote("S2")};

        instance.setGroupNotes(expResult);

        GroupNote[] result = instance.getGroupNotes();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of getPathHtml method, of class CharterDto.
     */
    @Test
    public void testGetPathHtml() {
        System.out.println("getPathHtml");
        CharterDto instance = new CharterDto("Test name");
        instance.setPathHtml("www.relytest.uy");
        String expResult = "www.relytest.uy";
        String result = instance.getPathHtml();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCharterFileName method, of class CharterDto.
     */
    @Test
    public void testGetCharterFileName() {
        System.out.println("getCharterFileName");
        CharterDto instance = new CharterDto("Test name");
        String expResult = "Test name 2";
        instance.setCharterFileName(expResult);
        String result = instance.getCharterFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setCharterFileName method, of class CharterDto.
     */
    @Test
    public void testSetCharterFileName() {
        System.out.println("setCharterFileName");
        String charterFileName = "";
        CharterDto instance = new CharterDto("Test name");
        instance.setCharterFileName(charterFileName);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalTime method, of class CharterDto.
     */
    @Test
    public void testGetTotalTime() {
        System.out.println("getTotalTime");
        CharterDto instance = new CharterDto("Test name");
        String expResult = "13246579";
        instance.setTotalTime(expResult);
        String result = instance.getTotalTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTotalTime method, of class CharterDto.
     */
    @Test
    public void testSetTotalTime() {
        System.out.println("setTotalTime");
        String totalTime = "1234658878787";
        CharterDto instance = new CharterDto("Test name");
        instance.setTotalTime(totalTime);

        String expResult = totalTime;
        String result = instance.getTotalTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPathHtml method, of class CharterDto.
     */
    @Test
    public void testSetPathHtml() {
        System.out.println("setPathHtml");
        String pathHtml = "www.google.com";
        CharterDto instance = new CharterDto("Test name");
        instance.setPathHtml(pathHtml);
        String expResult = pathHtml;
        String result = instance.getPathHtml();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFolderNamePath method, of class CharterDto.
     */
    @Test
    public void testGetFolderNamePath() {
        System.out.println("getFolderNamePath");
        CharterDto instance = new CharterDto("Test name");
        String expResult = "c:\\tmp\\relytest";
        instance.setFolderNamePath(expResult);
        String result = instance.getFolderNamePath();
        assertEquals(expResult, result);

    }

    /**
     * Test of setFolderNamePath method, of class CharterDto.
     */
    @Test
    public void testSetFolderNamePath() {
        System.out.println("setFolderNamePath");
        String folderNamePath = "c:\\relytest\\test\\test";
        CharterDto instance = new CharterDto("Test name");
        instance.setFolderNamePath(folderNamePath);

        String expResult = folderNamePath;
        String result = instance.getFolderNamePath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFolderName method, of class CharterDto.
     */
    @Test
    public void testGetFolderName() {
        System.out.println("getFolderName");
        CharterDto instance = new CharterDto("Test name");
        String expResult = "Funcionalidad 001";
        instance.setFolderName(expResult);
        String result = instance.getFolderName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setFolderName method, of class CharterDto.
     */
    @Test
    public void testSetFolderName() {
        System.out.println("setFolderName");
        String folderName = "A NAME WITH MANY LETTERS IS THIS TEST NAME FOR A FODER";
        CharterDto instance = new CharterDto("Test name");
        instance.setFolderName(folderName);

        String expResult = folderName;
        String result = instance.getFolderName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPicturePath method, of class CharterDto.
     */
    @Test
    public void testGetPicturePath() {
        System.out.println("getPicturePath");
        CharterDto instance = new CharterDto("Test name");
        String expResult = "pic.efsdfsdf sdf sdf sd";
        instance.setPicturePath(expResult);
        String result = instance.getPicturePath();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setPicturePath method, of class CharterDto.
     */
    @Test
    public void testSetPicturePath() {
        System.out.println("setPicturePath");
        String picturePath = "D:\\REPOSITORIO\\Proyecto\\relytest\\Prototype001";
        CharterDto instance = new CharterDto("Test name");
        instance.setPicturePath(picturePath);
        
        String expResult = picturePath;
        String result = instance.getPicturePath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNotesTaken method, of class CharterDto.
     */
    @Test
    public void testGetNotesTaken() {
        System.out.println("getNotesTaken");
        CharterDto instance = new CharterDto("Test name");
        ArrayList<Note> expResult  = new ArrayList<>();
        instance.setNotesTaken(expResult);
        ArrayList<Note> result = instance.getNotesTaken();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNotesTaken method, of class CharterDto.
     */
    @Test
    public void testSetNotesTaken() {
        System.out.println("setNotesTaken");
        ArrayList<Note> notesTaken = new ArrayList<>();
        notesTaken.add(new Note());
        CharterDto instance = new CharterDto("Test name");
        instance.setNotesTaken(notesTaken);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
