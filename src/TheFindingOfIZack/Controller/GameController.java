package TheFindingOfIZack.Controller;


import TheFindingOfIZack.FileIO.GameFile;
import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Takes user input and converts to actions on the model
 */
public class GameController implements ActionListener, KeyListener {

    private ViewManager view;
    private Game game;

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
                //TODO trigger load from file
                // This sets up a file to be selected and loaded
                LoadFile loadedGame = new LoadFile();
                game = loadedGame.getGame();

                view.goToGameView();
                break;
            }
            case "saveGame" :{
                //TODO trigger saving of game
                SaveFile saveGame = new SaveFile(game);
                break;
            }
            case "resumeGame":{
                //TODO start any lops or threads that need to be resumed
                view.goToGameView();
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            //TODO pause or resume game anf change view accordingly
            view.goToMenuView();
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
