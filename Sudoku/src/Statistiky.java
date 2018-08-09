import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Statistiky extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	static JLabel lbJmeno21;
	static JLabel lbJmeno22;
	static JLabel lbJmeno23;
	static JLabel lbJmeno13;
	static JLabel lbJmeno12;
	static JLabel lbJmeno11;
	static JLabel lbCas11;
	static JLabel lbCas12;
	static JLabel lbCas13;
	static JLabel lbCas21;
	static JLabel lbCas22;
	static JLabel lbCas23;
	static JLabel lbJmeno31;
	static JLabel lbJmeno32;
	static JLabel lbJmeno33;
	static JLabel lbCas31;
	static JLabel lbCas32;
	static JLabel lbCas33;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			Statistiky dialog = new Statistiky();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Create the dialog.
	 */
	public Statistiky() {
		
		Skore pole[] = new Skore[9];
		String casy[] = new String[9];
		String jmena[] = new String[9];
		
		pole = Stats.nacitani();
		
		for(int i=0; i<pole.length; i++) {
			if(pole[i]!=null) {
				jmena[i] = pole[i].getJmeno();
				
				String seconds="";
				String minutes="";
				String hours="";
				if(pole[i].getSec()<=9) {
					seconds="0"+pole[i].getSec();
				}else {
					seconds = Integer.toString(pole[i].getSec());
				}
				if(pole[i].getMin()<=9) {
					minutes="0"+pole[i].getMin() ;
				}else {
					minutes=Integer.toString(pole[i].getMin() );
				}
				if(pole[i].getHour()<=9) {
					hours="0"+pole[i].getHour();
				}else {
					hours=Integer.toString(pole[i].getHour());
				}
				casy[i] = hours + " : " + minutes + " : "+ seconds;
			}else {
				jmena[i]="- - / / - -";
				casy[i]="- - / / - -";
			}
		}
		
		
		setBounds(100, 100, 450, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		Label label = new Label("Statistiky");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
		label.setBounds(135, 10, 157, 34);
		contentPanel.add(label);
		
		Label label_1 = new Label("Zaèáteèník:");
		label_1.setAlignment(Label.CENTER);
		label_1.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 16));
		label_1.setBounds(10, 51, 93, 27);
		contentPanel.add(label_1);
		
		Label label_4 = new Label("1.");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setAlignment(Label.CENTER);
		label_4.setBounds(20, 84, 62, 22);
		contentPanel.add(label_4);
		
		Label label_5 = new Label("2.");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setAlignment(Label.CENTER);
		label_5.setBounds(20, 112, 62, 22);
		contentPanel.add(label_5);
		
		Label label_6 = new Label("3.");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setAlignment(Label.CENTER);
		label_6.setBounds(20, 140, 62, 22);
		contentPanel.add(label_6);
		
		lbJmeno11 = new JLabel(jmena[0]);
		lbJmeno11.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno11.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno11.setBounds(159, 84, 112, 22);
		contentPanel.add(lbJmeno11);
		
		lbJmeno12 = new JLabel(jmena[1]);
		lbJmeno12.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno12.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno12.setBounds(159, 112, 112, 22);
		contentPanel.add(lbJmeno12);
		
		lbJmeno13 = new JLabel(jmena[2]);
		lbJmeno13.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno13.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno13.setBounds(159, 140, 112, 22);
		contentPanel.add(lbJmeno13);
		
		lbCas11 = new JLabel(casy[0]);
		lbCas11.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas11.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas11.setBounds(312, 84, 112, 22);
		contentPanel.add(lbCas11);
		
		lbCas12 = new JLabel(casy[1]);
		lbCas12.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas12.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas12.setBounds(312, 112, 112, 22);
		contentPanel.add(lbCas12);
		
		lbCas13 = new JLabel(casy[2]);
		lbCas13.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas13.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas13.setBounds(312, 140, 112, 22);
		contentPanel.add(lbCas13);
		
		
		
		
		
		Label label_2 = new Label("Pokroèilý:");
		label_2.setAlignment(Label.CENTER);
		label_2.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 16));
		label_2.setBounds(10, 170, 93, 27);
		contentPanel.add(label_2);
		
		Label label_7 = new Label("1.");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setAlignment(Label.CENTER);
		label_7.setBounds(20, 204, 62, 22);
		contentPanel.add(label_7);
		
		Label label_8 = new Label("2.");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setAlignment(Label.CENTER);
		label_8.setBounds(20, 232, 62, 22);
		contentPanel.add(label_8);
		
		Label label_9 = new Label("3.");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setAlignment(Label.CENTER);
		label_9.setBounds(20, 260, 62, 22);
		contentPanel.add(label_9);
		
		lbJmeno21 = new JLabel(jmena[3]);
		lbJmeno21.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno21.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno21.setBounds(159, 204, 112, 22);
		contentPanel.add(lbJmeno21);
		
		lbJmeno22 = new JLabel(jmena[4]);
		lbJmeno22.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno22.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno22.setBounds(159, 232, 112, 22);
		contentPanel.add(lbJmeno22);
		
		lbJmeno23 = new JLabel(jmena[5]);
		lbJmeno23.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno23.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno23.setBounds(159, 260, 112, 22);
		contentPanel.add(lbJmeno23);
		
		lbCas21 = new JLabel(casy[3]);
		lbCas21.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas21.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas21.setBounds(312, 204, 112, 22);
		contentPanel.add(lbCas21);
		
		lbCas22 = new JLabel(casy[4]);
		lbCas22.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas22.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas22.setBounds(312, 232, 112, 22);
		contentPanel.add(lbCas22);
		
		lbCas23 = new JLabel(casy[5]);
		lbCas23.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas23.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas23.setBounds(312, 260, 112, 22);
		contentPanel.add(lbCas23);
		
		
		
		
		
		Label label_3 = new Label("Expert:");
		label_3.setAlignment(Label.CENTER);
		label_3.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 16));
		label_3.setBounds(10, 288, 93, 27);
		contentPanel.add(label_3);
		
		Label label_16 = new Label("1.");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_16.setAlignment(Label.CENTER);
		label_16.setBounds(20, 321, 62, 22);
		contentPanel.add(label_16);
		
		Label label_17 = new Label("2.");
		label_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_17.setAlignment(Label.CENTER);
		label_17.setBounds(20, 349, 62, 22);
		contentPanel.add(label_17);
		
		Label label_18 = new Label("3.");
		label_18.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_18.setAlignment(Label.CENTER);
		label_18.setBounds(20, 377, 62, 22);
		contentPanel.add(label_18);
		
		lbJmeno31 = new JLabel(jmena[6]);
		lbJmeno31.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno31.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno31.setBounds(159, 321, 112, 22);
		contentPanel.add(lbJmeno31);
		
		lbJmeno32 = new JLabel(jmena[7]);
		lbJmeno32.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno32.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno32.setBounds(159, 349, 112, 22);
		contentPanel.add(lbJmeno32);
		
		lbJmeno33 = new JLabel(jmena[8]);
		lbJmeno33.setHorizontalTextPosition(SwingConstants.CENTER);
		lbJmeno33.setHorizontalAlignment(SwingConstants.CENTER);
		lbJmeno33.setBounds(159, 377, 112, 22);
		contentPanel.add(lbJmeno33);
		
		lbCas31 = new JLabel(casy[6]);
		lbCas31.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas31.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas31.setBounds(312, 321, 112, 22);
		contentPanel.add(lbCas31);
		
		lbCas32 = new JLabel(casy[7]);
		lbCas32.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas32.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas32.setBounds(312, 349, 112, 22);
		contentPanel.add(lbCas32);
		
		lbCas33 = new JLabel(casy[8]);
		lbCas33.setHorizontalTextPosition(SwingConstants.CENTER);
		lbCas33.setHorizontalAlignment(SwingConstants.CENTER);
		lbCas33.setBounds(312, 377, 112, 22);
		contentPanel.add(lbCas33);
		
		Button btResetovat = new Button("Reset");
		btResetovat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stats.reset();
			}
		});
		btResetovat.setBounds(322, 17, 83, 27);
		contentPanel.add(btResetovat);
	}
}
