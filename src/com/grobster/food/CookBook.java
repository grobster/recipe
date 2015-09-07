package com.grobster.food;

import java.util.*;
import java.io.*;

public class CookBook implements Serializable {
	private static final long serialVersionUID = 87772423221L;
	private String name;
	public List<Recipe> recipes;
	public List<CookBookObserverInterface> observers;
	
	public CookBook(String name) {
		this.name = name;
		recipes = new ArrayList<>();
		observers = new ArrayList<>();
	}
	
	public void addRecipe(Recipe r) {
		recipes.add(r);
		notifyObservers();
	}
	
	public void removeRecipe(Recipe r) {
		recipes.remove(r);
		notifyObservers();
	}
	
	public void editRecipe(String name) {
		Recipe editRecipe = findByName(name);
		recipes.remove(editRecipe);
		recipes.add(editRecipe);
		notifyObservers();
	}
	
	public Recipe findByName(String name) {
		for (Recipe r: recipes) {
			if (r.getName().toLowerCase().equals(name.toLowerCase())) {
				return r;
			}
		}
		return null;
	}
	
	public List<Recipe> getRecipes() {
		return recipes;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void registerObserver(CookBookObserverInterface o) {
		observers.add(o);
	}
	
	public void removeObserver (CookBookObserverInterface o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers() {
		for (CookBookObserverInterface cbo: observers) {
			cbo.update();
		}
	}
}