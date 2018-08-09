import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Potvrzovani {
	
	public static void novaHra() {
		if(GUI.predloha[0][0]!=null) {
			UIManager.put("OptionPane.yesButtonText", "Ano");
			UIManager.put("OptionPane.noButtonText", "Ne");
			int moznost = JOptionPane.showConfirmDialog(null, "Opravdu chcete rozehr�t novou hru?", "", 
														JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE); // 0-ano, 1-ne
			if(moznost==0) {
				int obtiznost=0;
				GUI.sec=0;
				GUI.min=0;
				GUI.hour=0;
				
				if(GUI.rdbtZacatecnik.isSelected()) {
					obtiznost=0;
				}else if(GUI.rdbtPokrocily.isSelected()) {
					obtiznost=1;
				}else if(GUI.rdbtExpert.isSelected()) {
					obtiznost=2;
				}
				
				
				GUI.predloha = Generovani.generatorPole();
				GUI.hraciPole = Generovani.genObtiznost(obtiznost, GUI.predloha);	
				Generovani.novaHra();
			}

		}else {
			int obtiznost=0;
			GUI.sec=0;
			GUI.min=0;
			GUI.hour=0;
			
			if(GUI.rdbtZacatecnik.isSelected()) {
				obtiznost=0;
			}else if(GUI.rdbtPokrocily.isSelected()) {
				obtiznost=1;
			}else if(GUI.rdbtExpert.isSelected()) {
				obtiznost=2;
			}
			
			
			GUI.predloha = Generovani.generatorPole();
			GUI.hraciPole = Generovani.genObtiznost(obtiznost, GUI.predloha);	
			Generovani.novaHra();
		}
	}
	
	public static void nacistHru() {
		
		if(GUI.predloha[0][0]!=null) {
			UIManager.put("OptionPane.yesButtonText", "Ano");
			UIManager.put("OptionPane.noButtonText", "Ne");
			int moznost = JOptionPane.showConfirmDialog(null, "Opravdu chcete na��st posledn� ulo�enou hru?", "", 
														JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE); // 0-ano, 1-ne
			
			if(moznost==0) {
				
				Generovani.nacitani();
				
			}
			
		}else {
		
			Generovani.nacitani();
		
		}
		
		
	}
	
	public static void ulozitHru() {
		
		if(GUI.predloha[0][0]==null) {
			
			JOptionPane.showMessageDialog(null, "Nebyla spu�t�na hra k ulo�en�.");
			
		}else {
			
				UIManager.put("OptionPane.yesButtonText", "Ano");
				UIManager.put("OptionPane.noButtonText", "Ne");
				int moznost = JOptionPane.showConfirmDialog(null, "T�mto sma�ete posledn� ulo�enou hru, jste si jist�?", "", 
															JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE); // 0-ano, 1-ne
				
				if(moznost==0) {
					
					Generovani.ulozeni();
					
				}		    
		}
		
	}

}
