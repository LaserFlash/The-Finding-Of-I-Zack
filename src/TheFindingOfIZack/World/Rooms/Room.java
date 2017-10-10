package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by fieldryan on 19/09/17.
 * Interface for the general definition of a room
 */
public abstract class Room implements Drawable {

    private static Image roomImage;
    protected ArrayList<Item> collectables;
    public transient Door northDoor;
    public transient Door eastDoor;
    public transient Door southDoor;
    public transient Door westDoor;
    public boolean isCleared;
    private Player player;

    public Room() {
        this.collectables = new ArrayList<Item>();
        this.player = null;
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;
        this.roomImage = ImageLoader.loadImage("/room.png").getScaledInstance(GameDimensions.GAME_WIDTH, GameDimensions.GAME_HEIGHT,Image.SCALE_DEFAULT);
    }
    public ArrayList<Item> getCollectables(){
        return this.collectables;
    }

    public void addPlayer(Player p){
        this.player = p;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void removePlayer(){
        this.player = null;
    }
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

        for(Item i : this.collectables){
            i.draw(g);
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

    public boolean hasDoor(int dir) {
        if(dir == 2){
            if(this.southDoor != null){
                return true;
            }
        }
        if(dir == 0){
            if(this.northDoor != null){
                return true;
            }
        }
        if(dir == 1){
            if(this.eastDoor != null){
                return true;
            }
        }
        if(dir == 3){
            if(this.westDoor != null){
                return true;
            }
        }
        return false;
    }

}
