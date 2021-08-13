package game;

import edu.monash.fit2099.engine.Location;
/**
 * Class for item Vegetarian Meal Kit. Extends Portable Item.
 */
public class VegetarianMealKit extends PortableItem {

    public VegetarianMealKit() {
        super("VegetarianMealKit", 'V');

        addCapability(Status.VEGETARIANMEALKIT);
    }

    public void tick(Location location){

    }
}
