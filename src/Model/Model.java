package Model;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Controls.ControlsHandler;
import Controls.FrameRateHandler;

public class Model {

	private final int TILE_SIZE = 16; 							// pixels
	private final int FIELD_WIDTH = 480; 						// pixels of tiles
	private final int FIELD_HEIGHT = 480; 						// pixels of tiles
	private final int TILES_TOTAL = (FIELD_WIDTH/TILE_SIZE) * (FIELD_HEIGHT/TILE_SIZE);
	private final int RAND_POS = (FIELD_WIDTH/TILE_SIZE) - 1;
	private final int SPEED = 70;

	private final int x[] = new int[TILES_TOTAL];				// snake path
	private final int y[] = new int[TILES_TOTAL];				// snake path

	private int snakeLength;									// # of tiles
	
	private int apple_x;
	private int apple_y;
	
	private enum Direction {UP, DOWN, RIGHT, LEFT};
	private Direction currentDirection;
	private Direction nextDirection;
	
	private boolean gameOver = false;

	private Timer timer;
	private int score;
	
	// Snake images
	private Image head, headUp, headDown, headLeft, headRight;
	private Image curve, curveLtoU, curveRtoU, curveLtoD, curveRtoD;
	private Image straight, straightH, straightV;
	private Image tail, tailUp, tailDown, tailLeft, tailRight;
	
	// Loot images
	private Image apple;
	private Image mouse;
	
	private ImageIcon iis, iih, iic, iit, iia;
	
	private IModelListener listener;
	
	private JPanel cards;
	private CardLayout cl;
	
	public Model() {
		
		loadImages();	
	}
	
	public Model(JPanel cards) {
		this.cards = cards;
		this.cl = (CardLayout) cards.getLayout();
		loadImages();
	}

	public int getFIELD_WIDTH() {
		return FIELD_WIDTH;
	}

	public int getFIELD_HEIGHT() {
		return FIELD_HEIGHT;
	}

	public int getX(int index) {
		return x[index];
	}

	public int getY(int index) {
		return y[index];
	}

	public int getSnakeLength() {
		return snakeLength;
	}

	public int getApple_x() {
		return apple_x;
	}

	public int getApple_y() {
		return apple_y;
	}

	public Image getHead() {
		return head;
	}

	public Image getCurve() {
		return curve;
	}

	public Image getStraight() {
		return straight;
	}

	public Image getTail() {
		return tail;
	}

	public Image getApple() {
		return apple;
	}
	
	public void setNextDirection(Direction nextDirection) {
		this.nextDirection = nextDirection;
	}

	public void loadImages() {
		
		// old
		iih = new ImageIcon("head.png");
		head = iih.getImage();
		head = head.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
		
		iis = new ImageIcon("straight.png");
		straight = iis.getImage();
		straight = straight.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
		
		iic = new ImageIcon("curve.png");
		curve = iic.getImage();
		curve = curve.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
		
		iit = new ImageIcon("tail.png");
		tail = iit.getImage();
		tail = tail.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);

		iia = new ImageIcon("apple.png");
		apple = iia.getImage();
		apple = apple.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);

	}

	public void startGame() {

		snakeLength = 3;

		x[0]=80; y[0]=80;
		x[1]=64; y[1]=80;
		x[2]=48; y[2]=80;
		
		currentDirection = Direction.RIGHT;
		nextDirection = Direction.RIGHT;

		placeApple();
		
		FrameRateHandler frh = new FrameRateHandler(this);
		
        timer = new Timer(SPEED, frh);
        timer.start();
	}

	public void checkApple() {

		if ((x[0] == apple_x) && (y[0] == apple_y)) {

			snakeLength++;
			score += 100;
			placeApple();
		}
	}

	public void move() {

		for (int z = snakeLength; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (nextDirection == Direction.LEFT) {
			x[0] -= TILE_SIZE;
			if (x[0] < 0)
				x[0] = FIELD_WIDTH - TILE_SIZE;
			currentDirection = Direction.LEFT;
		}

		if (nextDirection == Direction.RIGHT) {
			x[0] += TILE_SIZE;
			if (x[0] >= FIELD_WIDTH)
				x[0] = 0;
			currentDirection = Direction.RIGHT;
		}

		if (nextDirection == Direction.UP) {
			y[0] -= TILE_SIZE;
			if (y[0] < 0)
				y[0] = FIELD_HEIGHT - TILE_SIZE;
			currentDirection = Direction.UP;
		}

		if (nextDirection == Direction.DOWN) {
			y[0] += TILE_SIZE;
			if (y[0] >= FIELD_HEIGHT)
				y[0] = 0;
			currentDirection = Direction.DOWN;
		}
	}

	public void checkCollision() {

		for (int z = snakeLength; z > 0; z--) {

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				gameOver = true;
			}
		}

		if (gameOver) {
			timer.stop();
		}
	}

	public void placeApple() {

		apple_x = (((int) (Math.random() * RAND_POS) * TILE_SIZE));
		apple_y = (((int) (Math.random() * RAND_POS) * TILE_SIZE));
	}

	public void turnLeft() {
		
		if (currentDirection != Direction.RIGHT) {
			nextDirection = Direction.LEFT;
		}
	}

	public void turnRight() {
		
		if (currentDirection != Direction.LEFT) {
			nextDirection = Direction.RIGHT;
		}
	}

	public void turnUp() {

		if (currentDirection != Direction.DOWN) {
			nextDirection = Direction.UP;
		}
	}

	public void turnDown() {
		
		if (currentDirection != Direction.UP) {
			nextDirection = Direction.DOWN;
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void addView(IModelListener ml) {
		listener = ml;
	}

	public void updateView() {
		listener.modelChanged();
	}
	
	public int getScore() {
		return score;
	}
	
	public void endGame() {
		gameOver = true;
	}
	
	public void changeViewTo(String viewname) {
		switch(viewname){
		case "Field":
			cl.show(cards, "Field");
			break;
		case "Menu":
			cl.show(cards, "Menu");
			break;
		case "Options":
			cl.show(cards, "Options");
			break;
		default:
			System.out.println("Nothing.");
		}
		
	}
}
