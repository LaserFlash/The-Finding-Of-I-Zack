package Tests.Entities;

import TheFindingOfIZack.Entities.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ben Allan
 */
public class PlayerTests {

    private int health = 100;
    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);

    @Test
    public void testMovement() {
        Player p = new Player(health, location);
        assertTrue(p!=null);
        p.moveUp();
        assertTrue(p.getLocation().getY()==y-p.getSpeed());
        p.moveDown();
        assertTrue(p.getLocation().getY()==y);
        p.moveLeft();
        assertTrue(p.getLocation().getX()==x-p.getSpeed());
        p.moveRight();
        assertTrue(p.getLocation().getX()==x);

    }

    @Test
    public void testBoundaries() {
        //To be written
    }

    @Test
    public void testDamage() {
        Player p = new Player(health, location);
        p.damage(50);
        assertTrue(p.getHealth()<p.getMaxHealth());
        assertTrue(p.getHealth() == p.getMaxHealth()-50);
    }

}
