package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class standardRoom extends Room{

    private ArrayList<Enemy> enemiesInRoom;

    public standardRoom(){
        super();
        this.enemiesInRoom = new ArrayList<Enemy>();


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


}