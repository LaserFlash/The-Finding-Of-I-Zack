package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Entity;
import TheFindingOfIZack.Entities.Point;
import TheFindingOfIZack.Entities.Projectile;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.standardRoom;

import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobProjectile extends Projectile implements Drawable, Savable {
    private double speed = 2;
    private double directionX;
    private double directionY;
    private Room room;
    private int size = 20;
    private static Image projectileImage;

    static {
        projectileImage = ImageLoader.loadImage("/inkySackyChappy.png").getScaledInstance(Entity.width/2,Entity.width/2,Image.SCALE_DEFAULT);

    }

    public MobProjectile(Point location, Point player, Room room){
        super(location, player);
        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        double h = Math.hypot(changeX,changeY);
        double a = h/speed;
        this.directionX = changeX/a;
        this.directionY = changeY/a;
        this.location = new Point(location.getX()+size/4,location.getY()+size/4);
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
        setBox();
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(projectileImage, (int) location.getX() +size/2, (int) location.getY() + size/2, null);
    }

}
