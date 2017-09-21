package TheFindingOfIZack.View;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The display intended for first landing
 * Has options for the user to:
 *  Start a new game
 *  Load a game
 *  or exit
 */
public class StartScreenPanel extends ScreenPanel {
    private final JButton newGame;
    private final JButton loadGame;
    private final JButton exitGame;

    /**
     * Create the panel and related buttons
     */
    public StartScreenPanel(){
        //TODO make the button and layout look good
        this.newGame = new JButton("New Game");
        this.newGame.setActionCommand("newGame");
        this.add(newGame);

        this.loadGame = new JButton("Load Game");
        this.loadGame.setActionCommand("loadGame");
        this.add(loadGame);

        this.exitGame = new JButton(" Exit");
        this.exitGame.setActionCommand("exitGame");
        this.add(exitGame);
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {
        newGame.addActionListener(controller);
        loadGame.addActionListener(controller);
        exitGame.addActionListener(controller);
    }
}
