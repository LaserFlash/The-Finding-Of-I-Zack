package TheFindingOfIZack.Controller;


import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.Util.CreateGameModel;
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
                break;
            }
            case "newGame": {
                this.game = CreateGameModel.newGame(this.view);
                this.game.beginNewGame();
                this.game.pauseGame();
                this.view.newGame(game);
                this.view.enableOtherButtons();
                this.view.goToGameView();
                this.game.resumeGame();
                break;
            }
            case "loadGame":{
                /* This sets up a file to be selected and loaded */
                LoadFile loadedGame = null;
                try {
                    loadedGame = new LoadFile();
                } catch (InvalidFileException e1) {
                    break; //No file was loaded successfully
                }
                this.game = loadedGame.getGame();
                this.view.newGame(game);
                this.view.enableOtherButtons();
                this.view.goToGameView();
                break;
            }
            case "saveGame" :{
                try {
                    SaveFile saveGame = new SaveFile((Game) this.game);
                } catch (InvalidFileException e1) {
                    System.out.printf("Unsuccessful Save");
                }
                break;
            }
            case "resumeGame":{
                this.game.resumeGame();
                this.view.goToGameView();
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
                this.game.pauseGame();
                this.view.goToMenuView();
                break;
            case KeyEvent.VK_W:
                this.game.trueUp();
                break;
            case KeyEvent.VK_S:
                this.game.trueDown();
                break;
            case KeyEvent.VK_A:
                this.game.trueLeft();
                break;
            case KeyEvent.VK_D:
                this.game.trueRight();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                this.game.shootLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                this.game.shootRight();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                this.game.shootUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                this.game.shootDown();
                break;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                this.game.falseUp();
                break;
            case KeyEvent.VK_S:
                this.game.falseDown();
                break;
            case KeyEvent.VK_A:
                this.game.falseLeft();
                break;
            case KeyEvent.VK_D:
                this.game.falseRight();
                break;
        }
    }
}
