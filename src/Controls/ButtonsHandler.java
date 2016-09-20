package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;

public class ButtonsHandler implements ActionListener {

	private Model model;
	
	public ButtonsHandler(Model model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		model.changeViewTo("Field");
	}
	
}
