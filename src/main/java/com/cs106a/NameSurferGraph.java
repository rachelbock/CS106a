package com.cs106a;/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);


    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryArray.clear();
        update();
    }

	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entryArray.add(entry);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        addGraphBackground();

        rgen = new RandomGenerator();
        rgen.setSeed(20);

        for (int i = 0; i < entryArray.size(); i++) {
            addEntrytoGraph(entryArray.get(i));
        }


    }

    public void addEntrytoGraph(NameSurferEntry entry) {

        int minY = GRAPH_MARGIN_SIZE;
        int maxY = getHeight() - GRAPH_MARGIN_SIZE;
        double d = maxY - minY;
        int objHeight = 5;
        int objWidth = 5;

        Color color = rgen.nextColor();
        for (int i = 0; i < NDECADES - 1; i++) {

            double v1 = 0;
            double v2 = 0;
            if (entry.getRank(i) == 0) {
                v1 = 1;
            } else {
                v1 = entry.getRank(i) / 1000.0;
            }
            if (entry.getRank(i + 1) == 0) {
                v2 = 1;
            } else {
                v2 = entry.getRank(i + 1) / 1000.0;
            }
            double startPoint = minY + v1 * d;
            double endPoint = minY + v2 * d;

            int x1 = xForDecade(i);
            int x2 = xForDecade(i + 1);

            GLine line = new GLine(x1, startPoint, x2, endPoint);
            line.setColor(color);
            add(line);

            GOval point = new GOval(x1 - (objWidth / 2), startPoint - (objHeight / 2), objWidth, objHeight);
            point.setFilled(true);
            point.setFillColor(color);
            add(point);

            if (entry.getRank(i) == 0) {
                GLabel nameLabel = new GLabel(entry.getName() + " *", x1 + 2, startPoint);
                nameLabel.setFont("8");
                nameLabel.setColor(color);
                add(nameLabel);
            } else {
                GLabel nameLabel = new GLabel(entry.getName() + " " + entry.getRank(i), x1 + 2, startPoint);
                nameLabel.setFont("8");
                nameLabel.setColor(color);
                add(nameLabel);
            }

            if (i == NDECADES - 2) {
                if (entry.getRank(i + 1) == 0) {
                    GLabel nameLabel = new GLabel(entry.getName() + " *", x2 + 2, endPoint);
                    nameLabel.setFont("8");
                    nameLabel.setColor(color);
                    add(nameLabel);

                    GOval oval = new GOval(x2 - (objWidth / 2), endPoint - (objHeight / 2), objWidth, objHeight);
                    oval.setFilled(true);
                    oval.setFillColor(color);
                    add(oval);
                } else {
                    GLabel nameLabel = new GLabel(entry.getName() + " " + entry.getRank(i + 1), x2 + 2, endPoint);
                    nameLabel.setFont("8");
                    nameLabel.setColor(color);
                    add(nameLabel);
                    GOval oval = new GOval(x2 - (objWidth / 2), endPoint - (objHeight / 2), objWidth, objHeight);
                    oval.setFilled(true);
                    oval.setFillColor(color);
                    add(oval);
                }
            }
        }


    }


    public int xForDecade(int decade) {

        int x = (getWidth() / 11) * decade;

        return x;
    }

    public void addGraphBackground() {


        GLine topMargin = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        GLine bottomMargin = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(topMargin);
        add(bottomMargin);


        int[] decadeArray = new int[NDECADES];
        for (int i = 0; i < NDECADES; i++) {
            int x = xForDecade(i);
            GLine line = new GLine(x, getHeight(), x, 0);
            add(line);
            decadeArray[i] = 1900 + (i * 10);
            String str = "";
            GLabel decadeLabel = new GLabel(str + decadeArray[i]);
            decadeLabel.setLocation(x + 2, getHeight() - 5);
            add(decadeLabel);


        }
    }


    private ArrayList<NameSurferEntry> entryArray = new ArrayList<NameSurferEntry>();
    private RandomGenerator rgen = new RandomGenerator();

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
