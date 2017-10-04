package TheFindingOfIZack.View;

import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observer;

public abstract class View extends JFrame implements Observer {

    public View(String name){
        super(name);
    }
    public abstract void showGUI();

    public abstract void addControllerForButtons(ActionListener controller);

    public abstract void goToGameView();

    public abstract void goToMenuView();

    public abstract void newGame(Model model);

    public abstract void enableOtherButtons();
}