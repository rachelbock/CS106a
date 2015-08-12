package com.cs106a;/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GRect;
import acm.program.*;

import java.awt.TextField;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		nameData = new NameSurferDataBase("src/main/resources/names-data.txt");
		buildInteractors();
		graph = new NameSurferGraph();
		add(graph);
		graph.update();
	}

/* Method: buildInteractors */

	/**
	 * THis method adds interactors to the Gcanvas. This method is called in the init method.
 	 */

	public void buildInteractors () {

		nameField = new TextField(TEXT_FIELD_LENGTH);
		add (new JLabel("Name"), SOUTH);
		add (nameField, SOUTH);
		nameField.addActionListener(this);

		graphButton = new JButton("Graph");
		add (graphButton, SOUTH);
		graphButton.addActionListener(this);

		clearButton = new JButton("Clear");
		add (clearButton, SOUTH);
		clearButton.addActionListener(this);

	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == graphButton) {
			NameSurferEntry graphName = nameData.findEntry(nameField.getText());

			if (graphName != null) {
				graph.addEntry(graphName);
			}


		}

		if (e.getSource() == clearButton) {
			graph.clear();
			graph.update();

		}

	}

	private TextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferDataBase nameData = null;
	private NameSurferGraph graph = null;
}
