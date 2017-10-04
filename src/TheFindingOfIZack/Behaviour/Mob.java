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
        int newX = (int)(player.getX() - location.getX());
        int newY = (int)(player.getY() - location.getY());

        if (newX<0 && newX<-speed){
            newX = -speed;
        }if(newX>0 && newX>speed){
            newX = speed;
        }if (newY<0 && newY<-speed){
            newY = -speed;}
        if(newY>0 && newY>speed){
            newY = speed;}

        //System.out.println("x1: " + newX + " y1: " + newY);//******************************************************************

        if(newX != 0 && newY != 0){
            /**
             *Todo make the mob movement doubles so that these proper methods work well
             *newX = (int)(Math.sqrt((speed^2)/2));
             *System.out.println("New X post maths: " + Math.sqrt((speed^2)/2));//****************************************************
             *newY = (int)(Math.sqrt((speed^2)/2));
             * */
            newX = newX/2;
            newY = newY/2;
        }

        //System.out.println("x2: " + newX + " y2: " + newY);//******************************************************************


        location.setLocation((newX + location.getX()),(newY + location.getY()));

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
