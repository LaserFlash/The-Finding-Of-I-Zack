package TheFindingOfIZack.Entities;
import TheFindingOfIZack.Items.Item;
import javax.xml.stream.Location;
import java.util.List;

/**
 * Created by Ben Allan
 */
public class Enemy extends Entity {

    private int damage;

    public Enemy(int health, Location location, int damage) {
        super(health, location);
        this.health = 0;
        this.damage =damage;
    }

}
