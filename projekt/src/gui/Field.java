/*
 * Copyright 2020 Marcin Kubach
 */


package gui;

import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import gui.Plansza;

import javax.swing.JButton;

public class Field extends JButton {
	
	//--Klasa odpowiadajaca za strukture przyciskow z ktorych sklada sie plansza do gry
	
	private static final long serialVersionUID = 1L;
	
	private int X;
	private int Y;
	public Color fieldColor;
	
		
		public void setFieldColor(Color color) {
			this.setBackground(color);
			this.fieldColor = color;
		}
	
		
		public Color getFieldColor() {
			return fieldColor;
		}
	
		
		public void sT(String napis) {
			this.setText(napis);
		}
	
		
		public Field(int X, int Y) {
			this.X = X;
			this.Y = Y;
		
		}
	
		
	
		public int whatI() { 
			return this.X;
		}
	
		public int whatJ() {
			return this.Y;
		}
		
}