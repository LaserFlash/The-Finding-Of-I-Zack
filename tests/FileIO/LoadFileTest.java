package FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.World.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by woodjess3 on 27/09/17.
 */
public class LoadFileTest {

    /**
     *  This test ensures that a LoadFile can be created
     */
    @Test
    public void test01_createLoadFile(){
        try {
            SaveFile saveFile = new SaveFile(new Game(new Player(new Point(GameSize.GAME_WIDTH /2 - 20,GameSize.GAME_HEIGHT/2 - 20))));
            LoadFile testLoadFile = new LoadFile();
            assertTrue("There should be a game",testLoadFile.getGame() != null);
            assertTrue("There should be a player",testLoadFile.getGame().getPlayer() != null);
            assertTrue("Player Health should be 999",testLoadFile.getGame().getPlayer().getMaxHealth() == 999 );
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }


    }
}
