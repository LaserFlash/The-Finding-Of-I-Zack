package View;

import Controller.GameController;

import java.util.Observable;

public class ViewManager implements java.util.Observer {

    /**
     * Initialise the ViewManager.
     * Takes a controller as an argument in order to create key bindings
     * @param controller
     */
    public ViewManager(GameController controller){

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
