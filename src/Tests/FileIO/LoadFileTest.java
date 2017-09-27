package Tests.FileIO;

import TheFindingOfIZack.FileIO.GameFile;
import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import org.junit.Test;

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
            LoadFile testLoadFile = new LoadFile();
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
    }
}
