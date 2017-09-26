package TheFindingOfIZack.Items;

import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by allanbenj1 on 26/09/17.
 */
public abstract class Item implements Drawable{

    protected Point location;
    private String type;

    public Item(String type) {
        this.type = type;
    }

}
