package com.cs106a;

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.*;
import sun.security.action.GetLongAction;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 */
public class Assignment7 extends GraphicsProgram {
    private static final double BOX_WIDTH = 120;
    private static final double BOX_HEIGHT = 50;
    private static final int TEXT_FIELD_LENGTH = 20;


    public Assignment7() {

    }

    public void init() {

        addInteractors();
        addMouseListeners();

    }

    public void addInteractors () {

        nameField = new TextField(TEXT_FIELD_LENGTH);
        add(new JLabel("Name"), SOUTH);
        add(nameField, SOUTH);
        nameField.addActionListener(this);

        addButton = new JButton ("Add");
        add(addButton, SOUTH);
        addButton.addActionListener(this);

        removeButton = new JButton("Remove");
        add(removeButton, SOUTH);
        removeButton.addActionListener(this);

        clearButton = new JButton("Clear");
        add(clearButton, SOUTH);
        clearButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {



        int centerX = getWidth()/2;
        int centerY = getHeight()/2;
        double centerBoxX = centerX - (BOX_WIDTH/2);
        double centerBoxY = centerY - (BOX_HEIGHT/2);

        if (e.getSource() == addButton) {
            labeledRect = new GCompound();
            labeledRect.setLocation(centerBoxX, centerBoxY);
            labeledRect.add(new GRect(BOX_WIDTH, BOX_HEIGHT));
            GLabel nameLabel;
            labeledRect.add(nameLabel = new GLabel(nameField.getText()));
            double labelLocX = (BOX_WIDTH - nameLabel.getWidth())/2;
            double labelLocY = (BOX_HEIGHT + nameLabel.getAscent())/2;
            nameLabel.setLocation(labelLocX, labelLocY);
            add(labeledRect);
            compoundMap.put(nameField.getText(), labeledRect);
        }

        if (e.getSource() == removeButton) {
            if (compoundMap.get(nameField.getText()) != null) {
            remove(compoundMap.get(nameField.getText()));

            }
        }

        if (e.getSource() == clearButton) {
            for (String key : compoundMap.keySet()) {
                remove(compoundMap.get(key));
            }
            compoundMap.clear();
        }

    }

    public void mousePressed (MouseEvent e) {

        lastPoint = new GPoint(e.getPoint());
        obj = getElementAt(lastPoint);

    }

    public void mouseDragged (MouseEvent e) {
        if (obj != null) {
            obj.move(e.getX() - lastPoint.getX(), e.getY() - lastPoint.getY());
            lastPoint = new GPoint(e.getPoint());
        }
    }

    public void mouseClicked (MouseEvent e) {
        if (obj != null) {
            obj.sendToFront();
        }
    }

    private TextField nameField;
    private JButton addButton;
    private JButton removeButton;
    private JButton clearButton;
    private GCompound labeledRect;
    private GObject obj;
    private GPoint lastPoint;

    private HashMap<String,GCompound> compoundMap = new HashMap<String, GCompound>();

    public static void main(String[] args) {
        new Assignment7().start();
    }
}
