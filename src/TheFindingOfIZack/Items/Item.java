package TheFindingOfIZack.Items;
import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;
import TheFindingOfIZack.Util.GameSize;


import java.awt.*;


/**
 * Created by allanbenj1 on 26/09/17.
 */
public abstract class Item implements Drawable{

    protected Point location;
    protected String type;
    protected BoundingBox box;
    protected int width = 40;
    protected boolean collected = false;

    protected Player player;

    public Item(String type, Player p) {
        this.type = type;
        location = new Point((GameSize.WINDOW_WIDTH/2)-(width/2), (GameSize.GAME_HEIGHT/2)-(width/2));
        box = new BoundingBox(location.getX(), location.getY(), width, width);
        this.player = p;
    }

    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    public void update() {}

    public void setLocation(Point p) {
        this.location = p;
    }

    public void draw(Graphics g) {}

    public boolean isCollected() {
        return collected;
    }



}
