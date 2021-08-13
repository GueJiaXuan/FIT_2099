package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * Class that calls when Player purchases Carnivore Meal Kit
 */

public class BuyCarnivoreMealKit extends Action {

    public BuyCarnivoreMealKit(){
    }
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new CarnivoreMealKit());
        EcoPoints.gainEcoPoints(-500);
        return menuDescription(actor);
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys 1 Carnivore Meal Kit.";
    }
}
