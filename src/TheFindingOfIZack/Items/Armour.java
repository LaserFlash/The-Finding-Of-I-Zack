package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Armour extends Item {

    private int armour = 5;

    public Armour(Player p) {
        super("armour", p);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) location.getX(), (int) location.getY(), width, width/3);
        g.fillRect((int) location.getX()+width/4, (int) location.getY(), width/2, width);
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY(), width, width/3);
        g.drawRect((int) location.getX()+width/4, (int) location.getY(), width/2, width);

    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.addArmour(armour);
        }
    }

}
