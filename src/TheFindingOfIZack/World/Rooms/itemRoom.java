package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Entity;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Entities.Urn;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Items.Key;
import TheFindingOfIZack.Util.GameDimensions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by fieldryan on 19/09/17.
 *
 */
public class itemRoom extends Room {


    private boolean populated;
    public List<Entity> items;

    public itemRoom(){
        this.populated = false;
        this.items = Collections.synchronizedList(new ArrayList<Entity>());
    }

    @Override
    public void populateRoom(Player p) {
        if(populated){
            return;
        }
        int numUrns =  7;

        for(int i = 0; i < numUrns; i++){
            Point point = randomPoint();
            if (point == null) continue;    //point not created for some reason
            items.add(new Urn(point, p));
        }



        this.getCollectibles().add(new Key(p));
        this.populated = true;
    }

    public void draw(Graphics g){
        super.draw(g);

        synchronized (items) {
            items.forEach(e -> e.draw(g));
        }
    }




    @Override
    public void update() {
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

        for (Item i : this.getCollectibles()) {
            i.update();
        }

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



}
