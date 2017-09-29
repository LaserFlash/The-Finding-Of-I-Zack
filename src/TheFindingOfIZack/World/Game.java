package TheFindingOfIZack.World;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.GameFile;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.*;
import com.sun.javafx.tk.Toolkit;

import java.util.Observable;


/**
 * Created by fieldryan on 19/09/17.
 * Stores all the objects within the game world
 */

public class Game extends Observable implements Model,Savable{


    private Player player;
    private Level currentLevel;
    private boolean running = true;
    private int frameCount;
    private boolean north;
    private boolean east;
    private boolean south;
    private boolean west;
    private boolean paused = false;


    /**
     * Constructor for creating a brand new game from the beginning
     * @param p
     */
    public Game(Player p){
        this.player = p;
        this.frameCount = 0;
        this.north = false;
        this.east = false;
        this.south = false;
        this.west = false;

    }

    /**
     *Constructor for loading a new game from a file
     * @param file
     */
    public Game(GameFile file){

    }

    /**
     * begins the logic of a brand new game
     */
    public void beginNewGame(){
        createLevelOne();
        runGameLoop();
    }

    /**
     * resumes the logic of a game loaded from a file
     */
    public void resumeGame(){

    }

    @Override
    public void pauseGame() {

    }

    /**
     *
     * @return the the player object
     */
    public Player getPlayer(){

        return this.player;
    }


    public void moveUp() {

        player.moveUp();
        drawGame();
    }


    public void moveDown() {
    player.moveDown();
        drawGame();
    }

    public void moveRight() {
    player.moveRight();
        drawGame();
    }

    public void moveLeft() {
    player.moveLeft();
        drawGame();
    }

    @Override
    public void trueUp() {
       this.north = true;

    }
    @Override
    public void trueRight() {
        this.east = true;

    }
    @Override
    public void trueDown() {
        this.south = true;

    }
    @Override
    public void trueLeft() {
        this.west = true;

    }

    @Override
    public void falseUp() {
        this.north = false;

    }
    @Override
    public void falseRight() {
        this.east = false;

    }
    @Override
    public void falseDown() {
        this.south = false;

    }
    @Override
    public void falseLeft() {
        this.west = false;

    }



    /**
     * updates all of the components within the game
     */
    public void updateGame(){

        checkMovement();
        currentLevel.update();


    }
    private void checkMovement(){
        if(north){
            moveUp();
        }
        if(east){
            moveRight();
        }
        if(south){
            moveDown();
        }
        if(west){
            moveLeft();
        }
    }

    /**
     * creates the first level of the game
     */
    public void createLevelOne(){
        this.currentLevel = new Level();

        Room r1 = new startRoom();
        Room r2 = new standardRoom();
        Room r3 = new standardRoom();
        Room r4 = new itemRoom();
        Room r5 = new standardRoom();
        Room r6 = new standardRoom();
        Room r7 = new standardRoom();
        Room r8 = new bossRoom();

        //Door connecting rooms 1 and 2
        Door r1t2 = new Door(r1,r2,3);
        r1.addDoor(r1t2,r1t2.getPosition());
        r2.addDoor(r1t2.getOpposite(),r1t2.getOpposite().getPosition());

        //Door connecting rooms 1 and 3
        Door r1t3 = new Door(r1,r3,2);
        r1.addDoor(r1t3,r1t3.getPosition());
        r3.addDoor(r1t3.getOpposite(),r1t3.getOpposite().getPosition());

        //Door connecting rooms 3 and 4
        Door r3t4 = new Door(r3,r4,1);
        r3.addDoor(r3t4,r3t4.getPosition());
        r4.addDoor(r3t4.getOpposite(),r3t4.getOpposite().getPosition());


        //Door connecting rooms 3 and 5
        Door r3t5 = new Door(r3,r5,2);
        r3.addDoor(r3t5,r3t5.getPosition());
        r5.addDoor(r3t5.getOpposite(),r3t5.getOpposite().getPosition());

        //Door connecting rooms 5 and 6
        Door r5t6 = new Door(r5,r6,2);
        r5.addDoor(r5t6,r5t6.getPosition());
        r6.addDoor(r5t6.getOpposite(),r5t6.getOpposite().getPosition());

        //Door connecting rooms 5 and 7
        Door r5t7 = new Door(r5,r7,1);
        r5.addDoor(r5t7,r5t7.getPosition());
        r7.addDoor(r5t7.getOpposite(),r5t7.getOpposite().getPosition());

        //Door connecting rooms 7 and 8
        Door r7t8 = new Door(r7,r8,2);
        r7.addDoor(r7t8,r7t8.getPosition());
        r8.addDoor(r7t8.getOpposite(),r7t8.getOpposite().getPosition());


        player.setRoom(r1);
        currentLevel.addRoom(r1);


    }


    /**
     * begin the game ticks
      */
    public void runGameLoop() {
        Thread loop = new Thread()
        {
            public void run()
            {
                gameLoop();
            }
        };
        loop.start();
    }


    /**
     * Tick method created by Eli Delventhal at http://www.java-gaming.org/index.php?topic=24220.0
     * Tells the game what to do every tick and ensures everything runs smoothly
     */

    private void gameLoop()
    {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 1;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);


        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;


            if (!paused)
            {
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {

                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
                {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                drawGame();

                lastRenderTime = now;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime)
                {
                   // System.out.println("NEW SECOND " + thisSecond + " " + frameCount);

                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
                {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {Thread.sleep(1);} catch(Exception e) {}

                    now = System.nanoTime();
                }
            }
        }
    }

    /**
     * draws everything within the game
     */
    private void drawGame(){
        this.setChanged();
        notifyObservers();

    }
}
