package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controls.ButtonsHandler;
import Custom.MyButton;
import Model.Model;

public class MenuView extends JPanel {

	private Model model;
	private Image img;
	private MyButton play, options, exit;
	private ButtonsHandler bh;
	
	public MenuView(Model model) {
		this.model = model;
		
		
		setPreferredSize(model.getDimension());
		setBackground(new Color(255, 128, 64));

		ImageIcon img = new ImageIcon(new ImageIcon("snake-logo.png").getImage().getScaledInstance(256, 128, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(img);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,0,5,0);
		setLayout(layout);
		gbc.gridx=0;
		gbc.gridy=0;
		
		add(logo,gbc);

		play = new MyButton("Play");
		play.setName("Field");
		play.setPreferredSize(new Dimension(100,35));
		options = new MyButton("Options");
		options.setName("Options");
		options.setPreferredSize(new Dimension(100,35));
		exit = new MyButton("Exit");
		exit.setName("Exit");
		exit.setPreferredSize(new Dimension(100,35));
		
		bh = new ButtonsHandler(model);

		play.addActionListener(bh);

		options.addActionListener(bh);

		exit.addActionListener(bh);
		gbc.gridx=0;
		gbc.gridy=1;
		add(play,gbc); 
		gbc.gridx=0;
		gbc.gridy=2;
		add(options,gbc); 
		gbc.gridx=0;
		gbc.gridy=3;
		add(exit,gbc);
	}

}
