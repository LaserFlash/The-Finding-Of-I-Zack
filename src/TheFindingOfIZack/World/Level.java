package TheFindingOfIZack.World;


import TheFindingOfIZack.World.Rooms.Room;

import java.util.ArrayList;

/**
 * Created by fieldryan on 19/09/17.
 * stores information about the current level such as what rooms consist of this level
 */
public class Level {

    ArrayList<Room> rooms;

    public Level(){
    this.rooms = new ArrayList<Room>();

    }

    public void addRoom(Room r){
    rooms.add(r);
    }

    public void update(){
        System.out.println("updating level");
        for(Room r: rooms){
            r.update();
        }
    }



}
