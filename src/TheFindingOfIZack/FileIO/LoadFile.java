package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.World.Game;

/**
 *  This class captures the notion of Loading a game file, it deals with
 *  interpreting the GameFile, and recreating the game that was stored.
 *  The class will return the Game, stored in the GameFile.
 */
public class LoadFile {

    private GameFile gameFile;
    private Game game;

    public LoadFile(GameFile gf) {
        gameFile = gf;
    }
}
