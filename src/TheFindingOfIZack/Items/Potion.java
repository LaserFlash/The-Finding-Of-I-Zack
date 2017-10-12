package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Ben Allan
 */
public class Potion extends Item implements Savable{

    private static Image potionImage;

    private int health = 25;

    public Potion(Player p) {
        super("potion", p);
        this.potionImage = ImageLoader.loadImage("/potion.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(potionImage, (int) location.getX(), (int) location.getY(), null);
    }

    @Override
    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            if (player.getHealth() == player.getMaxHealth()) {return;}
            collected = true;
            player.heal(health);
        }
    }

}
