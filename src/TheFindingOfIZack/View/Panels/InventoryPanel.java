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
    private static final Image healthImg;
    private static final Image armourImg;
    private static final Image keyImg;
    private Model model;

    static {
        header = ImageLoader.loadImage("/header.png").getScaledInstance(GameSize.GAME_WIDTH,GameSize.MENU_HEIGHT,Image.SCALE_DEFAULT);
        healthImg = ImageLoader.loadImage("/potion.png");
        keyImg = ImageLoader.loadImage("/doorKey.png");
        armourImg = ImageLoader.loadImage("/armour.png");
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

        int maxArmour = model.getPlayer().getMaxArmour() * 4;
        int armour = Math.max(model.getPlayer().getArmour(),0) * 4;

        g.drawImage(header,0,0,null);
        g.setColor(Color.GRAY);
        g.drawImage(healthImg,PADDING,BAR_PADDING,null);
        g.fillRoundRect(PADDING*3, BAR_PADDING * 2, maxHealth,BAR_HEIGHT,ARC,ARC);
        g.setColor(Color.red);
        g.fillRoundRect(PADDING*3, BAR_PADDING * 2, health,BAR_HEIGHT,ARC,ARC);

        g.setColor(Color.GRAY);
        g.drawImage(armourImg, PADDING * 4 + maxHealth,BAR_PADDING,null);
        g.fillRoundRect(PADDING * 6 + maxHealth, BAR_PADDING *2 , maxArmour,BAR_HEIGHT,ARC,ARC);
        g.setColor(Color.blue);
        g.fillRoundRect(PADDING * 6 + maxHealth, BAR_PADDING *2, armour,BAR_HEIGHT,ARC,ARC);

        if (model.getPlayer().getKey()){
            g.drawImage(keyImg, GameSize.GAME_WIDTH - PADDING*4,BAR_PADDING * 2,null);
        }
    }

    private void drawPlayerItems(Graphics g){

    }

    private void drawPlayerArmour(Graphics g){

    }

}
