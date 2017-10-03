package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.GameSize;

import java.awt.*;
import java.util.ArrayList;

import static TheFindingOfIZack.Util.GameSize.RIGHT_WALL;


public class standardRoom extends Room {

    private transient ArrayList<Enemy> enemiesInRoom;
    private transient ArrayList<Enemy> deadEnemies;
    public boolean isCleared;

    public standardRoom(){
        super();
        this.enemiesInRoom = new ArrayList<Enemy>();
        this.deadEnemies = new ArrayList<Enemy>();
        this.isCleared = false;
    }


    private Point randomPoint(){
        int x = (int)((Math.random() * GameSize.RIGHT_WALL - GameSize.LEFT_WALL) + GameSize.WALL_WIDTH) ;
        int y = (int)((Math.random() * GameSize.TOP_WALL - GameSize.BOTTOM_WALL) + GameSize.WALL_WIDTH) ;

        Point p = new Point(x,y);
        return p;
    }
    @Override
    public  void populateRoom(Player p){


        Enemy e1 = new Enemy(randomPoint(),p);
        enemiesInRoom.add(e1);

    }


    @Override
    public void update() {
        if(this.enemiesInRoom.size() == 0){
            System.out.println("all enemies are dead");
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
        for (Enemy e : enemiesInRoom){
            if (e.isDead()){
                this.deadEnemies.add(e);
            }
        }
        for (Enemy e :deadEnemies){
            enemiesInRoom.remove(e);
        }
        for (Enemy e : enemiesInRoom){
            e.move();
        }

    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemiesInRoom;
    }

    public void draw(Graphics g){
        super.draw(g);
        for(Enemy e : enemiesInRoom){
            e.draw(g);
        }
    }




}