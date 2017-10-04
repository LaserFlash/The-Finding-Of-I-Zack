package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;

import java.awt.*;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Rock extends Entity implements Savable {

    int health = 1000;

    public Rock(Point location) {
        super(location);
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        Polygon rock = new Polygon();

        rock.addPoint((int) location.getX(), (int) location.getY()+width);
        rock.addPoint((int) location.getX()+width, (int) location.getY()+width);
        rock.addPoint((int) location.getX()+width/2, (int) location.getY());

        g.fillPolygon(rock);

        g.setColor(Color.black);

        g.drawPolygon(rock);
    }

}
