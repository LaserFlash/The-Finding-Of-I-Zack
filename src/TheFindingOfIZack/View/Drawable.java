package TheFindingOfIZack.View;

import java.awt.*;

/**
 *  This allows an object to be drawn by the view
 */
public interface Drawable {

    /**
     * Draw the object
     * @param g graphics object to draw on
     */
    void draw(Graphics g);
}