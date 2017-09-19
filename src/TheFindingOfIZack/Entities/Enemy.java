package TheFindingOfIZack.Entities;
import TheFindingOfIZack.Entities.Items.Item;
import javax.xml.stream.Location;
import java.util.List;

/**
 * Created by Ben Allan
 */
public class Enemy extends Entity{

    private int health;
    private int damage;
    private Location location;

    private List<Item> items;

    public Enemy(int health, Location location, int damage) {
        super(health, location);
        this.damage =damage;
    }

}
