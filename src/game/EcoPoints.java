package game;

/**
 * Eco Points class where EcoPoints are updates
 * Gain/Set/Get methods are here to update EcoPoints
 */
public class EcoPoints {
    private static int point;


    public static void gainEcoPoints(int gainPoints){
            point+= gainPoints;
    }
    public static int getEcoPoints(){
            return point;
    }
    public static void setEcoPoints(int setPoints){ point = setPoints; }
}
