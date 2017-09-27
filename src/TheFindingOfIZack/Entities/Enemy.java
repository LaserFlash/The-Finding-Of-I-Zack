package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.World.Rooms.Room;
import javafx.geometry.BoundingBox;

import java.awt.*;

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

    public void move() {
        //Point p = behaviour.step(location, this.world.getPlayer().getLocation());
        //this.location = p;
        //this.box = new BoundingBox(p.getX(), p.getY(), this.width, this.width);
    }

    public void collisions(Room room) {

    }

}
