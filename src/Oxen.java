/**
* This class is very similar to Player, just less featured, much like the original game.
* Mostly responsible for controlling disease and injury.
* @author Anthony
*
*/

public class Oxen {
	boolean isDead;
	int health = 0;
	int daysTraveled;
	int pace;
	int daysWithoutRest;
	int hunger;
	int grassAmount;
	int diseasedDays = 11;
	int injuredDays = 31;
	// buffs and debuffs (future use)
	int hungerFactor = 2;
	int injuryFactor = 0;

   
    public Oxen(){
        isDead = false;
        health = 0;
    }

	public int healthCheck(){
		if(diseasedDays < 11) {
            health += 1;
            diseasedDays++;
        }
        if(injuredDays < 31) {
            health += injuryFactor;
            injuredDays++;
        }
       
        //these are all special circumstances, should also only run once a day
        if (health > 140) {
            isDead = true;
        }

		health += (pace * 2);
		
		return health;
	}

	/**
	 * Adds to health variable which can be used in the future to kill the oxen. N
	 * @return True, just in case it is needed in any class
	 */
	public boolean disease() {
		health += 20;
		diseasedDays = 0;
		return true;
	}

	/**
	 * Will handle oxen injury in the future
	 * @return True if the oxen has the disease.
	 */
	public boolean injured() {
		injuredDays = 0;
		return true;
	}
}
