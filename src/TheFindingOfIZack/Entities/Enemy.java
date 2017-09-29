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

    public Enemy(Point location, int damage) {
        super(100, location);
        this.damage = damage;
        int type = (int) Math.random()*5;
        if (type>3) {this.behaviour = new MobEnemy("standard"); this.setHealth(behaviour.getHealth());}
        else if (type>2) {this.behaviour = new MobEnemy("fast"); this.setHealth(behaviour.getHealth());}
        else if (type>1) {this.behaviour = new MobEnemy("fast"); this.setHealth(behaviour.getHealth());}
        else {this.behaviour = new MobEnemy("slow"); this.setHealth(behaviour.getHealth());}
    }

    public void damage(int damage) {
        this.health -= damage;
    }

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
        Point player = this.world.getPlayer().getLocation();
        Point potentialStep = behaviour.step(location, player);
        //potentialStep represents the move which will take place is there is no obstacle,
        // also checks if mob is currently touching player
        if(canMove(potentialStep) && !collision(location,player)) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.width, this.width);
        }
        if(collision(location,player)){
            this.world.getPlayer().damage(this.damage); //Not sure what this.damage actually is,
                                                        // hopefully it hurts the player
                                                        // (assuming mobs are given a damage value at some point)
        }
    }


    /**
     * The Beginning of Theo's hostile takeover of the Enemy class
     * @param location The point being tested for obstacles
     * @return if the point is clear return true
     */
    private boolean canMove(Point location){
        if(location.getY() < TOP_WALL){return false;}
        if(location.getY() < LEFT_WALL){return false;}
        if(location.getY()+width > BOTTOM_WALL){return false;}
        if(location.getY()+width > RIGHT_WALL){return false;}
        return true;
    }

    /**
     * Determines if the player and any mob have collided
     * @param mob mob location
     * @param player player location
     */
    public boolean collision(Point mob, Point player) {
        if(mob.getY() < player.getX()+width){return true;}
        if(mob.getY() < player.getY()+width){return true;}
        if(mob.getY()+width > player.getX()){return true;}
        if(mob.getY()+width > player.getY()){return true;}
        return false;
    }

}
