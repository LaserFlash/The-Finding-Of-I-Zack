package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Util.GameSize;

import java.awt.*;
import java.util.ArrayList;


public class standardRoom extends Room{

    private ArrayList<Enemy> enemiesInRoom;
    private ArrayList<Enemy> deadEnemies;
    private ArrayList<Entity> items;


    public standardRoom(){
        super();
        this.enemiesInRoom = new ArrayList<Enemy>();
        this.deadEnemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Entity>();
        this.isCleared = false;


    }


    private Point randomPoint(){
        int x = (int)((Math.random() * GameSize.RIGHT_WALL - GameSize.LEFT_WALL + 40)  + GameSize.WALL_WIDTH - 40) ;
        int y = (int)((Math.random() * GameSize.BOTTOM_WALL - GameSize.TOP_WALL + 40) + GameSize.WALL_WIDTH - 40) ;

        Point p = new Point(x,y);
        return p;
    }
    @Override
    public  void populateRoom(Player p){
        if(this.isCleared){
            return;
        }



        int numRocks = (int) (Math.random() * 5) + 1;
        int numUrns = (int) (Math.random() * 2) ;
        int numEnemies = (int) (Math.random() * 3) + 1 ;
        for(int i = 0; i < numRocks; i++){
            items.add(new Rock(randomPoint()));
        }

        for(int i = 0; i < numUrns; i++){
            items.add(new Urn(randomPoint()));
        }

        for(int i = 0; i < numEnemies; i++){
            enemiesInRoom.add(new Enemy(randomPoint(),p));
        }






    }

    public ArrayList<Entity> getItems() {
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

    }

    public void draw(Graphics g){
        super.draw(g);
        for(Enemy e : enemiesInRoom){
            e.draw(g);
        }
        for(Entity e : items){
            e.draw(g);
        }
    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemiesInRoom;
    }


}