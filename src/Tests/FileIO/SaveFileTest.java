package Tests.FileIO;

import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import org.junit.Test;

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
            SaveFile testSaveFile = new SaveFile(null);
        } catch (InvalidFileException e) {
            fail(e.getMessage());
        }
    }
}
