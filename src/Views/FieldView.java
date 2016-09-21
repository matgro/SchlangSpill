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

public class FieldView extends JPanel implements IModelListener {

	private Model model;

	public FieldView(Model model) {
		this.model = model;
		
		setPreferredSize(new Dimension(480, 480));
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

			int snakeLength = model.getSnakeLength();

			g.drawImage(model.getApple(), model.getApple_x(), model.getApple_y(), this);
			
			g.drawImage(model.getMouse(), model.getMouse_x(), model.getMouse_y(), this);
			
			for (int z = 0; z < snakeLength; z++) {
				
				
				if (z == 0) {
					g.drawImage(model.getHead(), model.getX(z), model.getY(z), this);
				} else if (z == snakeLength - 1) {
					g.drawImage(model.getTail(), model.getX(z), model.getY(z), this);
				} else {
					g.drawImage(model.getStraight(), model.getX(z), model.getY(z), this);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else {
	        String msg = "Game Over. Your score: "+"\n"+String.valueOf(model.getScore());
	        Font small = new Font("Helvetica", Font.BOLD, 14);
	        FontMetrics metr = getFontMetrics(small);

	        g.setColor(Color.white);
	        g.setFont(small);
	        g.drawString(msg, (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, model.getFIELD_HEIGHT() / 2);
		}
	}
}
