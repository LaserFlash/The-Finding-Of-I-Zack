package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Rock extends Entity implements Savable {

    private static Image rocksImage;

    int health = 1000;

    public Rock(Point location) {
        super(location);
        this.rocksImage = ImageLoader.loadImage("/rocks.png");
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(rocksImage, (int) location.getX(), (int) location.getY(), null);
    }
}
