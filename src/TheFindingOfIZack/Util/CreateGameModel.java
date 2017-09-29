package TheFindingOfIZack.Util;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Model;

import java.awt.*;

public class CreateGameModel {

    public static Model newGame(ViewManager v ){
        Game g = new Game(new Player(100,new Point(GameSize.GAME_WIDTH /2 - 20,GameSize.GAME_HEIGHT/2 - 20)));
        g.addObserver(v);
        return g;
    }
}
