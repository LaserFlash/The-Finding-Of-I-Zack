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
        g.setColor(Color.black);
        g.drawRect(GameSize.WALL_WIDTH,GameSize.WALL_WIDTH, GameSize.GAME_WIDTH - (2 * GameSize.WALL_WIDTH), GameSize.GAME_HEIGHT - (2 * GameSize.WALL_WIDTH));

    }

    public Door getNorthDoor(){
        return this.northDoor;
    }
    public Door getSouthDoor(){
        return this.southDoor;
    }

    public abstract void update();
    public abstract void addDoor(Door d, int n);
    public abstract boolean hasDoor(int dir);

}
