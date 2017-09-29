package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.View.Drawable;

import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobProjectile implements Drawable{
    private Point location;

    public MobProjectile(Point location){
        this.location = location;
    }

    @Override
    public void draw(Graphics g) {
        int size = 10;

        g.setColor(Color.RED);
        g.fillOval(location.x, location.y, size, size);
    }
}
