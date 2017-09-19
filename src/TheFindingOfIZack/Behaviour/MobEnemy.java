package TheFindingOfIZack.Behaviour;

import javax.xml.stream.Location;

/**
 * Created by gordontheo on 19/09/17.
 */
public class MobEnemy {
    Location location;

    /**
     * Shifts the mobs location
     * @param newX
     * @param newY
     * @return
     */
    public Location move(double newX, double newY){
        return location;
    }

    /**
     * Overridden in each different mob, is what changes movement behaviour/triggers attack behaviour
     */
    public void step(){
    }

    /**
     * To be used for different mob fields of view
     */
    public double distanceToPlayer(MobPlayer player){
        return 2.0;//Arbitrary double
    }
}
