package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.MobProjectile;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;

import java.awt.*;
import java.util.List;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile extends Entity implements Drawable{

    private int damage;
    private String direction;

    protected boolean pop = false;

    private int speed = 10;

    public Projectile(int damage, Point location, String direction) {
        super(location);
        this.damage = damage;
        this.direction = direction;
    }

    public Projectile(Point location, Point player){}

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

    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX()+width/4, location.getY()+width/4, width/2, width/2);
    }

    public boolean wallCollision() {

        if (location.getX()+width/4 < GameDimensions.LEFT_WALL) {
            return true;
        }
        else if (location.getY()+width/4 < GameDimensions.TOP_WALL) {
            return true;
        }
        else if (location.getX() + (3*width)/4 > GameDimensions.RIGHT_WALL) {
            return true;
        }
        else if (location.getY() + (3*width)/4 > GameDimensions.BOTTOM_WALL) {
            return true;
        }

        return false;
    }

    public void enemyCollision(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (e.box.intersects(location.getX()+width/4, location.getY()+width/4, width/2, width/2)) {e.damage(damage); pop = true;}
        }
    }

    public void entityCollision(List<Entity> entities) {
        for (Entity entity : entities) {

            if (entity instanceof Rock) {
                Rock r = (Rock) entity;
                if (r.box.intersects(location.getX()+width/4, location.getY()+width/4, width/2, width/2)) {r.damage(damage); pop = true;}
            }
            else if (entity instanceof Urn) {
                Urn u = (Urn) entity;
                if (u.box.intersects(location.getX()+width/4, location.getY()+width/4, width/2, width/2)) {u.damage(damage); pop = true;}
            }

        }
    }

    public boolean getPopped() {
        return this.pop;
    }

}
