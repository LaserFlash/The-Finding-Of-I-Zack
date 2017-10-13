package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Entity;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Entities.Rock;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.standardRoom;

import java.awt.*;
import java.util.List;

import static TheFindingOfIZack.Entities.Entity.width;

/**
 * Created by gordontheo on 27/09/17.
 */
public abstract class Mob implements Savable {
    protected double speed;
    protected int viewRange;
    protected int health;
    protected int damage;
    protected int bounce = 0;
    private Point tempDirection = null;
    private Room r;

    public transient static Image image;

    /**
     * Shifts the mob's location
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player, Room room){
        bounce ++;
        this.r = room;

        if(tempDirection != null){
            player = tempDirection;
        }

        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        double newX = changeX/a;
        double newY = changeY/a;

        entityCollision(location,newX,newY);
        location.setLocation((newX + location.getX()),(newY + location.getY()));

        return location;
    }

    /**
     * Tests if mob has collided with a rock and moves away to a random point in an attempt to move around the obstacle
     * @param location  mob's current location
     * @param newX  the projected horizontal speed
     * @param newY the projected vertical speed
     */
    private void entityCollision(Point location, double newX, double newY) {
        if (r instanceof standardRoom) {
            standardRoom room = (standardRoom) r;
            List<Entity> entities = (room.getItems());
            for (Entity entity : entities) {
                if (entity instanceof Rock) {
                    Rock r = (Rock) entity;

                    double rx = r.getBoundingBox().getMinX();
                    double ry = r.getBoundingBox().getMinY();
                    double mx = location.getX()+newX;
                    double my = location.getY()+newY;
                    int w = width;

                    if(mx<rx && my<ry && mx+w>rx && my+w>ry){   //Bottom right
                        tempDirection =  new Point(location.getX()-tempDistance(),location.getY()-tempDistance());
                    }
                    if(mx>rx && my>ry && mx<rx+w && my<ry+w){    //Top left
                        tempDirection = new Point(location.getX()+tempDistance(),location.getY()+tempDistance());
                    }
                    if(mx<rx && my>ry && mx+w>rx && my<ry+w){   //Top Right
                        tempDirection = new Point(location.getX()-tempDistance(),location.getY()+tempDistance());
                    }
                    if(mx>rx && my<ry && mx<rx+w && my+w>ry){   //Bottom left
                        tempDirection = new Point(location.getX()+tempDistance(),location.getY()-tempDistance());
                    }
                    if(tempDirection != null && bounce > 20){
                        tempDirection = null;
                        bounce = 0;
                    }
                }
            }
        }
    }

    /**
     * Method for creating a random number used for moving round obstacles
     * @return a random double between 100 and 500
     */
    private double tempDistance(){
        return (Math.random()*500+100);
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
    protected double distanceBetween(Point a, Point b){
        double distance = Math.hypot(a.getX()-b.getX(), a.getY()-b.getY());
        return distance;
    }
}
