package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 *
 */
public class itemRoom extends Room {
    private  Door northDoor;
    private Door eastDoor;
    private Door southDoor;
    private Door westDoor;



    public itemRoom(){
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;
    }


    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {

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
    public boolean hasDoor(int dir) {
        return false;
    }
}
