package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.Behaviour.MobProjectile;
import TheFindingOfIZack.Behaviour.MobShooter;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Rooms.Room;
import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameDimensions.*;


/**
 * Created by Ben Allan
 */
public class Enemy extends Entity {

    protected MobEnemy behaviour;
    protected int health;
    protected int MAX_HEALTH;
    protected Player player;
    protected boolean isDead = false;
    private final int DAMAGE_TICK = 20;
    protected int tick;
    protected Room r;

    protected Image enemyImage;


    public Enemy(Point location, Player p) {
        super(location);
        this.player = p;
        this.r = p.getRoom();

        if (this instanceof Boss) {
            this.behaviour = new MobEnemy("boss", r);
            this.enemyImage = ImageLoader.loadImage("/anEnemy.png").getScaledInstance(Boss.size,Boss.size,Image.SCALE_DEFAULT);
        }
        else {
            int type = (int) (Math.random()*5);
            //int type = 1;
            if (type>2) {
                this.behaviour = new MobEnemy("standard", r);
                this.enemyImage = ImageLoader.loadImage("/bothersomeBrittleStar.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);
            }
            else if (type==2) {
                this.behaviour = new MobEnemy("fast", r);
                this.enemyImage = ImageLoader.loadImage("/kookyCrabbyKid.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);
            }
            else if (type==1) {
                this.behaviour = new MobEnemy("shooter", r);
                this.enemyImage = ImageLoader.loadImage("/weeOctoBoi.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);
            }
            else {
                this.behaviour = new MobEnemy("slow", r);
                this.enemyImage = ImageLoader.loadImage("/painfulPointyPerson.png").getScaledInstance(Entity.width,Entity.width,Image.SCALE_DEFAULT);
            }
        }

        this.health = behaviour.getHealth();
        this.MAX_HEALTH = behaviour.getHealth();
    }

    public void damage(int damage) {
            this.health -= damage;
            if (this.health <= 0) {isDead = true;}
    }

    public void damagePlayer(){
        if(tick > DAMAGE_TICK) {
            this.player.damage(this.behaviour.getDamage()); //Takes the damage value from each mob type
            tick = 0;
        }
    }

    public boolean isDead() {return isDead;}

    @Override
    public void draw(Graphics g) {
        if (this.behaviour.getMob() instanceof MobShooter) {
            MobShooter m = (MobShooter) behaviour.getMob();
            drawProjectiles(m,g);
        }
        g.drawImage(enemyImage, (int) location.getX(), (int) location.getY(), null);


        double red = (((double)MAX_HEALTH-(double)health)/(double)MAX_HEALTH)*(double)255;
        if (red < 0) {red = 0;}
        else if (red > 255) {red = 255;}
        double green = ((double)health/(double)MAX_HEALTH)*(double)255;
        if (green < 0) {green = 0;}
        else if (green > 255) {green = 255;}
        Color c = new Color((int)red, (int) green, 0);

        double healthBar = ((double) health/(double) MAX_HEALTH) * (double) width;
        if (healthBar < 0) {healthBar = 0;}

        g.setColor(c);
        g.fillRect((int) location.getX(), (int) location.getY() - 8, (int) healthBar, 4);
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY() - 8, width, 4);

    }

    /**
     * Makes each mob run through its step method
     * Also checks for edge of map collisions
     * Also checks to see if the mob has touched the player
     */
    public void move() {
        tick ++;
        Point playerPoint = player.getLocation();
        Point potentialStep = this.behaviour.step(location, playerPoint, r);
        //potentialStep represents the move which will take place if there are no obstacle,
        // also checks if mob is currently touching player
        //entityCollision();*************************************************************************************************************************
        if(!collision(location,playerPoint)) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.width, this.width);
            setBox();
        }
        if(collision(location,playerPoint)){
            damagePlayer();
        }
        canMove();
    }


    /**
     * Determines if the mob has collided with a wall
     */
    protected void canMove(){
        double x = location.getX();
        double y = location.getY();
        boolean top = false;
        boolean bottom = false;
        boolean left = false;
        boolean right = false;
        if(y < TOP_WALL){top = true;}
        else if(x < LEFT_WALL){left = true;}
        else if(y+width > BOTTOM_WALL){bottom = true;}
        else if(x+width > RIGHT_WALL){right = true;}
        else if(top){y = TOP_WALL;}
        else if(bottom){y = BOTTOM_WALL-width;}
        else if(left){x = LEFT_WALL;}
        else if(right){x = RIGHT_WALL-width;}
        location.move(x,y);
    }

    /**
     * Determines if the player and any mob have collided
     * @param mob mob location
     * @param player player location
     */
    public boolean collision(Point mob, Point player) {
        double px = player.getX();
        double py = player.getY();
        double mx = mob.getX();
        double my = mob.getY();
        int w = width;

        if(mx<px && my<py && mx+w>px && my+w>py){return true;} //Top left
        if(mx>px && my>py && mx<px+w && my<py+w){return true;} //Bottom Right
        if(mx<px && my>py && mx+w>px && my<py+w){return true;} //Bottom left
        if(mx>px && my<py && mx<px+w && my+w>py){return true;} //Top Right
        return false;
    }

    public void drawProjectiles(MobShooter m, Graphics g){
        synchronized (m.getProjectile()) {
            for (MobProjectile i : m.getProjectile()) {
                i.draw(g);
                if (collision(i.getLocation(), player.getLocation())) {
                    i.pop();
                    damagePlayer();
                }
            }
        }
    }
}
