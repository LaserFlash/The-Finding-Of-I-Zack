package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.Room;

public abstract class AbstractPlayer extends Entity implements Savable {
    public AbstractPlayer(Point location){
        super(location);
    }

    /**
     * Move the player to the south / down
     */
    public abstract void moveSouth();

    /**
     * Move the player to the north / up
     */
    public abstract void moveNorth();

    /**
     * Move the player east / right
     */
    public abstract void moveEast();

    /**
     * Move the player west / left
     */
    public abstract void moveWest();

    /**
     * Tell the player to fire projectile North / Up
     */
    public abstract void shootUp();
    /**
     * Tell the player to fire projectile South / Down
     */
    public abstract void shootDown();
    /**
     * Tell the player to fire projectile West / Left
     */
    public abstract void shootLeft();
    /**
     * Tell the player to fire projectile East / right
     */
    public abstract void shootRight();

    /**
     * Get the room the player is in
     */
    public abstract Room getRoom();

    /**
     * Give the player a room it belongs to
     * @param r
     */
    public abstract void setRoom(Room r);

    /**
     * Return the maximum health of the player
     */
    public abstract int getMaxHealth();

    /**
     * Get the current health of the player
     * @return
     */
    public abstract int getHealth();

    /**
     * Get the current armour of the player
     * @return
     */
    public abstract int getArmour();

    /**
     * Get the max armour of the player
     * @return
     */
    public abstract int getMaxArmour();

    public abstract boolean getKey();
}
