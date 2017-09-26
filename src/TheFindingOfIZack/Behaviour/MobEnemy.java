package Behaviour;


import java.awt.*;


/**
 * Created by gordontheo on 19/09/17.
 * The MobEnemy class acts as the 'interface' for mobs,
 * all other enemies extend this and it houses the fundamental methods
 */
public class MobEnemy {
    private String type;

    public MobEnemy(String type){
        this.type = type;
    }

    /**
     * Shifts the mob's location
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player){
        double dtp = (distanceBetween(location,player));

        return null;
    }

    /**
     * To be used for different mob fields of view
     * @param a first Point
     * @param b second Point
     * @return distance between the two inputs
     */
    protected double distanceBetween(Point a, Point b){
        double distance = Math.hypot(a.getX()-b.getX(), a.getY()-b.getY());
        return distance;
    }
}
