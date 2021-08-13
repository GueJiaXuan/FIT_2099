package game;

import edu.monash.fit2099.engine.Location;
/**
 * Class for item Hay. Extends Portable Item.
 */
public class Hay extends PortableItem {

    public Hay() {
        super("hay", 'H');

        addCapability(Status.HAY);
    }

    public void tick(Location location){

    }
}
