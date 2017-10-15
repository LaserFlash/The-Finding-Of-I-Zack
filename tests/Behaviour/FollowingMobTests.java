package Behaviour;

import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertTrue;

public class FollowingMobTests {
    @Test
    public void createNewMob() throws Exception {
        MobEnemy test;

        test = new MobEnemy("standard", null);
        assert(test.getDamage() == 10);
        assert(test.getSpeed() == 3);
        assert(test.getHealth() == 50);

        test = new MobEnemy("fast", null);
        assert(test.getDamage() == 5);
        assert(test.getSpeed() == 4.5);
        assert(test.getHealth() == 20);

        test = new MobEnemy("slow", null);
        assert(test.getDamage() == 20);
        assert(test.getSpeed() == 2);
        assert(test.getHealth() == 100);

        test = new MobEnemy("boss", null);
        assert(test.getDamage() == 20);
        assert(test.getSpeed() == 4);
        assert(test.getHealth() == 1000);
    }

    @Test
    public void testStep() throws Exception {
        Player p = new Player(new Point(200,200));
        MobEnemy m;
        Point location;
        Point newlocation;

        m = new MobEnemy("standard",null);
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());

        m = new MobEnemy("fast",null);
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());

        m = new MobEnemy("slow",null);
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());
    }

    /**
     * Used for rounding movement distances in testStep
     * @param value
     * @param places
     * @return
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
