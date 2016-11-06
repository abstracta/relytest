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
 * @author Gabriela Sanchez - Miguel Sanchez
 */
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import javax.swing.JComponent;
import javax.imageio.*;
import java.awt.*;
import java.io.*;

public class JImageCropComponent extends JComponent implements MouseListener, MouseMotionListener
{
   private BufferedImage img;
   private int x1, y1, x2, y2;
   private boolean cropping=false;

   public JImageCropComponent(BufferedImage img)
   {
       this.img = img;
       this.addMouseListener(this);
       this.addMouseMotionListener(this);
   }

   public void setImage(BufferedImage img)
   {
       this.img = img;
   }

   public BufferedImage getImage()
   {
       return this.img;
   }

   @Override
   public void paintComponent(Graphics g)
   {
      g.drawImage(img, 0, 0, this);
      if (cropping)
      {
          // Paint the area we are going to crop.
          g.setColor(Color.RED);
          g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2));
      }
   }

   @Override
   public void mousePressed(MouseEvent evt)
   {
       this.x1 = evt.getX();
       this.y1 = evt.getY();
   }

   @Override
   public void mouseReleased(MouseEvent evt)
   {
       this.cropping = false;
       // Now we crop the image;
       // This is the method a wrote in the other snipped
       BufferedImage cropped = cropImage(this.img,Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2));
       // Now you have the cropped image;
       // You have to choose what you want to do with it
       this.img = cropped;
   }
private BufferedImage cropImage(BufferedImage image, int x, int y, int w, int h){
 
        
        BufferedImage subImgage = image.getSubimage(x, y, w, h);
        return subImgage;
 
    
}
   private BufferedImage cropImage(File filePath, int x, int y, int w, int h){
    try {
        BufferedImage originalImgage = ImageIO.read(filePath);
        BufferedImage subImgage = originalImgage.getSubimage(x, y, w, h);
        return subImgage;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}
   @Override
   public void mouseDragged(MouseEvent evt)
   {
       cropping = true;
       this.x2 = evt.getX();
       this.y2 = evt.getY();
   }

   //TODO: Implement the other unused methods from Mouse(Motion)Listener

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
