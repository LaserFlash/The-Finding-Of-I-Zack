package TheFindingOfIZack.World.Rooms;

import java.awt.*;

public class bossRoom extends Room{

    private  Door northDoor;
    private Door eastDoor;
    private Door southDoor;
    private Door westDoor;

    public bossRoom(){
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;
    }
    @Override
    public void update() {

    }

    @Override
    public void addDoor(Door d, int n) {
        if(this.northDoor == null && n == 0){
            this.northDoor = d;
        }
        if(this.eastDoor == null && n == 1){
            this.eastDoor = d;
        }
        if(this.southDoor == null && n == 2){
            this.southDoor = d;
        }
        if(this.westDoor == null && n == 3){
            this.westDoor = d;
        }

    }

    @Override
    public boolean hasDoor(int dir) {
        return false;
    }

    @Override
    public void draw(Graphics g) {

    }
}
