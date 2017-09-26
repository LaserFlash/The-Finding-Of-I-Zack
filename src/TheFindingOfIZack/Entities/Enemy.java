package Entities;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.World.Rooms.Room;
import javafx.geometry.BoundingBox;

import java.awt.*;
import java.util.List;

/**
 * Created by Ben Allan
 */
public class Enemy extends Entity {

    private int damage;
    private MobEnemy behaviour;

    public Enemy(int health, Point location, int damage) {
        super(health, location);
        this.health = 0;
        this.damage =damage;
        int type = (int) Math.random()*4;
        if (type>2) {this.behaviour = new MobEnemy("standard");}
        else if (type>1) {this.behaviour = new MobEnemy("fast");}
        else {this.behaviour = new MobEnemy("slow");}
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(location.x, location.y, width, width);
        super.draw(g);
    }

    public void move() {
        Point p = behaviour.step(location, this.world.getPlayer().getLocation());
        this.location = p;
        this.box = new BoundingBox(p.getX(), p.getY(), this.width, this.width);
    }

    public void collisions(Room room) {

    }

}
