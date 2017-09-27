package TheFindingOfIZack.World;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.View.Drawable;


public interface Model {
    void beginNewGame();

    void resumeGame();

    Player getPlayer();

    void startGameLoop();

    void stopGameLoop();
    
    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();
}
