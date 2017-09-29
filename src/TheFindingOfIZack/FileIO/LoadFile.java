package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;

/**
 *  This class captures the notion of Loading a game file, it deals with
 *  interpreting the GameFile, and recreating the game that was stored.
 *  The class will return the Game, stored in the GameFile.
 */
public class LoadFile extends GameFile{

    private Game game = null;

    public LoadFile() throws InvalidFileException {
        execute();
    }

    /**
     *  This method chooses the File to be loaded, and verifies
     *  the integrity of the .ZACK file
     */
    public Game execute(){
        boolean isValidFile = openFile(parent);
        if (!isValidFile)
            return null;
        createIn();
        //readHeader();
        game = readGame(in);
        return null; // TODO: 9/26/17 return a game if successful
    }

    // TODO: 9/26/17 Implement this
    public Game getGame(){
        return game;
    }
}
