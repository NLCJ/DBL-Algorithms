/*
 * The MIT License
 *
 * Copyright 2015 Melroy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author Melroy
 */
public class Plot extends JPanel {

    private static final int STARTPOINT = 200;
    private static final int SIZE = 10000 + STARTPOINT * 2;
    private static final int SUBDIVISIONS = SIZE / (STARTPOINT / 2);
    private static final int SCALER = 10;

    private static final int movementCorrection = 2;

    private long lastUpdate = System.currentTimeMillis();

    private static final Dimension preferredSize = new Dimension(SIZE, SIZE);
    private java.awt.Point pLoc;
    private static Point[] points;

    private static ArrayList<Label> collides = new ArrayList<Label>();

    double scale = 1;
    double pointSize = 2;

    public Plot(Point[] points) {
        for (Point p : points) {
            for (Point q : points) {
                if (p.getLabels() != null &&  ! p.getLabels().isEmpty() && q.getLabels() != null &&  ! q.getLabels().isEmpty() && p != q) {
                    for (Label l1 : p.getLabels()) {
                        for (Label l2 : q.getLabels()) {
                            if (Collision.intersects(l1, l2)) {
                                collides.add(l1);
                                collides.add(l2);
                            }
                        }
                    }
                }
            }
        }
        
        this.points = points;
        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                updatePreferredSize(e.getWheelRotation(), e.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                java.awt.Point nLoc = e.getPoint();
                if (System.currentTimeMillis() - lastUpdate > 100) {
                    int width = DBLGUI.content.getViewport().getWidth();

                    JScrollBar xBar = DBLGUI.content.getHorizontalScrollBar();
                    JScrollBar yBar = DBLGUI.content.getVerticalScrollBar();

                    int offX = (nLoc.x - pLoc.x);
                    int offY = (nLoc.y - pLoc.y);

                    int offXabs = Math.abs(offX);
                    int offYabs = Math.abs(offY);

                    if (offXabs < offYabs && offXabs * movementCorrection < offYabs) {
                        yBar.setValue((int) (yBar.getValue() - offY));
                    } else if (offXabs > offYabs && offXabs > offYabs * movementCorrection) {
                        xBar.setValue((int) (xBar.getValue() - offX));
                    } else {
                        yBar.setValue((int) (yBar.getValue() - offY));
                        xBar.setValue((int) (xBar.getValue() - offX));
                    }

                    pLoc = nLoc;
                    lastUpdate = System.currentTimeMillis();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                pLoc = e.getPoint();
            }
        }
        );

        this.setPreferredSize(preferredSize);

        /*for(dbl.Point p : points){
         if(!p.getLabels().isEmpty()){
         dbl.Point ref = p.getLabels().get(0).getReference();
         Rectangle2D.Double rect = new Rectangle2D.Double(ref.getX(), ref.getY(), dbl.MainReader.width, dbl.MainReader.height);

         }
         }*/
//        BufferedImage b = this.createImage(this, preferredSize.width, preferredSize.height);
//        File outputfile = new File("image.png");
//        try {
//            ImageIO.write(b, "png", outputfile);
//            //b.
//        } catch (IOException ex) {
//            Logger.getLogger(Plot.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 1; i < SUBDIVISIONS; i ++) {
            if (i % SCALER == 2) {
                continue;
            }
            int x = i * getSize().width / SUBDIVISIONS;
            g2.drawLine(x, 0, x, getSize().height);
        }
        for (int i = 1; i < SUBDIVISIONS; i ++) {
            if (i % SCALER == 2) {
                continue;
            }
            int y = i * getSize().width / SUBDIVISIONS;
            g2.drawLine(0, y, getSize().width, y);
        }

        g2.setPaint(Color.DARK_GRAY);
        for (int i = 2; i < SUBDIVISIONS; i = i + SCALER) {
            int x = i * getSize().width / SUBDIVISIONS;
            g2.drawLine(x, 0, x, getSize().height);
        }
        for (int i = 2; i < SUBDIVISIONS; i = i + SCALER) {
            int y = i * getSize().width / SUBDIVISIONS;
            g2.drawLine(0, y, getSize().width, y);
        }
    }

    private void updatePreferredSize(int n, java.awt.Point p) {
        if ((scale < 0.18 && n > 0) || (scale > 8 && n < 0)) {

        } else {
            double d = (double) n * 1.08;
            d = (n > 0) ? 1 / d :  - d;

            int w = (int) (getWidth() * d);
            int h = (int) (getHeight() * d);
            preferredSize.setSize(w, h);

            scale *= d;

            int offX = (int) (p.x * d) - p.x;
            int offY = (int) (p.y * d) - p.y;
            setLocation(getLocation().x - offX, getLocation().y - offY);

            this.repaint();

            getParent().doLayout();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);

        for (Point p : points) {
            g2d.setPaint(Color.BLACK);
            if (p.getLabels() != null &&  ! p.getLabels().isEmpty()) {
                List<Label> labels = p.getLabels();
                //for (dbl.Label l : labels) {
                Label l = labels.get(0);
                Point ref = l.getReference();
                Rectangle2D.Double rect = new Rectangle2D.Double(scale * (ref.getX() + STARTPOINT), scale * ((10000 + STARTPOINT) - (ref.getY() + MainReader.height)), scale * MainReader.width, scale * MainReader.height);

                if (collides.contains(l)) {
                    g2d.fill(rect);
                    g2d.setPaint(Color.YELLOW);
                    g2d.draw(rect);
                } else {
                    g2d.draw(rect);
                }
                //}
            }
            g2d.setPaint(Color.RED);
            Ellipse2D.Double ellipse = new Ellipse2D.Double(scale * (p.getX() + STARTPOINT - pointSize), scale * ((10000 + STARTPOINT) - p.getY() - pointSize), scale * pointSize * 2, scale * pointSize * 2);
            g2d.fill(ellipse);
        }
    }

    public BufferedImage createImage(JPanel panel, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setPaint(Color.WHITE);
        g.fillRect(0, 0, width, height);
        panel.paint(g);
        return bi;
    }
}
