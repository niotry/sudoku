import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Stats {	
	public static Skore[] nacitani() {
		
		Skore pole[] = new Skore[9];
		String cesta = System.getenv("APPDATA"); 
		try {
			
			 FileInputStream fileIn = new FileInputStream(cesta + "\\statistiky.obj");
			 ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			 
			 for(int i=0; i<9; i++) {
				 pole[i] = (Skore) objectIn.readObject();
			 }			
			 
			 objectIn.close();
		}catch(FileNotFoundException ex) {
			
			
		}catch (Exception ex) {
			 ex.printStackTrace();
	    }
		return pole;
	}
	
	
	public static void vyhra() {
		GUI.status=false;
		boolean hint = false;
		String cesta = System.getenv("APPDATA");
		
		int poziceBunky=0;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(GUI.poleBunek[poziceBunky].getForeground().equals(Color.GREEN)) {
					hint=true;
					break;
				}
				poziceBunky++;
			}
			if(hint==true) {
				break;
			}
		}
		
		if(hint==false) {
			Skore porovnavani[] = Stats.nacitani();
			
			if(GUI.rdbtZacatecnik.isSelected()) {
				for (int i = 0; i < 3; i++) {
					if(porovnavani[i]==null) {
						UIManager.put("OptionPane.cancelButtonText", "zrušit");
						String jmeno="";
						jmeno = jmeno + JOptionPane.showInputDialog(null, "Zadejte jméno", "Výhra!", JOptionPane.PLAIN_MESSAGE);
						jmeno = jmeno.trim();
						if(jmeno.length()==0 || jmeno.equals("null")) {
							jmeno = "Hráè";
						}
						porovnavani[i]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
						
						try {
							FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
							ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
							
							for (int o = 0; o < 9; o++) {
									objectOut.writeObject(porovnavani[o]);
							}
							
							objectOut.close();

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						break;
					} else {
						
						int hraSec = (GUI.hour*3600)+(GUI.min*60)+GUI.sec;
						int statSec = (porovnavani[i].getHour()*3600)+(porovnavani[i].getMin()*60)+porovnavani[i].getSec();
						
						if(hraSec <= statSec) {
							UIManager.put("OptionPane.cancelButtonText", "zrušit");
							String jmeno="";
							jmeno = jmeno + JOptionPane.showInputDialog(null, "Zadejte jméno", "Výhra!", JOptionPane.PLAIN_MESSAGE);
							jmeno = jmeno.trim();
							if(jmeno.length()==0 || jmeno.equals("null")) {
								jmeno = "Hráè";
							}
							
							if(i==0) {
								porovnavani[2]=porovnavani[1];
								porovnavani[1]=porovnavani[0];
								porovnavani[0]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							} else if(i==1){
								porovnavani[2]=porovnavani[1];
								porovnavani[1]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							} else if(i==2){
								porovnavani[2]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							}
							
							try {
								FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
								ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
								
								for (int o = 0; o < 9; o++) {
										objectOut.writeObject(porovnavani[o]);
								}
								
								objectOut.close();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
							break;
						}
						
					}
				 }
				
			}else if(GUI.rdbtPokrocily.isSelected()) {
				for (int i = 3; i < 6; i++) {
					if(porovnavani[i]==null) {
						UIManager.put("OptionPane.cancelButtonText", "zrušit");
						String jmeno="";
						jmeno = jmeno + JOptionPane.showInputDialog(null, "Zadejte jméno", "Výhra!", JOptionPane.PLAIN_MESSAGE);
						jmeno = jmeno.trim();
						if(jmeno.length()==0 || jmeno.equals("null")) {
							jmeno = "Hráè";
						}
						porovnavani[i]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
						
						try {
							FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
							ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
							
							for (int o = 0; o < 9; o++) {
									objectOut.writeObject(porovnavani[o]);
							}
							
							objectOut.close();

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						break;
					} else {
						
						int hraSec = (GUI.hour*3600)+(GUI.min*60)+GUI.sec;
						int statSec = (porovnavani[i].getHour()*3600)+(porovnavani[i].getMin()*60)+porovnavani[i].getSec();
						if(hraSec <= statSec) {
							UIManager.put("OptionPane.cancelButtonText", "zrušit");
							String jmeno="";
							jmeno = jmeno + JOptionPane.showInputDialog(null, "Zadejte jméno", "Výhra!", JOptionPane.PLAIN_MESSAGE);
							jmeno = jmeno.trim();
							if(jmeno.length()==0 || jmeno.equals("null")) {
								jmeno = "Hráè";
							}
							
							if(i==3) {
								porovnavani[5]=porovnavani[4];
								porovnavani[4]=porovnavani[3];
								porovnavani[3]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							} else if(i==4){
								porovnavani[5]=porovnavani[4];
								porovnavani[4]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							} else if(i==5){
								porovnavani[5]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							}
							
							try {
								FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
								ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
								
								for (int o = 0; o < 9; o++) {
										objectOut.writeObject(porovnavani[o]);
								}
								
								objectOut.close();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
							break;
						}
						
					}
				 }
			}else if(GUI.rdbtExpert.isSelected()) {
				for (int i = 6; i < 9; i++) {
					if(porovnavani[i]==null) {
						UIManager.put("OptionPane.cancelButtonText", "zrušit");
						String jmeno="";
						jmeno = jmeno + JOptionPane.showInputDialog(null, "Zadejte jméno", "Výhra!", JOptionPane.PLAIN_MESSAGE);
						jmeno = jmeno.trim();
						if(jmeno.length()==0 || jmeno.equals("null")) {
							jmeno = "Hráè";
						}
						porovnavani[i]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
						
						try {
							FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
							ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
							
							for (int o = 0; o < 9; o++) {
									objectOut.writeObject(porovnavani[o]);
							}
							
							objectOut.close();

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						break;
					} else {
						
						int hraSec = (GUI.hour*3600)+(GUI.min*60)+GUI.sec;
						int statSec = (porovnavani[i].getHour()*3600)+(porovnavani[i].getMin()*60)+porovnavani[i].getSec();
						if(hraSec <= statSec) {
							UIManager.put("OptionPane.cancelButtonText", "zrušit");
							String jmeno="";
							jmeno = jmeno + JOptionPane.showInputDialog(null, "Zadejte jméno", "Výhra!", JOptionPane.PLAIN_MESSAGE);
							jmeno = jmeno.trim();
							if(jmeno.length()==0 || jmeno.equals("null")) {
								jmeno = "Hráè";
							}
							
							if(i==6) {
								porovnavani[8]=porovnavani[7];
								porovnavani[7]=porovnavani[6];
								porovnavani[6]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							} else if(i==7){
								porovnavani[8]=porovnavani[7];
								porovnavani[7]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							} else if(i==8){
								porovnavani[8]=new Skore(jmeno, GUI.hour, GUI.min, GUI.sec);
							}
							
							try {
								FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
								ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
								
								for (int o = 0; o < 9; o++) {
										objectOut.writeObject(porovnavani[o]);
								}
								
								objectOut.close();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
							break;
						}
						
					}
				 }
			}
			Statistiky stat=new Statistiky();
			stat.setModal(true);
			stat.setVisible(true);	
		}else {
			JOptionPane.showMessageDialog(null, "Výhra! Bohužel s použitím nápovìdy...");
			Statistiky stat=new Statistiky();
			stat.setModal(true);
			stat.setVisible(true);	
		}
		
	}
	
	public static void reset() {
		Skore pole[]= new Skore[9];
		String cesta = System.getenv("APPDATA");
		try {
			FileOutputStream fileOut = new FileOutputStream(cesta+"\\statistiky.obj");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			
			for (int o = 0; o < 9; o++) {
					objectOut.writeObject(pole[o]);
			}
			
			objectOut.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		String nic = "- - / / - -";
		
		Statistiky.lbJmeno11.setText(nic);
		Statistiky.lbJmeno12.setText(nic);
		Statistiky.lbJmeno13.setText(nic);
		Statistiky.lbJmeno21.setText(nic);
		Statistiky.lbJmeno22.setText(nic);
		Statistiky.lbJmeno23.setText(nic);
		Statistiky.lbJmeno31.setText(nic);
		Statistiky.lbJmeno32.setText(nic);
		Statistiky.lbJmeno33.setText(nic);
		
		Statistiky.lbCas11.setText(nic);
		Statistiky.lbCas12.setText(nic);
		Statistiky.lbCas13.setText(nic);
		Statistiky.lbCas21.setText(nic);
		Statistiky.lbCas22.setText(nic);
		Statistiky.lbCas23.setText(nic);
		Statistiky.lbCas31.setText(nic);
		Statistiky.lbCas32.setText(nic);
		Statistiky.lbCas33.setText(nic);
		
	}

}
