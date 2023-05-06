import java.util.Random;
/**
 * @author Anthony
 * Player is used to create and manage the human characters that the user is responsible for.
 * 
 * [Character Index]
 * 0: Hattie
 * 1: Father
 * 2: Erin
 * 3: Bridget
 * 4: Jesse
 * 5: Scott
 * 6: Sarah
 */

public class Character {
    String name;
    boolean isDead;
    boolean diseaseStatus;
    boolean injuryStatus;
    int health;
    int hunger;
    int rationSize;
    int clothingQual;
    int diseasedDays = 11;
    int injuredDays = 31;
    Random rand = new Random();
    
   
    // buffs and debuffs (future use)
    int hungerFactor = 2;
    int injuryFactor = 1;
    double hungerHealthFactor = 0.5;
   
    public Character(){
        isDead = false;
        diseaseStatus = false;
        injuryStatus = false;
        health = 0;
    }
      
    /**
     * Used to add to the health variable when a player is diseased. This initially adds 20 to the variable.
     * Resets diseasedDays, which is used in other methods
     * @return Returns True
     */
    public int childHarm(){
        int chance = rand.nextInt(100);
        if(chance <= 5){
            isDead = true;
            return 1;
        }
        if(chance <= 10 ){
            injured();
            return 2;
        }
        if(chance <= 20){
            disease();
            return 3;
        }
        return  0;
    }

    public String setName(int index){
        switch(index){
            case 0:
                name = "Hattie";
                break;
            case 1:
                name = "Father";
                break;
            case 2:
                name = "Erin";
                break;
            case 3:
                name = "Bridget";
                break;
            case 4:
                name = "Jesse";
                break;
            case 5:
                name = "Scott";
                break;
            case 6:
                name = "Sarah";
                break;
        }
        return name;
    }

    public void disease() {
        health += 20;
        diseasedDays = 0;
        diseaseStatus = true;
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
    public void injured() {
        injuredDays = 0;
        injuryStatus = true;
    }
   
    /**
     * The higher the health, the more likely the player is to die. Should be thought of as a negative health bar
     * @param pace Int that is used to calculate how much health should be added or removed
     * @param badWater Bool that is used to calculate health
     * @param littleWater Bool that is used in a very similar way to badWater
     * @param roughTrail Boolean that is used in a very similar way to the other booleans
     * @return Returns health of the player object
     */
    public int healthCheck(int pace, boolean badWater, boolean littleWater, boolean roughTrail) {
       
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

        //these are all special circumstances, should also only run once a day
        if (health > 140) {
            isDead = true;
        }
   
    //Happen regardless of ailments
    health -= rationSize;
    health += (pace * 2);

    if(health < 0){health = 0;}

    return health;
    }
}








