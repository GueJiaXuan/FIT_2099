package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * Allosaur Eggs. Hatches into a baby steogsaur after staying on the ground for 75 turns.
 */
public class AllosaurEgg extends PortableItem {
    private int age = 0;

    public AllosaurEgg(String name) {
        super(name, 'O');
    }
    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        age++;
        if (age == 75) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Allosaur("Allosaur",0));
            EcoPoints.gainEcoPoints(1000);
        }
    }}
