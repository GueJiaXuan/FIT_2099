package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Class that calls when Player purchases Laser Gun
 */
public class BuyLaserGun extends Action {

    public BuyLaserGun(){
    }
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new LaserGun("Laser gun",'L',50,"shoot"));
        EcoPoints.gainEcoPoints(-500);
        return menuDescription(actor);
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys 1 Laser Gun.";
    }
}
