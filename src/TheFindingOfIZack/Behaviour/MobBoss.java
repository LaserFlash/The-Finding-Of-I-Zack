package TheFindingOfIZack.Behaviour;

/**
 * Created by gordontheo on 11/10/17.
 * Boss Mob
 */
public class MobBoss extends Mob{
    MobBoss(){
        this.viewRange = 50;
        this.speed = 3;
        this.health = 100;
        this.damage = 20;
    }

    @Override
    public String toString() {
        String string = "The Boss mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
}
