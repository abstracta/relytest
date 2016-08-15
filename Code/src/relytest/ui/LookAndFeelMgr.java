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

import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class LookAndFeelMgr {

    public void setLookAndFeel(int selectedLaf) {
        try {
            Properties props = getLAFProps();
            switch (selectedLaf) {
                case Constants.LAF_ACRYL:
                    // First set the theme of the look and feel. This must be done first because there
                    // is some static initializing (color values etc.) when calling setTheme.
                    // Another reason is that the theme variables are shared with all look and feels, so
                    // without calling the setTheme method the look and feel will look ugly (wrong colors).
                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(props);
                    // Now we can set the look and feel
                    UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    break;
                case Constants.LAF_AERO:
                    com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    break;
                case Constants.LAF_ALUMINIUM:
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                    break;
                case Constants.LAF_BERNSTEIN:
                    com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                    break;
                case Constants.LAF_FAST:
                    com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                    break;
                case Constants.LAF_GRAPHITE:
                    com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                    break;
                case Constants.LAF_HIFI:
                    com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    break;
                case Constants.LAF_LUNA:
                    com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                    break;
                case Constants.LAF_MCWIN:
                    com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                    break;
                case Constants.LAF_MINT:
                    com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                    break;
                case Constants.LAF_NOIRE:
                    com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                    break;
                case Constants.LAF_SMART:
                    com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                    break;
                case Constants.LAF_TEXTURE:
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                    break;
                default:
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            }
            // Tell all components that look and feel has changed.
//            Window windows[] = Window.getWindows();
//            for (Window window : windows) {
//                if (window.isDisplayable()) {
//                    SwingUtilities.updateComponentTreeUI(window);
//                }
//            }
            // Maybe selected item is not visible after changing the look and feel so we correct this
//            scrollSelectedToVisible(lafList);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    } // end setLookAndFeel

    public Properties getLAFProps() {
        return new Properties();
    }
}
