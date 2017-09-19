package TheFindingOfIZack.FileIO;

import java.io.*;

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

    private enum FILE_STATE {
        ENCODED,
        DECODED,
    }

    private FILE_STATE fileState;

    private Huffman encoding;

    /**
     *  This GameFile constructor sets up the BufferedReader for a GameFile to
     *  be manipulated, read and written too.
     */
    public GameFile(){

        encoding = new Huffman(this);

        try {
            br = new BufferedReader(new FileReader(new File("text.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  This method returns true if the end of the file has been reached
     *  @return true if EOF, false otherwise
     */
    public boolean isEOF() {
        boolean result = false;
        try
        {
            result = br.ready();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        return result;
    }

    /**
     *  This method closes BufferedReader after the GameFile is done with it
     * @return true if successful, false otherwise
     */
    public boolean close(){
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *  This method displays an a TheFindingOfIZack.FileIO error appropriately
     * @param str the error to be displayed
     */
    private static void fileError(String str){
        System.err.print("TheFindingOfIZack.FileIO Error: " + str + "\n");
    }

    /**
     * This method encodes the GameFile using the Huffman class
     */
    public void encode(){
        // Only allow encoding if it is decoded
        if (fileState == FILE_STATE.DECODED)
            encoding.encode();
    }

    /**
     * This method decodes the GameFile using the Huffman class
     */
    public void decode(){
        //  Only allow decoding if it is encoded
        if (fileState == FILE_STATE.ENCODED)
            encoding.decode();
    }

    /**
     *  This  main method is for testing purposes of the class ...
     * @param args the arguments for the main method
     */
    public static void main(String [] args){
        fileError("Error");
    }

}
