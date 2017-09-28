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

    public Enemy(Point location, int damage) {
        super(100, location);
        this.damage = damage;
        int type = (int) Math.random()*4;
        if (type>2) {this.behaviour = new MobEnemy("standard"); this.setHealth(behaviour.getHealth());}
        else if (type>1) {this.behaviour = new MobEnemy("fast"); this.setHealth(behaviour.getHealth());}
        else {this.behaviour = new MobEnemy("slow"); this.setHealth(behaviour.getHealth());}
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(location.x, location.y, width, width);
        super.draw(g);
    }

    /**
     * Makes each mob run through its step method
     */
    public void move() {
        Point potentialStep = behaviour.step(location, this.world.getPlayer().getLocation());
        if(canMove(potentialStep)) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.width, this.width);
        }
    }


    /**
     * The Beginning of Theo's hostile takeover of the Enemy class
     * @param location The point being tested for obstacles
     * @return if the point is clear return true
     */
    private boolean canMove(Point location){
        if(location.getY() < TOP_WALL){
            return false;
        }
        if(location.getY() < LEFT_WALL){
            return false;
        }
        if(location.getY()+width > BOTTOM_WALL){
            return false;
        }
        if(location.getY()+width > RIGHT_WALL){
            return false;
        }
        return true;
    }

    public void collisions(Point location) {

    }

}
