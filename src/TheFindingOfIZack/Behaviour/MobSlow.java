package TheFindingOfIZack.Behaviour;

/**
 * Created by gordontheo on 19/09/17.
 * The staple enemy, will remain still until the player enters its field of view.
 * After this it will follow the player at slow speed and cause damage if it touches
 */
public class MobSlow extends Mob {
    MobSlow(){
        this.viewRange = 200;
        this.speed = 2;
        this.health = 100;
        this.damage = 20;
    }
}
