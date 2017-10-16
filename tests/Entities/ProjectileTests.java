package Entities;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Entities.Projectile;
import TheFindingOfIZack.Util.GameDimensions;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by allanbenj1 on 16/10/17.
 */
public class ProjectileTests {

    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);

    @Test
    public void testCreation() {
        Projectile p1 = new Projectile(10, location, "left");
        Projectile p2 = new Projectile(10, location, "right");
        Projectile p3 = new Projectile(10, location, "up");
        Projectile p4 = new Projectile(10, location, "down");
        assertTrue(p1 != null);
        assertTrue(p2 != null);
        assertTrue(p3 != null);
        assertTrue(p4 != null);
    }



    @Test
    public void testDrawProjectile() throws InterruptedException {
        Projectile p = new Projectile(10, location, "left");
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> p.draw(panel.getGraphics())).start();
            new Timer(3000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

}
