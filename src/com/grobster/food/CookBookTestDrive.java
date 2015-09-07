package com.grobster.food;

import java.util.*;
import com.grobster.gui.*;
import java.io.*;
import java.nio.file.*;

public class CookBookTestDrive {
	public static void main(String[] args) {
		Path assumedSerialPath = null;
		CookBook book = null;
		ObjectInputStream os = null;
		try {
			assumedSerialPath = Paths.get(System.getProperty("user.home") + System.getProperty("file.separator") + "CookBook" + System.getProperty("file.separator") + "cookbook.ser");
			if (!Files.exists(assumedSerialPath)) {
				book = new CookBook("Jeff's Recipes");
				CookBookViewAction cbva = new CookBookViewAction("Jeff's Cook Book", 800, 600, book);
				cbva.createCookBookView(50, 25);
			} else {
				FileInputStream fis = new FileInputStream(assumedSerialPath.toFile());
				os = new ObjectInputStream(fis);
				Object o = os.readObject();
				book = (CookBook) o;
				CookBookViewAction cbva = new CookBookViewAction("Jeff's Cook Book", 800, 600, book);
				cbva.createCookBookView(50, 25);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}

		
		

		
	}
}