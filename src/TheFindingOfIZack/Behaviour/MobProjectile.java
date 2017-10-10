package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Projectile;
import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.Entities.Point;
import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobProjectile extends Projectile implements Drawable {
    private double speed = 3;
    private double directionX;
    private double directionY;
    private double x;
    private double y;

    public MobProjectile(Point location, Point player){
        super(location, player);
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        this.directionX = changeX/a;
        this.directionY = changeY/a;
        this.x = location.getX()+20;
        this.y = location.getY()+20;

    }

    @Override
    public void move(){
        x += directionX;
        y += directionY;
        //if (wallCollision()) {pop = true;}
    }

    @Override
    public void draw(Graphics g) {
        move();
        int size = 10;
        g.setColor(Color.GREEN);
        g.fillOval((int)x, (int)y, size, size);
    }
}
