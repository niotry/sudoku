import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class GUI {
	

	private JFrame frmSudoku;
	private final ButtonGroup btObtiznost = new ButtonGroup();
	private JPanel pBunky;
	
	static JRadioButtonMenuItem rdbtZacatecnik;
	static JRadioButtonMenuItem rdbtPokrocily;
	static JRadioButtonMenuItem rdbtExpert;
	
	static JTextField poleBunek[]=new JTextField[81];
	static Integer[][] predloha = new Integer[9][9];
	static Integer[][] hraciPole = new Integer[9][9];
	
	
	static int sec=0;
	static int min=0;
	static int hour=0;
	static boolean status = false;
	static Label lbTimer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSudoku.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frmSudoku = new JFrame();
		frmSudoku.setTitle("Sudoku");
		frmSudoku.setBounds(100, 100, 450, 480);
		frmSudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudoku.setResizable(false);
		frmSudoku.getContentPane().setLayout(null);
		frmSudoku.setLocationRelativeTo(null);
		
		pBunky = new JPanel();
		pBunky.setBounds(10, 11, 414, 367);
		frmSudoku.getContentPane().add(pBunky);
		pBunky.setLayout(new GridLayout(9, 9, 0, 0));
		
		Button btKontrola = new Button("Kontrola");
		btKontrola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Generovani.kontrola(predloha);
				
				
			}
		});
		btKontrola.setBounds(10, 384, 94, 27);
		frmSudoku.getContentPane().add(btKontrola);
		
		lbTimer = new Label("00:00:00");
		lbTimer.setAlignment(Label.CENTER);
		lbTimer.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		lbTimer.setBounds(308, 384, 116, 27);
		frmSudoku.getContentPane().add(lbTimer);
		
		
		Button btNapoveda = new Button("Nápovìda");
		btNapoveda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Generovani.napoveda();
				
			}
		});
		btNapoveda.setBounds(110, 384, 94, 27);
		frmSudoku.getContentPane().add(btNapoveda);
		
		
		JMenuBar menuBar = new JMenuBar();
		frmSudoku.setJMenuBar(menuBar);
		
		JMenu mnHra = new JMenu("Hra");
		menuBar.add(mnHra);
		
		JMenuItem mntmNovaHra = new JMenuItem("Nová hra");
		mntmNovaHra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Potvrzovani.novaHra();
								
			}
		});
		mnHra.add(mntmNovaHra);
		
		JMenuItem mntmNastHru = new JMenuItem("Naèíst hru");
		mntmNastHru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Potvrzovani.nacistHru();
				
			}
		});
		mnHra.add(mntmNastHru);
		
		JMenuItem mntmUloitHru = new JMenuItem("Uložit hru");
		mntmUloitHru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Potvrzovani.ulozitHru();
				
			}
		});
		mnHra.add(mntmUloitHru);
		
		JMenu mnObtiznost = new JMenu("Obtížnost");
		mnHra.add(mnObtiznost);
		
		rdbtZacatecnik = new JRadioButtonMenuItem("Zaèáteèník");
		rdbtZacatecnik.setSelected(true);
		btObtiznost.add(rdbtZacatecnik);
		mnObtiznost.add(rdbtZacatecnik);
		
		rdbtPokrocily = new JRadioButtonMenuItem("Pokroèilý");
		btObtiznost.add(rdbtPokrocily);
		mnObtiznost.add(rdbtPokrocily);
		
		rdbtExpert = new JRadioButtonMenuItem("Expert");
		btObtiznost.add(rdbtExpert);
		mnObtiznost.add(rdbtExpert);
		
		JMenuItem mntmUkonit = new JMenuItem("Ukonèit");
		mntmUkonit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setForeground(Color.LIGHT_GRAY);
		mnHra.add(separator_1);
		
		JMenuItem mntmStatistiky = new JMenuItem("Statistiky");
		mntmStatistiky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Statistiky stat = new Statistiky();
				stat.setModal(true);
				stat.setVisible(true);	
				
			}
		});
		mnHra.add(mntmStatistiky);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		mnHra.add(separator);
		mnHra.add(mntmUkonit);
		
		
		//vytvoøení prázdné hrací plochy
		int poziceJTextField=0;
		for (int i = 0; i < hraciPole.length; i++) {
			for (int j = 0; j < hraciPole.length; j++) {
				
				poleBunek[poziceJTextField] = new JTextField();

				PlainDocument doc = (PlainDocument) poleBunek[poziceJTextField].getDocument();
				doc.setDocumentFilter(new IntFilter());
				
				pBunky.add(poleBunek[poziceJTextField]);
				poleBunek[poziceJTextField].setColumns(1);
				poleBunek[poziceJTextField].setHorizontalAlignment(JTextField.CENTER);
				poleBunek[poziceJTextField].setBackground(new Color(255, 255, 255));
				if(j==2 || j==5) {
					if(i==2 || i==5) {
						poleBunek[poziceJTextField].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.gray));
					}else {
						poleBunek[poziceJTextField].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.gray));
					}
				}else if(i==2 || i==5){
					poleBunek[poziceJTextField].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.gray));
				}else {
					poleBunek[poziceJTextField].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
				}
				poleBunek[poziceJTextField].setFont(new Font("Segoe UI", Font.BOLD, 28));
				
				
					poleBunek[poziceJTextField].setEditable(false);
					poleBunek[poziceJTextField].setForeground(Color.blue);
					
				
				poziceJTextField++;
			}
		}
		
		
		
		
	}
	
	// https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
	class IntFilter extends DocumentFilter {
		
		   @Override
		   public void insertString(FilterBypass fb, int offset, String string,
		         AttributeSet attr) throws BadLocationException {

		      Document doc = fb.getDocument();
		      StringBuilder sb = new StringBuilder();
		      sb.append(doc.getText(0, doc.getLength()));
		      sb.insert(offset, string);

		      if (povoleni(sb.toString()))
		         super.insertString(fb, offset, string, attr);
		   }

		   private boolean povoleni(String text) {
		      
			   try {
		    	  if(text.trim().isEmpty()) {
		    		  return true;
		    	  } else {
			         Integer.parseInt(text);
			         if(text.length() == 1 && !text.equals("0")) {
			        	 return true;
			         } else {
			        	 return false;
			         }
		    	  }
		      } catch (NumberFormatException e) {
		         return false;
		      }
		   }

		   @Override
		   public void replace(FilterBypass fb, int offset, int length, String text,
		         AttributeSet attrs) throws BadLocationException {

		      Document doc = fb.getDocument();
		      StringBuilder sb = new StringBuilder();
		      sb.append(doc.getText(0, doc.getLength()));
		      sb.replace(offset, offset + length, text);

		      if (povoleni(sb.toString()))
		         super.replace(fb, offset, length, text, attrs);
		   }

		   @Override
		   public void remove(FilterBypass fb, int offset, int length)
		         throws BadLocationException {
		      Document doc = fb.getDocument();
		      StringBuilder sb = new StringBuilder();
		      sb.append(doc.getText(0, doc.getLength()));
		      sb.delete(offset, offset + length);

		      if (povoleni(sb.toString())) {
		         super.remove(fb, offset, length);
		      }
		   }
	}
}