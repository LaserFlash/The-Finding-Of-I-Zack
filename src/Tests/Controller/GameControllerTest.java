package Tests.Controller;

import Tests.Mocks.MockModel;
import Tests.Mocks.MockView;
import TheFindingOfIZack.Controller.GameController;
import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertTrue;

public class GameControllerTest {

    @Test
    /**
     * Check that the display/view is told to show itself
     */
    public void testShowGUICommunication(){
        MockView v = new MockView("Test View");
        GameController c = new GameController(v,new MockModel());

        c.showGUI();
        assertTrue("gui should be true",v.gui);
    }

    @Test
    public void actionPerformed() throws Exception {
        MockView v = new MockView("Test View");
        GameController c = new GameController(v,new MockModel());

        KeyEvent key = new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP);


    }

    @Test
    public void keyTyped() throws Exception {
    }

    @Test
    public void keyPressed() throws Exception {
    }

    @Test
    public void keyReleased() throws Exception {
    }

}