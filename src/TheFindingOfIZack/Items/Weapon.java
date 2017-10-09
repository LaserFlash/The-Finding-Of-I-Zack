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
        initialiseImage();
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
            img = ImageIO.read(ImageLoader.class.getResource(("/sword.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            weaponImage = img;
        }
    }

}
