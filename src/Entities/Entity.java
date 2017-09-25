package Entities;
import Items.Item;
import View.Drawable;
import World.Game;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Ben Allan
 */
public class Entity implements Drawable {

    protected int health;
    protected Point location;

    protected ArrayList<Item> items;

    protected Game world;

    public Entity(int health, Point location) {
        this.health = health;
        this.location = location;
        this.items = new ArrayList<Item>();
    }

    public void setWorld(Game g) {
        this.world = g;
    }

    public Point getLocation() {
        return this.location;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(location.x, location.y, 40, 40);
    }



}
