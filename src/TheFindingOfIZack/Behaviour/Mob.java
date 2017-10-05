package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Point;

/**
 * Created by gordontheo on 27/09/17.
 */
public abstract class Mob {
    protected double speed;
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

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        double newX = changeX/a;
        double newY = changeY/a;

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

    public double getSpeed() { return speed; }

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
