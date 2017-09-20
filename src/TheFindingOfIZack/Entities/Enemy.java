package TheFindingOfIZack.Entities;
import TheFindingOfIZack.Items.Item;
import java.awt.*;
import java.util.List;

/**
 * Created by Ben Allan
 */
public class Enemy extends Entity {

    private int damage;

    public Enemy(int health, Point location, int damage) {
        super(health, location);
        this.health = 0;
        this.damage =damage;
    }

}
