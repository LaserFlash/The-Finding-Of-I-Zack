package TheFindingOfIZack.View.Panels;


import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel indented to display the game
 * Consists of other panels to structure layout
 */
public class GamePanel extends ScreenPanel {

    private Model model;
    private JPanel topInfo;
    private JPanel gameArea;

    /**
     * Create a panel intended to draw the game on
     * @param model reference to the model being drawn
     */
    public GamePanel(Model model){
        super();
        this.model = model;
        this.topInfo = new JPanel();
        this.gameArea = new JPanel();
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("Trying to draw");
        /*
         * Draw player information such as health, armour, items etc
         */
        drawHealth(g);
        drawPlayerItems(g);
        drawPlayerArmour(g);
        drawRoom(g);

        drawPlayer(g);
        drawEntities(g);
    }

    private void drawHealth(Graphics g){
        int maxHealth = model.getPlayer().getMaxHealth();
        int health = model.getPlayer().getHealth();

        g.drawString(health + "/" + maxHealth, 50,50);
    }

    private void drawPlayerItems(Graphics g){

    }

    private void drawPlayerArmour(Graphics g){

    }

    private void drawRoom(Graphics g){
        model.getPlayer().getRoom().draw(g);
    }

    private void drawEntities(Graphics g){
        model.getPlayer();
    }
    private void drawPlayer(Graphics g){
        model.getPlayer().draw(g);
    }
}
