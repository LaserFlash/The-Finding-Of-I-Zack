package Entities;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.World.Rooms.standardRoom;
import org.junit.Test;

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
        Player p = new Player(location);
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
    public void testProjectiles() {
        Player p = new Player(location);
        p.shootLeft();

        assertTrue(!p.getProjectiles().isEmpty());

    }

    /*@Test
    public void testLockedDoor() {
        Player p = new Player(new Point(GameDimensions.VERT_DOOR_START, GameDimensions.TOP_WALL));
        standardRoom r1 = createRoom();

        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 0);

        r1.addDoor(d, 0);
        r1.populateRoom(p);

        p.setRoom(r1);

        p.moveNorth();

        assertTrue(p.getRoom().equals(r1));

    }

    @Test
    public void testUnlockedDoor() {
        Player p = new Player(new Point(GameDimensions.VERT_DOOR_START, GameDimensions.TOP_WALL));
        standardRoom r1 = createRoom();

        Room r2 = createRoom();

        Door d = new Door(r1, r2, 0);

        r1.addDoor(d, 0);


        p.setRoom(r1);
        r1.update();
        p.moveNorth();
        assertTrue(p.getRoom().equals(r1));

    }*/

    public standardRoom createRoom() {
        return new standardRoom() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
    }

    @Test
    public void testBoundaries() {
        Player p = new Player(new Point(GameDimensions.LEFT_WALL, GameDimensions.TOP_WALL));
        p.setRoom(createRoom());
        Point point = new Point(GameDimensions.LEFT_WALL, GameDimensions.TOP_WALL);

        p.moveUp();
        assertTrue(p.getLocation().getY() == point.getY());

        p.moveLeft();
        assertTrue(p.getLocation().getX() == point.getX());

        p = new Player(new Point(GameDimensions.RIGHT_WALL-p.width, GameDimensions.BOTTOM_WALL-p.width));
        p.setRoom(createRoom());
        point = new Point(GameDimensions.RIGHT_WALL-p.width, GameDimensions.BOTTOM_WALL-p.width);

        p.moveDown();
        assertTrue(p.getLocation().getY() == point.getY());

        p.moveRight();
        assertTrue(p.getLocation().getX() == point.getX());
    }

    @Test
    public void testDamage() {
        Player p = new Player(location);
        p.damage(50);
        assertTrue(p.getHealth()<p.getMaxHealth());
        assertTrue(p.getHealth() == p.getMaxHealth()-50);
    }

}
