package TheFindingOfIZack.World;


import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.GameFile;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.startRoom;

import java.util.Observable;

/**
 * Created by fieldryan on 19/09/17.
 * Stores all the objects within the game world
 */
public class Game extends Observable{

    private Player player;
    private Level currentLevel;


    private int frameCount;





    /**
     * Constructor for creating a brand new game from the beginning
     * @param p
     */
    public Game(Player p){
        this.player = p;
        this.frameCount = 0;

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
        updateGame();

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

    /**
     * updates all of the components within the game
     */
    public void updateGame(){
        currentLevel.update();


    }

    public void createLevelOne(){
        this.currentLevel = new Level();
        Room start = new startRoom();
        currentLevel.addRoom(start);

    }



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

    //game loop method found online:
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

        boolean running = false;
        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            boolean paused = false;
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

    private void drawGame(){
        notifyObservers();
    }


}
