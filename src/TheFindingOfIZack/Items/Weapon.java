package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Ben Allan
 */
public class Weapon extends Item {

    private static Image weaponImage;

    public Weapon(Player p) {
        super("weapon", p);
        this.weaponImage = ImageLoader.loadImage("/sword.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(weaponImage, (int) location.getX(), (int) location.getY(), null);
    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.weaponUpgrade();
        }
    }

}
