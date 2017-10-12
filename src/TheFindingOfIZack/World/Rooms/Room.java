package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fieldryan on 19/09/17.
 * Interface for the general definition of a room
 */
public abstract class Room implements Drawable {

    private static Image roomImage;
    protected List<Item> collectibles;
    public transient Door northDoor;
    public transient Door eastDoor;
    public transient Door southDoor;
    public transient Door westDoor;
    public boolean isCleared;
    private Player player;

    public Room() {
        this.collectibles = Collections.synchronizedList(new ArrayList<Item>());
        this.player = null;
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;
        this.roomImage = ImageLoader.loadImage("/room.png").getScaledInstance(GameDimensions.GAME_WIDTH, GameDimensions.GAME_HEIGHT,Image.SCALE_DEFAULT);
    }
    public List<Item> getCollectibles(){
        return this.collectibles;
    }

    /**
     * adds a player to the room
     * @param p player to add to the room
     */
    public void addPlayer(Player p){
        this.player = p;
    }


    /**
     * gets the player that is currently in the room
     * @return the current player in the room
     */
    public Player getPlayer(){
        return this.player;
    }


    /**
     * removes the player from the room, should be used when the player exits a room
     */
    public void removePlayer(){
        this.player = null;
    }


    /**
     * draws the room and everything within it
     * @param g graphics object to draw on
     */
    public void draw(Graphics g){


        g.drawImage(roomImage,0,0,null);
        if(this.westDoor!= null){
            this.westDoor.draw(g);
        }
        if(this.southDoor != null){
            this.southDoor.draw(g);
        }
        if(this.northDoor != null){
            this.northDoor.draw(g);
        }
        if(this.eastDoor != null){
            this.eastDoor.draw(g);
        }

        synchronized (this.collectibles) {
            for (Item i : this.collectibles) {
                i.draw(g);
            }
        }

    }

    public Door getNorthDoor(){
        return this.northDoor;
    }
    public Door getSouthDoor(){
        return this.southDoor;
    }
    public Door getEastDoor(){
        return this.eastDoor;
    }
    public Door getWestDoor(){
        return this.westDoor;
    }

    public void setNorthDoor(Door d){
        this.northDoor = d;
    }
    public void setSouthDoor(Door d){
        this.southDoor = d;
    }
    public void setEastDoor(Door d){this.eastDoor = d;}
    public void setWestDoor(Door d){ this.westDoor = d;}

    public abstract void populateRoom(Player p);
    public abstract void update();

    public void addDoor(Door d, int n) {

        if(this.northDoor == null && n == 0){
            this.northDoor = d;
        }
        if(this.eastDoor == null && n == 1){
            this.eastDoor = d;
        }
        if(this.southDoor == null && n == 2){
            this.southDoor = d;
        }
        if(this.westDoor == null && n == 3){
            this.westDoor = d;
        }
    }

    /**
     * checks if there is a door in the desired directions
     * @param dir the desired direction with 0 being north and increasing clockwise
     * @return true if there is a door in that direction or false if not
     */
    public boolean hasDoor(int dir) {
        //door south
        if(dir == 2){
            if(this.southDoor != null){
                return true;
            }
        }
        //door north
        if(dir == 0){
            if(this.northDoor != null){
                return true;
            }
        }
        //door east
        if(dir == 1){
            if(this.eastDoor != null){
                return true;
            }
        }
        //door west
        if(dir == 3){
            if(this.westDoor != null){
                return true;
            }
        }
        return false;
    }

}
