/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relytest.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class PropertiesMgr {
    private final String DefaultProp = "Properties.properties";
    private Properties prop = null;

    public PropertiesMgr() {  
        load();
    }
    public String getValue(String key){      
        return prop.getProperty(key);
    }
    public void setValue(String key, String newValue){      
         prop.setProperty(key, newValue);
         try{
            File f = new File(DefaultProp);
             OutputStream os = new FileOutputStream(f);
             prop.store(os, key);
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    public boolean containsKey(String key){
        return  prop.containsKey(key);
    }
    public boolean containsValue(String value){
        return  prop.containsValue(value);
    }
    private void load(){
        prop = new Properties();
        InputStream is =null;
        try{
            File f = new File(DefaultProp);
            is = new FileInputStream(f);
        }catch(Exception e){
            is=null;
        }
        try{
            if(is==null){
                is=getClass().getResourceAsStream(DefaultProp);
            }
            prop.load(is);
        }catch(Exception e){           
        }                
    }
}