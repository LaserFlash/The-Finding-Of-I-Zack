package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;

import java.awt.*;


/**
 * Created by Ben Allan
 */
public abstract class Entity implements Drawable, Savable{

    /**
     * Fields for location, bounding box, and width
     */

    protected Point location;
    protected transient BoundingBox box;

    public static int width = 40;

    public Entity(){}

    public Entity(Player p){
        this.location = p.location;
        setBox();
    }

    /**
     * Constructor for Entity
     * @param location  the location of the entity
     */
    public Entity(Point location) {
        this.location = location;
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    /**
     * Returns the location of the entity
     * @return  location
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Draw method for entity
     * @param g graphics object to draw on
     */
    public void draw(Graphics g) {}

    /**
     * Sets the bounding box
     */
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    /**
     * Returns the entity bounding box
     * @return  box
     */
    public BoundingBox getBoundingBox(){
        return box;
    }
}
