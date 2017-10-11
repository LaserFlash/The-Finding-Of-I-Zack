package TheFindingOfIZack.Entities;

import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameDimensions.*;

/**
 * Created by allanbenj1 on 11/10/17.
 */
public class Boss extends Enemy {

    /**
     * Stores the field for the size of the Boss
     */
    private int size = 120;

    /**
     * Constructor for the boss
     * @param location  the location of the boss
     * @param p the player associated with the boss
     */
    public Boss(Point location, Player p) {
        super(location, p);
    }

    /**
     * Sets the bounding box for the boss
     */
    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), size, size);
    }

    /**
     * draws the boss
     * @param g the graphics object used to draw the boss
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)location.getX(), (int)location.getY(), size, size);
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY(), size, size);

        double healthBar = ((double) health/(double) MAX_HEALTH) * (double) size;
        if (healthBar < 0) {healthBar = 0;}

        g.setColor(Color.green);
        g.fillRect((int) location.getX(), (int) location.getY() - 8, (int) healthBar, 4);
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY() - 8, size, 4);

    }

    /**
     * Damages the boss
     * @param damage    the amount of damage the boss receives
     */
    @Override
    public void damage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {isDead = true;}
    }

    /**
     * Moves the boss.
     * This acts in a similar way to enemy however it has minor differences
     */
    @Override
    public void move() {
        tick ++;
        Point playerPoint = player.getLocation();
        Point potentialStep = this.behaviour.step(location, playerPoint, r);
        //potentialStep represents the move which will take place if there are no obstacle,
        // also checks if mob is currently touching player
        if(!collision(location,playerPoint)) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.size, this.size);
            setBox();
        }
        canMove();
        if(collision(location,playerPoint)){
            damagePlayer();
        }
    }

    /**
     * Checks whether the boss can move or not
     * Also checks for wall collisions and fixes them
     */
    @Override
    protected void canMove(){
        double x = location.getX();
        double y = location.getY();
        boolean top = false;
        boolean bottom = false;
        boolean left = false;
        boolean right = false;
        if(y < TOP_WALL){top = true;}
        if(x < LEFT_WALL){left = true;}
        if(y+size > BOTTOM_WALL){bottom = true;}
        if(x+size > RIGHT_WALL){right = true;}
        if(top){y = TOP_WALL;}
        if(bottom){y = BOTTOM_WALL-size;}
        if(left){x = LEFT_WALL;}
        if(right){x = RIGHT_WALL-size;}
        location.move(x,y);
    }

}
