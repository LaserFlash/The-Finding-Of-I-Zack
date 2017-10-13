package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

/**
 * Created by gordontheo on 27/09/17.
 * This class is used to create new mobs and specify what type they are
 */
public class MobEnemy implements Savable {
    private Mob mob;

    /**
     * @param type Constructor which takes a type and makes a new mob of this type
     */
    public MobEnemy(String type, Room room) {
        switch (type) {
            case "standard":
                mob = new MobStandard();
                break;
            case "fast":
                mob = new MobFast();
                break;
            case "slow":
                mob = new MobSlow();
                break;
            case "shooter":
                mob = new MobShooter();
                break;
            case "boss":
                mob = new MobBoss();
                break;
            default: mobTypeError(type);
                break;
        }
    }

    /**
     * Returns health f
     * @return health
     */
    public int getHealth(){
        return mob.getHealth();
    }

    public int getDamage(){
        return mob.getDamage();
    }

    public double getSpeed() {return mob.getSpeed();}

    /**
     * Throws an error if an invalid mobType is called
     * @param str the invalid type name entered
     */
    private void mobTypeError(String str){
        System.err.print("Error: Invalid mob type " + str + "\n");
    }

    public Point step(Point location, Point player, Room r){
        return(mob.step(location,player,r));
    }

    public Mob getMob(){
        return mob;
    }

}