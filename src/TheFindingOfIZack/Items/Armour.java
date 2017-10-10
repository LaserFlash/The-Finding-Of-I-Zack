package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Ben Allan
 */
public class Armour extends Item {

    private static Image armourImage;

    private int armour = 5;

    public Armour(Player p) {
        super("armour", p);
        this.armourImage = ImageLoader.loadImage("/armour.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(armourImage, (int) location.getX(), (int) location.getY(), null);
    }

    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.addArmour(armour);
        }
    }

}
