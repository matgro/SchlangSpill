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
import Model.Model;

public class MenuView extends JPanel {

	private Model model;
	private Image img;
	private JButton play, options, exit;
	private ButtonsHandler bh;
	
	public MenuView(Model model) {
		this.model = model;
		
		
		setPreferredSize(new Dimension(480, 480));
		setBackground(new Color(255, 128, 64));

		ImageIcon img = new ImageIcon(new ImageIcon("snake-logo.png").getImage().getScaledInstance(256, 128, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(img);
		
		add(logo, BorderLayout.CENTER);

		play = new JButton("Play");
		play.setName("Field");
		options = new JButton("Options");
		options.setName("Options");
		exit = new JButton("Exit");
		exit.setName("Exit");
		
		bh = new ButtonsHandler(model);
		play.addActionListener(bh);
		options.addActionListener(bh);
		exit.addActionListener(bh);
		
		add(play); 
		add(options); 
		add(exit);
	}

}
