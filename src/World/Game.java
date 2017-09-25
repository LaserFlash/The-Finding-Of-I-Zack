package World;

import Entities.Player;

import FileIO.GameFile;
import World.Rooms.Room;
import World.Rooms.startRoom;

import java.util.Observable;

/**
 * Created by fieldryan on 19/09/17.
 * Stores all the objects within the game world
 */
public class Game extends Observable{

    private Player player;
    private Level currentLevel;


    /**
     * Constructor for creating a brand new game from the beginning
     * @param p
     */
    public Game(Player p){
        this.player = p;

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
        notifyObservers();

    }

    public void createLevelOne(){
        this.currentLevel = new Level();
        Room start = new startRoom();
        currentLevel.addRoom(start);

    }





}
