import Controller.GameController;
import Entities.Player;
import View.ViewManager;
import World.Game;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        Player player = new Player(100,new Point(0,0));
        Game game = new Game(player);
        ViewManager view = new ViewManager();
        game.addObserver(view);

        GameController controller = new GameController(view,game);

        view.addControllerForButtons(controller);

        controller.showGUI();





    }
}
