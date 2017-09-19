package TheFindingOfIZack.Entities;
import TheFindingOfIZack.Items.Item;
import javax.xml.stream.Location;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Ben Allan
 */
public class Entity {

    private int health;
    private Location location;

    private List<Item> items;

    public Entity(int health, Location location) {
        this.health = health;
        this.location = location;
        this.items = new ArrayList<Item>();
    }

}
