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

    public static int width = 40;

    protected Game world;

    public Entity(){

    }

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

    public int getHealth() {
        return health;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY(), width, width);
    }

    public void damage(int damage) {
        health -= damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }



}
