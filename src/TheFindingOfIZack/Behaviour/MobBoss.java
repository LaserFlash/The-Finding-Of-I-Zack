package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.bossRoom;

/**
 * Created by gordontheo on 11/10/17.
 * Boss Mob
 */
public class MobBoss extends Mob{
    private final int CHANGE_TYPE = 100;
    private final double MAX_SPEED = 4;
    private int moveType = 0;

    public MobBoss(){
        this.viewRange = 50;
        this.speed = MAX_SPEED;
        this.health = 500;
        this.damage = 20;
    }

    @Override
    public Point step(Point location, Point player, Room room){
        moveType ++;
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        double newX = changeX/a;
        double newY = changeY/a;

        if(moveType == CHANGE_TYPE || moveType == CHANGE_TYPE*2 && room instanceof bossRoom){
            bossRoom r = (bossRoom) room;
            Point mobPoint = new Point(location.getX()+50,location.getY()+50);
            r.setEnemiesInRoom(new Enemy(mobPoint,room.getPlayer()));
        }
        if(moveType >  CHANGE_TYPE*2){
            speed = MAX_SPEED/2;
            newX = -newX;
            newY = -newY;
            if(moveType >  CHANGE_TYPE*3){
                moveType = 0;
                speed = MAX_SPEED;
            }
        }

        location.setLocation((newX + location.getX()),(newY + location.getY()));

        return location;
    }


    @Override
    public String toString() {
        String string = "The Boss mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
}
