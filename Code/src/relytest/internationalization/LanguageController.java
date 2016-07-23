/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.internationalization;

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
public class LanguageController {

    public final String LanguageKey = "Language";
    private final String DefaultProp = "Properties.properties";
    private final String DefaultLanguageProp = "Relytest_en_US.properties";
    private Properties prop = null;
    private Properties propLanguage = null;
    private String selectedLanguage;

    public LanguageController() {
        prop = new Properties();
        propLanguage = new Properties();
        load(DefaultProp,prop);
String t = System.getProperty("user.dir");
        if (prop.containsKey(LanguageKey)) {
            String langID = prop.getProperty(LanguageKey);
            selectedLanguage = "RelyTest_" + langID + ".properties";
            File f = new File(selectedLanguage);
            if (!(f.exists() && !f.isDirectory())) {
                selectedLanguage = DefaultLanguageProp;
            }
        } else {
            selectedLanguage = DefaultLanguageProp;
        }
        load(selectedLanguage,propLanguage);
    }

    public String getValue(Texts key) {
        String strKey = key.toString();
        return propLanguage.getProperty(strKey);
    }    

    private void load(String propToLoad,Properties properties) {
        InputStream is = null;
        try {
            File f = new File(propToLoad);
            is = new FileInputStream(f);
        } catch (Exception e) {
            is = null;
        }
        try {
            if (is == null) {
                is = getClass().getResourceAsStream(propToLoad);
            }
            properties.load(is);
        } catch (Exception e) {
        }
    }
}
