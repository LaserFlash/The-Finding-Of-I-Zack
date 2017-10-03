package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.MobEnemy;
import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameSize.*;

/**
 * Created by Ben Allan
 */
public class Enemy extends Entity {

    private int damage;
    private MobEnemy behaviour;
    int health;
    private Player player;
    private boolean isDead = false;

    public Enemy(Point location, Player p) {
        super(location);
        this.player = p;
        int type = (int) (Math.random()*5);
        if (type==5) {this.behaviour = new MobEnemy("standard");}
        else if (type==3) {this.behaviour = new MobEnemy("fast");}
        else if (type==2) {this.behaviour = new MobEnemy("shooter");}
        else {this.behaviour = new MobEnemy("slow");}
        this.health = behaviour.getHealth();
    }

    public void damage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {isDead = true;}
    }

    public boolean isDead() {return isDead;}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(location.x, location.y, width, width);
        super.draw(g);
    }

    /**
     * Makes each mob run through its step method
     * Also checks for edge of map collisions
     * Also checks to see if the mob has touched the player
     */
    public void move() {
        Point playerPoint = player.getLocation();
        Point potentialStep = behaviour.step(location, playerPoint);
        //potentialStep represents the move which will take place is there is no obstacle,
        // also checks if mob is currently touching player
        if(canMove(potentialStep) && !collision(location,playerPoint)) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.width, this.width);
            setBox();
        }
        if(collision(location,playerPoint)){
            this.player.damage(this.behaviour.getDamage()); //Takes the damage value from each mob type
        }
    }


    /**
     * The Beginning of Theo's hostile takeover of the Enemy class
     * @param location The point being tested for obstacles
     * @return if the point is clear return true
     */
    private boolean canMove(Point location){
        if(location.getY() < TOP_WALL){return false;}
        if(location.getX() < LEFT_WALL){return false;}
        if(location.getY()+width > BOTTOM_WALL){return false;}
        if(location.getX()+width > RIGHT_WALL){return false;}
        return true;
    }

    /**
     * Determines if the player and any mob have collided
     * @param mob mob location
     * @param player player location
     */
    public boolean collision(Point mob, Point player) {
        double px = player.getX();
        double py = player.getY();
        double mx = mob.getX();
        double my = mob.getY();
        int w = width;

        if(mx<px && my<py && mx+w>px && my+w>py){return true;} //Top left
        if(mx>px && my>py && mx<px+w && my<py+w){return true;} //Bottom Right
        if(mx<px && my>py && mx+w>px && my<py+w){return true;} //Bottom left
        if(mx>px && my<py && mx<px+w && my+w>py){return true;} //Top Right
        return false;
    }

}
