package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.Model;

public class ButtonsHandler implements ActionListener {

	private Model model;
	
	public ButtonsHandler(Model model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton b = (JButton) ae.getSource();
		if (b.getName() == "Field")
			model.changeViewTo("Field");
		else if (b.getName() == "Menu")
			model.changeViewTo("Menu");
		else if (b.getName() == "Gameover")
			model.changeViewTo("Gameover");
		else if (b.getName() == "Options")
			model.changeViewTo("Options");
		else if (b.getName() == "Exit")
			System.exit(0);
		else
			System.out.println("Oups in ButtonsHandler.");
	}
	
}
