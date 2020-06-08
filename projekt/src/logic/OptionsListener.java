/*
 * Copyright 2020 Marcin Kubach
 */


package logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.Field;
import gui.Plansza;
import gui.Singleton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//--Zbior akcji wykonywanych przez opcje zarzadzajace rozgrywka


public class OptionsListener implements ActionListener {

	Color kolory[] = Singleton.kolory;
	static int coordinates[][] = new int[19][3];
	
	
	String pomoc = "Z ka¿dego pola z cyfr¹ nale¿y wyprowadziæ\n"
			+ "jeden lub kilka odcinków w rzêdzie\n"
			+ "lub/i kolumnie. Odcinki wyprowadzone z\n"
			+ "danego pola powinny w sumie \"obj¹æ\" tyle\n"
			+ "pól, jaka jest wartoœæ cyfry w polu\n"
			+ "\"startowym\" (nie licz¹c tego pola). ¯adne dwa\n"
			+ "odcinki nie mog¹ obj¹æ tego samego pola i ¿adne pole\n"
			+ "(bia³e, bez cyfry) nie mo¿e pozostaæ nie objête.";
	
	//--Wczytywanie planszy do rozgrywki z pliku ze wspolrzednymi
	public static void wczytanie() {
		try {
		   
			  
			String path1 = "./src/plansze/";
			String path = path1 + Singleton.string2;
		 
			FileReader fileReader = new FileReader(path);
		   
		   
			BufferedReader bufferReader = new BufferedReader(fileReader);
		   
			String linia;
			int x=0;
		   
			while((linia = bufferReader.readLine()) != null) {
				    
			   char a=linia.charAt(0);
			   char b=linia.charAt(2);
			   char c=linia.charAt(4);
		   
			   int i=Character.getNumericValue(a);
			   int j=Character.getNumericValue(b);
			   int n=Character.getNumericValue(c);
		    
			   	coordinates[x][0]=i;
			   	coordinates[x][1]=j;
			   	coordinates[x][2]=n;
			   	x++;
		   	}
		   	fileReader.close();
		  }
		  
		  
		  catch (FileNotFoundException e) {
			  
			  System.err.println("Zmien nazwe pliku lub zatwierdz klawiszem enter");
		  } 
		 
		  catch (IOException e) {
			
			  System.err.println("Nieudana proba odczytu");
		  }
		  
		  
	 } 
	
	
			void sprawdzenie(int tab[], int y, Color cw1, Color cw2) {
					
				if ( cw1 == cw2) 
				{ 
					tab[y]++;
				}
		
			}
	
	
			//--Funkcja poszukujaca danej wspolrzednej wybranego pola z wartoscia 
			int lookfor(char axis, int w) {
		
				int szukane=0;
		
				for (int i=0;i<8;i++){
					for (int j=0;j<8;j++){
						Field f = (Field)Plansza.Field[i][j];
						Color kolorek = f.getBackground();
						
							if(f.getText() != "" && kolorek == kolory[w]) {
									
									if(axis == 'j') {
										szukane = f.whatJ();
									}
									
									if(axis == 'i') {
										szukane = f.whatI();
									}
		    	  }
		    	
		     }
					
		}
				return szukane;
		
	}
	
	
	
	
	
	
	
