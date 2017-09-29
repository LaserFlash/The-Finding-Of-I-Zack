package Tests.Mocks;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.World.Model;
import TheFindingOfIZack.World.Rooms.Door;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;

public class MockModel implements Model {
    private Player p = new Player(new Point(20,20));

    public MockModel(){
        p.setRoom(new Room() {
            @Override
            public void update() {

            }

            @Override
            public void addDoor(Door d, int n) {

            }

            @Override
            public boolean hasDoor(int dir) {
                return false;
            }

            @Override
            public void draw(Graphics g) {
                g.setColor(Color.green);
                g.fillRect(0,0,900,500);
            }
        });
    }

    @Override
    public void beginNewGame() {

    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void trueRight() {

    }

    @Override
    public void trueLeft() {

    }

    @Override
    public void trueUp() {

    }

    @Override
    public void trueDown() {

    }

    @Override
    public void falseRight() {

    }

    @Override
    public void falseLeft() {

    }

    @Override
    public void falseUp() {

    }

    @Override
    public void falseDown() {

    }

    @Override
    public void shootLeft() {

    }

    @Override
    public void shootRight() {

    }

    @Override
    public void shootUp() {

    }

    @Override
    public void shootDown() {

    }
}
