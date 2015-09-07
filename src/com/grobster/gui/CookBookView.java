package com.grobster.gui;

import com.grobster.food.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CookBookView extends SimpleGui implements Serializable {
	private static final long serialVersionUID = 1223234299L;
	private JTextArea recipeArea;
	private JButton nextButton;
	private JButton previousButton;
	private JPanel editPanel;
	private JButton addButton;
	private JButton removeButton;
	private JButton editButton;
	
	public CookBookView(String programName, int frameWidth, int frameHeight) {
		super(programName, frameWidth, frameHeight);
	}
	
	public void createView(int recipeAreaHeight, int recipeAreaWidth) {
		//creat widgets
		recipeArea = new JTextArea(recipeAreaWidth, recipeAreaHeight);
		recipeArea.setMargin(new Insets(10, 10, 10, 10));
		nextButton = new JButton(">>");
		previousButton = new JButton("<<");
		editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		editButton = new JButton("Edit");
		
		//add widgets to panel
		JComponent areaComponent = SimpleGui.addComponentNoLabel(recipeArea);
		JComponent addComponent = SimpleGui.addComponentNoLabel(addButton);
		JComponent removeComponent = SimpleGui.addComponentNoLabel(removeButton);
		JComponent editComponent = SimpleGui.addComponentNoLabel(editButton);
		JPanel southPanel = new JPanel();
		
		
		southPanel.add(previousButton);
		southPanel.add(nextButton);
		
		editPanel.add(addComponent);
		editPanel.add(removeComponent);
		editPanel.add(editComponent);
		getFrame().getContentPane().add(BorderLayout.WEST, editPanel);
		getFrame().getContentPane().add(BorderLayout.SOUTH, southPanel);
		
		//add Panels to mainPanel
		getMainPanel().add(areaComponent);
		
		createView();
		
	}
	
	public JTextArea getRecipeArea() {
		return recipeArea;
	}
	
	public JButton getNextButton() {
		return nextButton;
	}
	
	public JButton getPreviousButton() {
		return previousButton;
	}
	
	public JPanel getEditPanel() {
		return editPanel;
	}
	
	public JButton getAddButton() {
		return addButton;
	}
	
	public JButton getRemoveButton() {
		return removeButton;
	}
	
	public JButton getEditButton() {
		return editButton;
	}
	
	public static void main(String[] args) {
		CookBookView view = new CookBookView("Jeff's CookBook", 800, 600);
		view.createView(50, 25);
	}

}