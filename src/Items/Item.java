package Items;

import View.Drawable;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public abstract class Item implements Drawable{

    protected Point location;
    private String type;

    public Item(String type) {
        this.type = type;
    }

    public void setLocation(Point p) {
        this.location = p;
    }



}
