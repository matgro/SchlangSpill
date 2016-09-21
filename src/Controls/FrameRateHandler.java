package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;
import Model.Snake;

public class FrameRateHandler implements ActionListener {

	private Model model;

	public FrameRateHandler(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!model.isGameOver()) {
			for (Snake snake : model.getSnakes()) {
				model.checkApple(snake);
				model.checkMouse(snake);
				model.checkCollision(snake);
				if (!model.checkGameOver())
					model.move(snake);
				else
					model.changeViewTo("Gameover");
			}
		}

		model.updateView();
	}

}
