package TheFindingOfIZack.Controller;


import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Takes user input and converts to actions on the model
 */
public class GameController implements ActionListener, KeyListener {

    private ViewManager view;
    private Model game;

    /**
     *  Create a controller for the game
     *  Manage interaction between the view and game model
     * @param view  game view
     * @param game  game model
     */
    public GameController(ViewManager view, Game game){
        this.view = view;
        this.game = game;
        view.addKeyListener(this);
    }

    /**
     * Ask the view to create the GUI and render itself
     */
    public void showGUI(){
        view.showGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "exitGame": {
                this.view.dispose();
                //TODO stop any other threads / gameloops
                break;
            }
            case "newGame": {
                this.game.beginNewGame();
                view.goToGameView();
                break;
            }
            case "loadGame":{
                /* This sets up a file to be selected and loaded */
                LoadFile loadedGame = null;
                try {
                    loadedGame = new LoadFile();
                } catch (InvalidFileException e1) {
                    System.out.print("Unsuccessful Load: " + e1.getMessage());
                }
                game = loadedGame.getGame();
                view.newGame(game);
                view.goToGameView();
                break;
            }
            case "saveGame" :{
                try {
                    SaveFile saveGame = new SaveFile( (Game) this.game);
                } catch (InvalidFileException e1) {
                    System.out.printf("Unsuccessful Save");
                }
                //TODO trigger write to file
                break;
            }
            case "resumeGame":{
                //TODO start any loops or threads that need to be resumed
                game.resumeGame();
                view.goToGameView();
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                game.pauseGame();
                view.goToMenuView();
                break;
            case KeyEvent.VK_W:
                game.trueUp();
                break;
            case KeyEvent.VK_S:
                game.trueDown();
                break;
            case KeyEvent.VK_A:
                game.trueLeft();
                break;
            case KeyEvent.VK_D:
                game.trueRight();
                break;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                game.falseUp();
                break;
            case KeyEvent.VK_S:
                game.falseDown();
                break;
            case KeyEvent.VK_A:
                game.falseLeft();
                break;
            case KeyEvent.VK_D:
                game.falseRight();
                break;
        }
    }
}
