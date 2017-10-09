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

    private static Image rocks;

    int health = 1000;

    public Rock(Point location) {
        super(location);
        initialiseImage();
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    @Override
    public void draw(Graphics g) {
        /*g.setColor(Color.DARK_GRAY);

        Polygon rock = new Polygon();

        rock.addPoint((int) location.getX(), (int) location.getY()+width);
        rock.addPoint((int) location.getX()+width, (int) location.getY()+width);
        rock.addPoint((int) location.getX()+width/2, (int) location.getY());

        g.fillPolygon(rock);

        g.setColor(Color.black);

        g.drawPolygon(rock);*/

        g.drawImage(rocks, (int) location.getX(), (int) location.getY(), null);
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
            img = ImageIO.read(ImageLoader.class.getResource(("/rocks.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            rocks = img;
        }
    }

}
