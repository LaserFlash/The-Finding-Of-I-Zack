package TheFindingOfIZack.Entities;

import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile extends Entity implements Drawable{

    private int damage;
    private String direction;

    private int speed = 1;

    public Projectile(int damage, Point location, String direction) {
        super(location);
        this.damage = damage;
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval((int) location.getX()+width/4, (int) location.getY() + width/4, width/2, width/2);

        g.setColor(Color.black);
        g.drawOval((int) location.getX()+width/4, (int) location.getY() + width/4, width/2, width/2);
    }

    public void move() {
        int x, y;
        if (direction.equals("up") || direction.equals("down")) {
            x = (int) location.getX();
            if (direction.equals("up")) {y = (int) location.getY()-speed;}
            else {y = (int) location.getY()+speed;}
        }
        else {
            y = (int) location.getY();
            if (direction.equals("left")) {x = (int) location.getX()-speed;}
            else {x = (int) location.getX()+speed;}
        }

        location.move(x, y);

    }



}
