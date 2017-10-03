package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.GameSize;

import java.awt.*;
import java.util.ArrayList;

import static TheFindingOfIZack.Util.GameSize.RIGHT_WALL;


public class standardRoom extends Room {

    private transient ArrayList<Enemy> enemiesInRoom;
    public boolean isCleared;

    public standardRoom(){
        super();
        this.enemiesInRoom = new ArrayList<Enemy>();
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


        Enemy e1 = new Enemy(new Point(50,50),p);
        enemiesInRoom.add(e1);

    }


    @Override
    public void update() {
        if(this.enemiesInRoom.size() == 0){
            this.isCleared = true;
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
    }




}