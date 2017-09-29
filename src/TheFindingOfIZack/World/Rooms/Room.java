package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.View.Drawable;

/**
 * Created by fieldryan on 19/09/17.
 * Interface for the general definition of a room
 */
public abstract class Room implements Drawable {



    public abstract void update();
    public abstract void addDoor(Door d, int n);
    public abstract boolean hasDoor(int dir);

}
