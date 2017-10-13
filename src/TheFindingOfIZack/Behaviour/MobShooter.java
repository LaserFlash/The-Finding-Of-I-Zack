package TheFindingOfIZack.Behaviour;


import TheFindingOfIZack.Entities.Entity;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobShooter extends Mob implements Savable{
    private final int PROJECTILE_TICK = 30;
    private int tick = (int)(Math.random()*PROJECTILE_TICK);
    private int stopDistance = 200;
    private List<MobProjectile> projectiles = Collections.synchronizedList(new ArrayList<MobProjectile>());
    private Room room;

    static {
        image = ImageLoader.loadImage("/weeOctoBoi.png").getScaledInstance(Entity.width,Entity.width, Image.SCALE_DEFAULT);
    }

    public MobShooter(){
        this.viewRange = 600;
        this.speed = 3;
        this.health = 50;
        this.damage = 5;
    }

    @Override
    /**
     * Shifts the mob's location but stops before getting to player and shoots
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player, Room r){
        this.room = r;
        popProjectiles();
        tick++;
        projectile(location,player);
        double range = distanceBetween(location,player);
        if (range < viewRange && range > stopDistance+60 || range < stopDistance) {
            double changeX = (player.getX() - location.getX());
            double changeY = (player.getY() - location.getY());

            double h = Math.hypot(changeX, changeY);
            double a = h / speed;
            double newX = changeX / a;
            double newY = changeY / a;

            if(range < stopDistance){
                newX = -newX;
                newY = -newY;
            }

            location.setLocation((newX + location.getX()), (newY + location.getY()));
            }
        return location;
    }

    @Override
    public String toString() {
        String string = "A shooting mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }

    public  List<MobProjectile> getProjectile(){
        return projectiles;
    }

    public void popProjectiles() {
        synchronized (projectiles) {
            ArrayList<MobProjectile> temp = new ArrayList<>();
            for (MobProjectile p : projectiles) {
                if (p.getPopped()) {
                    temp.add(p);
                }
            }

            for (MobProjectile p : temp) {
                projectiles.remove(p);
            }
        }
    }

    private void projectile(Point location, Point player){
        if(tick > PROJECTILE_TICK){
            projectiles.add(new MobProjectile(location, player, room));
            tick = 0;
        }
    }
}
