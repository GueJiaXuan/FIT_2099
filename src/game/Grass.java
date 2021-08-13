package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Grass Class.
 * Dirt grows into grass based on chance (shown in dirt class)
 */
public class Grass extends Ground{

    public Grass(){
        super(',');

        addCapability(Status.GRASS);
    }


}
