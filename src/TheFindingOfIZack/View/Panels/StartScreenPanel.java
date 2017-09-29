package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.Util.GameSize;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
    private final JButton saveGame;
    private final JButton resumeGame;
    private final JButton exitGame;

    private Image img;

    /**
     * Create the panel and related buttons
     */
    public StartScreenPanel(){
        super();
        this.newGame = new JButton("New Game");
        this.newGame.setActionCommand("newGame");
        this.add(newGame);

        this.loadGame = new JButton("Load");
        this.loadGame.setActionCommand("loadGame");
        this.add(loadGame);

        this.saveGame = new JButton("Save");
        this.saveGame.setActionCommand("saveGame");
        this.saveGame.setEnabled(false);
        this.add(saveGame);

        this.resumeGame = new JButton("Resume");
        this.resumeGame.setActionCommand("resumeGame");
        this.resumeGame.setEnabled(false);
        this.add(resumeGame);

        this.exitGame = new JButton("Exit");
        this.exitGame.setActionCommand("exitGame");
        this.add(exitGame);

        this.setVisible(true);
        try {
            this.img = ImageIO.read(getClass().getResource(("/jesse.png"))).getScaledInstance(GameSize.WINDOW_WIDTH,GameSize.WINDOW_HEIGHT,Image.SCALE_DEFAULT);
            //this.add(new JLabel(new ImageIcon(img)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {
        newGame.addActionListener(controller);
        loadGame.addActionListener(controller);
        saveGame.addActionListener(controller);
        resumeGame.addActionListener(controller);
        exitGame.addActionListener(controller);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    public void enableOtherButtons(){
        this.resumeGame.setEnabled(true);
        this.saveGame.setEnabled(true);
    }
}
