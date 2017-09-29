package TheFindingOfIZack;

import TheFindingOfIZack.Controller.GameController;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        Player player = new Player(100,new Point(GameSize.GAME_WIDTH /2 - 20,GameSize.GAME_HEIGHT/2 - 20));
        Game game = new Game(player);
        ViewManager view = new ViewManager(game);
        game.addObserver(view);

        GameController controller = new GameController(view,game);

        view.addControllerForButtons(controller);
        controller.showGUI();

    }
}
