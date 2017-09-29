package TheFindingOfIZack.Entities;

import java.awt.*;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile extends Entity{

    private int damage;
    private String direction;

    private int speed = 5;

    public Projectile(int damage, Point location, String direction) {
        super(location);
        this.damage = damage;
        this.direction = direction;
    }



}
