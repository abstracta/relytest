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
public class WriterTest {
    
    public WriterTest() {
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
     * Test of writeToFile method, of class Writer.
     */
    @Test
    public void testWriteToFile() {
        System.out.println("writeToFile");
        String file = "D:\\REPOSITORIO\\Proyecto\\relytest\\Prototype001\\build\\classes\\relytest";
        String text = "lot of text";
        Writer instance = new Writer();
        boolean expResult = false;
        boolean result = instance.writeToFile(file, text);
        assertEquals(expResult, result);

    }
    
}
