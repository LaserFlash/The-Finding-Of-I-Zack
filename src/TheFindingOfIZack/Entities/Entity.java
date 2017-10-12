package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.World.Game;
import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameDimensions.*;


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
        p.location.getX();
        this.box = new BoundingBox(p.location.getX(), p.location.getY(), width, width);
        outOfBounds();
    }

    /**
     * Constructor for Entity
     * @param location  the location of the entity
     */
    public Entity(Point location) {
        this.location = location;
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
        outOfBounds();
    }

    /**
     * Returns the location of the entity
     * @return  location
     */
    public Point getLocation() {
        return this.location;
    }

    public void outOfBounds(){
        if (this.box.getMaxX() > RIGHT_WALL ||
                this.box.getMinX() < LEFT_WALL ||
                this.box.getMaxY() > BOTTOM_WALL ||
                this.box.getMinY() < TOP_WALL){
            outOfMapBoundsError(this.toString());
        }
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

    /**
     * Prints out error if an entity moves out of bounds
     * @param str   the entity that has moved out of bounds
     */
    private void outOfMapBoundsError(String str){
        System.err.print("Error: Entity " + str + " is out of map bounds \n");
    }

}
