package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by fieldryan on 27/09/17.
 */
public class Door implements Drawable, Savable {

    private  Image openDoorImage;
    private  Image closedDoorImage;
    private  Image lockedDoorImage;

    private Room entry;
    private Room destination;
    int position;
    public static final int height = 50;
    public boolean isLocked;
    public boolean bossDoor;
    public boolean needsKey;



    public Door(Room entry, Room destination, int position){
        this.entry = entry;
        this.destination = destination;
        this.position = position;
        this.isLocked = true;
        if(destination instanceof bossRoom){
            this.bossDoor = true;
            this.needsKey = true;
           this.lockedDoorImage = ImageLoader.loadImage("/lockedDoor.png");
        }else{
            this.bossDoor = false;
            this.needsKey = false;
        }
        initialiseOPenImage();
        initialiseClosedImage();

    }

    public Room getDestination(){
        return this.destination;
    }

    public Door getOpposite(){
        Door d;
        int w = 0;
        if(this.position == 0){
            w = 2;
        }else if(this.position == 1){
            w = 3;
        }else if(this.position == 2){
            w = 0;
        }else if(this.position == 3){
            w = 1;
        }
    d = new Door(this.destination,this.entry,w);


        return d;
    }

    public int getPosition(){
        return this.position;
    }

    @Override
    public void draw(Graphics g) {
        if(this.isLocked && !this.bossDoor){

            g.setColor(Color.red);
            if(this.position == 3){
                //g.fillRect(0,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(closedDoorImage,0, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);

            }
            if(this.position == 2){
                g.drawImage(closedDoorImage, GameDimensions.GAME_WIDTH/2 - height/2, GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH,null);
               // g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH, height,GameDimensions.WALL_WIDTH);
            }
            if(this.position == 0){

                //g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,0, height,GameDimensions.WALL_WIDTH);
                g.drawImage(closedDoorImage, GameDimensions.GAME_WIDTH/2 - height/2,0,null);
            }
            if(this.position == 1){
                //g.fillRect(GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(closedDoorImage, GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);
            }
        }else if(!this.isLocked && !this.bossDoor){
            g.setColor(Color.green);
            if(this.position == 3){
                //g.fillRect(0,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(openDoorImage,0, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);

            }
            if(this.position == 2){
                g.drawImage(openDoorImage, GameDimensions.GAME_WIDTH/2 - height/2, GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH,null);
                // g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH, height,GameDimensions.WALL_WIDTH);
            }
            if(this.position == 0){

                //g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,0, height,GameDimensions.WALL_WIDTH);
                g.drawImage(openDoorImage, GameDimensions.GAME_WIDTH/2 - height/2,0,null);
            }
            if(this.position == 1){
                //g.fillRect(GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(openDoorImage, GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);
            }
        }else if(this.bossDoor){
            if(this.position == 2){
                g.drawImage(lockedDoorImage, GameDimensions.GAME_WIDTH/2 - height/2, GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH,null);
                // g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH, height,GameDimensions.WALL_WIDTH);
            }
        }



    }






    public void initialiseOPenImage() {
        if(this.position == 0) {
            openDoorImage = ImageLoader.loadImage("/openDoorTop.png");
        }
        if(this.position == 1) {
            openDoorImage = ImageLoader.loadImage("/openDoorLeft.png");
        }
        if(this.position == 2) {
            openDoorImage = ImageLoader.loadImage("/openDoorDown.png");
        }
        if(this.position == 3) {
            openDoorImage = ImageLoader.loadImage("/openDoorRight.png");
        }
    }

    public void initialiseClosedImage() {
        if(this.position == 0) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorTop.png");
        }
        if(this.position == 1) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorRight.png");
        }
        if(this.position == 2) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorDown.png");
        }
        if(this.position == 3) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorLeft.png");
        }
    }
}
