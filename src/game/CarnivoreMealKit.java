package game;

import edu.monash.fit2099.engine.Location;
/**
 * Class for item Carnivore Meal Kit. Extends Portable Item.
 */
public class CarnivoreMealKit extends PortableItem {

    public CarnivoreMealKit() {
        super("CarnivoreMealKit", 'C');

        addCapability(Status.CARNIVOREMEALKIT);
    }

    public void tick(Location location){

    }
}
