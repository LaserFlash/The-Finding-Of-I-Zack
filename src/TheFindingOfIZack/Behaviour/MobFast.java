package TheFindingOfIZack.Behaviour;

/**
 * Created by gordontheo on 19/09/17.
 */
public class MobFast extends Mob {
    MobFast(){
        this.viewRange = 40;
        this.speed = 6;
        this.health = 20;
        this.damage = 5;
    }

    @Override
    public String toString() {
        String string = "A fast mob. Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
}
