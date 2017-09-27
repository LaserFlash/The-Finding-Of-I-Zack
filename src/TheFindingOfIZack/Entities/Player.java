package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Savable;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Ben Allan
 */
public class Player extends Entity implements Savable {

    private int armour = 0;

    public Player(int health, Point location) {
        super(health, location);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(location.x, location.y, width, width);
        super.draw(g);
    }


}
