package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Items.*;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Urn extends Entity implements Savable{

    private static Image urnsImage;

    private Player p;

    private Item item = null;

    private boolean destroyed = false;

    int health = 15;

    public Urn(Point location, Player p) {
        super(location);
        this.p = p;
        this.urnsImage = ImageLoader.loadImage("/pot.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);

        int random = (int) (Math.random()*100);
        if (random >= 25) {
            random = (int) (Math.random()*100);
            if (random <= 33) {item = new Armour(p);}
            else if (random <= 66) {item = new Weapon(p);}
            else {item = new Potion(p);}
            item.setLocation(location);
            item.setBox();
        }

    }

    public void damage(int damage) {
        this.health -= damage;
        if (health < 0) {
            health = 0;
            destroyed = true;
            if (item != null) {
                p.getRoom().getCollectables().add(item);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(urnsImage, (int) location.getX(), (int) location.getY(), null);

    }

    public boolean isDestroyed() {
        return destroyed;
    }


}
