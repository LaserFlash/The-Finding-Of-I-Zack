package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class standardRoom extends Room{

    private ArrayList<Enemy> enemiesInRoom;

    public standardRoom(){
        this.enemiesInRoom = new ArrayList<Enemy>();

    }


public void draw(Graphics g){

        for(Enemy e: enemiesInRoom){
        //    e.draw(g);
        }

}


    @Override
    public void update() {

    }
}