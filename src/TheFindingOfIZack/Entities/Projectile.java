package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;

import java.awt.*;
import java.util.List;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Projectile extends Entity implements Drawable{

    /**
     * Fields for damage, direction, speed, and if it is popped
     */

    private int damage;
    private String direction;

    protected boolean pop = false;

    private int speed = 10;

    protected Image projectileImage;

    /**
     * Constructor for Projectile
     * @param damage    the damage the projectile does
     * @param location  the location the projectile is
     * @param direction the direction the projectile is travelling
     */
    public Projectile(int damage, Point location, String direction) {
        super(location);
        this.damage = damage;
        this.direction = direction;
        this.projectileImage = ImageLoader.loadImage("/zacksAttacks.png").getScaledInstance(Entity.width/2,Entity.width/2,Image.SCALE_DEFAULT);
    }

    /**
     * Alternative constructor for MobProjectile
     * @param location  location of the projectile
     * @param player    the player that the projectile is aiming at
     */
    public Projectile(Point location, Point player){}

    /**
     * Draws the projectile
     * @param g graphics object to draw with
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(projectileImage, (int) location.getX() + width/4, (int) location.getY() + width/4, null);
    }

    /**
     * Moves the projectile along its current direction
     * If the projectile collides with a wall, pop is set to true
     */
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

    /**
     * Sets the bounding box of the projectile to its new location
     */
    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX()+width/4, location.getY()+width/4, width/2, width/2);
    }

    /**
     * Checks whether the projectile has hit a wall
     * @return  true if the projectile has hit a wall
     */
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

    /**
     * Checks whether the projectile has hit an enemy
     * If so, the enemy is damaged and pop is set to true
     * @param enemies   the list of enemies in the room
     */
    public void enemyCollision(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (e.box.intersects(location.getX()+width/4, location.getY()+width/4, width/2, width/2)) {e.damage(damage); pop = true;}
        }
    }

    /**
     * Checks whether the projectile has hit an obstacle
     * If so, the obstacle is damaged and pop is set to true
     * @param entities  the list of obstacles in the room
     */
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

    /**
     * Returns whether the projectile is popped or not
     * @return  pop
     */
    public boolean getPopped() {
        return this.pop;
    }

}
