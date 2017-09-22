package TheFindingOfIZack.Behaviour;


import TheFindingOfIZack.Entities.Player;

import java.awt.*;


/**
 * Created by gordontheo on 19/09/17.
 */
public class MobEnemy {
    private Point location;
    private Player player;

    public MobEnemy(Point location, Player player){
        this.location = location;
        this.player = player;
    }

    /**
     * Shifts the mob's location
     * @param newX change in x
     * @param newY change in y
     * @return new location
     */
    public Point move(double newX, double newY){return location;}

    /**
     * Overridden in each different mob, is what changes movement behaviour/triggers attack behaviour
     */
    private void step(){
    }

    /**
     * To be used for different mob fields of view
     */
    private double distanceToPlayer(MobPlayer player){

        return 0;//temp
    }
}
