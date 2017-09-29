package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 */
public class startRoom extends Room implements Savable{
  private  Door northDoor;
   private Door eastDoor;
    private Door southDoor;
    private Door westDoor;



    public startRoom(){
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;

    }


    public void update(){

    }

    @Override
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

    @Override
    public void draw(Graphics g) {
        if(this.westDoor!= null){
            this.westDoor.draw(g);
        }
    g.setColor(Color.black);
        g.drawRect(GameSize.WALL_WIDTH,GameSize.WALL_WIDTH, GameSize.GAME_WIDTH - (2 * GameSize.WALL_WIDTH), GameSize.GAME_HEIGHT - (2 * GameSize.WALL_WIDTH));

    }
}
