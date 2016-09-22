package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Model.IModelListener;
import Model.Model;
import Model.Snake;

public class FieldView extends JPanel implements IModelListener {

	private Model model;

	public FieldView(Model model) {
		this.model = model;

		setPreferredSize(model.getDimension());
		setBackground(Color.BLACK);
	}

	@Override
	public void modelChanged() {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (!model.isGameOver()) {
			for (Snake snake : model.getSnakes()) {
				int snakeLength = snake.length;
				int gap = 2;
				g.drawImage(model.getApple(), model.getApple_x(), model.getApple_y(), this);
				g.drawImage(model.getMouse(), model.getMouse_x(), model.getMouse_y(), this);

				for (int z = 0; z < snakeLength; z++) {

					if (z == 0) {
						//g.drawImage(model.getHead(), snake.x[z], snake.y[z], this);
						g.setColor(snake.color);
						g.fillOval(snake.x[z] + gap, snake.y[z] + gap, model.getTileSize()-2*gap, model.getTileSize()-2*gap);
					} else if (z == snakeLength - 1) {
						//g.drawImage(model.getTail(), snake.x[z], snake.y[z], this);
						g.drawOval(snake.x[z] + gap, snake.y[z] + gap, model.getTileSize()-2*gap, model.getTileSize()-2*gap);
					} else {
						//g.drawImage(model.getStraight(), snake.x[z], snake.y[z], this);
						g.drawOval(snake.x[z] + gap, snake.y[z] + gap, model.getTileSize()-2*gap, model.getTileSize()-2*gap);
					}
				}

				Toolkit.getDefaultToolkit().sync();
			}
		} else {
			model.changeViewTo("Gameover");
		}
	}
}
