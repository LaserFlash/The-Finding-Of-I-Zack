package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel{

    private static final int PADDING = 35;
    private static final int BAR_PADDING = 20;
    private static final int BAR_HEIGHT = 20;
    private static final int ARC = 20;

    private static final Image header;
    private Model model;

    static {
        header = ImageLoader.loadImage("/header.png").getScaledInstance(GameSize.GAME_WIDTH,GameSize.MENU_HEIGHT,Image.SCALE_DEFAULT);
    }

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
        int health = Math.max(model.getPlayer().getHealth(),0);

        int maxArmour = model.getPlayer().getMaxArmour();
        int armour = Math.max(model.getPlayer().getArmour(),0);

        g.drawImage(header,0,0,null);
        g.setColor(Color.GRAY);
        g.drawString("Health:", PADDING,PADDING);
        g.fillRoundRect(PADDING + 100, BAR_PADDING, maxHealth,BAR_HEIGHT,ARC,ARC);
        g.setColor(Color.red);
        g.fillRoundRect(PADDING + 100, BAR_PADDING, health,BAR_HEIGHT,ARC,ARC);

        g.setColor(Color.GRAY);
        g.drawString("Armour:",PADDING,PADDING * 2);
        g.fillRoundRect(PADDING + 100, BAR_PADDING + PADDING, maxArmour,BAR_HEIGHT,ARC,ARC);
        g.setColor(Color.blue);
        g.fillRoundRect(PADDING + 100, BAR_PADDING + PADDING, armour,BAR_HEIGHT,ARC,ARC);
    }

    private void drawPlayerItems(Graphics g){

    }

    private void drawPlayerArmour(Graphics g){

    }

}
