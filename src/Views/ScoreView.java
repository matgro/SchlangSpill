package Views;

import java.awt.Dimension;

import javax.swing.JPanel;

import Model.Model;

public class ScoreView extends JPanel {
	
	private Model model;
	
	public ScoreView(Model model) {
		this.model = model;
		
		setPreferredSize(new Dimension(480, 32));
		
	}
	
	
}
