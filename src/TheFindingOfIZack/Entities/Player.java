package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.standardRoom;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Ben Allan
 */
public class Player extends AbstractPlayer {

    private int MAX_ARMOUR = 25;
    private int armour = 0;
    private transient Room room;
    private int MAX_HEALTH = 100;
    private int health = 100;
    private int speed = 5;
    private int key = 0;
    private int damage = 10;

    private int weaponTick = 0;
    private int firerate = 20;
    private int MIN_FIRERATE = 7;

    private transient ArrayList<Projectile> projectiles;

    public Player(Point location) {
        super(location);
        projectiles = new ArrayList<Projectile>();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void draw(Graphics g) {

        for (Projectile p : projectiles) {
            p.draw(g);
        }

        g.setColor(Color.CYAN);
        g.fillRect((int) location.getX(), (int) location.getY(), width, width);
        g.setColor(Color.MAGENTA);
        g.fillOval((int) location.getX()+4, (int) location.getY()+4, width-8, width-8);

        super.draw(g);

    }

    public void update() {
        if (weaponTick == firerate) {weaponTick = 0;}
        else if (weaponTick != 0) {weaponTick++;}
        for (Projectile p : projectiles) {
            p.move();
            if (room instanceof standardRoom) {
                standardRoom r = (standardRoom) room;
                p.enemyCollision(r.getEnemies());
                p.entityCollision(r.getItems());


                ArrayList<Entity> destroyed = new ArrayList<Entity>();
                for (Entity e : r.getItems()) {
                    if (e instanceof Rock) {
                        Rock rock = (Rock) e;
                        if (rock.isDestroyed()) {destroyed.add(e);}
                    }
                    else if (e instanceof Urn) {
                        Urn urn = (Urn) e;
                        if (urn.isDestroyed()) {destroyed.add(e);}
                    }
                }
                for (Entity e : destroyed) {
                    r.getItems().remove(e);
                }

            }
        }
        popProjectiles();

    }

    public void damage(int damage) {
        this.health -= (damage-armour);
        if (armour > 0) {armour--;}
    }

    public void popProjectiles() {
        ArrayList<Projectile> temp = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            if (p.getPopped()) {temp.add(p);}
        }

        for (Projectile p : temp) {
            projectiles.remove(p);
        }
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

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
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

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
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

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
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

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
    }

    public boolean checkCollision(int x, int y) {
        if (room instanceof standardRoom) {
            standardRoom r = (standardRoom) room;
            for (Entity e : r.getItems()) {
                if (e.box.intersects(x, y, width, width)) {return true;}
            }
        }
        return false;
    }

    public boolean horzDoor() {
        if (location.getY() > GameSize.HORZ_DOOR_START && location.getY() < GameSize.HORZ_DOOR_END) {
            if (location.getY()+width > GameSize.HORZ_DOOR_START && location.getY()+width < GameSize.HORZ_DOOR_END) {return true;}
        }
        return false;
    }

    @Override
    public void moveSouth() {
        if (room.getSouthDoor().isLocked) {return;}
        int x = (int) location.getX();
        int y = GameSize.TOP_WALL;
        room.removePlayer();
        room = room.getSouthDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }

    @Override
    public void moveNorth() {
        if (room.getNorthDoor().isLocked) {return;}
        int x = (int) location.getX();
        int y = GameSize.BOTTOM_WALL-width;
        room.removePlayer();
        room = room.getNorthDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }
    @Override
    public void moveWest() {
        if (room.getWestDoor().isLocked) {return;}
        int x = GameSize.RIGHT_WALL-width;
        int y = (int) location.getY();
        room.removePlayer();
        room = room.getWestDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }
    @Override
    public void moveEast() {
        if (room.getEastDoor().isLocked) {return;}
        int x = GameSize.LEFT_WALL;
        int y = (int) location.getY();
        room.removePlayer();
        room = room.getEastDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }

    private Point clonePoint() {
        int x = (int) location.getX();
        int y = (int) location.getY();
        return new Point(x, y);
    }

    @Override
    public void shootUp() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "up");
        projectiles.add(p);
        weaponTick++;
    }
    @Override
    public void shootDown() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "down");
        projectiles.add(p);
        weaponTick++;
    }
    @Override
    public void shootLeft() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "left");
        projectiles.add(p);
        weaponTick++;
    }
    @Override
    public void shootRight() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "right");
        projectiles.add(p);
        weaponTick++;
    }




    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getHealth() {
        return this.health;
    }

    public void heal(int potion) {
        if (health+potion > MAX_HEALTH) {health = MAX_HEALTH;}
        else {this.health += potion;}
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
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

    public void addArmour(int amount) {
        if (armour+amount > MAX_ARMOUR) {armour = MAX_ARMOUR;}
        else {this.armour += amount;}
    }

    public void weaponUpgrade() {
        damage += 5;
        if (firerate-3 < MIN_FIRERATE) {firerate = MIN_FIRERATE;}
        else {firerate -= 3;}
    }

    public ArrayList<Projectile> getProjecctiles() {
        return projectiles;
    }


}
