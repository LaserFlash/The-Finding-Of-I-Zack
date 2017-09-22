package TheFindingOfIZack.View.Panels;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Abstract representation of the main panel to form the game GUI
 * Can have it's own panels to form a more complex layout
 */
public abstract class ScreenPanel extends JPanel {

    /**
     * Allow for Button interactions to be delegated to a ActionListener elsewhere
     * @param controller ActionListener to associate any button interactions with
     */
    public abstract void addControllerForButtons(ActionListener controller);
}
