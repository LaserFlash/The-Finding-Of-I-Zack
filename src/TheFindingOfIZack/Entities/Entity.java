package TheFindingOfIZack.Entities;

import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.World.Game;
import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameDimensions.*;


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
        outOfBounds();
    }

    public void setWorld(Game g) {
        this.world = g;
    }

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

    public void draw(Graphics g) {

    }

    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    private void outOfMapBoundsError(String str){
        System.err.print("Error: Entity " + str + " is out of map bounds \n");
    }

}
