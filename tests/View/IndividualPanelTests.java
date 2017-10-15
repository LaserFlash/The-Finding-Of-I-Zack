package View;

import Mocks.MockModel;
import TheFindingOfIZack.View.Panels.GameArea;
import TheFindingOfIZack.View.Panels.GameEndScreen;
import TheFindingOfIZack.World.Game;
import org.junit.Test;

import javax.swing.*;

public class IndividualPanelTests {

    @Test
    public void endScreenDefaultText() throws InterruptedException {
        JFrame f = new JFrame();
        f.add(new GameEndScreen(new String[]{"Game Over"}));
        f.pack();

        SwingUtilities.invokeLater(()->{
            f.setVisible(true);
            new Timer(2000, e-> f.dispose()).start();
        });

        Thread.sleep((3000));
    }

    @Test
    public void endScreenLongText() throws InterruptedException {
        JFrame f = new JFrame();
        f.add(new GameEndScreen(new String[]{"This is a long one line String, which needs to be made longer and longer and longer"}));
        f.pack();

        SwingUtilities.invokeLater(()->{
            f.setVisible(true);
            new Timer(2000, e-> f.dispose()).start();
        });

        Thread.sleep((3000));
    }

    @Test
    public void endScreenMultipleLineText() throws InterruptedException {
        JFrame f = new JFrame();
        f.add(new GameEndScreen(new String[]{"This is a multiple line display","With each line in a different","index of the parsed array"}));
        f.pack();

        SwingUtilities.invokeLater(()->{
            f.setVisible(true);
            new Timer(2000, e-> f.dispose()).start();
        });

        Thread.sleep((3000));
    }


    @Test
    public void drawGameArea() throws InterruptedException {
        GameArea a = new GameArea(new MockModel());
        JFrame f = new JFrame();
        f.add(a);
        f.pack();

        SwingUtilities.invokeLater(()->{
            f.setVisible(true);
            new Timer(1000,e-> a.repaint()).start();
            new Timer(2000, e-> f.dispose()).start();
        });

        Thread.sleep((3000));
    }
}
