package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Panel representing the GameArea or the dungeon / play area
 * This is where the player, mobs and game world are drawn
 */
public class GameArea extends JPanel{
    private Model model;

    /**
     * Constructor for the GameArea requires a reference to the model is represents
     * @param m Model being represented
     */
    GameArea(Model m){
        super();
        this.model = m;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawRoom(g);
        drawPlayer(g);
        Toolkit.getDefaultToolkit().sync();     //Remove frame lag in linux
    }

    /**
     * Draw the room has given by the Model's Room object
     * Includes Entities in the room
     * @param g Graphic object to draw on
     */
    private void drawRoom(Graphics g){
        model.getPlayer().getRoom().draw(g);
    }

    /**
     * Draw the player as given by the Model's Player object
     * @param g Graphic object to draw on
     */
    private void drawPlayer(Graphics g){
        model.getPlayer().draw(g);
    }
}
