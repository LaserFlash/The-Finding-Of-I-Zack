package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Level;
import TheFindingOfIZack.World.Rooms.Room;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 *  This class captures the notion of Loading a game file, it deals with
 *  interpreting the GameFile, and recreating the game that was stored.
 *  The class will return the Game, stored in the GameFile.
 */
public class LoadFile extends GameFile{

    private Game game = null;

    public LoadFile() throws InvalidFileException {
        game = execute();
    }

    /**
     *  This method chooses the File to be loaded, and verifies
     *  the integrity of the .ZACK file
     */
    public Game execute() throws InvalidFileException{
        ObjectInputStream obIn = null;
        Game g;
        boolean isValidFile = openFile(parent);
        if (!isValidFile)
            return null;
        try {
            obIn = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            fileError("Failed to execute: " + e.getLocalizedMessage());
            throw new InvalidFileException("Failed to execute");
        }

        if (obIn == null)
            throw new InvalidFileException("Object Input is null");

        g = readGame(obIn);
        Player p = readPlayer(obIn);
        Level l = readLevel(obIn);
        Room r = readRoom(obIn);
        try {
            obIn.close();
        } catch (IOException e) {
            fileError("Failed to close object reader");
            throw new InvalidFileException("Failed to close object reader");
        }
        g = new Game(g, p, l, r);

        return g;
    }

    /**
     * This method reads the Room object from an
     * @param obIn object input stream
     * @return the loaded Level
     */
    public Room readRoom(ObjectInput obIn){
        Room r = null;
        try {
            r = (Room) obIn.readObject();
        } catch (ClassNotFoundException e) {
            fileError("Reading Level: " + e.getLocalizedMessage());
        } catch (IOException e) {
            fileError("Reading Level: " + e.getLocalizedMessage());
        }
        return r;
    }

    /**
     * This method reads the Level object from an
     * @param obIn object input stream
     * @return the loaded Level
     */
    public Level readLevel(ObjectInput obIn){
        Level l = null;
        try {
            try {
                l = (Level) obIn.readObject();
            } catch (ClassNotFoundException e) {
                fileError("Reading Level: " + e.getLocalizedMessage());
            }

        } catch (IOException e) {
            fileError("Reading Level: " + e.getLocalizedMessage());
        }
        return l;
    }

    /**
     * This method returns the Game object from an
     * @param obIn object input stream
     * @return the loaded Game
     */
    public Game readGame(ObjectInput obIn){
        Game g = null;
        try {
            try {
                g = (Game) obIn.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            fileError("Reading Game: " + e.getLocalizedMessage());
        }
        return g;
    }

    /**
     * This method returns the Player object from an
     * @param obIn object input stream
     * @return the loaded Player
     */
    public Player readPlayer(ObjectInput obIn){
        Player p = null;
        try {
            try {
                p = (Player) obIn.readObject();
            } catch (ClassNotFoundException e) {
                fileError("Reading Player: " + e.getLocalizedMessage());
            }

        } catch (IOException e) {
            fileError("Reading Game: " + e.getLocalizedMessage());
        }
        return p;
    }
    /**
     *  This method allows the user to select a GameFile from their computer
     *  and displays the valid file extensions
     *  @return true if valid, false otherwise
     */
    public boolean openFile(JFrame parent) throws InvalidFileException {
        JFileChooser chooser = new JFileChooser();

        // This method only accepts .txt or .zack file extensions
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ZACK Files", EXTENSION);
        //chooser.setFileFilter(filter);

        // Sets the current directory to make navigation easier
        chooser.setCurrentDirectory(new File(DIRECTORY));

        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("opening...");
            file = chooser.getSelectedFile();
            return true;
        }
        fileError("Invalid File chosen");
        throw new InvalidFileException("No file chosen");
    }

    /**
     * This method returns the Game
     * @return the Game if it exists or
     * @throws InvalidFileException if the Game is null
     */
    public Game getGame() throws InvalidFileException{
        if (this.game == null)
            throw new InvalidFileException("Game is null");
        return game;
    }
}
