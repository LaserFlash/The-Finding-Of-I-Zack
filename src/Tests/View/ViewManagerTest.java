package Tests.View;

import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Model;
import org.junit.Test;
import Tests.Mocks.MockModel;

import javax.swing.*;



public class ViewManagerTest {


    @Test
    public void testMenuDisplay() throws InterruptedException {
        MockModel m = new MockModel();
        killIn3Sec(m);
    }

    @Test
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


    void killIn3Sec(Model mock) throws InterruptedException {
        SwingUtilities.invokeLater(()->{
            ViewManager v = new ViewManager(mock);
            v.showGUI();
            new Timer(3000,e-> v.dispose()).start();
        });
        Thread.sleep(3000);
    }
}