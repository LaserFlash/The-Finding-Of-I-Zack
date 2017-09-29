package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 */
public class startRoom extends Room implements Savable{




    public startRoom(){
        super();

    }

    @Override
    public void populateRoom() {

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





}
