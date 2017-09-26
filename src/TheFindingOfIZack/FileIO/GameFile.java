package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Entity;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Level;
import TheFindingOfIZack.World.Rooms.Room;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
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
    private String fileName = "";

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

    private JFrame parent =
            new ViewManager(new Game(new Player(100, new Point(0,0))));

    /**
     *  This stores the files extension for all of the GameFiles
     */
    protected static final String EXTENSION = ".zack";

    /**
     * This stores the directory for all of the .zack GameFiles
     */
    // TODO: 9/26/17 Change this to YOUR directory for save files
    protected static final String DIRECTORY = "/home/rocktopus/Documents/tri2/swen222/IZack/saves";

    /**
     *  This GameFile constructor sets up the BufferedReader for a GameFile to
     *  be read, and the BufferedOutputStream to be written too.
     */
    public GameFile(){
        //boolean isValidFile = openFile(parent);
        boolean isValidFile = openFile(parent);
        if (!isValidFile)
            return;
        try {
            System.out.printf("filename: " + fileName + "\n");
            in = new BufferedReader(new FileReader(new File(fileName)));
            out = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            exit(in, out);
        } catch (FileNotFoundException e) {
            fileError("Creating GameFile " + e.getLocalizedMessage());
        }
        System.exit(0);
    }

    /**
     *  This method allows the user to select a GameFile from their computer
     *  and displays the valid file extensions
     *  @return true if valid, false otherwise
     */
    public boolean openFile(JFrame parent){
        JFileChooser chooser = new JFileChooser();

        // This method only accepts .txt or .zack file extensions
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ZACK Files", EXTENSION);
        chooser.setFileFilter(filter);

        // Sets the current directory to make navigation easier
        chooser.setCurrentDirectory(new File(DIRECTORY));

        int returnVal = chooser.showOpenDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            fileName = chooser.getSelectedFile().getName();
            return true;
        }
        fileError("Invalid File choosen");
        return false;
    }

    /**
     *  This method allows the user to select a GameFile from their computer
     *  and displays the valid file extensions
     *  @return true if valid, false otherwise
     */
    public boolean saveFile(JFrame parent){
        JFileChooser chooser = new JFileChooser();

        // This method only accepts .zack or .txt file extensions
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ZACK Files", EXTENSION);
        chooser.setFileFilter(filter);

        // Sets the current directory to make navigation easier
        chooser.setCurrentDirectory(new File(DIRECTORY));

        int returnVal = chooser.showSaveDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                fileName = chooser.getSelectedFile() + EXTENSION;

                FileWriter fw = new FileWriter(fileName);
                fw.write("test"); // TODO: 9/26/17 implement this
                fw.close(); // close will automatically flush

                return true;
            } catch (Exception ex) {
                fileError("Invalid saveFile " + ex.getLocalizedMessage());
            }
        }
        fileError("Invalid File chosen");
        return false;
    }

    public String readGame(BufferedReader in){
        if (isEOF(in))
            fileError("EOF reached in readGame");
        return null;
    }

    public String readLevel(BufferedReader in){
        if (isEOF(in))
            fileError("EOF reached in readLevel");
        return null;
    }

    public String readRoom(BufferedReader in){
        if (isEOF(in))
            fileError("EOF reached in readRoom");
        return null;
    }

    public String readEntity(BufferedReader in){
        if (isEOF(in))
            fileError("EOF reached in readEntity");
        return null;
    }

    public String readItem(BufferedReader in){
        if (isEOF(in))
        fileError("EOF reached in readItem");
        return null;
    }

    public Game writeGame(BufferedOutputStream out) {
        return null;
    }

    public Level writeLevel(BufferedOutputStream out) {
        return null;
    }

    public Room writeRoom(BufferedOutputStream out) {
        return null;
    }

    public Entity writeEntity(BufferedOutputStream out) {
        return null;
    }


    /**
     *  This method returns true if the end of the file has been reached
     *  @return true if EOF, false otherwise
     */
    public boolean isEOF(BufferedReader in) {
        boolean isEOF = false;
        try {
            isEOF = in.ready();
        }
        catch (IOException e)
        {
            //  The end of file has been reached
            fileError("End-of-file");
            isEOF = true;
        }
        return isEOF;
    }

    /**
     *  This method displays an a TheFindingOfIZack.FileIO error appropriately
     * @param str the error to be displayed
     */
    private void fileError(String str){
        System.err.print("TheFindingOfIZack.FileIO Error: " + str + "\n");
    }

    /**
     *  This method closes BufferedReader after the GameFile is done with it
     * @return true if successful, false otherwise
     */
    public boolean close(BufferedReader in){
        try {
            in.close();
        } catch (IOException e) {
            fileError("Failed to close BufferedReader");
            return false;
        }
        return true;
    }

    /**
     *  This method closes BufferedReader after the GameFile is done with it
     * @return true if successful, false otherwise
     */
    public boolean exit(BufferedReader in, BufferedOutputStream out){
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            fileError("Failed to close BufferedReader");
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
     *  Returns the JFrame component that the dialogue boxes
     *  for saving and loading .ZACK files is opened from
     * @return
     */
    public JFrame getParent(){  return parent; }

    /**
     *  This  main method is for testing purposes of the class ...
     * @param args the arguments for the main method
     */
    public static void main(String [] args){
        new GameFile();
    }

}
