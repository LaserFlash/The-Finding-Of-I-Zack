import TheFindingOfIZack.Controller.GameController;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        Player player = new Player(100,new Point(0,0));
        Game game = new Game(player);
        GameController controller = new GameController(game);
        ViewManager view = new ViewManager(controller);




    }
}
