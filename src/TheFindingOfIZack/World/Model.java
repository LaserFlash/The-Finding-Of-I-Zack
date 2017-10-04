package TheFindingOfIZack.World;

import TheFindingOfIZack.Entities.Player;

public interface Model {
    void beginNewGame();
    Player getPlayer();
    void resumeGame();
    void pauseGame();

    void moveUp(boolean b);
    void moveDown(boolean b);
    void moveLeft(boolean b);
    void moveRight(boolean b);

    void shootLeft(boolean b);
    void shootRight(boolean b);
    void shootUp(boolean b);
    void shootDown(boolean b);
}
