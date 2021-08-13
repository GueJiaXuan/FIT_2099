package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Feed Action class where Player feeds Dinosaurs food
 * Option will be provided when Player is right beside dinosaurs and whether dinosaurs can eat those food
 */
public class FeedAction extends Action {

    protected Dinosaur target;
    protected Item toFeed;


    public FeedAction(Dinosaur Target, Item ToFeed){
        this.target = Target;
        this.toFeed = ToFeed;

    }
    @Override
    public String execute(Actor actor, GameMap map) {

        if (toFeed.hasCapability(Status.FRUIT)){
            target.Feed(31);
            actor.removeItemFromInventory(toFeed);
            EcoPoints.gainEcoPoints(20);
            return "Player feeds "  + target + " Fruit" ;
        }
        else if (toFeed.hasCapability(Status.HAY)){
            target.Feed(21);
            actor.removeItemFromInventory(toFeed);
            EcoPoints.gainEcoPoints(10);
            return "Player feeds " + target + " Hay";
        }
        else if (toFeed.hasCapability(Status.CARNIVOREMEALKIT)){
            target.Feed(100);
            actor.removeItemFromInventory(toFeed);
            return "Player feeds " + target + " carnivore meal kit";
        }
        else if (toFeed.hasCapability(Status.VEGETARIANMEALKIT)){
            target.Feed(100);
            actor.removeItemFromInventory(toFeed);
            return "Player feeds " + target + " vegetarian meal kit";
        }
        else if (toFeed.hasCapability(Status.STEGOSAUR)){
            target.Feed(21);
            actor.removeItemFromInventory(toFeed);
            EcoPoints.gainEcoPoints(100);
            return "Player feed "+ target +" dead Stegosaur.";
        }
        else if(toFeed.hasCapability(Status.ALLOSAUR)){
            target.Feed(31);
            actor.removeItemFromInventory(toFeed);
            EcoPoints.gainEcoPoints(100);
            return "Player feed "+ target +" dead Allosaur.";
        }
        else if(toFeed.hasCapability(Status.AGILISAURUS)){
            target.Feed(11);
            actor.removeItemFromInventory(toFeed);
            EcoPoints.gainEcoPoints(100);
            return "Player feed "+ target +" dead Agilisaurus.";
        }
        else if (toFeed.hasCapability(Status.ARCHAEOPTERYX)){
            target.Feed(16);
            actor.removeItemFromInventory(toFeed);
            EcoPoints.gainEcoPoints(100);
            return "Player feed "+ target +" dead Archaeopteryx.";
        }
        else
            return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target + " " + toFeed;
    }
}
