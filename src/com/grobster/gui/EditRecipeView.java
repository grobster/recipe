package com.grobster.gui;

import com.grobster.food.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class EditRecipeView extends SimpleGui {
	private JTextField recipeNameField;
	private JTextArea ingredientsArea;
	private JTextArea instructionsArea;
	private JButton saveButton;
	private CookBook book;
	
	public EditRecipeView(String programName, int frameWidth, int frameHeight, CookBook book) {
		super(programName, frameWidth, frameHeight);
		this.book = book;
	}
	
	public void createView(String recipeNameString, String ingredientsString, String instructionsString) {
		recipeNameField = new JTextField(20);
		ingredientsArea = new JTextArea(15, 25);
		instructionsArea = new JTextArea(15, 25);
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());
		
		JComponent nameComponent = SimpleGui.addComponentWithLabel(recipeNameString, recipeNameField);
		JComponent ingredientComponent = SimpleGui.addComponentWithLabel(ingredientsString, ingredientsArea);
		JComponent instructionsComponent = SimpleGui.addComponentWithLabel(instructionsString, instructionsArea);
		JComponent saveButtonComponent = SimpleGui.addComponentNoLabel(saveButton);
		
		getMainPanel().add(nameComponent);
		getMainPanel().add(ingredientComponent);
		getMainPanel().add(instructionsComponent);
		getMainPanel().add(saveButtonComponent);
		createView();
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void save() {
		Path serializedFile = Paths.get("cookbook.ser");
		Path assumedDirectory = Paths.get(System.getProperty("user.home") + System.getProperty("file.separator") + "CookBook");
		Path completeSerialPath = assumedDirectory.resolve(serializedFile);
		System.out.println(completeSerialPath);
		ObjectOutputStream oos = null;
		try {
			if (!Files.exists(completeSerialPath)) {
				Files.createFile(completeSerialPath);
			}
			FileOutputStream fos = new FileOutputStream(completeSerialPath.toFile());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(book);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	class SaveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String recipeName = recipeNameField.getText();
			
			java.util.List<String> ingredients = null;
			String[] ingredientLines = ingredientsArea.getText().split("\\n");
			ingredients = Arrays.asList(ingredientLines);
			
			java.util.List<String> instructions = null;
			String[] instructionLines = instructionsArea.getText().split("\\n");
			instructions = Arrays.asList(instructionLines);
			
			Recipe newRecipe = new Recipe(recipeName, ingredients, instructions);
			book.addRecipe(newRecipe);
			save();
			//getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			
		}
	}

}