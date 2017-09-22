package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.World.Rooms.Room;

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





}