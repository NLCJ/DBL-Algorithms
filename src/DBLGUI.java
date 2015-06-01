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
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Melroy
 */
public class DBLGUI {

    public static JFrame f;
    public static JScrollPane content;

    public DBLGUI(){
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.setMinimumSize(new Dimension(800, 600));
        initComponents();
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        new DBLGUI();
    }

    public static void initComponents() {
        //MainReader.pModel = Model.ONESLIDER;
        //MainReader.width = 10;
        //MainReader.height = 10;

        Plot plotPanel = new Plot(MainReader.points);
        plotPanel.setBackground(Color.WHITE);
        plotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        content = new JScrollPane(plotPanel);
        content.setPreferredSize(new Dimension(800, 600));
//        content.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        content.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        
        f.add(content);
    }
}
