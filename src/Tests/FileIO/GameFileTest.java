package Tests.FileIO;

import TheFindingOfIZack.FileIO.GameFile;
import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woodjess3 on 27/09/17.
 */
public class GameFileTest {

    /**
     *  This test ensures that a GameFile can be created
     */
    @Test
    public void test01_createGameFile(){
        GameFile testGameFile = new GameFile();
    }


}
