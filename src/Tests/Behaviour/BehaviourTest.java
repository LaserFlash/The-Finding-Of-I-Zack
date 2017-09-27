package Tests.Behaviour;

import TheFindingOfIZack.Behaviour.MobEnemy;
import org.junit.Test;

public class BehaviourTest {
    @Test
    public void createNewMob() throws Exception {
        MobEnemy test;

        test = new MobEnemy("standard");

        test = new MobEnemy("fast");

        test = new MobEnemy("slow");


    }

    @Test
    public void testStep() throws Exception {

    }

    @Test
    public void testDistanceBetween() throws Exception {

    }
}
