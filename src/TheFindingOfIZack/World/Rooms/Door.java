package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by fieldryan on 27/09/17.
 */
public class Door implements Drawable, Savable {

    private Room entry;
    private Room destination;
    int position;
    public static final int height = 50;
    public boolean isLocked;



    public Door(Room entry, Room destination, int position){
        this.entry = entry;
        this.destination = destination;
        this.position = position;
        this.isLocked = true;
    }

    public Room getDestination(){
        return this.destination;
    }

    public Door getOpposite(){
        Door d;
        int w = 0;
        if(this.position == 0){
            w = 2;
        }else if(this.position == 1){
            w = 3;
        }else if(this.position == 2){
            w = 0;
        }else if(this.position == 3){
            w = 1;
        }
    d = new Door(this.destination,this.entry,w);


        return d;
    }

    public int getPosition(){
        return this.position;
    }

    @Override
    public void draw(Graphics g) {
        if(this.isLocked){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.green);
        }
        if(this.position == 3){
            g.fillRect(0,GameSize.GAME_HEIGHT/2 - this.height/2,GameSize.WALL_WIDTH,height);
        }
        if(this.position == 2){

            g.fillRect(GameSize.GAME_WIDTH/2 - height/2,GameSize.GAME_HEIGHT - GameSize.WALL_WIDTH, height,GameSize.WALL_WIDTH);
        }
        if(this.position == 0){

            g.fillRect(GameSize.GAME_WIDTH/2 - height/2,0, height,GameSize.WALL_WIDTH);
        }
        if(this.position == 1){
            g.fillRect(GameSize.GAME_WIDTH - GameSize.WALL_WIDTH,GameSize.GAME_HEIGHT/2 - this.height/2,GameSize.WALL_WIDTH,height);
        }


    }
}
