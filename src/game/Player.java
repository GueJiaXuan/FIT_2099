package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	private int ecoPoints = 0;
	private GameMap oldMap;
	private GameMap newMap;

	/**
	 * 3
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, GameMap oldMap, GameMap newMap) {
		super(name, displayChar, hitPoints);
		this.oldMap = oldMap;
		this.newMap = newMap;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		System.out.println("Player has : " +EcoPoints.getEcoPoints() + " Eco Points.");
		// Handle multi-turn Actions
		if (map.locationOf(this).getGround().hasCapability(Status.TREE)) {
			actions.add(new PickFruitAction(map.locationOf(this)));
		} else if (map.locationOf(this).getGround().hasCapability(Status.GRASS)) {
			actions.add(new HarvestAction(map.locationOf(this)));
		}
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}
		if (map == oldMap) {
			if (map.locationOf(this).y() == 0) {
				actions.add(new MoveActorAction(newMap.at(map.locationOf(this).x(), 24), "to new park."));
			}
		}
		if (map == newMap) {
			if (map.locationOf(this).y() == 24) {
				actions.add(new MoveActorAction(oldMap.at(map.locationOf(this).x(), 0), "to old park. "));
			}
		}

		actions.add((new QuitAction()));

		return menu.showMenu(this, actions, display);

	}

}
