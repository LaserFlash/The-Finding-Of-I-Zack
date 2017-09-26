package Behaviour;

import java.awt.*;

/**
 * Created by gordontheo on 19/09/17.
 * The staple enemy, will remain still until the player enters its field of view.
 * After this it will follow the player at slow speed and cause damage if it touches
 */
public class MobSlow extends MobEnemy {
    public MobSlow(String type) {
        super(type);
    }

    @Override
    public Point step(Point location, Point player){
        //performs Mod specific step
        int newX = 0;//temp value
        int newY = 0;//temp value
        return location;
    }
}
