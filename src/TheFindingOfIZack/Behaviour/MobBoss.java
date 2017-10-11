package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Point;

/**
 * Created by gordontheo on 11/10/17.
 * Boss Mob
 */
public class MobBoss extends Mob{
    MobBoss(){
        this.viewRange = 50;
        this.speed = 3;
        this.health = 100;
        this.damage = 20;
    }

    public Point step(Point location, Point player){
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        double newX = changeX/a;
        double newY = changeY/a;

        location.setLocation((newX + location.getX()),(newY + location.getY()));

        return location;
    }

    @Override
    public String toString() {
        String string = "The Boss mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
}
