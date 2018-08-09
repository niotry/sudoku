import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Generovani {
	
	public static void ulozeni() {
		Integer[][][] save=new Integer[9][9][4]; // 0-vyplnìné, 1-nápovìda, 2-vložené hráèem, 3-pøedloha
		int poziceJTextField=0;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(GUI.poleBunek[poziceJTextField].getForeground().equals(Color.BLACK)) {
					save[i][j][0]=Integer.parseInt(GUI.poleBunek[poziceJTextField].getText());
				}else if(GUI.poleBunek[poziceJTextField].getForeground().equals(Color.GREEN)) {
					save[i][j][1]=Integer.parseInt(GUI.poleBunek[poziceJTextField].getText());
				}else if(GUI.poleBunek[poziceJTextField].isEditable() && GUI.poleBunek[poziceJTextField].getForeground().equals(Color.BLUE) && (GUI.poleBunek[poziceJTextField].getText().length()==1)) {
					save[i][j][2]=Integer.parseInt(GUI.poleBunek[poziceJTextField].getText().trim());
				}
				save[i][j][3]=GUI.predloha[i][j];
				poziceJTextField++;
			}
		}
		
		
		String cesta = System.getenv("APPDATA"); 
		try {
		        FileOutputStream out = new FileOutputStream(cesta +"\\sudokuSave.out");
		        DataOutputStream outs = new DataOutputStream(out);

		        for (int i = 0; i < 4; i++) {

		            for (int j = 0; j < 9; j++) {

		                for (int k = 0; k < 9; k++) {
		                    if(save[k][j][i]!=null) {
		                    	outs.writeInt(save[k][j][i]);
		                    }else {
		                    	save[k][j][i]=0;
		                    	outs.writeInt(save[k][j][i]);
		                    }
		               
		                }
		            }
		        }
		       
		        //ukládání èasovaèe
		        outs.writeInt(GUI.sec);
		        outs.writeInt(GUI.min);
		        outs.writeInt(GUI.hour);

		        outs.close();
		        out.close();
			}catch (NoSuchFileException ee) {
				
		    } catch (IOException exception) {
		        exception.printStackTrace();
		    }
	}
	
	public static void nacitani() {
		int[][][] array = new int[9][9][4];
		String cesta = System.getenv("APPDATA"); 
	    try {
	        FileInputStream in = new FileInputStream(cesta + "\\sudokuSave.out");
	        DataInputStream ins = new DataInputStream(in);
	       
	            for (int i = 0; i < 4; i++) {
	                for (int j = 0; j < 9; j++) {
	                    for (int k = 0; k < 9; k++) {
	                        array[k][j][i] = ins.readInt();
	                    }
	                }
	            }
	            
	            GUI.sec=ins.readInt();
	            GUI.min=ins.readInt();
	            GUI.hour=ins.readInt();
	        in.close();
	        ins.close();

	      //naèítání pøedlohy
	        for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					GUI.predloha[i][j]=array[i][j][3]; // øádek/sloupec/3-pøedloha
				}
			}

	        
	        
	      //tvoøení hracího pole
	        int poziceJTextField=0;
	        int poziceArray=0;
				for (int j = 0; j < 9; j++) {
					for (int k = 0; k < 9; k++) {
						if(array[j][k][poziceArray]!=0) {
							if(poziceArray==0) {
								GUI.poleBunek[poziceJTextField].setText(array[j][k][poziceArray]+"");
								GUI.poleBunek[poziceJTextField].setEditable(false);
								GUI.poleBunek[poziceJTextField].setForeground(Color.BLACK);
								poziceArray=0;
								poziceJTextField++;
							}else if(poziceArray==1) {
								GUI.poleBunek[poziceJTextField].setText(array[j][k][poziceArray]+"");
								GUI.poleBunek[poziceJTextField].setEditable(false);
								GUI.poleBunek[poziceJTextField].setForeground(Color.GREEN);
								poziceArray=0;
								poziceJTextField++;
							}else if(poziceArray==2) {
								GUI.poleBunek[poziceJTextField].setText(array[j][k][poziceArray]+"");
								GUI.poleBunek[poziceJTextField].setEditable(true);
								GUI.poleBunek[poziceJTextField].setForeground(Color.BLUE);
								poziceArray=0;
								poziceJTextField++;
							}else {
								GUI.poleBunek[poziceJTextField].setText("");
								GUI.poleBunek[poziceJTextField].setEditable(true);
								GUI.poleBunek[poziceJTextField].setForeground(Color.BLUE);
								poziceArray=0;
								poziceJTextField++;
							}
						}else {
							poziceArray++;
							k--;
						}
					}
				}
				if(GUI.status==false) {
					Thread t = new Thread() {
						public void run() {
							GUI.status=true;
							while(true) {	
								if(GUI.status==true) {
									GUI.sec++;
									if(GUI.sec==60) {
										GUI.sec=0;
										GUI.min++;
										if(GUI.min==60) {
											GUI.min=0;
											GUI.hour++;
											if(GUI.hour==24) {
												GUI.lbTimer.setText("-_-  -_-  -_-");
												break;
											}
										}
									}
									
									String seconds="";
									String minutes="";
									String hours="";
									if(GUI.sec<=9) {
										seconds="0"+GUI.sec;
									}else {
										seconds=GUI.sec+"";
									}
									if(GUI.min<=9) {
										minutes="0"+GUI.min;
									}else {
										minutes=GUI.min+"";
									}
									if(GUI.hour<=9) {
										hours="0"+GUI.hour;
									}else {
										hours=GUI.hour+"";
									}
									
									
									
									GUI.lbTimer.setText(hours+":"+minutes+":"+seconds);
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					};
					t.start();
				}

	        
	        
	    } catch (FileNotFoundException exception) {
	    	JOptionPane.showMessageDialog(null, "Dosud nebyla uložena hra.");
	    } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void novaHra() {
		int poziceJTextField=0;
		
		for (int i = 0; i < GUI.hraciPole.length; i++) {
			for (int j = 0; j < GUI.hraciPole.length; j++) {
				if(GUI.hraciPole[i][j]!=null) {
					GUI.poleBunek[poziceJTextField].setText(GUI.hraciPole[i][j]+"");
					GUI.poleBunek[poziceJTextField].setEditable(false);
					GUI.poleBunek[poziceJTextField].setForeground(Color.BLACK);
				}else {
					GUI.poleBunek[poziceJTextField].setText("");
					GUI.poleBunek[poziceJTextField].setEditable(true);
					GUI.poleBunek[poziceJTextField].setForeground(Color.BLUE);
				}
				poziceJTextField++;
			
			}
		}
		
		if(GUI.status==false) {
		Thread t = new Thread() {
			public void run() {
				GUI.status=true;
				while(true) {	
					if(GUI.status==true) {
						GUI.sec++;
						if(GUI.sec==60) {
							GUI.sec=0;
							GUI.min++;
							if(GUI.min==60) {
								GUI.min=0;
								GUI.hour++;
								if(GUI.hour==24) {
									GUI.lbTimer.setText("-_-  -_-  -_-");
									break;
								}
							}
						}
						
						String seconds="";
						String minutes="";
						String hours="";
						if(GUI.sec<=9) {
							seconds="0"+GUI.sec;
						}else {
							seconds=GUI.sec+"";
						}
						if(GUI.min<=9) {
							minutes="0"+GUI.min;
						}else {
							minutes=GUI.min+"";
						}
						if(GUI.hour<=9) {
							hours="0"+GUI.hour;
						}else {
							hours=GUI.hour+"";
						}
						
						
						
						GUI.lbTimer.setText(hours+":"+minutes+":"+seconds);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		
		
		t.start();
		}
		
		
	};
	
	
	public static void napoveda() {
		if(GUI.predloha[0][0]==null) {
			JOptionPane.showMessageDialog(null, "Zahajte novou hru.");
		}else {
			Random r = new Random();
			boolean plnePole=true;
			
			for (int i = 0; i < GUI.poleBunek.length; i++) {
				if(GUI.poleBunek[i].getText().length()==0) {
					plnePole=false;
					break;
				}
			}
			if(plnePole==true) {
				int pocetChyb=0;
				int poziceJTextField=0;
				for (int i = 0; i < GUI.predloha.length; i++) {
					for (int j = 0; j < GUI.predloha.length; j++) {	
							if(GUI.predloha[i][j] != Integer.parseInt(GUI.poleBunek[poziceJTextField].getText())) {
								pocetChyb++;
							}
							poziceJTextField++;
					}
				}
				if(pocetChyb==0) {
					
					Stats.vyhra();
					
				}else {
					
					poziceJTextField=0;
					int chyba=r.nextInt(pocetChyb)+1;
					pocetChyb=0;
					for (int i = 0; i < GUI.predloha.length; i++) {
						for (int j = 0; j < GUI.predloha.length; j++) {	
								if(GUI.predloha[i][j] != Integer.parseInt(GUI.poleBunek[poziceJTextField].getText())) {
									pocetChyb++;
									if(pocetChyb==chyba) {
										GUI.poleBunek[poziceJTextField].setText(GUI.predloha[i][j]+"");
										GUI.poleBunek[poziceJTextField].setEditable(false);
										GUI.poleBunek[poziceJTextField].setForeground(Color.green);
									}
								}
								poziceJTextField++;
						}
					}
					
				}						
			}else {
				int pocetPrazdnychPoli=0;
				for (int i = 0; i < GUI.poleBunek.length; i++) {
					if(GUI.poleBunek[i].getText().length()==0) {
						pocetPrazdnychPoli++;
					}
				}
				int pozice=r.nextInt(pocetPrazdnychPoli)+1;
				pocetPrazdnychPoli=0;
				int poziceJTextField=0;
				for (int i = 0; i < GUI.predloha.length; i++) {
					for (int j = 0; j < GUI.predloha.length; j++) {	
							if(GUI.poleBunek[poziceJTextField].getText().length()==0) {
								pocetPrazdnychPoli++;
								if(pocetPrazdnychPoli==pozice) {
									GUI.poleBunek[poziceJTextField].setText(GUI.predloha[i][j]+"");
									GUI.poleBunek[poziceJTextField].setEditable(false);
									GUI.poleBunek[poziceJTextField].setForeground(Color.green);
								}
							}
							poziceJTextField++;
					}
				}						
			}
		}
	}
	
	public static void kontrola(Integer[][] predloha) {
		if(predloha[0][0]==null) {
			JOptionPane.showMessageDialog(null, "Zahajte novou hru.");
		}else {
			int poziceJTextField=0;
			boolean plnePole=true;
			boolean chyba=false;
			
			for (int i = 0; i < GUI.poleBunek.length; i++) {
				if(GUI.poleBunek[i].getText().length()==0) {
					plnePole=false;
					break;
				}
			}
			
			if(plnePole==true) {
				
				for (int i = 0; i < predloha.length; i++) {
					for (int j = 0; j < predloha.length; j++) {	
							if(predloha[i][j] != Integer.parseInt(GUI.poleBunek[poziceJTextField].getText())) {
								chyba=true;
								break;
							}
							poziceJTextField++;
							if(chyba==true) {
								break;
							}
					}
				}
				if(chyba==true) {
					JOptionPane.showMessageDialog(null, "Není vyplnìno správnì");
				}else {
					Stats.vyhra();
				}
			
			
			}else {
				JOptionPane.showMessageDialog(null, "Vyplòte celé sudoku.");
			}
			
		}
	}
 
	public static Integer[][] genObtiznost(int obtiznost, Integer[][] predloha){
		Integer[][] pole=new Integer[9][9];
		int nic=0;
		for (int i = 0; i < predloha.length; i++) {
			for (int j = 0; j < predloha.length; j++) {
				pole[i][j]=nic+predloha[i][j];
			}
		}
		
		Random r=new Random();
		if(obtiznost==0){
			int cislo=r.nextInt(10)+10;
			
			for (int i = 0; i < cislo;) {
				int cisloRadek=r.nextInt(9);
				int cisloSloupec=r.nextInt(9);
				
				if(pole[cisloRadek][cisloSloupec]!=null) {
					pole[cisloRadek][cisloSloupec]=null;
					i++;
				}
			}
		}else if(obtiznost==1) {
			int cislo=r.nextInt(10)+20;
			
			for (int i = 0; i < cislo;) {
				int cisloRadek=r.nextInt(9);
				int cisloSloupec=r.nextInt(9);
				
				if(pole[cisloRadek][cisloSloupec]!=null) {
					pole[cisloRadek][cisloSloupec]=null;
					i++;
				}
			}
		}else if(obtiznost==2) {
			int cislo=r.nextInt(10)+30;
			
			for (int i = 0; i < cislo;) {
				int cisloRadek=r.nextInt(9);
				int cisloSloupec=r.nextInt(9);
				
				if(pole[cisloRadek][cisloSloupec]!=null) {
					pole[cisloRadek][cisloSloupec]=null;
					i++;
				}
			}
		}
		
		
		return pole;
	}

	public static Integer[][] generatorPole() {
		Random r=new Random();
		Integer[][] hra=new Integer[9][9];
		
		for(int radek=0;radek<9;radek++){
			int pocetChyb=0;
			for(int sloupec=0;sloupec<9;sloupec++){
				
				//generovaní pole èísel 1-9
				Integer poleCisel[]=new Integer[9];
				for (int mozneCislo = 0; mozneCislo < poleCisel.length; mozneCislo++) {
					poleCisel[mozneCislo]=mozneCislo+1;
				}
				
				
				// generování èísla 
				while(true) {
					boolean obsazeno=false;
					pocetChyb=0;
					//poèítání, kolik už není možno využít èísel
					for (int zkouska = 0; zkouska < poleCisel.length; zkouska++) {
						if(poleCisel[zkouska]==null) {
							pocetChyb=pocetChyb+1;
						}
					}
					
					//pokud nebude možné vložit žádné èíslo, jde se od druhého øádku od znova
					if(pocetChyb==9) {
						
						for (int i = 1; i < poleCisel.length; i++) {
							for (int j = 0; j < poleCisel.length; j++) {
								hra[i][j]=null;
							}
						}
						radek=0;
						sloupec=8;
						break;
					}
					
					
					
					
					
					//pokud již není èíslo v poli možnýchÈísel, tak se generuje nové èíslo
					int cislo=r.nextInt(9)+1;
					if(poleCisel[cislo-1]==null) {
						continue;
					}
					
					
					
					//zkouška jestli generované èíslo už je v øádce
					for (int sloupec2 = 0; sloupec2 < sloupec; sloupec2++) {
						if(hra[radek][sloupec2] != null && cislo==hra[radek][sloupec2]) {
							obsazeno=true;
							poleCisel[cislo-1]=null;
							break;
						}
					}
					
					//pokud není v øádce, tak kontrola jestli je už ve sloupci
					if(obsazeno==false) {
						for (int radek2 = 0; radek2 < radek; radek2++) {
							if(hra[radek2][sloupec] != null && cislo==hra[radek2][sloupec]) {
								obsazeno=true;
								poleCisel[cislo-1]=null;
								break;
							}
						}
					}
					
					//pokud není èíslo v øádce, ani ve sloupci, tak kontrola jestli už je v kontejneru
					if(obsazeno==false) {
						//levé kontejnery
						if(sloupec<=2) {
							if(radek<=2) {
								for (int radekk = 0; radekk <=2; radekk++) {
									for (int sloupeck = 0; sloupeck <=2; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
								
							}else if(radek>=3 && radek<=5) {
								for (int radekk = 3; radekk <=5; radekk++) {
									for (int sloupeck = 0; sloupeck <=2; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}else if(radek>=6) {
								for (int radekk = 6; radekk <=8; radekk++) {
									for (int sloupeck = 0; sloupeck <=2; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}
						
							
							
							
							
							
						//prostøední kontejnery	
						}else if(sloupec>=3 && sloupec <=5) {
							if(radek<=2) {
								for (int radekk = 0; radekk <=2; radekk++) {
									for (int sloupeck = 3; sloupeck <=5; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
								
							}else if(radek>=3 && radek<=5) {
								for (int radekk = 3; radekk <=5; radekk++) {
									for (int sloupeck = 3; sloupeck <=5; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}else if(radek>=6) {
								for (int radekk = 6; radekk <=8; radekk++) {
									for (int sloupeck = 3; sloupeck <=5; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}
							
							
							
							
						//pravé kontejnery	
						}else if(sloupec>=6) {
							if(radek<=2) {
								for (int radekk = 0; radekk <=2; radekk++) {
									for (int sloupeck = 6; sloupeck <=8; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}else if(radek>=3 && radek<=5) {
								for (int radekk = 3; radekk <=5; radekk++) {
									for (int sloupeck = 6; sloupeck <=8; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}else if(radek>=6) {
								for (int radekk = 6; radekk <=8; radekk++) {
									for (int sloupeck = 6; sloupeck <=8; sloupeck++) {
										if(hra[radekk][sloupeck] != null && cislo==hra[radekk][sloupeck]) {
											obsazeno=true;
											poleCisel[cislo-1]=null;
											break;
										}
									}
									if(obsazeno==true) {
										break;
									}
								}
							}							
						}
						
						
						
						
						
						
					}
					
					//pokud projde všemi podmínky, uloží se
					if(obsazeno==false) {	
						
						hra[radek][sloupec]=cislo;
						poleCisel[cislo-1]=null;
						break;
					}
				}
				
			}
			
		}
		
	return hra;
	}

}