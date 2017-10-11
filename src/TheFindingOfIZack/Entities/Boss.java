package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.MobShooter;
import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameDimensions.*;
import static TheFindingOfIZack.Util.GameDimensions.RIGHT_WALL;

/**
 * Created by allanbenj1 on 11/10/17.
 */
public class Boss extends Enemy{

    private int size = 120;

    public Boss(Point location, Player p) {
        super(location, p);
    }

    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), size, size);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)location.getX(), (int)location.getY(), size, size);
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY(), size, size);

    }

    @Override
    public void damage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {isDead = true;}
    }

    @Override
    public void move() {
        tick ++;
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
            damagePlayer();
        }
    }

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
