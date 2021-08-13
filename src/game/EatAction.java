package game;

import edu.monash.fit2099.engine.*;

/**
 * Automatic Eat Action for Dinosaurs
 * Will be called when a specific situation arises
 * Called by HungerBehaviour Only
 */
public class EatAction extends Action {

    protected Item edible;
    protected Location location;



    /**
     * This constructor is called when the ground contains something a Dinosaur can eat
     * @param Edible food that is on the ground where the dinosaur can eat
     */
    public EatAction(Item Edible){
        this.edible = Edible;
    }
    /**
     * This constructor is called when Stegosaur or Agilisaurus eat grass or leaves on a specific location
     * @param location1 location where the grass is (currently where the dinosaur is standing on) or where Tree is
     */
    public EatAction(Location location1){this.location = location1;}

    @Override
    public String execute(Actor actor, GameMap map) {
        //All auto Eat Actions by Stegosaur
        if (actor.hasCapability(Status.STEGOSAUR)) {
            for (Item item : map.locationOf(actor).getItems()){
                if (item.hasCapability(Status.FRUIT)){
                    ((Dinosaur) actor).Feed(21);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dropped fruit.";
                }
            }
            //Eat Grass
            if (location.getGround().hasCapability(Status.GRASS)) {
                ((Dinosaur) actor).Feed(6);
                map.locationOf(actor).setGround(new Dirt());
                return actor + " grazes on grass.";
            }
            //Eat leaves on tree
            else if (location.getGround().hasCapability(Status.TREE)) {
                ((Dinosaur) actor).Feed(6);
                return actor + " eats leaves on tree.";
            }

        }
        //All auto Eat Actions by Allosaur
        else if (actor.hasCapability(Status.ALLOSAUR)) {
            for (Item item : map.locationOf(actor).getItems()) {
                //Eat dead Stegosaur on ground
                if (item.hasCapability(Status.STEGOSAUR)) {
                    ((Dinosaur) actor).Feed(21);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Stegosaur.";
                }
                //Eat dead Agilisaurus on ground
                else if (item.hasCapability(Status.AGILISAURUS)) {
                    ((Dinosaur) actor).Feed(11);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Agilisaurus.";
                }
                //Eat dead new dino
                else if (item.hasCapability(Status.ARCHAEOPTERYX)){
                    ((Dinosaur) actor).Feed(16);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Archaeopteryx.";
                }
            }
            return null;

        }
        //All auto Eat Actions by Agilisaurus
        else if(actor.hasCapability(Status.AGILISAURUS)){
            //Eat fruit and dead dinosaurs on ground
            for (Item item : map.locationOf(actor).getItems()) {
                //eat dead allosaur
                if (item.hasCapability(Status.ALLOSAUR)) {
                    ((Dinosaur) actor).Feed(31);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Allosaur on the ground.";
                }
                //eat dead stegosaur
                else if (item.hasCapability(Status.STEGOSAUR)) {
                    ((Dinosaur) actor).Feed(21);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Stegosaur on the ground.";
                }
                //eat fruit
                else if (item.hasCapability(Status.FRUIT)) {

                    ((Dinosaur) actor).Feed(21);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats fruit on the ground.";
                }
                //eat dead new dino
                else if (item.hasCapability(Status.ARCHAEOPTERYX)){
                    ((Dinosaur) actor).Feed(16);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Archaeopteryx.";
                }
            }
            //Eat Grass
            if (location.getGround().hasCapability(Status.GRASS)) {
                ((Dinosaur) actor).Feed(6);
                map.locationOf(actor).setGround(new Dirt());
                return actor + " grazes on grass.";
            }
            //Eat leaves on tree
            else if (location.getGround().hasCapability(Status.TREE)) {
                ((Dinosaur) actor).Feed(6);
                return actor + " eats leaves on tree.";
            }


            return null;

        }
        //All auto Eat Actions by Archaeopteryx
        else if (actor.hasCapability(Status.ARCHAEOPTERYX)){
            for (Item item : map.locationOf(actor).getItems()) {
                //Eat dead Stegosaur on ground
                if (item.hasCapability(Status.STEGOSAUR)) {
                    ((Dinosaur) actor).Feed(21);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Stegosaur.";
                }
                //Eat dead Agilisaurus on ground
                else if (item.hasCapability(Status.AGILISAURUS)) {
                    ((Dinosaur) actor).Feed(11);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Agilisaurus.";
                }
                //Eat dead Allosaur on ground
                else if (item.hasCapability(Status.ALLOSAUR)){
                    ((Dinosaur) actor).Feed(31);
                    map.locationOf(actor).removeItem(edible);
                    return actor + " eats dead Allosaur.";
                }
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
