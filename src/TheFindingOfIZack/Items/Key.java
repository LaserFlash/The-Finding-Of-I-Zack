package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;

import java.awt.*;

/**
 * Created by allanbenj1 on 26/09/17.
 */
public class Key extends Item {

    public Key(Player p) {
        super("key", p);
    }

    @Override
    public void draw(Graphics g) {

    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.addKey();
        }
    }


}
