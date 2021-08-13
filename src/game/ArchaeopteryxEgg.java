package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * Archaropteryx Egg. Hatches into a babry Archaeopteryx after 40 turns on the ground.
 */
public class ArchaeopteryxEgg extends PortableItem {
    private int age = 0;

    public ArchaeopteryxEgg (String name) {
        super(name, '"');
    }
    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        age++;
        if (age == 40) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Archaeopteryx("Archaeopteryx", 0));
            EcoPoints.gainEcoPoints(350);
        }
    }}
