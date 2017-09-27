package TheFindingOfIZack.Behaviour;

import java.awt.*;

/**
 * Created by gordontheo on 27/09/17.
 */
public class Mob {
    protected int speed;
    protected int viewRange;
    protected int health;

    /**
     * Shifts the mob's location
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player){
        double newX = location.getX();
        double newY = location.getY();

        if (distanceBetween(location,player) < viewRange){
            if(player.getX() > location.getX()){
                newX += speed;
            }
            if(player.getY() > location.getY()){
                newY += speed;
            }
            if(player.getX() < location.getX()){
                newX -= speed;
            }
            if(player.getY() < location.getY()){
                newY -= speed;
            }
        }

        location.move((int)newX,(int)newY);

        return location;
    }

    /**
     * Returns health
     * @return health
     */
    public int getHealth(){
        return health;
    }

    /**
     * To be used for different mob fields of view
     * @param a first Point
     * @param b second Point
     * @return distance between the two inputs
     */
    public double distanceBetween(Point a, Point b){
        double distance = Math.hypot(a.getX()-b.getX(), a.getY()-b.getY());
        return distance;
    }
}
