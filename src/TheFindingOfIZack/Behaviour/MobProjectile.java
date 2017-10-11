package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Entities.Projectile;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;

import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobProjectile extends Projectile implements Drawable {
    private double speed = 3;
    private double directionX;
    private double directionY;
    private int size = 10;
    //private Room room =

    public MobProjectile(Point location, Point player){
        super(location, player);
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        this.directionX = changeX/a;
        this.directionY = changeY/a;
        this.location = new Point(location.getX()+20,location.getY()+20);
    }

    public void pop(){
        pop = true;
    }

    @Override
    public void move(){
        this.location.move(location.getX()+directionX,location.getY()+directionY);
        if (wallCollision()) {pop = true;}
        setBox();
        //if (enemyCollision()) {pop = true;}
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.setColor(Color.GREEN);
        g.fillOval((int)location.getX(), (int)location.getY(), size, size);
    }

    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), size, size);
    }
}
