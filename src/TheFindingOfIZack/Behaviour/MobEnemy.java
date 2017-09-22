package TheFindingOfIZack.Behaviour;


import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.World.Game;
import java.awt.*;


/**
 * Created by gordontheo on 19/09/17.
 */
public class MobEnemy {
    private Point location;
    private Player player;

    public MobEnemy(Point location){
        this.location = location;
        this.player = World.getPlayer;
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
    private void step(){
    }

    /**
     * To be used for different mob fields of view
     * @param first Point
     * @param second Point
     * @return distance between the two inputs
     */
    private double distanceBetween(Point a, Point b){
        double distance = Math.hypot(a.getX()-b.getX(), a.getY()-b.getY());
        return distance;
    }
}
