package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by fieldryan on 27/09/17.
 */
public class Door implements Drawable {

    private Room entry;
    private Room destination;
    int position;
    int width = 10;
    int height = 50;



    public Door(Room entry, Room destination, int position){
        this.entry = entry;
        this.destination = destination;
        this.position = position;
    }

    public Door getOpposite(){
        Door d = null;
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
        g.setColor(Color.green);
        if(this.position == 3){
            g.fillRect(0,GameSize.GAME_HEIGHT/2 - this.height,GameSize.WALL_WIDTH,height);
        }


    }
}
