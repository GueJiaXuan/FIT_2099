package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that gives the option for player to quit.
 */
public class QuitAction extends Action {
    /**
     * Removes the player from the map and ends the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            map.removeActor(actor);
        }
        return "";
    }

    public String menuDescription(Actor actor) {
        return actor + " quits the game";
    }
}
