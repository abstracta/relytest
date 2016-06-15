/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relytest.ui.common;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import relytest.interfaces.IScreenPrinter;
/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class ScreenPrinter implements IScreenPrinter{
    private final String PicFormat =".jpg";
    @Override
    public String print(String path) {  
        try {

            Toolkit tool = Toolkit.getDefaultToolkit();
            Dimension d = tool.getScreenSize();
            Rectangle rect = new Rectangle(d);
            Robot robot = new Robot();
            Thread.sleep(1000);                  
            File f = new File(path+ PicFormat);
            BufferedImage img = robot.createScreenCapture(rect);
            ImageIO.write(img, "jpeg", f);
            tool.beep();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PicFormat;           
    }   
}
