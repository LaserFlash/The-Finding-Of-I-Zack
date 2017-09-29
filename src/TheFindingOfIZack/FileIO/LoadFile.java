package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Level;
import TheFindingOfIZack.World.Rooms.Room;

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
        Game g = readGame(in);
        Player p = readPlayer(in);
        Level l = readLevel(in);
        Room r = readRoom(in);

        return new Game(g,p,l,r);
    }

    // TODO: 9/26/17 Implement this
    public Game getGame(){
        return game;
    }
}
