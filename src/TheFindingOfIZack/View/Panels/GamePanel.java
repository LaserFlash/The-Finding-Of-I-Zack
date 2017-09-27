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
        this.topInfo = new InventoryPanel(model);
        this.topInfo.setPreferredSize(new Dimension(900,100));
        this.gameArea = new GameArea(model);
        this.gameArea.setPreferredSize(new Dimension(900,500));

        this.add(topInfo,BorderLayout.NORTH);
        this.add(gameArea,BorderLayout.SOUTH);
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

        this.topInfo.repaint();
        this.gameArea.repaint();
    }
}
