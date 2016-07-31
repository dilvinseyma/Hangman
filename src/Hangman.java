import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class Hangman {
	JLabel wordArea = null;
	JLabel messageArea = null;

	ButtonGroup bg = null;
	JButton exitButton = null;
	JButton newGameButton = null;
	JRadioButton rb1 = null;
	JRadioButton rb2 = null;
	JRadioButton rb3 = null;

	// these will change.
	 String[] cities = {"istanbul","izmir","van"};
	    String[] countries = {"turkiye","almanya","italya"};
	    String[] animals = {"kedi","kopek","tavsan"};
	    
	    String currentGuess;
	    String targetWord;

	    int numberWrong       = 0;
	    int numberOfBodyParts = 6;
	    int next              = 0;
	    
	public void setUpNewGame() {

		String category = "";
		String s = "Please select category";
		if (category == "") {

			JOptionPane.showMessageDialog(null, s);
			return;
		}

		double numb = Math.random();
		if (category == "Cities") {
			next = (int) (numb * cities.length);
			targetWord = cities[next];
		} else if (category == "Countries") {
			next = (int) (numb * countries.length);
			targetWord = countries[next];
		} else if (category == "Animals") {
			next = (int) (numb * animals.length);
			targetWord = animals[next];
		}
		 currentGuess = "_ ";
	        for( int i=0; i<targetWord.length()-1; i++) {
	            currentGuess = currentGuess.concat("_ ");
	        }
	        wordArea.setText( currentGuess );
	}

	public Component createComponents() {
		JPanel pane = new JPanel();

		pane.setLayout(new BorderLayout());
		pane.add(createNorthPane(), BorderLayout.NORTH);
		pane.add(createWestPane(), BorderLayout.WEST);
		pane.add(createSouthPane(), BorderLayout.SOUTH);
		pane.add(createCenterPane(), BorderLayout.CENTER);
		pane.add(createEastPane(), BorderLayout.EAST);

		return pane;
	}

	public Component createNorthPane() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));
		wordArea = new JLabel("Press New Game");
		wordArea.setFont(new Font("serif", Font.ITALIC, 24));
		wordArea.setBackground(Color.lightGray);
		wordArea.setForeground(Color.black);
		pane.add(wordArea);
		return pane;
	}

	public Component createWestPane() {

		JPanel pane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		pane.setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		JButton button;

		String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z", "" };

		int k = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 8 && j == 3)
					break;
				button = new JButton(letters[k]);
				c.weightx = 0.5;
				c.weighty = 0.5;
				c.gridx = j;
				c.gridy = i;
				if (i == 0 && j == 0)
					c.gridheight = 1;
				gridbag.setConstraints(button, c);

				pane.add(button);

				k = k + 1;
			}
		}

		return pane;
	}

	public Component createSouthPane() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));

		messageArea = new JLabel("Wining or Losing Message.");
		messageArea.setFont(new Font("serif", Font.ITALIC, 28));
		messageArea.setBackground(Color.lightGray);
		messageArea.setForeground(Color.red);
		pane.add(messageArea);
		return pane;
	}

	public Component createEastPane() {

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new BorderLayout());

		newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		pane.add(newGameButton, BorderLayout.NORTH);

		JPanel centerPane = new JPanel();
		centerPane.setLayout(new GridLayout(4, 1));

		JLabel categoryLabel = new JLabel("==> CATEGORIES");

		centerPane.add(categoryLabel);

		rb1 = new JRadioButton("Cities");
		rb2 = new JRadioButton("Animals");
		rb3 = new JRadioButton("Countries");

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rb1);
		bgroup.add(rb2);
		bgroup.add(rb3);

		centerPane.add(rb1);
		centerPane.add(rb2);
		centerPane.add(rb3);
		pane.add(centerPane, BorderLayout.CENTER);

		exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		pane.add(exitButton, BorderLayout.SOUTH);

		return pane;
	}

	GallowsArea gallowsArea = null;

	public Component createCenterPane() {
		gallowsArea = new GallowsArea();
		return gallowsArea;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Hangman");

		frame.setSize(750, 450);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		Hangman app = new Hangman();
		Component contents = app.createComponents();

		frame.getContentPane().add(contents);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class GallowsArea extends JPanel {

	public GallowsArea() {
		// this.controller = controller;
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedBevel,
				loweredBevel);
		setBorder(compound);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// For gallows
		g.setColor(Color.black);
		g.drawRect(10, 10, 25, 350);
		g.drawRect(10, 10, 200, 25);
		g.setColor(Color.darkGray);
		g.fillRect(11, 11, 25, 350);
		g.fillRect(11, 11, 200, 25);
	}
}