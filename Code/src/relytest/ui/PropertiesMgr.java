/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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