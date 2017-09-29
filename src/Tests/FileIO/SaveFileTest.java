package Tests.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.World.Game;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.fail;

/**
 * Created by woodjess3 on 27/09/17.
 */
public class SaveFileTest {

    /**
     * This test ensures that a SaveFile can be created
     */
    @Test
    public void test01_createSaveFile(){
        try {
            SaveFile testSaveFile = new SaveFile(new Game(new Player(100,new Point(GameSize.GAME_WIDTH /2 - 20,GameSize.GAME_HEIGHT/2 - 20))));

        } catch (InvalidFileException e) {
            fail(e.getMessage());
        }
    }
}
