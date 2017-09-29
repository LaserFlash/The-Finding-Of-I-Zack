package TheFindingOfIZack.Entities;

import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile extends Entity implements Drawable{

    private int damage;
    private String direction;

    private int speed = 5;

    public Projectile(int damage, Point location, String direction) {
        super(location);
        this.damage = damage;
        this.direction = direction;
    }





}
