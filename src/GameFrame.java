import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameFrame extends JFrame implements KeyListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea txtrL;
	private JLabel lblTries;
	private JButton btnGiveUp;
	private CowsAndBullsAI game;
	private static String playername;
	/**
	 * Launch the application.
	 */
	public static void mainmethod(String name, int len) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playername = name;
					GameFrame frame = new GameFrame(len);
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
	public GameFrame(int len)  {
		
		try {
			game = new CowsAndBullsAI(len);
		} catch (IOException e) {}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1043, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLetterWord = new JLabel(game.getNumLetters() + " Letter Word");
		lblLetterWord.setBounds(5, 319, 506, 29);
		lblLetterWord.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLetterWord.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLetterWord.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLetterWord);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(516, 5, 4, 553);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_1);
		
		txtrL = new JTextArea();
		txtrL.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrL.setLineWrap(true);
		txtrL.setText("LOG:\nWelcome " + playername + "! Let's play Cows and Bulls!");
		txtrL.setEditable(false);
		
		JScrollPane scroll=new JScrollPane(
				txtrL,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			);
		scroll.setBounds(525, 5, 482, 553);
		contentPane.add(scroll);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 353, 506, 15);
		contentPane.add(separator);
		
		JLabel lblEnterYourGuess = new JLabel("Enter your guess:");
		lblEnterYourGuess.setBounds(5, 373, 506, 39);
		lblEnterYourGuess.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblEnterYourGuess.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEnterYourGuess);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(5, 428, 506, 85);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameMenuFrame.mainmethod();
			}
		});
		btnRestart.setBounds(15, 58, 115, 29);
		contentPane.add(btnRestart);
		
		lblTries = new JLabel("Tries: " + game.getNumTries());
		lblTries.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTries.setHorizontalAlignment(SwingConstants.CENTER);
		lblTries.setBounds(5, 49, 496, 39);
		contentPane.add(lblTries);
		
		btnGiveUp = new JButton("Give Up?");
		btnGiveUp.setBackground(new Color(240, 128, 128));
		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTextLog("\n" + playername + " LOSES!");
				JOptionPane.showMessageDialog(null,"The word was " + game.getWord() + ". Better luck next time!");
				textField.setEditable(false);
				btnGiveUp.setEnabled(false);
			}
		});
		btnGiveUp.setBounds(197, 529, 115, 29);
		contentPane.add(btnGiveUp);
		
		JLabel cowpic = new JLabel("");
		cowpic.setHorizontalAlignment(SwingConstants.CENTER);
		cowpic.setBounds(15, 82, 235, 221);
		contentPane.add(cowpic);
		BufferedImage cowpicimg;
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("cowy.png");
			cowpicimg = ImageIO.read(in);
			cowpic.setIcon(new ImageIcon(cowpicimg));
		} catch (IOException e) {
		}
		
		JLabel bullpic = new JLabel("");
		bullpic.setHorizontalAlignment(SwingConstants.CENTER);
		bullpic.setBounds(268, 82, 235, 221);
		contentPane.add(bullpic);
		BufferedImage bullpicimg;
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("bully.png");
			bullpicimg = ImageIO.read(in);
			bullpic.setIcon(new ImageIcon(bullpicimg));
			
			JLabel lblCowsAndBulls = new JLabel("Cows and Bulls");
			lblCowsAndBulls.setForeground(new Color(30, 144, 255));
			lblCowsAndBulls.setBackground(new Color(169, 169, 169));
			lblCowsAndBulls.setHorizontalAlignment(SwingConstants.CENTER);
			lblCowsAndBulls.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 30));
			lblCowsAndBulls.setBounds(15, 5, 496, 37);
			contentPane.add(lblCowsAndBulls);
		} catch (IOException e) {
		}
		
		textField.addKeyListener(this);
	}
	
	public void addTextLog(String s)
	{
		txtrL.setText(txtrL.getText() + s);
	}
	
	public boolean checkRepeats(String s)
	{
		for(int i = 0; i < s.length()-1; i++)
		{
			for(int j = i+1; j < s.length(); j++)
			{
				if(s.charAt(i) == s.charAt(j))
					return true;
			}
		}
		
		return false;
	}
	
	public boolean checkInDict(String s) throws IOException
	{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("FullDict.txt");
		Scanner readfulldict = new Scanner(in);
		while(readfulldict.hasNextLine())
		{
			String temp = readfulldict.nextLine();
			if(s.equals(temp))
				return true;
		}
		readfulldict.close();
		return false;
	}
	static String temp = "";
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !(temp.equals(textField.getText()))) 
		{
			temp = textField.getText();
			game.updateGuess(textField.getText());
			try {
				if(checkRepeats(game.getGuess()) || (game.getNumLetters() != game.getGuess().length()) || !(checkInDict(game.getGuess())))
				{
					JOptionPane.showMessageDialog(null,"Error! Must enter a valid " + game.getNumLetters() + " letter word (in the dictionary) with no repeated letters!");
					return;
				}
			} catch (HeadlessException e) {
				
			} catch (IOException e) {
				
			}
			addTextLog("\n" + "------------------------------------------");
			addTextLog("\n"  + game.getGuess());
			addTextLog("\nCows: " + game.getNumCows());
			addTextLog("\nBulls: " + game.getNumBulls());
			
			game.incrementTries();
			lblTries.setText("Tries: " + game.getNumTries());
			if(game.hasWon())
			{
				addTextLog("\n" + playername + " WINS!");
				JOptionPane.showMessageDialog(null,"Great Job " + playername + "! You won! The word was " + game.getWord() + ". You took " +  game.getNumTries() + " tries to guess the word.");
				textField.setEditable(false);
				btnGiveUp.setEnabled(false);
			}
				
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
			textField.setText("");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
}
