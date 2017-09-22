package TheFindingOfIZack.FileIO;

import java.io.*;

/**
 *  This class captures the notion of a GameFile. The file itself will be
 *  a long string of text, this method will make it easier to manipulate, read
 *  and write to that file.
 *
 *  This class handles the various exceptions and errors and exceptions that may be
 *  encountered when reading a file. Abstracting the technical details of TheFindingOfIZack.FileIO
 *
 *  This class should be able to represent the entire state of the game in text, and load the entire
 *  from text. So that the game can be loaded and saved in between launches
 *
 */
public class GameFile {

    /**
     *  This BufferedReader stores the reader that interacts with the files
     */
    private BufferedReader in;

    private BufferedOutputStream out;

    /**
     * This stores the location of the GameFile .txt file to be read or written
     */
    private String fileName = "text.txt";

    /**
     *  This is the the text to be encoded/decoded or saved/loaded
     */
    private String text = "";

    private enum FILE_STATE {
        ENCODED,
        DECODED,
    }

    /**
     *  This stores the state of the text for the GameFile, this determines whether or not
     *  the GameFile can be manipulated at this time
     */
    private FILE_STATE fileState = FILE_STATE.DECODED;

    /**
     *  This creates the ability for this file to be encoded using the Huffman encoding class
     */
    private Huffman huffman = new Huffman(text);

    /**
     *  Ensures that different versions of the game are not saved and loaded
     */
    private String release = "0.0.1";

    /**
     *  This GameFile constructor sets up the BufferedReader for a GameFile to
     *  be read, and the BufferedOutputStream to be written too.
     */
    public GameFile(){
        try {
            in = new BufferedReader(new FileReader(new File(fileName)));
            out = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String readGame(){
        return "";
    }

    public String readLevel(){
        return "";
    }

    public String readRoom(){
        return "";
    }

    public String readEntity(){
        return "";
    }

    public String readItem(){
        return "";
    }


    /**
     *  This method returns true if the end of the file has been reached
     *  @return true if EOF, false otherwise
     */
    public boolean isEOF() {
        boolean result = false;
        try {
            result = in.ready();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        return result;
    }

    /**
     *  This method displays an a TheFindingOfIZack.FileIO error appropriately
     * @param str the error to be displayed
     */
    private static void fileError(String str){
        System.err.print("TheFindingOfIZack.FileIO Error: " + str + "\n");
    }

    /**
     *  This method closes BufferedReader after the GameFile is done with it
     * @return true if successful, false otherwise
     */
    public boolean close(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method encodes the GameFile using the Huffman class
     */
    public void encode(){
        // Only allow encoding if it is decoded
        if (fileState == FILE_STATE.DECODED)
            huffman.encode(text);
    }

    /**
     * This method decodes the GameFile using the Huffman class
     */
    public void decode(){
        //  Only allow decoding if it is encoded
        if (fileState == FILE_STATE.ENCODED)
            huffman.decode(text);
    }

    /**
     *  This  main method is for testing purposes of the class ...
     * @param args the arguments for the main method
     */
    public static void main(String [] args){
        fileError("Error");
    }

}
