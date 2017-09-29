package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;
import java.math.BigInteger;

/**
 * Created by Ben Allan
 */
public class Player extends Entity implements Savable {

    private int MAX_ARMOUR = 25;
    private int armour = 0;
    private Room room;
    private int MAX_HEALTH = 100;
    private int health = 100;
    private int speed = 3;
    private int key = 0;
    private int damage = 10;

    public int weaponTick;

    public Player(){

    }

    public Player(Point location) {
        super(location);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) location.getX(), (int) location.getY(), width, width);
        g.setColor(Color.MAGENTA);
        g.fillOval((int) location.getX()+4, (int) location.getY()+4, width-8, width-8);
        super.draw(g);
    }

    public void moveUp() {
        int x = (int) location.getX();
        int y = (int) location.getY()-speed;

        if (y < GameSize.TOP_WALL) {
            y = GameSize.TOP_WALL;
            if (room.hasDoor(0) && vertDoor()) {
                moveNorth();
                return;
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
                moveSouth();
                return;
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
                moveWest();
                return;
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
                moveEast();
                return;
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


    public void moveSouth() {
        int x = (int) location.getX();
        int y = GameSize.TOP_WALL;
        room = room.getSouthDoor().getDestination();
        location.move(x, y);
    }

    public void moveNorth() {
        int x = (int) location.getX();
        int y = GameSize.BOTTOM_WALL-width;
        room = room.getNorthDoor().getDestination();
        location.move(x, y);
    }

    public void moveWest() {
        int x = GameSize.RIGHT_WALL-width;
        int y = (int) location.getY();
        room = room.getWestDoor().getDestination();
        location.move(x, y);
    }

    public void moveEast() {
        int x = GameSize.LEFT_WALL;
        int y = (int) location.getY();
        room = room.getEastDoor().getDestination();
        location.move(x, y);
    }




    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getHealth() {
        return this.health;
    }

    public void damage(int damage) {
        this.health -= damage;
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
