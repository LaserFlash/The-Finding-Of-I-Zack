package TheFindingOfIZack.Controller;


import TheFindingOfIZack.FileIO.LoadFile;
import TheFindingOfIZack.FileIO.SaveFile;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.Util.CreateGameModel;
import TheFindingOfIZack.View.View;
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

    private View view;
    private Model game;

    /**
     *  Create a controller for the game
     *  Manage interaction between the view and game model
     * @param view  game view
     * @param game  game model
     */
    public GameController(View view, Model game){
        this.view = view;
        this.game = game;
        view.addKeyListener(this);
        view.addControllerForButtons(this);
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
                try {
                    this.game = loadedGame.getGame();
                } catch (InvalidFileException e1) {
                    break; // No game was loaded successfully
                }
                this.view.newGame(game);
                this.view.enableOtherButtons();
                this.view.goToGameView();
                break;
            }
            case "saveGame" :{
                try {
                    SaveFile saveGame = new SaveFile((Game) this.game);
                } catch (InvalidFileException e1) {
                    System.out.printf("Unsuccessful Save\n");
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
        }

        checkPlayerActions(e,true);

    }
    @Override
    public void keyReleased(KeyEvent e) {
        checkPlayerActions(e,false);
    }

    /**
     * Call methods on model when player controls are used
     * @param e The key that was pressed
     * @param b boolean stating if key is stopping or starting action
     */
    private void checkPlayerActions(KeyEvent e,boolean b){
        switch (e.getKeyCode()){
            /* Player movement controls */
            case KeyEvent.VK_W:
                this.game.moveUp(b);
                break;
            case KeyEvent.VK_S:
                this.game.moveDown(b);
                break;
            case KeyEvent.VK_A:
                this.game.moveLeft(b);
                break;
            case KeyEvent.VK_D:
                this.game.moveRight(b);
                break;

            /* Shooting controls */
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                this.game.shootLeft(b);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                this.game.shootRight(b);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                this.game.shootUp(b);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                this.game.shootDown(b);
                break;
        }
    }
}
