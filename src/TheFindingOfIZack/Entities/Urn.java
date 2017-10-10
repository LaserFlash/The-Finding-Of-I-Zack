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

    int health = 10;

    public Urn(Point location, Player p) {
        super(location);
        this.p = p;
        initialiseImage();

        int random = (int) (Math.random()*100);
        if (random >= 50) {
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


    public void initialiseImage() {
        Image img = new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

        try {
            img = ImageIO.read(ImageLoader.class.getResource(("/pot.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urnsImage = img;
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }


}
