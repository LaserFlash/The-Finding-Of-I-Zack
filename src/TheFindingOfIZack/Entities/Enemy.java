package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.Mob;
import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.Behaviour.MobShooter;
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
        //int type = 1;
        //System.out.println("Type = " + type);//*****************************************************************
        if (type>2) {this.behaviour = new MobEnemy("standard");}
        else if (type==2) {this.behaviour = new MobEnemy("fast");}
        else if (type==1) {this.behaviour = new MobEnemy("shooter");}
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
        g.fillRect((int)location.getX(), (int)location.getY(), width, width);
        super.draw(g);
    }

    /**
     * Makes each mob run through its step method
     * Also checks for edge of map collisions
     * Also checks to see if the mob has touched the player
     */
    public void move() {
        Point playerPoint = player.getLocation();
        Point potentialStep = this.behaviour.step(location, playerPoint);
        //potentialStep represents the move which will take place if there are no obstacle,
        // also checks if mob is currently touching player
        if(!collision(location,playerPoint)) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.width, this.width);
            setBox();
        }
        canMove();
        if(collision(location,playerPoint)){
            this.player.damage(this.behaviour.getDamage()); //Takes the damage value from each mob type
        }

        if (behaviour.getMob() instanceof MobShooter) {
            Mob m = (MobShooter) behaviour.getMob();
        }
    }


    /**
     * The Beginning of Theo's hostile takeover of the Enemy class
     */
    private void canMove(){
        double x = location.getX();
        double y = location.getY();
        boolean top = false;
        boolean bottom = false;
        boolean left = false;
        boolean right = false;
        if(y < TOP_WALL){top = true;}
        if(x < LEFT_WALL){left = true;}
        if(y+width > BOTTOM_WALL){bottom = true;}
        if(x+width > RIGHT_WALL){right = true;}
        if(top){y = TOP_WALL;}
        if(bottom){y = BOTTOM_WALL-width;}
        if(left){x = LEFT_WALL;}
        if(right){x = RIGHT_WALL-width;}
        location.move(x,y);
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
