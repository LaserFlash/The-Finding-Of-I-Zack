package Entities;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class WorldItem extends Entity {

    public WorldItem(int health, Point location) {
        super(health, location);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(location.x, location.y, 40, 40);
        super.draw(g);
    }

}
