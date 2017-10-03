package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile extends Entity implements Drawable{

    private int damage;
    private String direction;

    private boolean pop = false;

    private int speed = 5;

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
        setBox();

        if (wallCollision()) {pop = true;}

    }

    public boolean wallCollision() {

        if (location.getX()+width/4 < GameSize.LEFT_WALL) {
            return true;
        }
        else if (location.getY()+width/4 < GameSize.TOP_WALL) {
            return true;
        }
        else if (location.getX() + (3*width)/4 > GameSize.RIGHT_WALL) {
            return true;
        }
        else if (location.getY() + (3*width)/4 > GameSize.BOTTOM_WALL) {
            return true;
        }

        return false;
    }

    public void enemyCollision(ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (e.box.intersects(location.getX()+width/4, location.getY()+width/4, width/2, width/2)) {e.damage(damage); pop = true;}
        }
    }

    public void rockCollision(ArrayList<Rock> rocks) {

    }

    public boolean getPopped() {
        return this.pop;
    }

}
