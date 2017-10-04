package Tests.Behaviour;

import TheFindingOfIZack.Behaviour.MobEnemy;
import org.junit.Test;

public class BehaviourTest {
    @Test
    public void createNewMob() throws Exception {
        MobEnemy test;

        test = new MobEnemy("standard");
        assert(test.getDamage() == 10);
        assert(test.getSpeed() == 2);
        assert(test.getHealth() == 50);

        test = new MobEnemy("fast");
        assert(test.getDamage() == 5);
        assert(test.getSpeed() == 4);
        assert(test.getHealth() == 20);

        test = new MobEnemy("slow");
        assert(test.getDamage() == 20);
        assert(test.getSpeed() == 1);
        assert(test.getHealth() == 100);

    }

    @Test
    public void testStep() throws Exception {
    }

    @Test
    public void testDistanceBetween() throws Exception {
    }
}
