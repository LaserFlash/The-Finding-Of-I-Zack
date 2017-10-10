package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by fieldryan on 27/09/17.
 */
public class Door implements Drawable, Savable {

    private  Image openDoorImage;
    private  Image closedDoorImage;
    private static Image lockedDoorImage;

    private Room entry;
    private Room destination;
    int position;
    public static final int height = 50;
    public boolean isLocked;



    public Door(Room entry, Room destination, int position){
        this.entry = entry;
        this.destination = destination;
        this.position = position;
        this.isLocked = true;
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
        if(this.isLocked){
            g.setColor(Color.red);
            if(this.position == 3){
                //g.fillRect(0,GameSize.GAME_HEIGHT/2 - this.height/2,GameSize.WALL_WIDTH,height);
                g.drawImage(closedDoorImage,0,GameSize.GAME_HEIGHT/2 - this.height/2,null);

            }
            if(this.position == 2){
                g.drawImage(closedDoorImage,GameSize.GAME_WIDTH/2 - height/2,GameSize.GAME_HEIGHT - GameSize.WALL_WIDTH,null);
               // g.fillRect(GameSize.GAME_WIDTH/2 - height/2,GameSize.GAME_HEIGHT - GameSize.WALL_WIDTH, height,GameSize.WALL_WIDTH);
            }
            if(this.position == 0){

                //g.fillRect(GameSize.GAME_WIDTH/2 - height/2,0, height,GameSize.WALL_WIDTH);
                g.drawImage(closedDoorImage,GameSize.GAME_WIDTH/2 - height/2,0,null);
            }
            if(this.position == 1){
                //g.fillRect(GameSize.GAME_WIDTH - GameSize.WALL_WIDTH,GameSize.GAME_HEIGHT/2 - this.height/2,GameSize.WALL_WIDTH,height);
                g.drawImage(closedDoorImage,GameSize.GAME_WIDTH - GameSize.WALL_WIDTH,GameSize.GAME_HEIGHT/2 - this.height/2,null);
            }
        }else {
            g.setColor(Color.green);
            if(this.position == 3){
                //g.fillRect(0,GameSize.GAME_HEIGHT/2 - this.height/2,GameSize.WALL_WIDTH,height);
                g.drawImage(openDoorImage,0,GameSize.GAME_HEIGHT/2 - this.height/2,null);

            }
            if(this.position == 2){
                g.drawImage(openDoorImage,GameSize.GAME_WIDTH/2 - height/2,GameSize.GAME_HEIGHT - GameSize.WALL_WIDTH,null);
                // g.fillRect(GameSize.GAME_WIDTH/2 - height/2,GameSize.GAME_HEIGHT - GameSize.WALL_WIDTH, height,GameSize.WALL_WIDTH);
            }
            if(this.position == 0){

                //g.fillRect(GameSize.GAME_WIDTH/2 - height/2,0, height,GameSize.WALL_WIDTH);
                g.drawImage(openDoorImage,GameSize.GAME_WIDTH/2 - height/2,0,null);
            }
            if(this.position == 1){
                //g.fillRect(GameSize.GAME_WIDTH - GameSize.WALL_WIDTH,GameSize.GAME_HEIGHT/2 - this.height/2,GameSize.WALL_WIDTH,height);
                g.drawImage(openDoorImage,GameSize.GAME_WIDTH - GameSize.WALL_WIDTH,GameSize.GAME_HEIGHT/2 - this.height/2,null);
            }
        }



    }




    public void initialiseOPenImage() {
        Image img = new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

        try {
            if(this.position == 0) {
                img = ImageIO.read(ImageLoader.class.getResource(("/openDoorTop.png")));
            }
            if(this.position == 1) {
                img = ImageIO.read(ImageLoader.class.getResource(("/openDoorLeft.png")));
            }
            if(this.position == 2) {
                img = ImageIO.read(ImageLoader.class.getResource(("/openDoorDown.png")));
            }
            if(this.position == 3) {
                img = ImageIO.read(ImageLoader.class.getResource(("/openDoorRight.png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            openDoorImage = img;
        }
    }


    public void initialiseClosedImage() {
        Image img = new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

        try {
            if(this.position == 0) {
                img = ImageIO.read(ImageLoader.class.getResource(("/closedDoorTop.png")));
            }
            if(this.position == 1) {
                img = ImageIO.read(ImageLoader.class.getResource(("/closedDoorRight.png")));
            }
            if(this.position == 2) {
                img = ImageIO.read(ImageLoader.class.getResource(("/closedDoorDown.png")));
            }
            if(this.position == 3) {
                img = ImageIO.read(ImageLoader.class.getResource(("/closedDoorLeft.png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            closedDoorImage = img;
        }
    }
}
