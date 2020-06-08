/*
 * Copyright 2020 Marcin Kubach
 */


package logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import gui.Field;
import gui.Plansza;
import gui.Singleton;

public class FieldListener implements ActionListener {

	
	//--Zbior akcji wykonywanych przez pola na naszej planszy
	
	
	Color kolorek;
	
		
	void set(Field pole, Color k) {
		Singleton.instance.SetColor(k);
		pole.setFieldColor(k);
	}
	    
	
	public void actionPerformed(ActionEvent e) {
		
    	Object source = e.getSource();
  		Field b = null;
  		String buttonText;
  		    	  
  		if (source instanceof JButton) 
  		{
			b = (Field)source;
			buttonText = b.getText();
			kolorek = b.getBackground();
			
			//--Zapamietanie koloru ktorym chcemy malowac pola
			if (buttonText != "" && kolorek != Color.black) 
			{
				set(b, Singleton.kolory[Integer.parseInt(buttonText)]);
				}
			
			//--Pomalowanie pustego pola oraz dodanie wykonanych ruchow do historii
			if (buttonText == "")
			{
				Singleton.history.push(b);
				Singleton.historyColor.push(kolorek);
				Singleton.redoColor.clear();
				Singleton.redostory.clear();
				
				b.setBackground(Singleton.instance.GetColor());
				
			//--Wyczyszczenie koloru na polu
				if (kolorek != Color.black && kolorek != Color.white) 
				{
					b.setBackground(Color.white);
				}
			
			}
				Plansza.fieldcw.setBackground(Singleton.instance.GetColor());
  		}
  		
	}
	
}
