package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Automatic Eat Action for Dinosaurs
 * Will be called when a specific situation arises
 * Called by SeekWaterBehaviour Only
 */
public class DrinkAction extends Action {


    public DrinkAction(){
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ((Dinosaur) actor).Drink(50);
        return actor + " drinks water";

    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
