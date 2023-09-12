package oregon_trail;
/**
* This class is very similar to Player, just less featured, much like the original game.
* Mostly responsible for controlling disease and injury.
* @author Anthony
*
*/
public class Oxen {
	boolean isDead;
	double health = 0;
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
	double hungerHealthFactor = 0.5;
	double healFactor = 0.9;
	//Is this the same base as the player? yeah.
	// health code
	//this line should run once per day, regardless of ailments
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
