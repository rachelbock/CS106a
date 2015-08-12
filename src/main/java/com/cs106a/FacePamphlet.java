package com.cs106a;/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.TextField;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program
        implements FacePamphletConstants {

    /**
     * This method has the responsibility for initializing the
     * interactors in the application, and taking care of any other
     * initialization that needs to be performed.
     */
    public void init() {

        buildInteractors();
    }

    public void buildInteractors() {
        nameField = new TextField(TEXT_FIELD_SIZE);
        add(new JLabel("Name"), NORTH);
        add(nameField, NORTH);
        nameField.addActionListener(this);

        addButton = new JButton("Add");
        add(addButton, NORTH);
        addButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        add(deleteButton, NORTH);
        deleteButton.addActionListener(this);

        lookupButton = new JButton("Lookup");
        add(lookupButton, NORTH);
        lookupButton.addActionListener(this);

        cStatusField = new TextField(TEXT_FIELD_SIZE);
        add(cStatusField, WEST);
        cStatusField.addActionListener(this);

        cStatusButton = new JButton("Change Status");
        add(cStatusButton, WEST);
        cStatusButton.addActionListener(this);

        add(new JLabel(EMPTY_LABEL_TEXT), WEST);

        cPictureField = new TextField(TEXT_FIELD_SIZE);
        add(cPictureField, WEST);
        cPictureField.addActionListener(this);

        cPictureButton = new JButton("Change Picture");
        add(cPictureButton, WEST);
        cPictureButton.addActionListener(this);

        add(new JLabel(EMPTY_LABEL_TEXT), WEST);

        addFriendField = new TextField(TEXT_FIELD_SIZE);
        add(addFriendField, WEST);
        addFriendField.addActionListener(this);

        addFriendButton = new JButton("Add Friend");
        add(addFriendButton, WEST);
        addFriendButton.addActionListener(this);

    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
        // You fill this in as well as add any additional methods
    }

    private TextField nameField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookupButton;
    private TextField cStatusField;
    private JButton cStatusButton;
    private TextField cPictureField;
    private JButton cPictureButton;
    private TextField addFriendField;
    private JButton addFriendButton;

}
