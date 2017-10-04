package TheFindingOfIZack.Behaviour;

/**
 * Created by gordontheo on 19/09/17.
 * The staple enemy, will remain still until the player enters its field of view.
 * After this it will follow the player at slow speed and cause damage if it touches
 */
public class MobSlow extends Mob {
    MobSlow(){
        this.viewRange = 200;
        this.speed = 4;
        this.health = 100;
        this.damage = 20;
    }

    @Override
    public String toString() {
        String string = "A slow mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
}
