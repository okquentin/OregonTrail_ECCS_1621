/**
 * @author Anthony
 * Player is used to create and manage the human characters that the user is responsible for.
 */
public class Player {
    String name;
    boolean isDead;
    int score;
    char type;
    double health = 0;
    int pace;
    int daysWithoutRest;
    int hunger;
    int rationSize;
    int clothingQual;
    int diseasedDays = 11;
    int injuredDays = 31;
    
   
    // buffs and debuffs (future use)
    int hungerFactor = 2;
    int injuryFactor = 1;
    double hungerHealthFactor = 0.5;
    double healFactor = 0.9;
   
   
    // health code
   
    //this line should run once per day, regardless of ailments
    /**
     * Used to add to the health variable when a player is diseased. This initially adds 20 to the variable.
     * Resets diseasedDays, which is used in other methods
     * @return Returns True
     */
    public boolean disease() {
        health += 20;
        diseasedDays = 0;
        return true;
    }

    public void eating(int pace){
        


    }
    public void setRation(int ration){
        rationSize = ration;
    }
   
    /**
     * Used in the same way as disease(), just for injuries
     * @return Returns True when called.
     */
    public boolean injured() {
        injuredDays = 0;
        return true;
    }
   
    /**
     * The higher the health, the more likely the player is to die. Should be thought of as a negative health bar
     * @param pace Int that is used to calculate how much health should be added or removed
     * @param badWater Bool that is used to calculate health
     * @param littleWater Bool that is used in a very similar way to badWater
     * @param roughTrail Boolean that is used in a very similar way to the other booleans
     * @return Returns health of the player object
     */
    public double healthCheck(int pace, boolean badWater, boolean littleWater, boolean roughTrail) {
        if(diseasedDays < 11) {
            health += 1;
        }
        if(injuredDays < 31) {
            health += injuryFactor;
        }
       
        //these are all special circumstances, should also only run once a day
        if (health > 140) {
            isDead = true;
        }
       
        health = health - injuryFactor;
        //Controls for random world events
        if(badWater == true) {
            health += 20;
        }
        if(littleWater == true) {
            health += 10;
        }
        if(roughTrail == true) {
            health += 10;
        }
   
    //Happen regardless of ailments
    health += rationSize;
    health = health * healFactor;
    health += (pace * 2);
   
   
    return health;
    }
}








