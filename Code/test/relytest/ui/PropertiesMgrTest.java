/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui;

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
public class PropertiesMgrTest {
    
    public PropertiesMgrTest() {
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
     * Test of getValue method, of class PropertiesMgr.
     */
    @Test
    public void testGetValue() {
      //  System.out.println("getValue");
        String key = "test";
        PropertiesMgr instance = new PropertiesMgr();
        String expResult = "123";
        instance.setValue(key, expResult);
        String result = instance.getValue(key);
        assertEquals(expResult, result);
   
    }

    /**
     * Test of setValue method, of class PropertiesMgr.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        String key = "test";
        String newValue = "123erwerewr";
        PropertiesMgr instance = new PropertiesMgr();
        instance.setValue(key, newValue);       
        String expResult = newValue;    
        String result = instance.getValue(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsKey method, of class PropertiesMgr.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        String key = "no key";
        PropertiesMgr instance = new PropertiesMgr();
        boolean expResult = false;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
     
    }
    
    /**
     * Test of containsKey method, of class PropertiesMgr.
     */
    @Test
    public void testContainsKey2() {
        System.out.println("containsKey2");
        String key = "test";
        PropertiesMgr instance = new PropertiesMgr();
        boolean expResult = true;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);     
    }

    /**
     * Test of containsValue method, of class PropertiesMgr.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");
        String value = "not exits";
        PropertiesMgr instance = new PropertiesMgr();
        boolean expResult = false;
        boolean result = instance.containsValue(value);
        assertEquals(expResult, result);
    
    }
    
     @Test
    public void testContainsValue2() {
        System.out.println("containsValue2");
         String key = "test";
        String newValue = "we wer wer wer";
        PropertiesMgr instance = new PropertiesMgr();
        instance.setValue(key, newValue);   
        
        boolean expResult = true;
        boolean result = instance.containsValue(newValue);
        assertEquals(expResult, result);
    
    }
    
}
