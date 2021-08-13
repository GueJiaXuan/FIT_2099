package game;

import edu.monash.fit2099.engine.*;

/**
 * Mating Behaviour is called when dinosaur has more than 50 hungerPoints
 * Allows Dinosaur to mates and lay eggs. (this is implemented in MateAction and in Dinosaur classes respectively)
 */
public class MatingBehaviour implements Behaviour {
    protected Enum<?> dinoType;
    protected boolean Gender;

    public MatingBehaviour(Enum<?> DinoCapability, boolean genderType){
        this.dinoType = DinoCapability;
        this.Gender = genderType;
    }
    @Override
    public Action getAction(Actor actor, GameMap map) {

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            //whether it contain an actor
            if (destination.containsAnActor()){
                //whether its the same dinotype and whether its opposite gender and whether its not hungry or thirsty
                if (destination.getActor().hasCapability(dinoType) && ((Dinosaur)destination.getActor()).gender != Gender
                && ((Dinosaur)destination.getActor()).hungerPoints > 50 & ((Dinosaur)destination.getActor()).thirstPoints > 50){
                    //whether if any of the dinosaurs are pregnant
                    if (actor.hasCapability(Status.PREGNANT) ||destination.getActor().hasCapability(Status.PREGNANT)){
                        return new WanderBehaviour().getAction(actor, map);
                    }
                    return new MateAction(Gender);
                }
            }
        }
        return new WanderBehaviour().getAction(actor, map);
    }
}
