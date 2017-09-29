package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 * Interface for the general definition of a room
 */
public abstract class Room implements Drawable {


    public Door northDoor;
    public Door eastDoor;
    public Door southDoor;
    public Door westDoor;

    protected Room() {
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;
    }

    public void draw(Graphics g){
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
        g.setColor(Color.black);
        g.drawRect(GameSize.WALL_WIDTH,GameSize.WALL_WIDTH, GameSize.GAME_WIDTH - (2 * GameSize.WALL_WIDTH), GameSize.GAME_HEIGHT - (2 * GameSize.WALL_WIDTH));

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
