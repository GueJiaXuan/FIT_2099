package edu.monash.fit2099.engine;

import game.*;

import java.sql.SQLOutput;
import java.util.*;

/**
 * Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid.
 */
public class World {
	protected Display display;
	protected ArrayList<GameMap> gameMaps = new ArrayList<GameMap>();
	protected ActorLocations actorLocations = new ActorLocations();
	protected Actor player; // We only draw the particular map this actor is on.
	protected Map<Actor, Action> lastActionMap = new HashMap<Actor, Action>();

	/**
	 * Constructor.
	 * 
	 * @param display the Display that will display this World.
	 */
	public World(Display display) {
		Objects.requireNonNull(display);
		this.display = display;
	}

	/**
	 * Add a GameMap to the World.
	 * @param gameMap the GameMap to add
	 */
	public void addGameMap(GameMap gameMap) {
		Objects.requireNonNull(gameMap);
		gameMaps.add(gameMap);
		gameMap.actorLocations = actorLocations;
	}

	/**
	 * Set an actor as the player. The map is drawn just before this Actor's turn
	 * 
	 * @param player   the player to add
	 * @param location the Location where the player is to be added
	 */
	public void addPlayer(Actor player, Location location) {
		this.player = player;
		actorLocations.add(player, location.map().at(location.x(), location.y()));
		actorLocations.setPlayer(player);
	}

	/**
	 * Runs this code when game/Application is running
	 */
	public void run_game(){
		EcoPoints.setEcoPoints(0);
		if (player == null)
			throw new IllegalStateException();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Dinosaur game!" + "\n");
		System.out.println("1. Challenge " + "\n");
		System.out.println("2. SandBox Mode "+ "\n");
		System.out.println("3. Close Program " + "\n");
		System.out.println("Choose game mode: ");
		int input = scanner.nextInt();
		if (input > 4 || input < 0){
			System.out.println("Invalid input. Input either 1 , 2 or 3."+ "\n");
			run_game();
		}
		else if (input == 3){
			System.out.println("Game Program has ended." + "\n");
			System.exit(0);
		}
		else if (input == 2){
			System.out.println("!!!!!SANDBOX GAME MODE START!!!!!"+ "\n");
			run();
		}
		else if (input == 1){
			System.out.println("You have selected Challenge Mode"+ "\n");
			System.out.println("Key in number of Moves: ");
			int moves = scanner.nextInt();
			if (moves < 1){
				System.out.println("Invalid input. Number of moves must be positive.");
				run_game();
			}
			System.out.println("Key in number of EcoPoints to aim for: ");
			int points = scanner.nextInt();
			if (points < 1){
				System.out.println("Invalid input. Number of EcoPoints must be positive.");
				run_game();
			}
			System.out.println("!!!!!CHALLENGE GAME MODE START!!!!!"+ "\n");
			System.out.println("Moves: " + moves + " || EcoPoints to get: " + points + "\n");
			run_challenge(moves, points);

		}



	}
	/**
	 * Run the game.(in sandbox mode)
	 *
	 * On each iteration the gameloop does the following: - displays the player's
	 * map - processes the actions of every Actor in the game, regardless of map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors. We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()){
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage() + "\n");
		run_game();
	}

	/**
	 * Runs the game in Challenge Mode
	 * @param moves number of moves before game is over
	 * @param ecoPoints number of ecoPoints to achieve before game is over
	 */
	public void run_challenge(int moves, int ecoPoints){
		int moves_count = 0;
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()){

			moves_count ++;
			//checks whether moves_count is more than the moves to aim for
			if (moves_count > moves){
				display.println("You lose! You did not achieve enough Eco Points." + "\n");
				display.println(endGameMessage());
				run_game();
			}
			//checks whether eco points is more than the eco points achieved
			if (EcoPoints.getEcoPoints() > ecoPoints){
				display.println("You win! You achieved enough Eco Points." + "\n");
				display.println(endGameMessage());
				run_game();
			}
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage());
		run_game();
	}

	/**
	 * Gives an Actor its turn.
	 *
	 * The Actions an Actor can take include:
	 * <ul>
	 * <li>those conferred by items it is carrying</li>
	 * <li>movement actions for the current location and terrain</li>
	 * <li>actions that can be done to Actors in adjacent squares</li>
	 * <li>actions that can be done using items in the current location</li>
	 * <li>skipping a turn</li>
	 * </ul>
	 *
	 * @param actor the Actor whose turn it is.
	 */
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
			// Game rule. If you're carrying it, you can drop it.
			actions.add(item.getDropAction());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();

			// Game rule. You don't get to interact with the ground if someone is standing
			// on it.
			if (actorLocations.isAnActorAt(destination)) {
				actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
			} else {
				actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
			}
			actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
			// Game rule. If it's on the ground you can pick it up.
			actions.add(item.getPickUpAction());
		}
		actions.add(new DoNothingAction());

		Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
		lastActionMap.put(actor, action);
		
		String result = action.execute(actor, map);
		display.println(result);
	}

	/**
	 * Returns true if the game is still running.
	 *
	 * The game is considered to still be running if the player is still around.
	 *
	 * @return true if the player is still on the map.
	 */
	protected boolean stillRunning() {
		return actorLocations.contains(player);
	}

	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Game Over"
	 */
	protected String endGameMessage() {
		return "Game Over";
	}
}
