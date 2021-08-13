package game;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.List;
import java.util.Random;

/**
 * Vending Machine. Represented with a '$' on the Map.
 * When Player is right beside Vending machine, he/she can purchase items with their EcoPoints
 */
public class VendingMachine extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public VendingMachine() {
        super('$');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public Actions allowableActions(Actor actor, Location location, String direction ){
        Actions actions = new Actions();
        if(actor instanceof Player){

            if(EcoPoints.getEcoPoints() > 20){
                actions.add(new BuyHay());
            }
            if(EcoPoints.getEcoPoints() > 30){
                actions.add(new BuyFruit());
            }
            if(EcoPoints.getEcoPoints() > 100){
                actions.add(new BuyVegetarianMealKit());
            }
            if(EcoPoints.getEcoPoints() > 200) {
                actions.add(new BuyStegosaurEggs());
            }
            if(EcoPoints.getEcoPoints() > 500){
                actions.add(new BuyLaserGun());
                actions.add(new BuyCarnivoreMealKit());
                actions.add(new BuyAgilisaurusEggs());
            }
            if(EcoPoints.getEcoPoints() > 750){
                actions.add(new BuyArchaeopteryxEggs());
            }
            if(EcoPoints.getEcoPoints() > 1000){
                actions.add(new BuyAllosaurEggs());
            }

            return actions;
        }
        else{
            return actions;
        }

    }

}
