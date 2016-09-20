package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Model;

public class ControlsHandler implements KeyListener {

	private Model model;
	
	public ControlsHandler(Model model) {
		this.model = model;
	}
	
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	model.turnLeft();
        	System.out.println("Left.");
        }

        if (key == KeyEvent.VK_RIGHT) {
        	model.turnRight();
        	System.out.println("Right.");
        }

        if (key == KeyEvent.VK_UP) {
        	model.turnUp();
        	System.out.println("Up.");
        }

        if (key == KeyEvent.VK_DOWN) {
        	model.turnDown();
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
