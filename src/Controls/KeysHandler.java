package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Model;

public class KeysHandler implements KeyListener {

	private Model model;
	
	public KeysHandler(Model model) {
		this.model = model;
	}
	
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
        	model.getSnake1().turnLeft();
        	System.out.println("Left.");
        }

        if (key == KeyEvent.VK_RIGHT) {
        	model.getSnake1().turnRight();
        	System.out.println("Right.");
        }

        if (key == KeyEvent.VK_UP) {
        	model.getSnake1().turnUp();
        	System.out.println("Up.");
        }

        if (key == KeyEvent.VK_DOWN) {
        	model.getSnake1().turnDown();
        	System.out.println("Down.");
        }
        
        if (key == KeyEvent.VK_A) {
        	model.getSnake2().turnLeft();
        	System.out.println("Left.");
        }

        if (key == KeyEvent.VK_D) {
        	model.getSnake2().turnRight();
        	System.out.println("Right.");
        }

        if (key == KeyEvent.VK_W) {
        	model.getSnake2().turnUp();
        	System.out.println("Up.");
        }

        if (key == KeyEvent.VK_S) {
        	model.getSnake2().turnDown();
        	System.out.println("Down.");
        }
        
        if (key == KeyEvent.VK_ESCAPE) {
        	model.changeViewTo("Menu");
        	System.out.println("Escape.");
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setField(Model model) {
		this.model = model;
	}
}
