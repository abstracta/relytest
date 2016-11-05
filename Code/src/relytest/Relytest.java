/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest;

import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import relytest.ui.Constants;
import relytest.ui.LookAndFeelMgr;
import relytest.ui.PropertiesMgr;
import relytest.ui.forms.MainForm;
import relytest.ui.forms.SessionsForm;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class Relytest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PropertiesMgr p = new PropertiesMgr();
        String lookAndFeel = p.getValue(Constants.KEY_LOOK_AND_FEEL);
        Integer lookAndFeelInt = 0;
        if (lookAndFeel != null) {
            lookAndFeelInt = Integer.valueOf(lookAndFeel);
        }
        LookAndFeelMgr laf = new LookAndFeelMgr();
        laf.setLookAndFeel(lookAndFeelInt);                               
        MainForm main = new MainForm();
        //main.setIconImage(Toolkit.getDefaultToolkit().getImage("Aplicación_1.png"));
       // main.setIconImage(new ImageIcon("Logo_final.png").getImage());
        main.setLocationRelativeTo(null);   
        main.setVisible(true);
    }

}
