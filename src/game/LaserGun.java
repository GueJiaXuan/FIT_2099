package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class for item LaserGun. Extends Weapen Item.
 */
public class LaserGun extends WeaponItem {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public LaserGun(String name, char displayChar, int damage, String verb) {
        super("LaserGun",'L',50,"shoot ");
    }
}
