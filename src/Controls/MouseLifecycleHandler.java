package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;

public class MouseLifecycleHandler implements ActionListener {
	
	private Model model;
	
	public MouseLifecycleHandler(Model model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.mouseDisappear();
	}
	
}
