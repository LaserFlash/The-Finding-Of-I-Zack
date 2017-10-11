package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Items.Key;

/**
 * Created by fieldryan on 19/09/17.
 *
 */
public class itemRoom extends Room {


    private boolean populated;

    public itemRoom(){
        this.populated = false;
    }

    @Override
    public void populateRoom(Player p) {
        if(populated){
            return;
        }

        this.getCollectibles().add(new Key(p));
        this.populated = true;
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

        for (Item i : this.getCollectibles()) {
            i.update();
        }
    }




}
