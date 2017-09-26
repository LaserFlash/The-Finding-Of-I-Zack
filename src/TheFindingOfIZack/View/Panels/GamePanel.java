package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.World.Game;
import com.sun.corba.se.impl.orbutil.graph.Graph;

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
        /*
         * Draw player information such as health, armour, items etc
         */
        drawHealth(g);
        drawPlayerItems(g);
        drawPlayerArmour();
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
