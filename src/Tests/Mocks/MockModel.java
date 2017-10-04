package Tests.Mocks;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.World.Model;
import TheFindingOfIZack.World.Rooms.Room;


public class MockModel implements Model {
    private Player p = new Player(new Point(20,20));

    public boolean sLeft = false;
    public boolean sRight = false;
    public boolean sDown = false;
    public boolean sUp = false;
    public boolean mLeft = false;
    public boolean mUp = false;
    public boolean mDown = false;
    public boolean mRight = false;

    public MockModel(){
        p.setRoom(new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        });
    }

    @Override
    public void beginNewGame() {

    }

    @Override
    public Player getPlayer() {
        return p;
    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void moveUp(boolean b) {
        mUp = b;
    }

    @Override
    public void moveDown(boolean b) {
        mDown = b;
    }

    @Override
    public void moveLeft(boolean b) {
        mLeft = b;
    }

    @Override
    public void moveRight(boolean b) {
        mRight = b;
    }

    @Override
    public void shootLeft(boolean b) {
        sLeft = b;
    }

    @Override
    public void shootRight(boolean b) {
        sRight = b;
    }

    @Override
    public void shootUp(boolean b) {
        sUp = b;
    }

    @Override
    public void shootDown(boolean b) {
        sDown = b;
    }
}
