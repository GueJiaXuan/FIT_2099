package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Behaviour when Dinosaurs get Hungry
 * extends Behaviour
 */
public class HungerBehaviour implements Behaviour {
    private Random random = new Random();

    /**
     * Action for Dinosaurs when hungry
     * @return An Action that will be called for the Dinosaurs
     * This hunger behaviour get Action is used for Stegosaur, Allosaur, Agilisaurus and Archaropteryx only
     * */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<Action>();
        //For Stegosaur
        if (actor.hasCapability(Status.STEGOSAUR)) {
            //Priority 1: When Stegosaur standing on grass
            if (map.locationOf(actor).getGround().hasCapability(Status.GRASS)) {
                return new EatAction(map.locationOf(actor));
            }
            //Priority 2: When Stegosaur standing on ground containing Fruit
            for (Item item: map.locationOf(actor).getItems()) {
                if (item.hasCapability(Status.FRUIT)){
                    return new EatAction(item);
                }
            }
            //Priority 3: When Stegosaur standing on tree, it can eat leaves on tree.
            if (map.locationOf(actor).getGround().hasCapability(Status.TREE)){
                return new EatAction(map.locationOf(actor));
            }
            //Priority 4: Stegosaur moves to nearest food source within one tile radius
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    if (destination.getGround().hasCapability(Status.GRASS)) {
                        actions.add(exit.getDestination().getMoveAction(actor, "towards grass", exit.getHotKey()));
                    }
                    if (destination.getGround().hasCapability(Status.TREE)){
                        actions.add(exit.getDestination().getMoveAction(actor, "towards tree", exit.getHotKey()));
                    }
                    for (Item item : destination.getItems()){
                        if (item.hasCapability(Status.FRUIT)){
                            actions.add(exit.getDestination().getMoveAction(actor, "towards dropped fruit", exit.getHotKey()));
                        }
                    }
                }
            }
        }
        //For Allosaur
        else if (actor.hasCapability(Status.ALLOSAUR)){
            //Priority 1: When Allosaur standing on ground containing Allosaur Edibles
            for(Item j: map.locationOf(actor).getItems()) {
                if (j.hasCapability(Status.STEGOSAUR) || j.hasCapability(Status.AGILISAURUS) || j.hasCapability(Status.ARCHAEOPTERYX)){
                    return new EatAction(j);
                }
            }
           //Priority 2: When Allosaur sees prey or dead dinosaurs around him
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.STEGOSAUR) || destination.getActor().hasCapability(Status.AGILISAURUS)| destination.getActor().hasCapability(Status.ARCHAEOPTERYX)) {
                        actions.add(new AttackAction(destination.getActor()));
                    }
                }
                if (destination.canActorEnter(actor)){
                    for(Item i: destination.getItems()) {
                        if (i.hasCapability(Status.STEGOSAUR) || i.hasCapability(Status.AGILISAURUS) || i.hasCapability(Status.ARCHAEOPTERYX)){
                            actions.add(exit.getDestination().getMoveAction(actor, "towards dead Dinosaur's body", exit.getHotKey()));
                        }
                    }
                }
            }
        }
        //For Agilisaurus. Since it can eat anything Stegosaur can eat + dead Dinosaurs, adding dead Dinosaurs from Stegosaur's code
        else if(actor.hasCapability(Status.AGILISAURUS)){
            //Priority 1: When Agilisaurus standing on grass
            if (map.locationOf(actor).getGround().hasCapability(Status.GRASS)) {
                return new EatAction(map.locationOf(actor));
            }
            //Priority 2: When Agilisaurus standing on ground containing Fruit
            for (Item item: map.locationOf(actor).getItems()) {
                if (item.hasCapability(Status.FRUIT)){
                    return new EatAction(item);
                }
            }
            //Priority 3: When Agilisaurus standing on tree, it can eat leaves on tree.
            if (map.locationOf(actor).getGround().hasCapability(Status.TREE)){
                return new EatAction(map.locationOf(actor));
            }
            //Priority 4: When Agilisaurus standing on dead Dinosaur
            for(Item j: map.locationOf(actor).getItems()) {
                if (j.hasCapability(Status.STEGOSAUR) || j.hasCapability(Status.ARCHAEOPTERYX) || j.hasCapability(Status.ALLOSAUR)){
                    return new EatAction(j);
                }
            }
            //Priority 4: Agilisaurus moves to nearest food source within one tile radius
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    if (destination.getGround().hasCapability(Status.GRASS)) {
                        actions.add(exit.getDestination().getMoveAction(actor, "towards grass", exit.getHotKey()));
                    }
                    for (Item item : destination.getItems()){
                        if (item.hasCapability(Status.FRUIT)){
                            actions.add(exit.getDestination().getMoveAction(actor, "towards dropped fruit", exit.getHotKey()));
                        }
                        if (item.hasCapability(Status.STEGOSAUR) || item.hasCapability(Status.ALLOSAUR) || item.hasCapability(Status.ARCHAEOPTERYX)){
                            actions.add(exit.getDestination().getMoveAction(actor, "towards dead Dinosaur", exit.getHotKey()));
                        }
                    }
                }
            }
        }
        //For Archearopteryx. Can attack and eat any dead dinosaur
        else {
            //Priority 1: When Allosaur standing on ground containing Allosaur Edibles
            for(Item j: map.locationOf(actor).getItems()) {
                if (j.hasCapability(Status.STEGOSAUR) || j.hasCapability(Status.AGILISAURUS) || j.hasCapability(Status.ALLOSAUR)){
                    return new EatAction(j);
                }
            }
            //Priority 2: When Allosaur sees prey or dead dinosaurs around him
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.STEGOSAUR) || destination.getActor().hasCapability(Status.AGILISAURUS)| destination.getActor().hasCapability(Status.ALLOSAUR)) {
                        actions.add(new AttackAction(destination.getActor()));
                    }
                }
                if (destination.canActorEnter(actor)){
                    for(Item i: destination.getItems()) {
                        if (i.hasCapability(Status.STEGOSAUR) || i.hasCapability(Status.AGILISAURUS) || i.hasCapability(Status.ALLOSAUR)){
                            actions.add(exit.getDestination().getMoveAction(actor, "towards dead Dinosaur's body", exit.getHotKey()));
                        }
                    }
                }
            }
        }
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return new WanderBehaviour().getAction(actor, map);
        }

    }
}
