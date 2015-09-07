package com.grobster.food;

import java.util.*;
import java.io.*;

public class Recipe implements Serializable {
	private String name;
	private static final long serialVersionUID = 7326263622L;
	private List<String> ingredients;
	private List<String> instructions;
	
	public Recipe(String name, List<String> ingredients, List<String> instructions) {
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public Recipe(String name) {
		this.name = name;
		ingredients = new ArrayList<>();
		instructions = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getIngredients() {
		return ingredients;
	}
	
	public List<String> getInstructions() {
		return instructions;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
}