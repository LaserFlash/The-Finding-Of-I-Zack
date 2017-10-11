package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Entities.Projectile;
import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.standardRoom;

import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobProjectile extends Projectile implements Drawable {
    private double speed = 2;
    private double directionX;
    private double directionY;
    private Room room;
    private int size = 20;

    public MobProjectile(Point location, Point player, Room room){
        super(location, player);
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        this.directionX = changeX/a;
        this.directionY = changeY/a;
        this.location = new Point(location.getX()+10,location.getY()+10);
        this.room = room;
    }

    public void pop(){
        pop = true;
    }

    @Override
    public void move(){
        this.location.move(location.getX()+directionX,location.getY()+directionY);
        if (wallCollision()) {pop = true;}

        if (room instanceof standardRoom) {
            standardRoom r = (standardRoom) room;
            entityCollision(r.getItems());
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.setColor(Color.GREEN);
        g.fillOval((int)location.getX(), (int)location.getY(), size, size);
    }
}
