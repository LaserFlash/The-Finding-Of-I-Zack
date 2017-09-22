package TheFindingOfIZack.Behaviour;

/**
 * Created by gordontheo on 19/09/17.
 */
public class MobStandard extends MobEnemy {
    @Override
    protected void step(){
        //performs Mod specific step
        int newX = 0;//temp value
        int newY = 0;//temp value
        move(newX,newY);
    }
}
