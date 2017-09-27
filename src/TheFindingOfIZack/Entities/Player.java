package TheFindingOfIZack.Entities;

import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Player extends Entity {

    private int armour = 0;
    private Room room;
    private int MAX_HEALTH = 100;
    private int speed = 3;
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
        int x = (int) location.getX();
        int y = (int) location.getY()-speed;
        location.move(x, y);
    }

    public void moveDown() {
        int x = (int) location.getX();
        int y = (int) location.getY()+speed;
        location.move(x, y);
    }

    public void moveLeft() {
        int x = (int) location.getX()-speed;
        int y = (int) location.getY();
        location.move(x, y);
    }

    public void moveRight() {
        int x = (int) location.getX()+speed;
        int y = (int) location.getY();
        location.move(x, y);
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
