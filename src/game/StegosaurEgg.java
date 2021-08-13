package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.List;
/**
 * Class for item StegosaurEgg. Extends Portable Item.
 * Stegosaur Egg hatches in 20 turns after being on the ground.
 */
public class StegosaurEgg extends PortableItem {
    private int age = 0;

    public StegosaurEgg (String name) {
        super(name, '*');
    }
    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        age++;
        if (age == 20) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Stegosaur("Stegosaur",0));
            EcoPoints.gainEcoPoints(100);
        }
    }}
