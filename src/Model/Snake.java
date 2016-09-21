package Model;

import java.awt.Color;

import Model.Model.Direction;

public class Snake {
	
	public int tiles_total;
	public final int x[];				// snake path
	public final int y[];	

	public Model.Direction current_direction;
	public Model.Direction next_direction;
	public int score;
	public int length;
	public boolean game_over;
	public Color color;
	
	public Snake(int tiles_total, int tile_size, int x, int y, Model.Direction cur, Model.Direction nex, Color color) 
	{
		this.tiles_total = tiles_total;
		this.x = new int[tiles_total];
		this.y = new int[tiles_total];
		this.x[0] = x; this.x[1] = this.x[0]-tile_size; this.x[2] = this.x[1]-tile_size;
		this.y[0] = y; this.y[1] = this.y[0]; this.y[2] = this.y[0];
		this.current_direction = cur;
		this.next_direction = nex;
		this.color = color;
		this.score = 0;
		this.length = 3;
		this.game_over = false;
	}
	
	public void turnLeft() {
		
		if (current_direction != Direction.RIGHT) {
			next_direction = Direction.LEFT;
		}
	}

	public void turnRight() {
		
		if (current_direction != Direction.LEFT) {
			next_direction = Direction.RIGHT;
		}
	}

	public void turnUp() {

		if (current_direction != Direction.DOWN) {
			next_direction = Direction.UP;
		}
	}

	public void turnDown() {
		
		if (current_direction != Direction.UP) {
			next_direction = Direction.DOWN;
		}
	}
	
	public void printX() {
		for (int i : x) {
			System.out.print(i + ";");
		}
		System.out.println();
	}
}
