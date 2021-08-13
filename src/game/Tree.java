package game;

import edu.monash.fit2099.engine.*;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

/**
 * Tree Class that extends Ground.
 * Starts off as '+', then 't', then 'T'.
 * Fruit can be dropped by chance on this tile.
 */
public class Tree extends Ground {

	private int age = 0;


	public Tree() {
		super('+');
		addCapability(Status.TREE);

	}

	@Override
	public void tick(Location location) {
		//super.tick(location);
		Random rand = new Random();

		int rand0 = rand.nextInt(100);
		if (rand0 > 94){

			location.addItem(new Apple());

		}

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';


	}


}
