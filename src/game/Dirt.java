package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents bare dirt.
 * Updates and grows into grass based on change
 */
public class Dirt extends Ground {

	private int tickCount = 0;


	public Dirt() {
		super('.');
		addCapability(Status.DIRT);
	}

	public void tick(Location location) {
		//Start of game. Has 2% chance for all dirt to grow into grass
		Random rand = new Random();
		if (tickCount == 0) {
			int rand0 = rand.nextInt(100);
			if (rand0 > 97) {
				location.setGround(new Grass());
				EcoPoints.gainEcoPoints(1);

			}
		}
		else {
			//Has 10% chance for dirt to grow into grass when there are more than 1 grass tile surrounding it
			if (grassNeighboursCount(location) > 1) {
				int rand1 = rand.nextInt(100);
				if (rand1 > 89) {
					location.setGround(new Grass());
					EcoPoints.gainEcoPoints(1);
				}
			}
			//Has 2% chance for dirt to grow into grass when there is at least one tree surrounding it.
			else if (treeNeighboursCount(location) >0){
				int rand2 = rand.nextInt(100);
				if (rand2 > 97){
					location.setGround(new Grass());
					EcoPoints.gainEcoPoints(1);
				}
			}
		}
		tickCount++;
	}

	/**
	 * Counts the number of grass tile surrouding a specific ground
	 * @param location of specific ground
	 * @return number of grass tiles surrounding it
	 */
	private int grassNeighboursCount(Location location) {
		List<Exit> exitList = location.getExits();

		List<Ground> groundList = new ArrayList<>();
		for (Exit exit: exitList){
			groundList.add(exit.getDestination().getGround());

		}
		int final_counter = 0;
		for (Ground ground: groundList){
			if (ground.hasCapability(Status.GRASS)){
				final_counter++;
			}
		}
		return final_counter;
	}

	/**
	 * Counts the number of tree tile surrouding a specific ground
	 * @param location of specific ground
	 * @return number of tree tiles surrounding it
	 */
	private int treeNeighboursCount(Location location) {
		List<Exit> exitList = location.getExits();

		List<Ground> groundList = new ArrayList<>();
		for (Exit exit: exitList){
			groundList.add(exit.getDestination().getGround());

		}
		int final_counter = 0;
		for (Ground ground: groundList){
			if (ground.hasCapability(Status.TREE)){
				final_counter++;
			}
		}
		return final_counter;
	}

}
