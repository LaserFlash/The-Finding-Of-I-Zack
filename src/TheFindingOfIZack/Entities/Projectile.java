package TheFindingOfIZack.Entities;

import java.awt.*;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile {

    private int damage;
    private Point location;
    private String direction;

    private int speed = 5;

    public Projectile(int damage, Point location, String direction) {
        this.damage = damage;
        this.location = location;
        this.direction = direction;
    }



}
