package com.grobster.gui;

import com.grobster.food.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class CookBookViewAction extends CookBookView implements CookBookObserverInterface, Serializable {
	private static final long serialVersionUID = 89898787827262L;
	private CookBook book;
	private int positionInBook;
	private int nextPositionInBook;
	
	//public CookBookViewAction() {}
	
	public CookBookViewAction(String programName, int frameWidth, int frameHeight, CookBook book) {
		super(programName, frameWidth, frameHeight);
		this.book = book;
		book.registerObserver((CookBookObserverInterface)this);
		positionInBook = 0;
		setup();
	}
	
	public void createCookBookView(int recipeAreaHeight, int recipeAreaWidth) {
		createView(recipeAreaHeight, recipeAreaWidth);
		getPreviousButton().addActionListener(new PreviousButtonListener());
		getNextButton().addActionListener(new NextButtonListener());
		getAddButton().addActionListener(new AddRecipeListener());
		getSearchField().addKeyListener(new SearchFieldListener());
		displayCurrentRecipe(positionInBook);
		setup();
		enableDisablePositionButtons();
	}
	
	private void setup() {
		Path programDirectory = Paths.get(System.getProperty("user.home") + System.getProperty("file.separator") + "CookBook");
		System.out.println(programDirectory);
		if (!Files.exists(programDirectory)) {
			try {
				Files.createDirectory(programDirectory);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void displayCurrentRecipe(int position) {
		Recipe recipe = book.getRecipes().get(position);
		getRecipeArea().setText("");
		getRecipeArea().append(recipe.getName() + "\n");
		
		getRecipeArea().append("\n");
		getRecipeArea().append("\n");
		
		for (String s: recipe.getIngredients()) {
			getRecipeArea().append(s + "\n");
		}
		
		getRecipeArea().append("\n");
		getRecipeArea().append("\n");
		
		for (String i: recipe.getInstructions()) {
			getRecipeArea().append(i + "\n");
		}
		
	}
	
	public void update() {
		java.util.List<Recipe> recipes = book.getRecipes();
		System.out.println("Received an updated recipe list");
		displayCurrentRecipe(0);
	}
	
	public Recipe searchRecipes(String rs) {
		for (Recipe r: book.getRecipes()) {
			if (r.getName().toLowerCase().equals(rs.toLowerCase())) {
				positionInBook = book.getRecipes().indexOf(r);
				displayCurrentRecipe(positionInBook);
				return r;
			}
		}
		displayCurrentRecipe(positionInBook);
		return null;
	}
	
	public void enableDisableButton(JButton button, boolean condition) {
		if(condition) {
			button.setEnabled(false);
		} else {
			button.setEnabled(true);
		}
	}
	
	public void enableDisablePositionButtons() {
		enableDisableButton(getPreviousButton(), positionInBook == 0);
		enableDisableButton(getNextButton(), positionInBook == book.getRecipes().size() - 1);
	}
	
	class PreviousButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextPositionInBook = 0;
			nextPositionInBook = positionInBook - 1;
			System.out.println("before previous Operation " + nextPositionInBook);
			if (nextPositionInBook >= 0) {
				positionInBook-= 1;
				System.out.println("Position: " + positionInBook);
				displayCurrentRecipe(positionInBook);
			}
			enableDisablePositionButtons();
		}
	}
	
	class NextButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextPositionInBook = 0;
			nextPositionInBook = 1 + positionInBook;
			System.out.println("before next Operation " + nextPositionInBook);
			if (nextPositionInBook <= book.getRecipes().size() - 1) {
				positionInBook += 1;
				System.out.println("Next Position: " + positionInBook);
				displayCurrentRecipe(positionInBook);
			}
			enableDisablePositionButtons();
		}
	}
	
	class AddRecipeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			EditRecipeView editView = new EditRecipeView("Edit View", 600, 400, book);
			editView.createView("Recipe Name", "Ingredients", "Instructions");
		}
	}
	
	class SearchFieldListener implements KeyListener {
		public void searchAction() {
			String searchItem = getSearchField().getText();
			searchRecipes(searchItem);
			enableDisablePositionButtons();
		}
		
		public void keyPressed(KeyEvent e) {
			searchAction();
		}
		
		public void keyReleased(KeyEvent e) {
			searchAction();
		}
		
		public void keyTyped(KeyEvent e) {
			searchAction();
		}
	}
}