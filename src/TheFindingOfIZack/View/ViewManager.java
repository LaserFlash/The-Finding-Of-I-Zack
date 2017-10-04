package TheFindingOfIZack.View;

import TheFindingOfIZack.Util.GameSize;
import TheFindingOfIZack.View.Panels.GamePanel;
import TheFindingOfIZack.View.Panels.ScreenPanel;
import TheFindingOfIZack.View.Panels.StartScreenPanel;
import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Manage the view for the game.
 * Handles what needs to be drawn and the GUI
 */
public class ViewManager extends View {

    private Model model;

    private ScreenPanel startScreen;
    private ScreenPanel gameScreen;

    /**
     * Initialise the ViewManager.
     * Takes a controller as an argument in order to create key bindings
     */
    public ViewManager(Model model) {
        super("The Finding of I, Zack");
        setUIFont (new javax.swing.plaf.FontUIResource(new Font("ComicSans", Font.BOLD, 18)));
        setPreferredSize(new Dimension(GameSize.WINDOW_WIDTH,GameSize.WINDOW_HEIGHT));
        this.model = model;
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.startScreen = new StartScreenPanel();
        this.gameScreen = new GamePanel(this.model);

        this.add(this.startScreen);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void update(Observable observable, Object o) {
        gameScreen.repaint();
    }

    @Override
    public void showGUI() {
        this.setVisible(true);
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {
        this.startScreen.addControllerForButtons(controller);
        this.gameScreen.addControllerForButtons(controller);
    }

    @Override
    public void goToGameView() {
        this.remove(startScreen);
        this.add(gameScreen);
        this.repaint();
    }

    @Override
    public void goToMenuView() {
        this.remove(gameScreen);
        this.add(startScreen);
        this.repaint();
    }

    @Override
    public void repaint(){
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    @Override
    public void newGame(Model game) {
        this.model = game;
        this.gameScreen = new GamePanel(this.model);
    }

    @Override
    public void enableOtherButtons() {
        this.startScreen.enableOtherButtons();
        this.gameScreen.enableOtherButtons();
    }

    /**
     * Set a global font for all view elements
     * @param f the font to use
     */
    private static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, f);
            }
        }
    }

}