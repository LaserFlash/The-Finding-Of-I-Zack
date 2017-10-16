package Behaviour;

import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.Behaviour.MobType;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by gordontheo on 16/10/17.
 */
public class MobShooterTests {
    @Test
    public void createNewShooter() {
        MobEnemy test = new MobEnemy(MobType.Shooter);
        assert (test.getDamage() == 5);
        assert (test.getSpeed() == 3);
        assert (test.getHealth() == 50);
    }

    @Test
    public void testStep(){
        Player p = new Player(new Point(200,200));
        MobEnemy m;
        Point location;
        Point newlocation;

        m = new MobEnemy(MobType.Shooter);
        location = new Point(500,500);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        System.out.println("a " + newlocation.getX() + " b " + newlocation.getY());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
    }
}
