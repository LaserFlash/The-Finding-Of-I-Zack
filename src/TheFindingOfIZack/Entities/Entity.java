package TheFindingOfIZack.Entities;

import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.World.Game;
import javafx.geometry.BoundingBox;

import java.awt.*;


/**
 * Created by Ben Allan
 */
public class Entity implements Drawable {

    protected int health;
    protected Point location;
    protected BoundingBox box;

    public static int width = 20;

    protected Game world;

    public Entity(int health, Point location) {
        this.health = health;
        this.location = location;
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    public void setWorld(Game g) {
        this.world = g;
    }

    public Point getLocation() {
        return this.location;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(location.x, location.y, width, width);
    }



}
