package TheFindingOfIZack.Behaviour;

import java.awt.*;

/**
 * Created by gordontheo on 19/09/17.
 * The staple enemy, will remain still until the player enters its field of view.
 * After this it will follow the player at slow speed and cause damage if it touches
 */
public class MobStandard extends MobEnemy {
    private double viewRange = 50;
    public MobStandard(String type) {
        super(type);
    }

    @Override
    public Point step(Point location, Point player){

        if (distanceBetween(location,player) < viewRange){

        }

        //performs Mob specific step
        //int newX = 0;//temp value
        //int newY = 0;//temp value
        return location;
    }
}
