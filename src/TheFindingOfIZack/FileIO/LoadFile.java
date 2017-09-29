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
        Game g = null;
        boolean isValidFile = openFile(parent);
        if (!isValidFile)
            return null;
        try {
            obIn = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (obIn != null) {
            g = readGame(obIn);
            Player p = readPlayer(obIn);
            Level l = readLevel(obIn);
            Room r = readRoom(obIn);
            try {
                obIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            g = new Game(g, p, l, r);
        } else
            throw new InvalidFileException("Failed to Load");
        return g;
    }
    public Room readRoom(ObjectInput obIn){
        Room r = null;
        try {
            try {
                r = (Room) obIn.readObject();
            } catch (ClassNotFoundException e) {
                fileError("Reading Level: " + e.getLocalizedMessage());
            }

        } catch (IOException e) {
            fileError("Reading Level: " + e.getLocalizedMessage());
        }
        return r;
    }

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
    public void writePlayer(Game g, ObjectOutputStream obOut){
        Player p = g.getPlayer();

        try {
            obOut.writeObject(p);
            System.out.printf(" Player Serialized data is saved in " + file.getName() + EXTENSION + "\n");
        }   catch(IOException i) {
            fileError("Writing player" + i.getLocalizedMessage());
        }
    }

    public Room writeRoom(Game g, ObjectOutputStream obOut){
        Room r = g.getPlayer().getRoom();
        try {
            obOut.writeObject(r);
            System.out.printf(" Room Serialized data is saved in " + file.getName() + EXTENSION + "\n");
        }   catch(IOException i) {
            fileError("Writing Room: " + i.getLocalizedMessage());
        }
        return r;
    }

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
    public boolean openFile(JFrame parent) {
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
        return false;
    }

    // TODO: 9/26/17 Implement this
    public Game getGame(){
        return game;
    }
}