			public void actionPerformed(ActionEvent e) {
				
				Object source = e.getSource();
				JButton b = null;
				Field f = null;
				String buttonText;
		
	
				if (source instanceof JButton) {
					b = (JButton)source;
					buttonText = b.getText();
			
					
					
					
				//-- Wyswietlanie pomocy	
				if (buttonText == "Help") {
			
							JOptionPane.showMessageDialog(null, pomoc, "Instrukcja rozgrywki", JOptionPane.INFORMATION_MESSAGE);
				}
		
				
				//Sprawdzenie poprawnosci aktualnego postepu w rozgrywce
				if (buttonText == "Check") {
			
					int ilekolor[] = new int[10];
					Color kolorek;
			
			
			
					for (int x=0; x<10; x++) {
						ilekolor[x]=0;
					}
			
			
					outerloop:
						for (int i=0;i<8;i++){
							for (int j=0;j<8;j++){
								f = (Field)Plansza.Field[i][j];
								kolorek = f.getBackground();
			    	  
								for (int y=0; y<10; y++ ) {
									Color cw = kolory[y];
									sprawdzenie(ilekolor, y, kolorek, cw);
									
								//--Porownania sluzace sprawdzeniu czy ilosc pomalowanych
								//--pol w danym kolorze nie jest wieksza niz dopuszczalna
									
						if ( y != 0 && ilekolor[y] != 0 && ilekolor[y]-1 > y ) {
							
										Plansza.t.setText("Widac blad");
									}
			    	  
						else if ( y != 0 && ilekolor[y] != 0 && ilekolor[y]-1 <= y) {
			    		  
			    	 
			    		 if(kolorek == cw && f.getText() == "") {
			    			 
			    		  int j1 = lookfor('j', y);
			    		  int i1 = lookfor('i', y);
			    		  			    
			    		  		//--Porownania sluzace sprawdzeniu czy pomalowane pola
			    		  		//--znajduja sie w odpowiedniej odleglosci od pola "matki"
			    		  					    		  
			    		  
			    		  if( i == i1 && (j1-y)<=j && j<=(j1+y)) {
			    			  Plansza.t.setText("Brak bledow");
			    			  
			    			  if (ilekolor[0] == 0) {
			    				  Plansza.t.setText("Brawo, wygrales");
			    			  }
			    			  continue;
			    		  }
			    		  
			    		  else if( j == j1 && (i1-y)<=i && i<=(i1+y)) {
			    			  Plansza.t.setText("Brak bledow");
			    			  if (ilekolor[0] == 0) {
			    				  Plansza.t.setText("Brawo, wygrales");
			    			  }
			    			  continue;
			    		  }
			    		  
			    		  else {
			    		  Plansza.t.setText("Widac blad");
			    		  break outerloop;
			    		  }
			    		 }
						}    	  
					}
			    }
			}
		}
		
		
		
		//--Wyjscie z rozgrywki
		if (buttonText == "Exit") {
			
			int decyzja = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjœæ?", "Confirm Dialog",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			
			if (decyzja == JOptionPane.YES_OPTION) {
				System.exit(0);
				}
		}
		
		
		//--Odczytanie wczytanych wspolrzednych i przelozenie ich bezposrednio na uklad pol na planszy
		if (buttonText == "Laduj plansze") {
			
			
			//--Petla "czyszczaca" plansze po poprzedniej rozgrywce
			for (int k=0;k<8;k++)
				for (int j=0;j<8;j++){
					((gui.Field) Plansza.Field[k][j]).sT("");
				 }
			
			
				wczytanie();
				
							
				
				for (int i=0;i<19;i++){
					
					((gui.Field) Plansza.Field[(coordinates[i][0])][coordinates[i][1]]).setFieldColor(kolory[coordinates[i][2]]);
					((gui.Field) Plansza.Field[(coordinates[i][0])][coordinates[i][1]]).sT(String.valueOf(coordinates[i][2]));
					
					if(coordinates[i][2] == 0) {
						
						((gui.Field) Plansza.Field[(coordinates[i][0])][coordinates[i][1]]).setFieldColor(Color.black);
						Singleton.instance.SetColor(Color.white);
					}
				}	

				    for (int k=0;k<8;k++)
				        for (int j=0;j<8;j++){
				        	String txt = ((gui.Field) Plansza.Field[k][j]).getText();
				 	if(txt == "") ((gui.Field) Plansza.Field[k][j]).setFieldColor(Color.white);
				    if(txt == "0") ((gui.Field) Plansza.Field[k][j]).setFieldColor(Color.black);
					
				}
				     
			}
		
		}
	
	}	
			
}
	

