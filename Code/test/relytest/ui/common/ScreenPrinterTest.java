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
public class ScreenPrinterTest {
    
    public ScreenPrinterTest() {
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
     * Test of print method, of class ScreenPrinter.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        String path = "tmp";
        ScreenPrinter instance = new ScreenPrinter();
        String expResult = ".jpg";
        String result = instance.print(path);
        assertEquals(expResult, result);

    }
    
}
