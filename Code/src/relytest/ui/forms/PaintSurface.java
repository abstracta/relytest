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

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class PaintSurface extends JFrame {
    ArrayList<Shape> shapes = new ArrayList<>();

    Point startDrag, endDrag;

    public PaintSurface() {
      this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          startDrag = new Point(e.getX(), e.getY());
          endDrag = startDrag;
          repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
          Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
          shapes.add(r);
          startDrag = null;
          endDrag = null;
          repaint();
        }
      });

      this.addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
          endDrag = new Point(e.getX(), e.getY());
          repaint();
        }
      });
    }
    private void paintBackground(Graphics2D g2){
      g2.setPaint(Color.LIGHT_GRAY);
      for (int i = 0; i < getSize().width; i += 10) {
        Shape line = new Line2D.Float(i, 0, i, getSize().height);
        g2.draw(line);
      }

      for (int i = 0; i < getSize().height; i += 10) {
        Shape line = new Line2D.Float(0, i, getSize().width, i);
        g2.draw(line);
      }

      
    }
    @Override
    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      paintBackground(g2);
      Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN , Color.RED, Color.BLUE, Color.PINK};
      int colorIndex = 0;

      g2.setStroke(new BasicStroke(2));
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

      for (Shape s : shapes) {
        g2.setPaint(Color.BLACK);
        g2.draw(s);
        g2.setPaint(colors[(colorIndex++) % 6]);
        g2.fill(s);
      }

      if (startDrag != null && endDrag != null) {
        g2.setPaint(Color.LIGHT_GRAY);
        Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
        g2.draw(r);
      }
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
      return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
  }
