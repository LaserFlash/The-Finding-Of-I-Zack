package TheFindingOfIZack.Controller;


import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
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
                LoadFile loadedGame = new LoadFile();
                game = loadedGame.getGame();
                view.newGame(game);
                view.goToGameView();
                break;
            }
            case "saveGame" :{
                SaveFile saveGame = new SaveFile((Game)game);
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
                game.stopGameLoop();
                view.goToMenuView();
            case KeyEvent.VK_W:
                game.moveUp();
                break;
            case KeyEvent.VK_S:
                game.moveDown();
                break;
            case KeyEvent.VK_A:
                game.moveLeft();
                break;
            case KeyEvent.VK_D:
                game.moveRight();
                break;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
