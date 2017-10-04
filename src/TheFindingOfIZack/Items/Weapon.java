package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Weapon extends Item {

    public Weapon(Player p) {
        super("weapon", p);
    }

    @Override
    public void draw(Graphics g) {

    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.weaponUpgrade();
        }
    }

}
