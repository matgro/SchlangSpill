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
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Controls.KeysHandler;
import Controls.FrameRateHandler;
import Controls.MouseHandler;
import Controls.MouseLifecycleHandler;
import Views.FieldView;

public class Model {

	private final int TILE_SIZE = 16; // pixels
	private final int FIELD_WIDTH = 480; // pixels of tiles
	private final int FIELD_HEIGHT = 480; // pixels of tiles
	private final int TILES_TOTAL = (FIELD_WIDTH / TILE_SIZE) * (FIELD_HEIGHT / TILE_SIZE);
	private final int RAND_POS = (FIELD_WIDTH / TILE_SIZE) - 1;
	private final int SPEED = 80;

	private int snakeLength; // # of tiles

	private int apple_x;
	private int apple_y;

	private int mouse_x = -100;
	private int mouse_y = -100;
	
	public enum Direction {
		UP, DOWN, RIGHT, LEFT
	};

	private boolean gameOver = false;

	private Timer timer;
	private Timer mouse_timer;
	private Timer mouse_lifecycle_timer;

	private int score;

	// Snake images
	private Image head, headUp, headDown, headLeft, headRight;
	private Image curve, curveLtoU, curveRtoU, curveLtoD, curveRtoD;
	private Image straight, straightH, straightV;
	private Image tail, tailUp, tailDown, tailLeft, tailRight;

	// Loot images
	private Image apple;
	private Image mouse;

	private ImageIcon iis, iih, iic, iit, iia, iim;

	private IModelListener listener;

	private JPanel cards;
	private CardLayout cl;

	Snake snake1, snake2;
	ArrayList<Snake> snakes = new ArrayList<Snake>();
	ArrayList<Snake> winners = new ArrayList<Snake>();
	ArrayList<Snake> losers = new ArrayList<Snake>();

	public Model() {

		loadImages();
	}

	public Model(JPanel cards) {
		this.cards = cards;
		this.cl = (CardLayout) cards.getLayout();
		
		snake1 = new Snake(TILES_TOTAL, TILE_SIZE, 80, 80, Direction.UP, Direction.UP, Color.BLUE);
		snake2 = new Snake(TILES_TOTAL, TILE_SIZE, 384, 80, Direction.UP, Direction.UP, Color.RED);
		
		snakes.add(snake1);
		snakes.add(snake2);

		loadImages();
		startGame();
	}

	public Snake getSnake1() {
		return snake1;
	}

	public Snake getSnake2() {
		return snake2;
	}

	public Snake getSnake(int index) {
		return snakes.get(index);
	}

	public ArrayList<Snake> getSnakes() {
		return snakes;
	}
	
	public ArrayList<Snake> getWinners() {
		return winners;
	}
	
	public ArrayList<Snake> getLosers() {
		return losers;
	}

	public int getFIELD_WIDTH() {
		return FIELD_WIDTH;
	}

	public int getFIELD_HEIGHT() {
		return FIELD_HEIGHT;
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

	public int getMouse_x() {
		return mouse_x;
	}

	public int getMouse_y() {
		return mouse_y;
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

	public Image getMouse() {
		return mouse;
	}
	
	public int getTileSize() {
		return TILE_SIZE;
	}

	public void setNextDirection(Direction nextDirection) {
		this.snake1.next_direction = nextDirection;
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

		iim = new ImageIcon("mouse.png");
		mouse = iim.getImage();
		mouse = mouse.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);

	}

	public void startGame() {

		snakeLength = 3;

		placeApple();

		FrameRateHandler frh = new FrameRateHandler(this);

		timer = new Timer(SPEED, frh);
		timer.start();

		mouse_timer = new Timer(10000, new MouseHandler(this));
		mouse_timer.start();

		mouse_lifecycle_timer = new Timer(5000, new MouseLifecycleHandler(this));
	}

	public void placeMouse() {
		mouse_x = (((int) (Math.random() * RAND_POS) * TILE_SIZE));
		mouse_y = (((int) (Math.random() * RAND_POS) * TILE_SIZE));

		mouse_lifecycle_timer.start();
	}

	public void mouseDisappear() {
		mouse_x = -1000;
		mouse_y = -1000;
		mouse_lifecycle_timer.stop();
	}

	public void checkApple(Snake snake) {

		if ((snake.x[0] == apple_x) && (snake.y[0] == apple_y)) {

			snake.length++;
			snake.score += 100;
			placeApple();
		}

	}

	public void checkMouse(Snake snake) {

		if ((snake.x[0] == mouse_x) && (snake.y[0] == mouse_y)) {

			snake.length++;
			snake.score += 200;
			mouseDisappear();
		}
	}

	public void move(Snake snake) {

		for (int z = snake.length; z > 0; z--) {
			snake.x[z] = snake.x[(z - 1)];
			snake.y[z] = snake.y[(z - 1)];
		}

		if (snake.next_direction == Direction.LEFT) {
			snake.x[0] -= TILE_SIZE;
			if (snake.x[0] < 0)
				snake.x[0] = FIELD_WIDTH - TILE_SIZE;
			snake.current_direction = Direction.LEFT;
		}

		if (snake.next_direction == Direction.RIGHT) {
			snake.x[0] += TILE_SIZE;
			if (snake.x[0] >= FIELD_WIDTH)
				snake.x[0] = 0;
			snake.current_direction = Direction.RIGHT;
		}

		if (snake.next_direction == Direction.UP) {
			snake.y[0] -= TILE_SIZE;
			if (snake.y[0] < 0)
				snake.y[0] = FIELD_HEIGHT - TILE_SIZE;
			snake.current_direction = Direction.UP;
		}

		if (snake.next_direction == Direction.DOWN) {
			snake.y[0] += TILE_SIZE;
			if (snake.y[0] >= FIELD_HEIGHT)
				snake.y[0] = 0;
			snake.current_direction = Direction.DOWN;
		}
	}

	public void checkCollision(Snake snake) {
		for (Snake s : snakes) {
			for (int z = s.length; z > 0; z--) {

				if ( (snake.x[0] == s.x[z]) && (snake.y[0] == s.y[z])) {
					snake.game_over = true;
					System.out.println("Collision.");
				}
			}
		}
	}

	public boolean checkGameOver() {
		losers.clear();
		winners.clear();
		boolean gameover = false;
		for (Snake s : snakes) {
			if (s.game_over == true) {
				losers.add(s);
				timer.stop();
				gameover = true;
			} else {
				winners.add(s);
			}
		}
		return gameover;
	}

	public void placeApple() {

		apple_x = (((int) (Math.random() * RAND_POS) * TILE_SIZE));
		apple_y = (((int) (Math.random() * RAND_POS) * TILE_SIZE));
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
		switch (viewname) {
		case "Field":
			cl.show(cards, "Field");
			break;
		case "Menu":
			cl.show(cards, "Menu");
			break;
		case "Options":
			cl.show(cards, "Options");
			break;
		case "Gameover":
			cl.show(cards, "Gameover");
			break;
		default:
			System.out.println("Nothing.");
		}

	}
}
