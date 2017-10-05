package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Player;

import java.awt.*;

public class bossRoom extends Room{



    public bossRoom(){
        this.isCleared = true;
    }

    @Override
    public void populateRoom(Player p) {

    }



    @Override
    public void update() {
        this.isCleared = true;
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
