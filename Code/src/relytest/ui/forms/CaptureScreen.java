/*
 * Copyright (C) 2016 MS
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

package relytest.ui.forms;

/**
 *
 * @author MS
 */
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import relytest.interfaces.IScreenPrinter;

public class CaptureScreen implements ActionListener,IScreenPrinter {

    private JFrame f = new JFrame("Screen Capture");
    private JPanel pane = new JPanel();
    private JButton capture = new JButton("Capture");
    private JFrame d = new JFrame();
    private JScrollPane scrollPane = new JScrollPane();
    private JLabel l = new JLabel();
    private Point location;
    private final String PicFormat = ".jpg";

    public CaptureScreen() {
        capture.setActionCommand("CaptureScreen");
        capture.setFocusPainted(false);
        capture.addActionListener(this);
        capture.setPreferredSize(new Dimension(300, 50));
        pane.add(capture);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(pane);
        f.setLocation(100, 100);
        f.pack();
        f.setVisible(false);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                createPicContainer();
            }
        });
    }
    File file;
    @Override
    public String print(String path) {
        try {
           
            Rectangle screenRect = new Rectangle(0, 0, 0, 0);
            for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
                screenRect = screenRect.union(gd.getDefaultConfiguration().getBounds());
            }
            Robot robot = new Robot();
            Thread.sleep(1000);
            file = new File(path + PicFormat);
            BufferedImage bI = robot.createScreenCapture(screenRect);
            ImageIO.write(bI, "jpeg", file);     
            showPic(bI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PicFormat;
    }
    public void capture(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // gets the screen size
            Robot r;
            BufferedImage bI;
            try {
                r = new Robot(); // creates robot not sure exactly how it works
                Thread.sleep(1000); // waits 1 second before capture
                bI = r.createScreenCapture(new Rectangle(d)); // tells robot to capture the screen
                showPic(bI);
                saveImage(bI);
            } catch (AWTException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
    }

    private void createPicContainer() {
        l.setPreferredSize(new Dimension(700, 500));
        scrollPane = new JScrollPane(l,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.white);
        scrollPane.getViewport().setBackground(Color.white);
        d.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        d.add(scrollPane);        
        d.pack();
        d.setVisible(false);
        d.setIconImage(new ImageIcon(getClass().getResource("RelyTest_logo.png")).getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CaptureScreen")) {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // gets the screen size
            Robot r;
            BufferedImage bI;
            try {
                r = new Robot(); // creates robot not sure exactly how it works
                Thread.sleep(1000); // waits 1 second before capture
                bI = r.createScreenCapture(new Rectangle(d)); // tells robot to capture the screen
                showPic(bI);
                saveImage(bI);
            } catch (AWTException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    
    private void saveImage(BufferedImage bI) {
        try {
            ImageIO.write(bI, "JPG", new File("screenShot.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPic(String path){
        try {
            BufferedImage img = ImageIO.read(new File(path));
            file = new File(path);
            showPic(img);
        } catch (IOException ex) {
            Logger.getLogger(CaptureScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPic(BufferedImage bI) {
        ImageIcon pic = new ImageIcon(bI);
        l.setIcon(pic);
        l.revalidate();
        l.repaint();
        d.setVisible(false);
        d.setTitle(file.getName());
//        location = f.getLocationOnScreen();
//        int x = location.x;
//        int y = location.y;
//        d.setLocation(x, y + f.getHeight());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                d.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                CaptureScreen cs = new CaptureScreen();
            }
        });
    }
}
