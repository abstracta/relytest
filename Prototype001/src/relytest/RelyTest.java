/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest;

import relytest.ui.Constants;
import relytest.ui.LookAndFeelMgr;
import relytest.ui.folder.MainForm;
import relytest.ui.PropertiesMgr;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class RelyTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
            // TODO code application logic here
            PropertiesMgr p = new PropertiesMgr();
            String lookAndFeel = p.getValue(Constants.KEY_LOOK_AND_FEEL);
            Integer lookAndFeelInt = 0;
            if (lookAndFeel != null) {
                lookAndFeelInt = Integer.valueOf(lookAndFeel);
            }
            LookAndFeelMgr laf = new LookAndFeelMgr();
            laf.setLookAndFeel(lookAndFeelInt);
//            if(lookAndFeel==null){
//                lookAndFeel="com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
//            }
//            UIManager.setLookAndFeel(lookAndFeel);
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(RelyTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        MainForm main = new MainForm();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }

}
