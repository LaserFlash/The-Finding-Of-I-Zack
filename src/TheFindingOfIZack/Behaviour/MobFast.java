package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Player;

import java.awt.*;

/**
 * Created by gordontheo on 19/09/17.
 */
public class MobFast extends MobEnemy {
    public MobFast(Point location, Player player) {
        super(location, player);
    }

    @Override
    public void step(){
        //performs Mod specific step
        int newX = 0;//temp value
        int newY = 0;//temp value
        move(newX,newY);
    }
}
