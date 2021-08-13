package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * AgilisaurusEgg, hatches after 30 turns to become a baby Agilisaurus
 */
public class AgilisaurusEgg extends PortableItem {
    private int age = 0;

    public AgilisaurusEgg (String name) {
        super(name, '`');
    }
    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        age++;
        if (age == 30) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Agilisaurus("Agilisaurus", 0));
            EcoPoints.gainEcoPoints(250);
        }
    }
}
