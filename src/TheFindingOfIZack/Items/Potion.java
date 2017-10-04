package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Potion extends Item {

    private int health = 25;

    public Potion(Player p) {
        super("potion", p);
    }

    @Override
    public void draw(Graphics g) {

    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.heal(health);
        }
    }

}
