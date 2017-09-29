package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class standardRoom extends Room{
    private  Door northDoor;
    private Door eastDoor;
    private Door southDoor;
    private Door westDoor;
    private ArrayList<Enemy> enemiesInRoom;

    public standardRoom(){
        this.enemiesInRoom = new ArrayList<Enemy>();
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;

    }


public void draw(Graphics g){

        for(Enemy e: enemiesInRoom){
        //    e.draw(g);
        }

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