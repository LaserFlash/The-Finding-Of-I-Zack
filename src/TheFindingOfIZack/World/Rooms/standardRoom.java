package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Util.GameDimensions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class standardRoom extends Room implements Savable{


    public standardRoom(){
        super();
        enemiesInRoom = new ArrayList<Enemy>();
        deadEnemies = new ArrayList<Enemy>();
        items = Collections.synchronizedList(new ArrayList<Entity>());
        isCleared = false;
    }

    public standardRoom(Room r){
        super(r);
    }

    private Point randomPoint(){
        Random r = new Random();

        int left = GameDimensions.LEFT_WALL;  //low x boundary
        int right = GameDimensions.RIGHT_WALL - Entity.width; //high x boundary, allowing for space after object
        int top = GameDimensions.TOP_WALL;    //low y boundary
        int bottom = GameDimensions.BOTTOM_WALL - Entity.width;   //high y boundary, allowing for space after object

        int x = r.nextInt(right - left) + left;
        int y = r.nextInt(bottom - top) + top;

        boolean suitable = false;
        int attempts = 0;
        int MAXATTEMPS = 10000;
        while (!suitable && attempts < MAXATTEMPS) {
            //Check if point is blocking a door or potential door
            //TODO, add to abstract room and door to only check certain door locations
            if (checkPointBlockingDoor(x,y) && checkPointNotOverlap(x,y)){
                suitable = true;
                continue;
            }
            x = r.nextInt(right - left) + left;
            y = r.nextInt(bottom - top) + top;
            attempts++;
        }
        if (attempts >= MAXATTEMPS){
            return null;
        }
        return new Point(x,y);
    }

    /**
     * Check point will not create an entity that overlaps another
     * @param x x position
     * @param y y position
     * @return  true if overlaps with another item or mob
     */
    private boolean checkPointNotOverlap(int x, int y) {

        for (Entity i : items){
            if (i.getBoundingBox().intersects(x,y,x+Entity.width,y+Entity.width)) return false;
        }
        for (Entity i : enemiesInRoom){
            if (i.getBoundingBox().intersects(x,y,x+Entity.width,y+Entity.width)) return false;
        }
        return true;
    }

    /**
     * Check if the given point would block a doorway if it belonged to an entity
     * @param x the x coordinate of point
     * @param y the y coordinate of point
     * @return  true if the door is not blocked false otherwise
     */
    private boolean checkPointBlockingDoor(int x, int y){
        if (x >= GameDimensions.GAME_WIDTH/2 - Door.height/2 - Entity.width &&
                x <= GameDimensions.GAME_WIDTH/2 + Door.height/2){     //potentially blocking north or south door
            if (y >= GameDimensions.BOTTOM_WALL - Entity.width*2){  //Blocking south door
                return false;
            }
            else if (y <= GameDimensions.TOP_WALL + Entity.width){    //Blocking North door
                return false;
            }

        }else if(y >= GameDimensions.GAME_HEIGHT/2 - Door.height/2 - Entity.width &&
                y <= GameDimensions.GAME_HEIGHT/2 + Door.height/2){     //potentially blocking east or west door
            if (x <= GameDimensions.LEFT_WALL + Entity.width){
                return false;
            }
            else if(x >= GameDimensions.RIGHT_WALL - Entity.width*2){
                return false;
            }
        }
        return true;
    }

    @Override
    public  void populateRoom(Player player){
        if(this.isCleared){
            return;
        }

        int numRocks = (int) (Math.random() * 10) + 3;
        int numUrns =  (int) (Math.random() * 3) + 1;
        int numEnemies = (int) (Math.random() * 3 + 4);

        for(int i = 0; i < numRocks; i++){
            Point p = randomPoint();
            if (p == null) continue;    //point not created for some reason
            items.add(new Rock(p));
        }

        for(int i = 0; i < numUrns; i++){
            Point p = randomPoint();
            if (p == null) continue;    //point not created for some reason
            items.add(new Urn(p, getPlayer()));
        }


        for(int i = 0; i < numEnemies; i++){
            Point p = randomPoint();
            if (p == null) continue;    //point not created for some reason
            enemiesInRoom.add(new Enemy(p,getPlayer()));
        }
    }

    /**
     * gets all of the entities in the room that are not enemies
     * @return list of non enemy entities
     */
    public List<Entity> getItems() {
        return items;
    }

    @Override
    public void update() {
        if(this.enemiesInRoom.size() == 0){

            this.isCleared = true;
            if(this.northDoor != null){
                this.northDoor.isLocked = false;
            }
            if(this.eastDoor != null){
                this.eastDoor.isLocked = false;
            }
            if(this.southDoor != null){
                this.southDoor.isLocked = false;
            }
            if(this.westDoor != null){
                this.westDoor.isLocked = false;
            }




        }
        for(Enemy e : enemiesInRoom){
            if(e.isDead()){
                this.deadEnemies.add(e);
            }
        }
        for(Enemy e : deadEnemies){
            enemiesInRoom.remove(e);
        }
        for(Enemy e : enemiesInRoom){
            e.move();
        }
        for(Item i: getCollectibles()){
            i.update();
        }

    }

    public void draw(Graphics g){
        super.draw(g);
        synchronized (items) {
            items.forEach(e -> e.draw(g));
        }
        enemiesInRoom.forEach( e -> e.draw(g));
    }

    /**
     * gets the enemies in the room
     * @return a list of all enemies in the room
     */
    public List<Enemy> getEnemies(){
        return this.enemiesInRoom;
    }


}