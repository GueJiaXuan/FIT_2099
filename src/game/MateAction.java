package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Mate Action that allows Dinosaurs to mate and for the female dinosaur to get pregnant
 */
public class MateAction extends Action {
    protected boolean genderType;

    public MateAction(boolean gender){
        genderType = gender;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (genderType){
            actor.addCapability(Status.PREGNANT);
        }
        return actor + " mates.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
