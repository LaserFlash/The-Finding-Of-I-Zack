package TheFindingOfIZack.FileIO;

import java.io.Serializable;

/**
 *  This abstract class captures the notion of a Save friendly object,
 *  objects like Levels, Players, Games, Items, ... etc need to be able
 *  to be saved and stored in the GameFile
 */
public interface Savable extends Serializable {

    /**
     *  This method returns all of the information a class stores
     *  needed to recreate the that class in its current state
     * @return the class information as a String
     */
    //public abstract String save();

    /**
     *  This method returns a string containing all of the information
     *  a class needs in order to be created in the same state as when
     *  it was saved
     * @param str
     */
    //public abstract void load(String str);
}
