package TheFindingOfIZack.View;

import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.Panels.GamePanel;
import TheFindingOfIZack.View.Panels.ScreenPanel;
import TheFindingOfIZack.View.Panels.StartScreenPanel;
import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Manage the view for the game.
 * Handles what needs to be drawn and the GUI
 */
public class ViewManager extends JFrame implements View, java.util.Observer {

    private Model model;

    private ScreenPanel startScreen;
    private ScreenPanel gameScreen;

    /**
     * Initialise the ViewManager.
     * Takes a controller as an argument in order to create key bindings
     */
    public ViewManager(Model model) {
        super("The Finding of I, Zack");
        setPreferredSize(new Dimension(GameSize.WINDOW_WIDTH,GameSize.WINDOW_HEIGHT));
        this.model = model;
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.startScreen = new StartScreenPanel();
        this.gameScreen = new GamePanel(this.model);

        this.add(this.startScreen);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void update(Observable observable, Object o) {
        gameScreen.repaint();
    }

    /**
     * Make the GUI visible
     * Separate method so the GUI can be made visible when all setup is finished
     */
    public void showGUI() {
        this.setVisible(true);
    }

    /**
     * Provide ActionListener to any buttons present in the GUI
     *
     * @param controller
     */
    public void addControllerForButtons(ActionListener controller) {
        this.startScreen.addControllerForButtons(controller);
        this.gameScreen.addControllerForButtons(controller);
    }

    /**
     * Change the view to displaying the game world
     */
    public void goToGameView() {
        this.remove(startScreen);
        this.add(gameScreen);
        this.repaint();
    }

    /**
     * Change the view to displaying the menu
     */
    public void goToMenuView() {
        this.remove(gameScreen);
        this.add(startScreen);
        this.repaint();
    }

    @Override
    public void repaint(){
        Toolkit.getDefaultToolkit().sync();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * Replace the game panel with a new one
     * Intended to be used when loading or replacing the game model
     * @param game the game model to be used
     */
    public void newGame(Model game) {
        this.gameScreen = new GamePanel(game);
    }
}