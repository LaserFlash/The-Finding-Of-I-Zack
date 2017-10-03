package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobProjectile implements Drawable {
    private double x;
    private double y;

    public MobProjectile(Point location){
        this.x = location.getX();
        this.y = location.getY();
    }

    @Override
    public void draw(Graphics g) {
        int size = 10;

        g.setColor(Color.RED);
        g.fillOval((int)x, (int)y, size, size);
    }
}
