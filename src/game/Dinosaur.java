package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * Dinosaur Class.
 * Is a super class for Stegosaur/Allosaur/Agilisaurus/Archaeopteryx Classes
 */
public class Dinosaur extends Actor {
    private Random rand = new Random();
    protected int hungerPoints;
    protected int maxHungerPoints = 100;
    protected int thirstPoints;
    protected int maxThirstPoints = 100;
    protected boolean gender; //true represents female, false represents male
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     *
     */
    public Dinosaur(String name, char displayChar, int hitPoints, int HungerPoints, int ThirstPoints) {
        super(name, displayChar, hitPoints);
        this.hungerPoints = HungerPoints;
        this.thirstPoints = ThirstPoints;
        //generate gender here //true represents female, false represents male
        this.gender = rand.nextBoolean();

    }

    /**
     * To increase hunger points for Dinosaur
     * @param hungerPointsIncrease amount of hunger points to be increased by.
     */
    public void Feed(int hungerPointsIncrease){
        hungerPoints += hungerPointsIncrease;
        hungerPoints = Math.min(hungerPoints, maxHungerPoints);
    }
    /**
     * To increase thirst points for Dinosaur
     * @param thirstPointsIncrease amount of thirst points to be increased by.
     */
    public void Drink(int thirstPointsIncrease){
        thirstPoints += thirstPointsIncrease;
        thirstPoints = Math.min(thirstPoints, maxThirstPoints);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
