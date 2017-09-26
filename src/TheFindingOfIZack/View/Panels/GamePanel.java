package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.World.Game;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel indented to display the game
 * Consists of other panels to structure layout
 */
public class GamePanel extends ScreenPanel {

    private Game model;

    public GamePanel(Game model){
        super();
        this.model = model;
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("Trying to draw");
        //TODO get stuff from model to paint
        //Health
        //Items
        //Room
        //Enemy
        //Player
    }
}
