package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Model.Model;
import Model.Snake;

public class GameOverView extends JPanel {

	private Model model;

	public GameOverView(Model model) {
		this.model = model;
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		int i = 32;
		String msg = "Game Over. The winners are:" + System.lineSeparator();

		Font small = new Font("Helvetica", Font.BOLD, 24);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString("Game over.", (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i);
		i += metr.getHeight();
		g.drawString("Winner(s):", (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i );
		i += metr.getHeight();
		for (Snake s : model.getWinners()) {
			g.setColor(s.color);
			g.drawString(String.valueOf((char)9632)+String.valueOf((char)9632)+String.valueOf((char)9632), 16 + (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i);
			i += metr.getHeight();
			g.setColor(Color.white);
			g.drawString(String.valueOf(s.score), 16 + (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i );
			i += metr.getHeight();
		}
		g.drawString("Loser(s):", (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i);
		i += metr.getHeight();
		for (Snake s : model.getLosers()) {
			g.setColor(s.color);
			g.drawString(String.valueOf((char)9632)+String.valueOf((char)9632)+String.valueOf((char)9632), 16 + (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i);
			i += metr.getHeight();
			g.setColor(Color.white);
			g.drawString(String.valueOf(s.score), 16 + (model.getFIELD_WIDTH() - metr.stringWidth(msg)) / 2, i);
			i += metr.getHeight();
		}
	}
}
