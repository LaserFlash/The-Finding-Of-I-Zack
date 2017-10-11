package TheFindingOfIZack.Entities;

import javafx.geometry.BoundingBox;

/**
 * Created by allanbenj1 on 11/10/17.
 */
public class Boss extends Enemy{

    private int size = 120;

    public Boss(Point location, Player p) {
        super(location, p);
    }

    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), size, size);
    }

}
