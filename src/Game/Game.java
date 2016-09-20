package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controls.ControlsHandler;
import Model.Model;
import Views.FieldView;
import Views.MenuView;

public class Game extends JFrame {

	private FieldView field;
	private MenuView menu;
	private Model model;
	private JPanel panel;
	
    public Game() {

    	
    	model = new Model();
    	
    	field = new FieldView(model);
    	menu = new MenuView(model);
    	
        model.addView(field);
        
        this.getContentPane().add(field);
        //this.getContentPane().add(menu);
        
        addKeyListener(new ControlsHandler(model));

        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        System.out.println("Dest ass nach een Test.");
    }
    

    public static void main(String[] args) {
        
         new Game();
    }
}