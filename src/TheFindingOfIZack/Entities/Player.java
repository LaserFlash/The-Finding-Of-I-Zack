package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Savable;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Player extends Entity implements Savable {

    private int armour = 0;
    private Room room;
    private int MAX_HEALTH = 100;
    private int speed = 5;
    private int key = 0;

    public Player(int health, Point location) {
        super(health, location);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(location.x, location.y, width, width);
        g.setColor(Color.MAGENTA);
        g.fillOval(location.x+4, location.y+4, width-8, width-8);
        super.draw(g);
    }

    public void moveUp() {
        location.move(0, -5);
    }

    public void moveDown() {
        location.move(0, 5);
    }

    public void moveLeft() {
        location.move(-5, 0);
    }

    public void moveRight() {
        location.move(5, 0);
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void addKey() {
        key++;
    }

    public void removekey() {
        key--;
    }


}
