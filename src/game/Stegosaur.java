package game;


import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.

	private List<Behaviour> behaviour = new ArrayList<Behaviour>();
	private int unconsciousTick = 20;
	private int lifeTick = 31;
	private int pregnantTick = 0;


	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 * All Stegosaurs also start off with 50 Hunger Points
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name) {
		super(name, 'd', 100,50,50);
		addCapability(Status.STEGOSAUR);
		behaviour.add(new WanderBehaviour());
		behaviour.add(new HungerBehaviour());
		behaviour.add(new SeekWaterBehaviour());
	}

	/**
	 * This Stegosaur constructor is called when Egg hatches
	 * @param name  name of Dinosaur
	 * @param lifetick age of Dinosaur
	 */
	public Stegosaur(String name, int lifetick){
		super(name, 'd', 100,10,50);
		addCapability(Status.STEGOSAUR);
		addCapability(Status.BABY);
		behaviour.add(new WanderBehaviour());
		behaviour.add(new HungerBehaviour());
		behaviour.add(new SeekWaterBehaviour());
		lifeTick = lifetick;
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {

		Actions actions = super.getAllowableActions(otherActor, direction, map);
		actions.add(new AttackAction(this));
		List<Item> otherActorInventory = otherActor.getInventory();
		for (Item i: otherActorInventory){
			// needs to contain whatever player can feed them
			if (i.hasCapability(Status.FRUIT) || i.hasCapability(Status.HAY) || i.hasCapability(Status.VEGETARIANMEALKIT)){
				actions.add(new FeedAction(this, i));
			}
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 * 
	 * playTurn for Stegosaur
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
				map.locationOf(this).addItem(new StegosaurEgg("Stegosaur Egg"));
				System.out.println("Stegosaur at (" + map.locationOf(this).x()
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


		if (hungerPoints == 0 || thirstPoints == 0){
			System.out.println("Stegosaur at (" + map.locationOf(this).x()
					+ "," + map.locationOf(this).y() + ") is unconscious!");
			unconsciousTick--;
			if (unconsciousTick == 0){
				Item corpse = new PortableItem("dead " + this, '%');
				corpse.addCapability(Status.STEGOSAUR);
				map.locationOf(this).addItem(corpse);
				map.removeActor(this);
			}
			return new DoNothingAction();
		}

		if (thirstPoints < 25 && thirstPoints> 0) {
			unconsciousTick = 20;
			System.out.println("Stegosaur at (" + map.locationOf(this).x()
					+ "," + map.locationOf(this).y() + ") is getting thirst!");
			return behaviour.get(2).getAction(this, map);
		}

		if (hungerPoints < 25 && hungerPoints > 0) {
			unconsciousTick = 20;
			System.out.println("Stegosaur at (" + map.locationOf(this).x()
					+ "," + map.locationOf(this).y() + ") is getting hungry!");

			return behaviour.get(1).getAction(this, map);
		}


		if (hungerPoints > 50){
			if (this.hasCapability(Status.BABY)){
				// Baby Wanders, no breeding
				return behaviour.get(0).getAction(this, map);
			}
			return new MatingBehaviour(Status.STEGOSAUR,super.gender).getAction(this,map);
		}

		Action wander = behaviour.get(0).getAction(this, map);
		if (wander != null)
			return wander;
		
		return new DoNothingAction();
	}

}
