package TheFindingOfIZack.Behaviour;

import java.awt.*;

/**
 * Created by gordontheo on 27/09/17.
 */
public class Mob {
    protected int speed;
    protected int viewRange;
    protected int health;
    protected int damage;

    /**
     * Shifts the mob's location
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player){
        double newX = player.getX() - location.getX();
        double newY = player.getY() - location.getY();

        if (newX<0 && newX<-speed){
            newX = -speed;
        }if(newX>0 && newX>speed){
            newX = speed;
        }if (newY<0 && newY<-speed){
            newY = -speed;}
        if(newY>0 && newY>speed){
            newY = speed;}

        //System.out.println("x1: " + (int)(newX) + " y1: " + (int)(newY));//******************************************************************

        if(newX != 0 && newY != 0){
            newX = newX/2;
            newY = newY/2;
        }



        //System.out.println("x2: " + (int)(newX) + " y2: " + (int)(newY));//******************************************************************


        location.setLocation((int)(newX + location.getX()),(int)(newY + location.getY()));

        return location;
    }

    /**
     * Returns health
     * @return health
     */
    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
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
