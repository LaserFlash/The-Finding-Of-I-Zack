package TheFindingOfIZack.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Manage the view for the game.
 * Handles what needs to be drawn and the GUI
 */
public class ViewManager extends JFrame implements java.util.Observer{

    private ScreenPanel startScreen;
    private ScreenPanel gameScreen;

    /**
     * Initialise the ViewManager.
     * Takes a controller as an argument in order to create key bindings
     */
    public ViewManager(){
        super("The Finding of Zack");
        this.setFocusable(true);
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.startScreen = new StartScreenPanel();
        this.add(this.startScreen);

        this.pack();
        this.setLocationRelativeTo( null );

        this.gameScreen = new GamePanel();
    }

    @Override
    public void update(Observable observable, Object o) {
        //Draw stuff here
        //Or find what needs to be drawn
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
     * @param controller
     */
    public void addControllerForButtons(ActionListener controller) {
        this.startScreen.addControllerForButtons(controller);
        this.gameScreen.addControllerForButtons(controller);
    }

    /**
     * Change the view to displaying the game world
     */
    public void goToGameView(){
        this.remove(startScreen);
        this.add(gameScreen);
        this.repaint();
    }

    /**
     * Change the view to displaying the menu
     */
    public void goToMenuView(){
        this.remove(gameScreen);
        this.add(startScreen);
        this.repaint();
    }
}
