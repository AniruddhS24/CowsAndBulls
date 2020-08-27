import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameMenuFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nametextField;

	/**
	 * Launch the application.
	 */
	public static void mainmethod() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMenuFrame frame = new GameMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameMenuFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nametextField = new JTextField();
		nametextField.setBounds(65, 134, 195, 26);
		contentPane.add(nametextField);
		nametextField.setColumns(10);
		
		JLabel lblPlayerName = new JLabel("Player name:");
		lblPlayerName.setBounds(65, 98, 120, 20);
		contentPane.add(lblPlayerName);
		
		JLabel lblCowsAndBulls = new JLabel("Cows And Bulls: Startup");
		lblCowsAndBulls.setHorizontalAlignment(SwingConstants.CENTER);
		lblCowsAndBulls.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCowsAndBulls.setBounds(15, 16, 598, 56);
		contentPane.add(lblCowsAndBulls);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6"}));
		comboBox.setBounds(401, 134, 56, 26);
		contentPane.add(comboBox);
		
		JLabel lblNumberOfLetters = new JLabel("Number of letters:");
		lblNumberOfLetters.setBounds(401, 98, 148, 20);
		contentPane.add(lblNumberOfLetters);
		
		JButton btnHowToPlay = new JButton("How to Play");
		btnHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Discover the hidden word chosen by the computer!\n" + 
						"(Note: Bulls and Cows is similar to, but came before, the board game MastermindTM*)\n" + 
						"Bulls = correct letter, correct position.\n" +
						"Cows = correct letter, wrong position.\n"+
						"The player must guess a word with a FIXED number of letters and with no repeated characters!\n"+
						"Please keep in mind, some words may not be accepted due to its absence in the dictionary used.");
			}
		});
		btnHowToPlay.setBounds(15, 232, 136, 29);
		contentPane.add(btnHowToPlay);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Aniruddh Sriram : 2017\n" +
						"Game's Dictionary: http://www.mieliestronk.com/corncob_caps.txt");
			}
		});
		btnCredits.setBounds(15, 272, 115, 29);
		contentPane.add(btnCredits);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(124, 252, 0));
		btnOk.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nametextField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid name!");
					return;
				}
				dispose();
				GameFrame.mainmethod(nametextField.getText(), Integer.parseInt(comboBox.getSelectedItem().toString()));
			}
		});
		btnOk.setBounds(263, 218, 115, 56);
		contentPane.add(btnOk);
	}
}
