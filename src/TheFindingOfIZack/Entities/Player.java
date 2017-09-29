package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Player extends Entity implements Savable {

    private int MAX_ARMOUR = 25;
    private int armour = 0;
    private Room room;
    private int MAX_HEALTH = 100;
    private int speed = 2;
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

        if (y < GameSize.TOP_WALL) {
            y = GameSize.TOP_WALL;
            if (room.hasDoor(0) && vertDoor()) {
                System.out.println("Move north");
            }
        }

        location.move(x, y);
    }

    public void moveDown() {
        int x = (int) location.getX();
        int y = (int) location.getY()+speed;

        if (y > GameSize.BOTTOM_WALL-width) {
            y = GameSize.BOTTOM_WALL-width;
            if (room.hasDoor(2) && vertDoor()) {
                System.out.println("Move South");
            }
        }

        location.move(x, y);
    }

    public boolean vertDoor() {
        if (location.getX() > GameSize.VERT_DOOR_START && location.getX() < GameSize.VERT_DOOR_END) {
            if (location.getX()+width > GameSize.VERT_DOOR_START && location.getX()+width < GameSize.VERT_DOOR_END) {return true;}
        }
        return false;
    }

    public void moveLeft() {
        int x = (int) location.getX()-speed;
        int y = (int) location.getY();

        if (x < GameSize.LEFT_WALL) {
            x = GameSize.LEFT_WALL;
            if (room.hasDoor(3) && horzDoor()){
                System.out.println("Move west");
            }
        }

        location.move(x, y);
    }

    public void moveRight() {
        int x = (int) location.getX()+speed;
        int y = (int) location.getY();

        if (x > GameSize.RIGHT_WALL-width) {
            x = GameSize.RIGHT_WALL-width;
            if (room.hasDoor(1) && horzDoor()) {
                System.out.println("Move east");
            }
        }

        location.move(x, y);
    }

    public boolean horzDoor() {
        if (location.getY() > GameSize.HORZ_DOOR_START && location.getY() < GameSize.HORZ_DOOR_END) {
            if (location.getY()+width > GameSize.HORZ_DOOR_START && location.getY()+width < GameSize.HORZ_DOOR_END) {return true;}
        }
        return false;
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

    public int getSpeed() {
        return this.speed;
    }

    public int getMaxArmour() {
        return MAX_ARMOUR;
    }

    public int getArmour() {
        return armour;
    }


}
