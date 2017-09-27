package TheFindingOfIZack.World;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.GameFile;
import TheFindingOfIZack.FileIO.Savable;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.startRoom;
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
        System.out.println("level created");
        runGameLoop();

    }

    /**
     * resumes the logic of a game loaded from a file
     */
    public void resumeGame(){

    }

    /**
     *
     * @return the the player object
     */
    public Player getPlayer(){

        return this.player;
    }

    @Override
    public void startGameLoop() {

    }

    @Override
    public void stopGameLoop() {

    }

    @Override
    public void moveUp() {
        System.out.println("moving up");
        player.moveUp();
        drawGame();
    }

    @Override
    public void moveDown() {
    player.moveDown();
        drawGame();
    }

    @Override
    public void moveRight() {
    player.moveRight();
        drawGame();
    }

    @Override
    public void moveLeft() {
    player.moveLeft();
        drawGame();
    }

    @Override
    public void changeUp() {
        if(this.north){
            this.north = false;
        }else{
            System.out.println("moving up is true");
            this.north = true;

        }

    }

    @Override
    public void changeDown() {
        if(this.south){
            this.south = false;
        }else{
            this.south = true;

        }

    }

    @Override
    public void changeRight() {
        if(this.east){
            this.east = false;
        }else{
            this.east = true;

        }

    }

    @Override
    public void changeLeft() {
        if(this.west){
            this.west = false;
        }else{
            this.west = true;

        }

    }

    /**
     * updates all of the components within the game
     */
    public void updateGame(){
        System.out.println("updating game");
        checkMovement();
        currentLevel.update();


    }
    private void checkMovement(){
        if(north){
            moveUp();
        }
        if(east){
            moveLeft();
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
        Room start = new startRoom();
        player.setRoom(start);
        currentLevel.addRoom(start);

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
                    System.out.println("is it reaching here?");
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
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);

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
