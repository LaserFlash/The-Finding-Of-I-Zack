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
public class Armour extends Item {

    private static Image armourImage;

    private int armour = 5;

    public Armour(Player p) {
        super("armour", p);
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
            img = ImageIO.read(ImageLoader.class.getResource(("/armour.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            armourImage = img;
        }
    }

}
