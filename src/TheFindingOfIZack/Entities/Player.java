package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.bossRoom;
import TheFindingOfIZack.World.Rooms.itemRoom;
import TheFindingOfIZack.World.Rooms.standardRoom;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ben Allan
 */
public class Player extends AbstractPlayer {

    /**
     *  Fields for storing armour information
     */
    private int MAX_ARMOUR = 25;
    private int armour = 0;

    /**
     *  Field that stores what room the player is in
     */
    private transient Room room;

    /**
     *  Fields that store health information
     */
    private int MAX_HEALTH = 100;
    private int health = 100;

    /**
     *  Other fields for speed, number of keys, and damage
     */
    private int speed = 5;
    private int key = 0;
    private int damage = 10;
    private int MAX_DAMAGE = 20;

    /**
     *  Fields for storing weapon information
     */
    private int weaponTick = 0;
    private int firerate = 20;
    private int MIN_FIRERATE = 7;

    /**
     *  List of projectiles that the player has fired
     */
    private transient List<Projectile> projectiles;

    private transient Image playerImage;

    /**
     *  Constructor takes a Point as a parameter
     *  Uses the super type of Entity and initialises the projectile list
     *
     * @param location the location that the player starts at
     */
    public Player(Point location) {
        super(location);
        projectiles = Collections.synchronizedList(new ArrayList<Projectile>());
        this.playerImage = ImageLoader.loadImage("/iZack.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);
    }

    public Player(Player p){
        super(p);
        projectiles = Collections.synchronizedList(new ArrayList<Projectile>());
        this.playerImage = ImageLoader.loadImage("/iZack.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);
        this.health = p.getHealth();
        this.armour = p.getArmour();
        this.room = p.getRoom();
        this.health = p.getHealth();
        this.speed = p.getSpeed();
        this.key = (p.getKey())? 1:0;
        this.damage = p.getDamage();
        this.weaponTick = p.weaponTick;
        this.firerate = p.firerate;
    }

    /**
     * Draws the player and player projectiles
     *
     * @param g the graphics object used to draw the player
     */
    @Override
    public void draw(Graphics g) {
        synchronized (projectiles) {
            projectiles.forEach(p -> p.draw(g));
        }

        g.drawImage(playerImage, (int) location.getX(), (int) location.getY(), null);

    }

    /**
     *  Update method
     *  Controls the weapon fire and calls update on all projectiles
     *  It also checks whether items in the room have been destroyed and removes them from the room.
     */
    public void update() {
        /*
        Checks whether the player can fire again and iterates weaponTick
         */
        if (weaponTick >= firerate) {weaponTick = 0;}
        else if (weaponTick != 0) {weaponTick++;}
        for (Projectile p : projectiles) {
            p.move();

            /*
            If the room is a boss room, the boss is added to an array and handed to the projectile to check for collisions
             */
            if (room instanceof bossRoom) {
                bossRoom r = (bossRoom) room;
                ArrayList<Enemy> boss = new ArrayList<Enemy>();
                boss.add(r.getBoss());
                p.enemyCollision(r.getEnemies());
                p.enemyCollision(boss);
            }

            if (room instanceof itemRoom) {
                itemRoom r = (itemRoom) room;

                p.entityCollision(r.items);
                ArrayList<Entity> destroyed = new ArrayList<Entity>();
                for (Entity e : r.items) {

                    if (e instanceof Urn) {
                        Urn urn = (Urn) e;
                        if (urn.isDestroyed()) {destroyed.add(e);}
                    }
                }
                for (Entity e : destroyed) {
                    r.items.remove(e);
                }
            }

            /*
            If the room is a standard room, the projectiles check collisions with all enemies in the room.
             */
            if (room instanceof standardRoom) {
                standardRoom r = (standardRoom) room;
                p.enemyCollision(r.getEnemies());
                p.entityCollision(r.getItems());

                /*
                This checks whether the items in the room have been destroyed and removes them from the list
                 */
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
        /*
        calls popProjectiles method to remove any popped projectiles from the list
         */
        popProjectiles();

        /*
        Checks for any collected items in the room and removes them from the room
         */
        ArrayList<Item> collected = new ArrayList<Item>();
        for (Item i : room.getCollectibles()) {
            if (i.isCollected()) {
                collected.add(i);
            }
        }
        for (Item i : collected) {
            room.getCollectibles().remove(i);
        }

    }


    /**
     *  Damages the player
     *  If the player has armour, the amount of damage is reduced by the amount of armour
     *  When armour absorbs damage it is damaged and its stat decreases by 1
     *
     * @param damage the amount of damage done to the player
     */
    public void damage(int damage) {
        int hit;
        if (armour > damage) {hit = 0;}
        else if (damage > armour) {hit = Math.abs(armour-damage);}
        else {hit = damage;}
        this.health -= hit;
        if (armour > 0) {armour--;}
    }

    /**
     *  Iterates through the projectiles in the projectiles list and removes any popped projectiles
     */
    public void popProjectiles() {
        ArrayList<Projectile> temp = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            if (p.getPopped()) {temp.add(p);}
        }

        for (Projectile p : temp) {
            projectiles.remove(p);
        }
    }

    /**
     *  Moves the player up by speed
     *  If the player is at the top wall then the player doesn't move up
     *  If the player is by a north door the player moves into the next room
     */
    public void moveUp() {
        int x = (int) location.getX();
        int y = (int) location.getY()-speed;

        if (y < GameDimensions.TOP_WALL) {
            y = GameDimensions.TOP_WALL;
            if (room.hasDoor(0) && vertDoor()) {
                moveNorth();
                return;
            }
        }

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
    }

    /**
     *  Moves the player down by speed
     *  If the player is at the bottom wall then the player doesn't move up
     *  If the player is by a south door the player moves into the next room
     */
    public void moveDown() {
        int x = (int) location.getX();
        int y = (int) location.getY()+speed;

        if (y > GameDimensions.BOTTOM_WALL-width) {
            y = GameDimensions.BOTTOM_WALL-width;
            if (room.hasDoor(2) && vertDoor()) {
                moveSouth();
                return;
            }
        }

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
    }

    /**
     *  Moves the player left by speed
     *  If the player is at the left wall then the player doesn't move up
     *  If the player is by a west door the player moves into the next room
     */
    public void moveLeft() {
        int x = (int) location.getX()-speed;
        int y = (int) location.getY();

        if (x < GameDimensions.LEFT_WALL) {
            x = GameDimensions.LEFT_WALL;
            if (room.hasDoor(3) && horzDoor()){
                moveWest();
                return;
            }
        }

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
    }

    /**
     *  Moves the player right by speed
     *  If the player is at the right wall then the player doesn't move up
     *  If the player is by a east door the player moved into the next room
     */
    public void moveRight() {
        int x = (int) location.getX()+speed;
        int y = (int) location.getY();

        if (x > GameDimensions.RIGHT_WALL-width) {
            x = GameDimensions.RIGHT_WALL-width;
            if (room.hasDoor(1) && horzDoor()) {
                moveEast();
                return;
            }
        }

        if (checkCollision(x, y)) {return;}

        location.move(x, y);
        setBox();
    }

    /**
     * Checks whether the player is colliding with an entity
     *
     * @param x target x value
     * @param y target y value
     * @return  true if the new point intersects with an entity
     */
    public boolean checkCollision(int x, int y) {
        if (room instanceof standardRoom) {
            standardRoom r = (standardRoom) room;
            for (Entity e : r.getItems()) {
                if (e.box.intersects(x, y, width, width)) {return true;}
            }
        }
        if (room instanceof itemRoom) {
            itemRoom r = (itemRoom) room;
            for (Entity e : r.items) {
                if (e.box.intersects(x, y, width, width)) {return true;}
            }
        }
        return false;
    }

    /**
     * Checks whether the player is within the boundaries of the vertical doors
     * The vertical doors are the top and bottom doors
     *
     * @return  true if the player is within the boundaries of the vertical doors
     */
    public boolean vertDoor() {
        if (location.getX() >= GameDimensions.VERT_DOOR_START && location.getX() <= GameDimensions.VERT_DOOR_END) {
            if (location.getX()+width >= GameDimensions.VERT_DOOR_START && location.getX()+width <= GameDimensions.VERT_DOOR_END) {return true;}
        }
        return false;
    }

    /**
     * Checks whether the player is within the boundaries of the horizontal doors
     * The horizontal doors are the left and right doors
     *
     * @return  true if the player is within the boundaries of the horizontal doors
     */
    public boolean horzDoor() {
        if (location.getY() >= GameDimensions.HORZ_DOOR_START && location.getY() <= GameDimensions.HORZ_DOOR_END) {
            if (location.getY()+width >= GameDimensions.HORZ_DOOR_START && location.getY()+width <= GameDimensions.HORZ_DOOR_END) {return true;}
        }
        return false;
    }

    /**
     * Moves the player into the room south of the current room
     * If there is no south door or the door is locked the player cannot move south
     * If the door leads to a boss room that hasn't been unlocked yet the player requires a key
     * Once a boss door is unlocked it stays unlocked if the player wishes to return
     */
    @Override
    public void moveSouth() {
        if (room.getSouthDoor().isLocked) {return;}
        if (room.getSouthDoor().bossDoor && room.getSouthDoor().needsKey) {
            if (this.key > 0) {
                removekey();
                room.getSouthDoor().useKey();
            }
            else {return;}
        }

        int x = (int) location.getX();
        int y = GameDimensions.TOP_WALL;
        room.removePlayer();
        room = room.getSouthDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }

    /**
     * Moves the player into the room north of the current room
     * If there is no north door or the door is locked the player cannot move south
     * If the door leads to a boss room that hasn't been unlocked yet the player requires a key
     * Once a boss door is unlocked it stays unlocked if the player wishes to return
     */
    @Override
    public void moveNorth() {
        if (room.getNorthDoor().isLocked) {return;}
        if (room.getNorthDoor().bossDoor && room.getNorthDoor().needsKey) {
            if (this.key > 0) {
                removekey();
                room.getNorthDoor().useKey();
            }
            else {return;}
        }

        int x = (int) location.getX();
        int y = GameDimensions.BOTTOM_WALL-width;
        room.removePlayer();
        room = room.getNorthDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }

    /**
     * Moves the player into the room west of the current room
     * If there is no west door or the door is locked the player cannot move south
     * If the door leads to a boss room that hasn't been unlocked yet the player requires a key
     * Once a boss door is unlocked it stays unlocked if the player wishes to return
     */
    @Override
    public void moveWest() {
        if (room.getWestDoor().isLocked) {return;}
        if (room.getWestDoor().bossDoor && room.getWestDoor().needsKey) {
            if (this.key > 0) {
                removekey();
                room.getWestDoor().useKey();
            }
            else {return;}
        }

        int x = GameDimensions.RIGHT_WALL-width;
        int y = (int) location.getY();
        room.removePlayer();
        room = room.getWestDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }

    /**
     * Moves the player into the room east of the current room
     * If there is no east door or the door is locked the player cannot move south
     * If the door leads to a boss room that hasn't been unlocked yet the player requires a key
     * Once a boss door is unlocked it stays unlocked if the player wishes to return
     */
    @Override
    public void moveEast() {
        if (room.getEastDoor().isLocked) {return;}
        if (room.getEastDoor().bossDoor && room.getEastDoor().needsKey) {
            if (this.key > 0) {
                removekey();
                room.getEastDoor().useKey();
            }
            else {return;}
        }

        int x = GameDimensions.LEFT_WALL;
        int y = (int) location.getY();
        room.removePlayer();
        room = room.getEastDoor().getDestination();
        room.addPlayer(this);
        location.move(x, y);
        room.populateRoom(this);
        projectiles.clear();
    }

    /**
     * Clones the current location of the player
     * @return  a clone of the players location
     */
    private Point clonePoint() {
        int x = (int) location.getX();
        int y = (int) location.getY();
        return new Point(x, y);
    }

    /**
     * Creates a new projectile that moves up
     */
    @Override
    public void shootUp() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "up");
        projectiles.add(p);
        weaponTick++;
    }

    /**
     * Creates a new projectile that moves down
     */
    @Override
    public void shootDown() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "down");
        projectiles.add(p);
        weaponTick++;
    }

    /**
     * Creates a new projectile that moves left
     */
    @Override
    public void shootLeft() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "left");
        projectiles.add(p);
        weaponTick++;
    }

    /**
     * Creates a new projectile that moves right
     */
    @Override
    public void shootRight() {
        if (weaponTick != 0) {return;}
        Projectile p = new Projectile(this.damage, clonePoint(), "right");
        projectiles.add(p);
        weaponTick++;
    }


    /**
     *  Getter and setters for player fields
     */


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

    public boolean getKey(){
        return key > 0;
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

    public int getDamage(){ return damage; }

    public void weaponUpgrade() {
        if (damage + 2 > MAX_DAMAGE) {damage = MAX_DAMAGE;}
        else {damage += 2;}
        if (firerate-3 < MIN_FIRERATE) {firerate = MIN_FIRERATE;}
        else {firerate -= 3;}
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public boolean isDead() {
        return health <= 0;
    }


    public int getSpeed() {
        return speed;
    }
}
