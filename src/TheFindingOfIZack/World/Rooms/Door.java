package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by fieldryan on 27/09/17.
 */
public class Door implements Drawable {

    private Room entry;
    private Room destination;
    int position;



    public Door(Room entry, Room destination, int position){
        this.entry = entry;
        this.destination = destination;
        this.position = position;
    }



    @Override
    public void draw(Graphics g) {

    }
}
