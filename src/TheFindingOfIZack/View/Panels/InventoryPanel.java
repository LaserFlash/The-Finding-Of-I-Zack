package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel{

    private Model model;

    public InventoryPanel(Model m){
        super();
        this.model = m;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawHealth(g);
        drawPlayerArmour(g);
        drawPlayerItems(g);
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

}
