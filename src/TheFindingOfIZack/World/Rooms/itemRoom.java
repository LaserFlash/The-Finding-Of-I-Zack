package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 *
 */
public class itemRoom extends Room {




    public itemRoom(){

    }

    @Override
    public void populateRoom(Player p) {

    }




    @Override
    public void update() {
        if(this.northDoor != null){
            this.northDoor.isLocked = false;
        }
        if(this.eastDoor != null){
            this.eastDoor.isLocked = false;
        }
        if(this.southDoor != null){
            this.southDoor.isLocked = false;
        }
        if(this.westDoor != null){
            this.westDoor.isLocked = false;
        }
    }




}
