/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.common;

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
public class NoteTest {
    
    public NoteTest() {
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
     * Test of getText method, of class Note.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        Note instance = new Note();
        String expResult = "PRUEBA 1 2 3";
        instance.setText(expResult);
        
        String result = instance.getText();
        assertEquals(expResult, result);      
    }

    /**
     * Test of setText method, of class Note.
     */
    @Test
    public void testSetText() {
        System.out.println("setText");
         Note instance = new Note();
        String expResult = "PRUEBA 1 2 3 4 5 6";
        instance.setText(expResult);
        
        String result = instance.getText();
        assertEquals(expResult, result); 
    }

    /**
     * Test of getTimeStamp method, of class Note.
     */
    @Test
    public void testGetTimeStamp() {
        System.out.println("getTimeStamp");
        Note instance = new Note();
        String expResult = "13246579";
        instance.setTimeStamp(expResult);
        String result = instance.getTimeStamp();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setTimeStamp method, of class Note.
     */
    @Test
    public void testSetTimeStamp() {
        System.out.println("setTimeStamp");
          Note instance = new Note();
        String expResult = "13246546546546546579";
        instance.setTimeStamp(expResult);
        String result = instance.getTimeStamp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Note.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        long expResult = 1000L;
        Note instance = new Note(expResult, "132", "1324659879", "Label");        
        long result = instance.getId();
        assertEquals(expResult, result);      
    }

    /**
     * Test of setId method, of class Note.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Note instance = new Note();
        long expResult = 1000L;
        instance.setId(expResult);
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLabel method, of class Note.
     */
    @Test
    public void testGetLabel() {
        System.out.println("getLabel");
         String expResult = "445454";
        Note instance = new Note(0,"text 654654654" , "131", expResult);
       
        String result = instance.getLabel();
        assertEquals(expResult, result);  
    }

    /**
     * Test of setLabel method, of class Note.
     */
    @Test
    public void testSetLabel() {
        System.out.println("setLabel");
        String label = "test label";
        Note instance = new Note(0, "445454", "131", "545");
        instance.setLabel(label);
   
        String expResult = label;
        String result = instance.getLabel();
        assertEquals(expResult, result);  
    }
    
}
