/*
 * Copyright 2020 Marcin Kubach
 */


package logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import gui.Field;
import gui.Plansza;
import gui.Singleton;

//--Zbior akcji potrzebnych do przechodzenia przez historie ruchow


public class UndoRedo implements ActionListener {

public void actionPerformed(ActionEvent e)
{
 
	String choice = e.getActionCommand();

	if(choice.equals("Undo"))
    {
	  try {
		  Field previous = Singleton.history.pop();
		  Color prevColor = Singleton.historyColor.pop();
		  Singleton.redostory.push(previous);
		  Singleton.redoColor.push(previous.getBackground());
		  
		  		previous.setBackground(prevColor);
       		}
      
	  catch (EmptyStackException s) 
	  {
		  Plansza.t.setText("Koniec historii");
		  System.out.println("Koniec stosu, doszedles do ostatniej pozycji w historii");
	  }
	}
	
	
	if(choice.equals("Redo"))
	{  
	   try {
	        Field redo = Singleton.redostory.pop();
	        Color redoColor = Singleton.redoColor.pop();
	        Singleton.history.push(redo);
			Singleton.historyColor.push(redo.getBackground());
				
				redo.setBackground(redoColor);
	       	}
	   catch (EmptyStackException s)
	   {
		   Plansza.t.setText("Koniec historii");
	       	System.out.println("Koniec stosu, doszedles do ostatniej pozycji w historii");
	   } 
	 }
  
   }

}
