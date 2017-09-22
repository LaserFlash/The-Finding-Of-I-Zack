package TheFindingOfIZack.Behaviour;


import TheFindingOfIZack.Entities.Player;

import java.awt.*;


/**
 * Created by gordontheo on 19/09/17.
 * The MobEnemy class acts as the 'interface' for mobs,
 * all other enemies extend this and it houses the fundamental methods
 */
public class MobEnemy {
    private Point location;
    private Player player;

    public MobEnemy(Point location, Player player){
        this.location = location;
        this.player = player;
    }

    /**
     * Shifts the mob's location
     * @param newX change in x
     * @param newY change in y
     * @return new location
     */
    public Point move(double newX, double newY){return location;}

    /**
     * Overridden in each different mob, is what changes movement behaviour/triggers attack behaviour
     */
    private void step(){}

    /**
     * To be used for different mob fields of view
     * @param a first Point
     * @param b second Point
     * @return distance between the two inputs
     */
    private double distanceBetween(Point a, Point b){
        double distance = Math.hypot(a.getX()-b.getX(), a.getY()-b.getY());
        return distance;
    }
}
