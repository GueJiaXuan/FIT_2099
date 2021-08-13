package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import javax.sound.sampled.Port;
import java.util.Random;

/**
 * Action that allows players to pick fruits when they are on the tree tile.
 */
public class PickFruitAction extends Action {

    private Location location;



    public PickFruitAction(Location Location) {

        this.location = Location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        int rand0 = rand.nextInt(100);
        if (rand0 > 59) {
            actor.addItemToInventory(new Apple());
            return "Player manages to pick fruit";
        }
        else
            return "Player search the tree for fruit, but can't find any ripe ones";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attempts to pick fruit ";
    }
}
