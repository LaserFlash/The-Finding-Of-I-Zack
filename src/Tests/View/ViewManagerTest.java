package Tests.View;

import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Model;
import org.junit.Test;
import Tests.Mocks.MockModel;

import javax.swing.*;



public class ViewManagerTest {


    @Test
    /**
     * Check that the view correctly displays the start screen menu
     */
    public void testMenuDisplay() throws InterruptedException {

        MockModel m = new MockModel();
        killIn3Sec(m);
    }

    @Test
    /**
     * Check the game game be displayed and then returned to the start menu screen
     */
    public void testGameDisplayToggle() throws InterruptedException {
        MockModel m = new MockModel();
        SwingUtilities.invokeLater(()->{
            ViewManager v = new ViewManager(m);
            v.showGUI();
            new Timer(1000,e-> v.goToGameView()).start();
            new Timer(2000,e-> v.goToMenuView()).start();
            new Timer(3000,e-> v.dispose()).start();
        });
        Thread.sleep(4000);
    }

    @Test
    /**
     * Check that the initial disabled buttons become enabled
     * Disabled buttons are:
     *      Save
     *      Resume
     */
    public void testActivateOtherButtons() throws InterruptedException {
        MockModel m = new MockModel();
        SwingUtilities.invokeLater(()->{
            ViewManager v = new ViewManager(m);
            v.showGUI();
            new Timer(2000, e-> v.enableOtherButtons()).start();
        });
        Thread.sleep((3000));
    }


    /**
     * Restrict the window to being displayed for 3 second period
     * @param mock
     * @throws InterruptedException
     */
    void killIn3Sec(Model mock) throws InterruptedException {
        SwingUtilities.invokeLater(()->{
            ViewManager v = new ViewManager(mock);
            v.showGUI();
            new Timer(3000,e-> v.dispose()).start();
        });
        Thread.sleep(3000);
    }
}