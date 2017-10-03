package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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

    /**
     * Create the panel and related buttons
     */
    public StartScreenPanel(){
        super();
        this.newGame = buildButton("New Game", "newGame");
        this.add(newGame);

        this.loadGame = buildButton("Load", "loadGame");
        this.add(loadGame);

        this.saveGame = buildButton("Save","saveGame");
        this.saveGame.setEnabled(false);
        this.add(saveGame);

        this.resumeGame = buildButton("Resume","resumeGame");
        this.resumeGame.setEnabled(false);
        this.add(resumeGame);

        this.exitGame = buildButton("Exit","exitGame");
        this.add(exitGame);


        this.setVisible(true);
    }

    private JButton buildButton(String name,String command){
        JButton b = new JButton(name,ImageLoader.normalButton);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.CENTER);
        b.setDisabledIcon(ImageLoader.disabledButton);
        b.setRolloverIcon(ImageLoader.hoverButton);
        b.setForeground(Color.lightGray);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);

        b.setActionCommand(command);

        return b;
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
        g.drawImage(ImageLoader.startBG, 0, 0, null);
        g.drawImage(ImageLoader.controls,GameSize.WINDOW_WIDTH/2 - 330/2,GameSize.WINDOW_HEIGHT / 3 * 2,null);
    }

    public void enableOtherButtons(){
        this.resumeGame.setEnabled(true);
        this.saveGame.setEnabled(true);
    }
}
