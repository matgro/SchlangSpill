package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Controls.ButtonsHandler;
import Controls.OptionsHandler;
import Custom.MyButton;
import Model.Model;

public class OptionsView extends JPanel {
	private Model model;
	private MyButton back;
	private ButtonsHandler bh;
	private OptionsHandler oh;
	
	public OptionsView(Model model) {
		this.model = model;
		this.oh = new OptionsHandler(model);
		
		setPreferredSize(model.getDimension());
		setBackground(new Color(255, 128, 64));
		
		ImageIcon img = new ImageIcon(new ImageIcon("snake-logo.png").getImage().getScaledInstance(256, 128, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(img);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		//spacing between the components
		gbc.insets = new Insets(5,0,5,0);
		setLayout(layout);
		//First Row : Logo
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = 3;
		add(logo,gbc);


		gbc.fill = GridBagConstraints.NONE;
		
		//Second Row : Label for Number of players Option 
		JLabel label = new JLabel("Number of players: ");
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx=0;
		gbc.gridy=1;
		add(label,gbc);

		//Adjust spacing between Label and Radiobuttons
		gbc.insets = new Insets(2,0,2,0);
		
		//Third Row : Radiobuttons for number of players
		ButtonGroup group1 = new ButtonGroup();
		JPanel radioPanel1 = new JPanel(new GridLayout(0,2));
	    JRadioButton radio = new JRadioButton ("1 Player", true);
		radio.setName("1");
		radio.addActionListener(oh);
	    radio.setBackground(new Color(255, 128, 64));
		group1.add(radio);
		gbc.gridx=0;
		gbc.gridy=2;
		radioPanel1.add(radio);
	    
		radio = new JRadioButton("2 Players",false);
		radio.setName("2");
		radio.addActionListener(oh);
	    radio.setBackground(new Color(255, 128, 64));
		group1.add(radio);

		radioPanel1.add(radio);
		add(radioPanel1,gbc);

		//Readjust spacing
		gbc.insets = new Insets(5,0,5,0);
		
		//Forth Row : Label for Difficulty Option
		label = new JLabel("Difficulty: ");
		gbc.gridx=0;
		gbc.gridy=3;
		add(label,gbc);
		
		//Adjust spacing between Label and Radiobuttons
		gbc.insets = new Insets(2,0,2,0);
		
		//Forth Row : Radiobuttons for difficulty
		ButtonGroup group2 = new ButtonGroup();
		
		JPanel radioPanel2 = new JPanel(new GridLayout(0,3));

		radio = new JRadioButton("Easy", true);
		radio.setName("Easy");
		radio.addActionListener(oh);
	    radio.setBackground(new Color(255, 128, 64));
		group2.add(radio);
		gbc.gridx=0;
		gbc.gridy=4;
		radioPanel2.add(radio);
		
		radio = new JRadioButton("Medium", false);
		radio.setName("Medium");
		radio.addActionListener(oh);
	    radio.setBackground(new Color(255, 128, 64));
		group2.add(radio);
		radioPanel2.add(radio);
		
		radio = new JRadioButton("Hard", false);
		radio.setName("Hard");
		radio.addActionListener(oh);
	    radio.setBackground(new Color(255, 128, 64));
		group2.add(radio);
		radioPanel2.add(radio);

		add(radioPanel2,gbc);
		
		//Readjust spacing
		gbc.insets = new Insets(5,0,5,0);
		
		//Last Row : Return to Menu button
		back = new MyButton("Back");
		back.setName("Back");
		back.setPreferredSize(new Dimension(100,35));
		
		//add listener to the button
		bh = new ButtonsHandler(model);

		back.addActionListener(bh);
		gbc.gridx=1;
		gbc.gridy=5;
		add(back,gbc);
		
		
	    
	}
}
