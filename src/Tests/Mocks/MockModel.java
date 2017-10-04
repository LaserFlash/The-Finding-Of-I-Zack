package Tests.Mocks;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.World.Model;
import TheFindingOfIZack.World.Rooms.Door;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.standardRoom;

import java.awt.*;

public class MockModel implements Model {
    private Player p = new Player(new Point(20,20));

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

    }

    @Override
    public void moveDown(boolean b) {

    }

    @Override
    public void moveLeft(boolean b) {

    }

    @Override
    public void moveRight(boolean b) {

    }

    @Override
    public void shootLeft(boolean b) {

    }

    @Override
    public void shootRight(boolean b) {

    }

    @Override
    public void shootUp(boolean b) {

    }

    @Override
    public void shootDown(boolean b) {

    }

}
