package TheFindingOfIZack.FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *  This class captures the notion of a GameFile. The file itself will be
 *  a long string of text, this method will make it easier to manipulate, read
 *  and write to that file.
 *
 *  This class handles the various exceptions and errors and exceptions that may be
 *  encountered when reading a file. Abstracting the technical details of TheFindingOfIZack.FileIO
 */
public class GameFile {

    /**
     *  This BufferedReader stores the reader that interacts with the files
     */
    private BufferedReader br;

    /**
     *  This GameFile constructor sets up the BufferedReader for a GameFile to
     *  be manipulated, read and written too.
     */
    public GameFile(){
        try {
            br = new BufferedReader(new FileReader(new File("text.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  This method displays an a TheFindingOfIZack.FileIO error appropriately
     * @param str the error to be displayed
     */
    private static void fileError(String str){
        System.err.print("TheFindingOfIZack.FileIO Error: " + str + "\n");
    }

    /**
     *  This  main method is for testing purposes of the class ...
     * @param args the arguments for the main method
     */
    public static void main(String [] args){
        fileError("Error");
    }

}
