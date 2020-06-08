/*
 * Copyright 2020 Marcin Kubach
 */


package gui;

import java.awt.Color;
import java.util.Stack;

public class Singleton {
  
	//--Zastosowanie wzorca projektowego Singleton--
	//--Klasa w ktorej inicjujemy nasze zmienne dostepne z kazdego miejsca (np. stos historii ruchow)
	
	public static final Singleton instance = new Singleton();

  	public Color kolorek;
  	public static Color kolory[] = new Color[] {Color.white, Color.yellow, Color.magenta, Color.red, Color.green, Color.blue, Color.pink, Color.cyan, Color.gray, Color.orange};
  	public String nazwy[] = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
  	public static String string2;
  	
  
  	public static Stack<Field> history = new Stack<Field>();
  	public static Stack<Color> historyColor = new Stack<Color>();
  	public static Stack<Field> redostory = new Stack<Field>();
  	public static Stack<Color> redoColor = new Stack<Color>();
  	
  	
  	
  	public static Singleton GetInstance() {
  		return instance;
  	}
  	
  	
  	public void SetColor(Color kolorek) {
  		this.kolorek = kolorek;
  	}
  	
  	public Color GetColor() {
  		return kolorek;
  	}
  	
  	public Color ColorTab(int x) {
  		return kolory[x];
  	}
  	
  	public String GetNazwa(int x) {
  		return nazwy[x];
  	}
  		
    private Singleton() {
    	 if (instance != null) {
             throw new IllegalStateException("Singleton already constructed");
         }
    }
}
