package TheFindingOfIZack.Behaviour;

import java.awt.*;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobShooter extends Mob{
    public MobShooter(){
        this.viewRange = 100;
        this.speed = 1;
        this.health = 50;
        this.damage = 0;
    }

    @Override
    /**
     * Shifts the mob's location but stops before getting to player and shoots
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player){
        int stopDistance = 40;
        double newX = location.getX();
        double newY = location.getY();

        double range = distanceBetween(location,player);
        if (range < viewRange && range > stopDistance){
            if(player.getX() > location.getX()){
                newX += speed;}
            if(player.getY() > location.getY()){
                newY += speed;}
            if(player.getX() < location.getX()){
                newX -= speed;}
            if(player.getY() < location.getY()){
                newY -= speed;}
        }
        location.move((int)newX,(int)newY);

        //Controls random shooting
        if(range < viewRange){
            int shootChance = (int) Math.random()*3;
            if(shootChance < 2){
                MobProjectile shot = new MobProjectile(location);
            }
        }

        return location;
    }
}
