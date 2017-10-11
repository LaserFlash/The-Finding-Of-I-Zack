package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Boss;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;

import java.awt.*;

public class bossRoom extends Room{

    Boss boss;

    public bossRoom(){
        this.isCleared = true;
    }

    @Override
    public void populateRoom(Player p) {
        this.boss = new Boss(new Point(100,100),p);

    }


    public void draw(Graphics g){
        super.draw(g);
        boss.draw(g);
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
        boss.update();

    }

}
