import java.util.Random;
/**
*
* @author Anthony
* The randomEvents class is responsible for generating any events that randomly happen, and uses
* main to actually interact with other classes in the program
*/

public class randomEvents {
	//injuries are handled by player
	Random rand = new Random();

	/**
	 * Rolls a d20. 
	 * @return rollResult
	 */
	public int roll() {
		int rollResult = rand.nextInt(1,20);
		return(rollResult);
	}

	/**
	* Method to kill oxen
	* @param pace Passed from Map, responsible for chance calculation
	* @param distanceTraveled Passed from Map, responsible for chance calculation
	* @return boolean if an oxen is killed or not
	*/
	public boolean oxenDeath(int pace, int distanceTraveled){
	int distTraveled = distanceTraveled;
	int bound = 100;
	bound -= distTraveled/50;
	bound -= (pace - 2);
	int deathChance = rand.nextInt(bound);
	if(deathChance == 2){
	return true;
	}
	else{
	return false;
	}
	}

	/**
	* Method to initiate the wagon part loss/destruction
	* @return whichPart
	*/
	public int partLoss() {

		int partLoss = rand.nextInt(100);
		int whichPart = rand.nextInt(3);
		if(partLoss == 1){
			switch(whichPart) {
				case 1: // Wheel
				return(5);

				case 2: // Axle
				return(6);

				case 3: // Tongue
				return(7);
			}
		}
		return(0);
	}

	/**
	* A robber steals an item from you.
	* @return whichItem
	*/
	public int robber() {

		int partLoss = rand.nextInt(200);
		int whichItem = rand.nextInt(3);
		if(partLoss == 1){
			switch(whichItem) {
				case 1: // Oxen
				return(1);

				case 2: // Clothes
				return(3);

				case 3: // Wheel
				return(5);

				case 4: // Axle
				return(6);

				case 5: // Tongue
				return(7);
			}
		}
		return(0);
	}

	/**
	* Method to initiate the wrong trail event
	* @return Returns a boolean if the wrong trail is taken. Totally random chance every time this is called.
	*/
	public boolean wrongTrail(){
	if(rand.nextInt(40) == 1){
	return true;
	}else{
	return false;
	}
	}

	/**
	 * Husband comes back with a semi random amount of food.
	 * @param childHelp
	 * @return foodHunted
	 */
	public int foodHunted(boolean childHelp) {
		int foodHunted = rand.nextInt(30,100);
		if(childHelp){
			foodHunted += 20;
		}

		return(foodHunted);
	}

	/**
	 * Has player cook and preserve hunted food to get the edible amount of food available.
	 * Gets called after foodHunted. 
	 * @param foodHunted
	 * @param cookMod
	 * @param childHelp
	 * @return foodGained
	 */
	public int cookingMinigame(int foodHunted, int cookMod, boolean childHelp) {

		int cookRoll = roll();
		int cookFinal = cookRoll + cookMod;
		if(childHelp){
			cookFinal += 2;
		}

		int foodGained = 0;

		System.out.println("Your husband comes back from hunting with " + foodHunted + " lbs of food.");
		System.out.println("You decide to cook and preserve the food.");
		System.out.println("You roll a " + cookRoll + " plus " + cookMod + " results in " + cookFinal + ".");

		if(cookRoll == 1) {
			foodGained = foodHunted / 2;
			System.out.println("You ruined it! ");
		}
		else if(cookRoll == 20) {
			foodGained = foodHunted * 2;
			System.out.println("Wow! It's delicious!");
		}
		else {
			foodGained = foodHunted + (cookFinal - 10) * 2;
			if(foodGained == foodHunted) {
				System.out.println("You did an alright job.");
			}
			else if(foodGained > foodHunted) {
				System.out.println("You did a great job!");
			}
			else {
				System.out.println("...Ew...");
			}
		}

		System.out.println("This resulted in " + foodGained + "lbs of food for you and your family.");

		return(foodGained);
	}

	public void NPC() {

		int whichOne = rand.nextInt(3);

		switch (whichOne) {
			case 1:
			System.out.println("You pass another family on the trail.");
			System.out.println("You eat together that night and share some tips and tricks.");
			System.out.println("In the morning, you go your seperate ways.");
			break;

			case 2:
			System.out.println("You hear some wolves in the distance.");
			System.out.println("You decide to avoid that.");
			break;

			case 3:
			System.out.println("You pass a small grave site.");
			System.out.println("You pay your respects and move on.");
			break;
		}

	}

}


