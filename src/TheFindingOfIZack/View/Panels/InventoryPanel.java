package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel{

    private static final int PADDING = 20;
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

        int maxArmour = model.getPlayer().getMaxArmour();
        int armour = model.getPlayer().getArmour();
        g.setFont(new Font("TimesRoman", Font.BOLD, 18));

        g.drawString("Health:", PADDING,PADDING);
        g.drawString("Armour:",PADDING,PADDING * 3);
        g.setColor(Color.red);
        g.drawString( health + "/" + maxHealth, PADDING + 60,PADDING);
        g.setColor(Color.BLUE);
        g.drawString(armour + "/" + maxArmour, PADDING + 60, PADDING * 3);
    }

    private void drawPlayerItems(Graphics g){

    }

    private void drawPlayerArmour(Graphics g){

    }

}
