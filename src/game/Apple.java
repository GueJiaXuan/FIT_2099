package game;

import edu.monash.fit2099.engine.Location;

/**
 * Apple class that extends Portable Item
 * Apple rots in 20 Turns
 *
 */
public class Apple extends PortableItem {

    private int rotTime = 20;

    public Apple() {
        super("fruit", 'f');

        addCapability(Status.FRUIT);
    }

    public void tick(Location location){
        rotTime--;
        if (rotTime == 0){
            location.removeItem(this);
        }
    }
}
