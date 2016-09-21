package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;

public class MouseHandler implements ActionListener {
	
	private Model model;
	
	public MouseHandler(Model model) {
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		model.placeMouse();
	}

}
