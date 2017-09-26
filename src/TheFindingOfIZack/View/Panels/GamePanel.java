package TheFindingOfIZack.View.Panels;


import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Model;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel indented to display the game
 * Consists of other panels to structure layout
 */
public class GamePanel extends ScreenPanel {

    private Model model;

    /**
     * Create a panel intended to draw the game on
     * @param model reference to the model being drawn
     */
    public GamePanel(Model model){
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
        /*
         * Draw player information such as health, armour, items etc
         */
        drawHealth(g);
        drawPlayerItems(g);
        drawPlayerArmour(g);
        drawRoom(g);
        //Health
        //Items
        //Room
        //Enemy
        //Player
    }

    private void drawHealth(Graphics g){

    }

    private void drawPlayerItems(Graphics g){

    }

    private void drawPlayerArmour(Graphics g){

    }

    private void drawRoom(Graphics g){

    }

    private void drawEntities(Graphics g){

    }
    private void drawPlayer(Graphics g){

    }
}
