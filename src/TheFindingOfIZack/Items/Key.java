package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by allanbenj1 on 26/09/17.
 */
public class Key extends Item {

    private static Image keyImage;

    public Key(Player p) {
        super("key", p);
        this.keyImage = ImageLoader.loadImage("/key.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(keyImage, (int) location.getX(), (int) location.getY(), null);
    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.addKey();
        }
    }
}
