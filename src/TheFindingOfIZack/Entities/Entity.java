package TheFindingOfIZack.Entities;
import TheFindingOfIZack.Items.Item;

import java.util.ArrayList;

/**
 * Created by Ben Allan
 */
public class Entity {

    protected int health;
    protected Point location;

    protected ArrayList<Item> items;

    public Entity(int health, Point location) {
        this.health = health;
        this.location = location;
        this.items = new ArrayList<Item>();
    }

}
