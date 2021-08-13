package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Pool Class(water class) that extends ground.
 * only Archaeopteryx can enter this tile since it is the only actor that can fly
 * Represented with '~'
 * Dinosaurs when beside it can increase thirstPoints by drinking water
 */
public class Pool extends Ground {

    public Pool() {
        super('~');

        addCapability(Status.WATER);
    }
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.FLY)){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}

