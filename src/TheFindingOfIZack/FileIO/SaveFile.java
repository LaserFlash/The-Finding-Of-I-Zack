package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *  This class captures the notion of Saving a GameFile, it deals with storing
 *  all of the different components of a Game, and translating them all into a
 *  GameFile, that stores the Game in its current state. This class will return
 *  a GameFile, made from the current game.
 */
public class SaveFile extends GameFile {

    private Game game;

    public SaveFile(Game g) throws InvalidFileException{
        //if (game == null)
            //throw new InvalidFileException("Null pointer to Game");
        game = g;
        execute(game);
    }

    /**
     *  This method chooses the File to saved, and verifies
     *  the integrity of the .ZACK file
     */
    public void execute(Game g){
        boolean isValidFile = saveFile(parent);
        if (!isValidFile)
            return;
        createOut();
        writeHeader(out);
        writeGame(g, out);
    }

}
