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
        double x = player.getX() - location.getX();
        double y = player.getY() - location.getY();


        //Todo Make the move a double so that the mobs will move in a straight line
        //The code bellow will do this (it's all jittery at the moment)
        //double h = Math.hypot(x,y);
        /**double n = h/speed;
        double newX = x/n;
        double newY = y/n;*/

        //**********Temp**************
        double newX = x/speed;
        double newY = y/speed;
        //**********Temp**************

        if (newX<0 && newX<-speed){
            newX = -speed;
        } else if(newX>0 && newX>speed){
            newX = speed;
        }else if (newY<0 && newY<-speed){
            newY = -speed;}
        else if(newY>0 && newY>speed){
            newY = speed;}

        //System.out.println("x: " + (int)(newX+location.getX()) + " y: " + (int)(newY+location.getY()));//******************************************************************


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
