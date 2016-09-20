package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;

public class FrameRateHandler implements ActionListener {

	private Model model;
	
	public FrameRateHandler(Model model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
        if (!model.isGameOver()) {

            model.checkApple();
            model.checkCollision();
            model.move();
        }

        model.updateView();
	}

}
