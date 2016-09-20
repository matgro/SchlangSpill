package Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controls.ControlsHandler;
import Model.Model;
import Views.FieldView;
import Views.GameOverView;
import Views.MenuView;
import Views.OptionsView;

public class Game extends JFrame {

	private FieldView field;
	private MenuView menu;
	private OptionsView options;
	private GameOverView gameover;
	private Model model;
	private JPanel cards;
	
    public Game() {
    	
    	// creation of layout and model
    	cards = new JPanel(new CardLayout());
    	model = new Model(cards);
    	
    	// creation of all views
    	field = new FieldView(model);
    	menu = new MenuView(model);
    	options = new OptionsView(model);
    	gameover = new GameOverView(model);
    	
    	// adds fieldview to Model for event handling
        model.addView(field);
        
        // adds views to cardlayout
        cards.add(field, "Field");
        cards.add(menu, "Menu");
        cards.add(options, "Options");
        cards.add(gameover, "Gameover");
        
        this.getContentPane().add(cards);
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "Menu");
        addKeyListener(new ControlsHandler(model));

        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    

    public static void main(String[] args) {
        
         new Game();
    }
}