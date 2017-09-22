package TheFindingOfIZack.Entities;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Player extends Entity {

    private int armour = 0;

    public Player(int health, Point location) {
        super(health, location);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(location.x, location.y, 40, 40);
        super.draw(g);
    }


}
