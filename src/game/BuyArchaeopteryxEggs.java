package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.List;
/**
 * Class that calls when Player purchases Archaeopteryx Egg
 */
public class BuyArchaeopteryxEggs extends Action {
    private List<Actor> targets;
    private Location location;
    private String direction;
    private VendingMachine vendingMachine;

    public BuyArchaeopteryxEggs(){

    }
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new ArchaeopteryxEgg("ArchaeopteryxEgg"));
        EcoPoints.gainEcoPoints(-750);
        return menuDescription(actor);
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys 1 archaeopteryx egg.";
    }
}
