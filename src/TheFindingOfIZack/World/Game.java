package TheFindingOfIZack.World;

/**
 * Created by fieldryan on 19/09/17.
 * Stores all the objects within the game world
 */
public class Game {

    private Player player;


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




}
