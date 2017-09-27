package TheFindingOfIZack.Behaviour;

/**
 * Created by gordontheo on 27/09/17.
 * This class is used to create new mobs and specify what type they are
 */
public class MobEnemy {
    Mob mob;

    /**
     * @param type Constructor which takes a type and makes a new mob of this type
     */
    public MobEnemy(String type) {
        switch (type) {
            case "standard":
                mob = new MobStandard();
                break;
            case "fast":
                mob = new MobFast();
                break;
            case "slow":
                mob = new MobSlow();
                break;
            default: mobTypeError(type);
                break;
        }
    }

    /**
     * Returns health f
     * @return health
     */
    public int getHealth(){
        return mob.health;
    }

    /**
     * Throws an error if an invalid mobType is called
     * @param str the invalid type name entered
     */
    private void mobTypeError(String str){
        System.err.print("Error: Invalid mob type " + str + "\n");
    }
}