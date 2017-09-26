package TheFindingOfIZack.View;

import TheFindingOfIZack.World.Model;

import java.awt.event.ActionListener;

public interface View {
    void showGUI();

    void addControllerForButtons(ActionListener controller);

    void goToGameView();

    void goToMenuView();

    void newGame(Model model);
}