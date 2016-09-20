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

	public MenuView(Model model) {
		this.model = model;
		
		
		setPreferredSize(new Dimension(480, 480));
		setBackground(new Color(255, 128, 64));

		ImageIcon img = new ImageIcon(new ImageIcon("snake-logo.png").getImage().getScaledInstance(256, 128, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(img);
		
		add(logo, BorderLayout.CENTER);

		play = new JButton("Play");
		options = new JButton("Options");
		exit = new JButton("Exit");
		play.addActionListener(new ButtonsHandler(model));
		
		add(play); 
		add(options); 
		add(exit);
	}

}
