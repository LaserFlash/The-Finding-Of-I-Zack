package TheFindingOfIZack.View;

import TheFindingOfIZack.Controller.GameController;
import java.util.Observable;

/**
 * Manage the view for the game.
 * Handles what needs to be drawn and the GUI
 */
public class ViewManager implements java.util.Observer {

    /**
     * Initialise the ViewManager.
     * Takes a controller as an argument in order to create key bindings
     * @param controller
     */
    public ViewManager(GameController controller){
        //Create key bindings
    }

    @Override
    public void update(Observable observable, Object o) {
        //Draw stuff here
        //Or find what needs to be drawn
    }
}
