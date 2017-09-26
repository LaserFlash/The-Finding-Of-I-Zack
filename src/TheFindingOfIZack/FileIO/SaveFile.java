package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.World.Game;

/**
 *  This class captures the notion of Saving a GameFile, it deals with storing
 *  all of the different components of a Game, and translating them all into a
 *  GameFile, that stores the Game in its current state. This class will return
 *  a GameFile, made from the current game.
 */
public class SaveFile extends GameFile {

    private Game game;

    public SaveFile(Game g){
        game = g;
        execute();
    }

    /**
     *  This method chooses the File to saved, and verifies
     *  the integrity of the .ZACK file
     */
    public void execute(){
        boolean isValidFile = saveFile(parent);
        if (!isValidFile)
            return;
        createOut();
    }

}
