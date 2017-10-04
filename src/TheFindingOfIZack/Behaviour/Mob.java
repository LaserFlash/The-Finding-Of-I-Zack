package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Point;

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
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());
        double newX = 0;
        double newY = 0;
        int Xneg = 1;
        int Yneg = 1;

        if (changeX<0 && changeX<-speed){
            newX = -speed;
            Xneg = -1;
        }if(changeX>0 && changeX>speed){
            newX = speed;
        }if (changeY<0 && changeY<-speed){
            newY = -speed;
            Yneg = -1;
        }if(changeY>0 && changeY>speed){
            newY = speed;}

        if(newX < -0.5 && newX > 0.5 && newY < -0.5 && newY > 0.5){
            newX = (Math.sqrt((speed^2)/2))*Xneg;
            newY = (Math.sqrt((speed^2)/2))*Yneg;
        }

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

    public int getSpeed() { return speed; }

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
