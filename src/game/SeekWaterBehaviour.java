package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Behaviour for Dinosaurs when they are thirsty.
 */
public class SeekWaterBehaviour implements Behaviour {
    private Random random = new Random();


    @Override
    public Action getAction(Actor actor, GameMap map) {
        //Only for Archaeopteryx because it can fly
        if (actor.hasCapability(Status.FLY) && map.locationOf(actor).getGround().hasCapability(Status.WATER)){
            return new DrinkAction();
        }
        //For all Dinosaurs
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                if (destination.getGround().hasCapability(Status.WATER)) {
                    return new DrinkAction();
                }
            }
        }
        if (((Dinosaur)actor).hungerPoints < 40 &&((Dinosaur)actor).hungerPoints > 0){
            return new HungerBehaviour().getAction(actor, map);
        }

        return new WanderBehaviour().getAction(actor,map);

}
}
