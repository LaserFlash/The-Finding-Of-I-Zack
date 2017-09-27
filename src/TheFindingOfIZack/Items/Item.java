package TheFindingOfIZack.Items;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;
import TheFindingOfIZack.Util.GameSize;


import java.awt.*;

/**
 * Created by allanbenj1 on 26/09/17.
 */
public abstract class Item implements Drawable{

    protected Point location;
    private String type;
    private BoundingBox box;
    private int width = 20;

    public Item(String type) {
        this.type = type;
        location = new Point((GameSize.WINDOW_WIDTH/2)-(width/2), (GameSize.GAME_HEIGHT/2)-(width/2));
        box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

}
