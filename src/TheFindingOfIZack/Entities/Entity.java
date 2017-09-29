package TheFindingOfIZack.Entities;

import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.World.Game;
import javafx.geometry.BoundingBox;


import java.awt.*;


/**
 * Created by Ben Allan
 */
public abstract class Entity implements Drawable {

    protected Point location;
    protected BoundingBox box;

    public static int width = 40;

    protected Game world;

    public Entity(){

    }

    public Entity(Point location) {
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
        g.drawRect((int) location.getX(), (int) location.getY(), width, width);
    }

    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }



}
