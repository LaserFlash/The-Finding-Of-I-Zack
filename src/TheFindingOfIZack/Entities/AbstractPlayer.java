package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.Room;

public abstract class AbstractPlayer extends Entity implements Savable {
    AbstractPlayer(Point location){
        super(location);
    }

    /**
     * Move the player to the south / down
     */
    abstract void moveSouth();

    /**
     * Move the player to the north / up
     */
    abstract void moveNorth();

    /**
     * Move the player east / right
     */
    abstract void moveEast();

    /**
     * Move the player west / left
     */
    abstract void moveWest();

    /**
     * Tell the player to fire projectile North / Up
     */
    abstract void shootUp();
    /**
     * Tell the player to fire projectile South / Down
     */
    abstract void shootDown();
    /**
     * Tell the player to fire projectile West / Left
     */
    abstract void shootLeft();
    /**
     * Tell the player to fire projectile East / right
     */
    abstract void shootRight();

    /**
     * Get the room the player is in
     */
    abstract Room getRoom();
}
