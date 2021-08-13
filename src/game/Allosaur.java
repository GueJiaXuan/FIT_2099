package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Allosaur extends Dinosaur{
    public List<Behaviour> behaviour = new ArrayList<Behaviour>();
    protected int unconsciousTick = 20;
    private int lifeTick = 31;
    private int pregnantTick = 0;
    /**
     * Constructor.
     *
     * All Allosaur are represented by 'a' and have 100 hit points.
     * All Allosaur start of with 50 hunger points
     *
     * @param name         the name of the Actor
     * displayChar  the character that will represent the Actor in the display
     * hitPoints    the Actor's starting hit points
     * HungerPoints the Dinosaur's starting hit points
     *
     */
    public Allosaur(String name) {
        super(name, 'A',100,50,50);
        addCapability(Status.ALLOSAUR);
        behaviour.add(new WanderBehaviour());
        behaviour.add(new HungerBehaviour());
        behaviour.add(new SeekWaterBehaviour());
    }
    /**
     * Constructor.
     *
     * All Allosaur are represented by 'a' and have 100 hit points.
     * All Allosaur start of with 50 hunger points
     *
     * @param name         the name of the Actor
     * displayChar  the character that will represent the Actor in the display
     * hitPoints    the Actor's starting hit points
     * HungerPoints the Dinosaur's starting hit points
     *
     */
    public Allosaur(String name, int lifetick){
        super(name, 'A', 100,10,50);
        addCapability(Status.ALLOSAUR);
        addCapability(Status.BABY);
        behaviour.add(new WanderBehaviour());
        behaviour.add(new HungerBehaviour());
        behaviour.add(new SeekWaterBehaviour());
        lifeTick = lifetick;
    }
    /**
     * Actions when other Actor gets near Allosaur
     * @param otherActor the other actor
     * @param direction direction of player travel
     * @param map Map
     */
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        actions.add(new AttackAction(this));
        List<Item> otherActorInventory = otherActor.getInventory();
        for (Item i: otherActorInventory){
            // needs to contain whatever player can feed them
            if (i.hasCapability(Status.STEGOSAUR) || i.hasCapability(Status.AGILISAURUS) || i.hasCapability(Status.ARCHAEOPTERYX) || i.hasCapability(Status.CARNIVOREMEALKIT)){
                actions.add(new FeedAction(this, i));
            }
        }
        return actions;
    }
    /**
     * Figure out what to do next for Allosaur
     *
     * playTurn for Allosaur
     *
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.PREGNANT)){
            pregnantTick ++;
            if (pregnantTick == 10){
                this.removeCapability(Status.PREGNANT);
                pregnantTick = 0;
                map.locationOf(this).addItem(new AllosaurEgg("Allosaur Egg"));
                System.out.println("Allosaur at (" + map.locationOf(this).x()
                        + "," + map.locationOf(this).y() + ") lays an egg!");
            }
        }

        lifeTick ++;
        if (lifeTick == 30)
            this.removeCapability(Status.BABY);

        if (hungerPoints > 0) {
            this.hungerPoints--;
        }

        if(thirstPoints>0){
            this.thirstPoints --;
        }
        else if (hungerPoints == 0 || thirstPoints == 0){
            System.out.println("Allosaur at (" + map.locationOf(this).x()
                    + "," + map.locationOf(this).y() + ") is unconscious!");
            unconsciousTick--;
            if (unconsciousTick == 0){
                Item corpse = new PortableItem("dead " + this, '%');
                corpse.addCapability(Status.ALLOSAUR);
                map.locationOf(this).addItem(corpse);
                map.removeActor(this);
            }
            return new DoNothingAction();
        }
        if (thirstPoints < 25 && thirstPoints> 0) {
            unconsciousTick = 20;
            System.out.println("Allosaur at (" + map.locationOf(this).x()
                    + "," + map.locationOf(this).y() + ") is getting thirsty!");

            return behaviour.get(2).getAction(this, map);

        }

        //System.out.println("Allosaur hunger point is " + this.hungerPoints);
        if (hungerPoints < 40 && hungerPoints > 0) {
            unconsciousTick = 20;
            System.out.println("Allosaur at (" + map.locationOf(this).x()
                    + "," + map.locationOf(this).y() + ") is getting hungry!");

            return behaviour.get(1).getAction(this, map);

        }


        if (hungerPoints > 50){
            if (this.hasCapability(Status.BABY)){
                // Baby Wanders, no breeding
                return behaviour.get(0).getAction(this, map);
            }
            return new MatingBehaviour(Status.ALLOSAUR,super.gender).getAction(this,map);
        }

        Action wander = behaviour.get(0).getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }
}
