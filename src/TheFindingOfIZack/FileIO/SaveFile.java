package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Level;
import TheFindingOfIZack.World.Rooms.Room;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *  This class captures the notion of Saving a GameFile, it deals with storing
 *  all of the different components of a Game, and translating them all into a
 *  GameFile, that stores the Game in its current state. This class will return
 *  a GameFile, made from the current game.
 */
public class SaveFile extends GameFile {

    private Game game;

    public SaveFile(Game g) throws InvalidFileException{
        //if (game == null)
            //throw new InvalidFileException("Null pointer to Game");
        game = g;
        execute(game);
    }

    /**
     *  This method chooses the File to saved, and verifies
     *  the integrity of the .ZACK file
     */
    public void execute(Game g){
        ObjectOutputStream obOut = null;
        boolean isValidFile = saveFile(parent);
        if (!isValidFile)
            return;
        try {
            obOut = new ObjectOutputStream(new FileOutputStream(file+EXTENSION));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (obOut != null) {
            writeGame(g, obOut);
            writePlayer(g, obOut);
            writeLevel(g, obOut);
            writeRoom(g, obOut);
            try {
                obOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeLevel(Game g, ObjectOutputStream obOut){
        Level l = g.getCurrentLevel();

        try {
            obOut.writeObject(l);
            System.out.printf(" Room Serialized data is saved in " + file.getName()  + EXTENSION + "\n");
        }   catch(IOException i) {
            GameFile.fileError("Writing Room: " + i.getLocalizedMessage());
        }
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
    public void writeGame(Game g, ObjectOutputStream obOut) {
        Game e = new Game(new Player(new Point(0,0)));

        try {
            obOut.writeObject(e);
            System.out.printf("Game Serialized data is saved in " + file.getName() + EXTENSION + "\n");
        }   catch(IOException i) {
            i.printStackTrace();
        }
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
        //chooser.setFileFilter(filter);

        // Sets the current directory to make navigation easier
        chooser.setCurrentDirectory(new File(DIRECTORY));

        int returnVal = chooser.showSaveDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("saving...");
            try {
                file = chooser.getSelectedFile();
                return true;
            } catch (Exception ex) {
                fileError("Invalid saveFile " + ex.getLocalizedMessage());
            }
        }
        fileError("Invalid File chosen");
        return false;
    }
}
