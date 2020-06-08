/*
 * Copyright 2020 Marcin Kubach
 */


package gui;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import gui.Field;
import logic.FieldListener;


class Model{
    char  tab[][] = new char[8][8];
	}

public class Plansza extends JFrame {

		//--Klasa odpowiadajaca za strukture okna w ktorym prowadzimy rozgrywke
	
		private static final long serialVersionUID = 1L;
	
		Model model = new Model();


  public static JButton Field[][] = new JButton[8][8] ;
  public static JButton fieldcw;
  JButton save;
  JButton load;
  JButton check;
  JButton help;
  JButton undo;
  JButton redo;
  JButton exit;
  JButton template;
  
   
  JPanel plansza = new JPanel();
  JPanel sterowanie = new JPanel();
  
  public static JTextField t = new JTextField(10);
  JTextField input = new JTextField(15);
  
  static Color kolorek;
 
  
 
  
 public Plansza() {
	  
    int i,j;
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(1,2));
    cp.add(plansza);
    cp.add(sterowanie);
    
    
    setSize(700,450);
    setLocation(600,300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    
    template = new JButton("Laduj plansze");
	template.setFont(new Font("Calibri", Font.BOLD, 17));
	template.addActionListener(new logic.OptionsListener());
	
    
    check = new JButton("Check");
	check.setFont(new Font("Calibri", Font.BOLD, 17));
	check.addActionListener(new logic.OptionsListener());
	
	fieldcw = new JButton("AKTUALNY KOLOR");
	fieldcw.setFont(new Font("Calibri", Font.BOLD, 17));
    
	help = new JButton("Help");
	help.setFont(new Font("Calibri", Font.BOLD, 17));
	help.addActionListener(new logic.OptionsListener());
	
	
	exit = new JButton("Exit");
	exit.setFont(new Font("Calibri", Font.BOLD, 17));
	exit.addActionListener(new logic.OptionsListener());
    
	
	undo = new JButton("Undo");
	undo.setFont(new Font("Calibri", Font.BOLD, 17));
	undo.addActionListener(new logic.UndoRedo());
	
	redo = new JButton("Redo");
	redo.setFont(new Font("Calibri", Font.BOLD, 17));
	redo.addActionListener(new logic.UndoRedo());
	
	
	TextFieldListener tfListener = new TextFieldListener();
	input.addActionListener(tfListener);
	
	t.setFont(t.getFont().deriveFont(30.0f));
	input.setFont(t.getFont().deriveFont(30.0f));
	
		
   
   sterowanie.setLayout(new GridLayout(9,2));
   sterowanie.add(t);
   sterowanie.add(input);
   sterowanie.add(template);
   sterowanie.add(fieldcw);
   sterowanie.add(check);
   sterowanie.add(undo);
   sterowanie.add(redo);
   sterowanie.add(help);
   sterowanie.add(exit);   
   
   
  
   
    plansza.setLayout(new GridLayout(8,8));
    
    //Rozmieszczenie na planszy pol
    
    for (i=0;i<8;i++){
       for (j=0;j<8;j++){
    	   
    	   Field[i][j]=new Field(i, j);
    	   plansza.add(Field[i][j]);
    	   (Field[i][j]).addActionListener(new FieldListener());
    	   
       }    
    }
      
    		t.setText("Wpisz nazwe planszy");
    		setVisible(true);
  
    
  }
  
  
  
  
  		private class TextFieldListener implements ActionListener	{  
  				
  			public void actionPerformed(ActionEvent evt) {  
	  	
  				Singleton.string2 = input.getText();
  				System.out.println("Wybrana plansza: " + Singleton.string2);
  				input.setText("");
  			}
  		}
  
}

  
  	
  	
  	