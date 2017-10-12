package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Boss;
import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.FileIO.Util.Savable;

import java.awt.*;
import java.util.ArrayList;

public class bossRoom extends Room implements Savable {

    private Boss boss;
    private ArrayList<Enemy> enemiesInRoom;
    private ArrayList<Enemy> deadEnemies;

    public bossRoom(){
        this.enemiesInRoom = new ArrayList<Enemy>();
        this.deadEnemies = new ArrayList<Enemy>();
        this.isCleared = true;
    }

    @Override
    public void populateRoom(Player p) {
        this.boss = new Boss(new Point(100,100),p);

    }


    public void draw(Graphics g){
        super.draw(g);
        enemiesInRoom.forEach(e -> e.draw(g));
        if(boss != null){boss.draw(g);}
    }



    @Override
    public void update() {
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

        for(Enemy e : enemiesInRoom){
            if(e.isDead()){
                this.deadEnemies.add(e);
            }
        }
        if(!enemiesInRoom.isEmpty()){
        for(Enemy e : deadEnemies){
            enemiesInRoom.remove(e);
        }}
        for(Enemy e : enemiesInRoom){
            e.move();
        }
        boss.move();

    }

    public void setEnemiesInRoom(Enemy enemy){
        this.enemiesInRoom.add(enemy);
    }

    public Boss getBoss(){
        return this.boss;
    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemiesInRoom;
    }
}
