package View;

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

    default void drawBase(int x, int y, Color color, Graphics g){
        g.setColor(color);
        g.fillRect(x-10,y-10,x+10,y+10);
    }
}