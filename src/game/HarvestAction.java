package game;

import edu.monash.fit2099.engine.*;


/**
 * HarvestAction
 * Called when player is standing on grass, give the option to harvest
 * IF player selects option, they add Hay to inventory and ground becomes dirt again.
 */
public class HarvestAction extends Action{


    private Location location;



    public HarvestAction(Location Location) {

        this.location = Location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        location.setGround(new Dirt());
        actor.addItemToInventory(new Hay());
        EcoPoints.gainEcoPoints(1);
        return "Player Harvests Grass";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvest grass ";
    }
}
