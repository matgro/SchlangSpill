package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import Model.Model;

public class OptionsHandler implements ActionListener{

	private Model model;
	
	public OptionsHandler(Model model)  {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton b = (JRadioButton) e.getSource();
		if (b.getName() == "1")
			model.setPlayerNumber(1);
		else if (b.getName() == "2")
			model.setPlayerNumber(2);
		else if (b.getName() == "Easy")
			model.setSpeed(80);
		else if (b.getName() == "Medium")
			model.setSpeed(60);
		else if (b.getName() == "Hard")
			model.setSpeed(40);
		else
			System.out.println("Oups in OptionsHandler.");
	}

}
