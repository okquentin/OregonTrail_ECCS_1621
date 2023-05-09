import java.util.Random;
/**
*
* @author Anthony
* The randomEvents class is responsible for generating any events that randomly happen, and uses
* main to actually interact with other classes in the program
*/

public class Events {
	//injuries are handled by player
	Random rand = new Random();

	/**
	 * Rolls a d20. 
	 * @return rollResult
	 */
	public int roll() {
		int rollResult = rand.nextInt(20)+1;
		return(rollResult);
	}

	/**
	* Method to kill oxen
	* @param pace Passed from Map, responsible for chance calculation
	* @param distanceTraveled Passed from Map, responsible for chance calculation
	* @return boolean if an oxen is killed or not
	*/
	// public boolean oxenDeath(int pace){
	// 	int chance = 0;
	// 	switch(pace){
	// 		case 0:
	// 			chance = 0;
	// 			break;
	// 		case 1:
	// 			chance = rand.nextInt(11);
	// 			break;
	// 		case 2:
	// 			chance = rand.nextInt(20);
	// 			break;
	// 		case 3:
	// 			chance = rand.nextInt(30);
	// 			break;
	// 	}
	// 	if(chance != 0 && chance % 10 == 0){return true;}
	// 	else{return false;}
	// }

	public boolean badWater(){
		return false;
	}

	public boolean littleWater(){
		return false;
	}

	public boolean roughTrail(){
		return false;
	}

	/**
	* Method to initiate the wagon part loss/destruction
	* @return whichPart
	*/
	public int partBroke() {

		int partLoss = rand.nextInt(100);
		int whichPart = rand.nextInt(3);
		if(partLoss <= 1){
			switch(whichPart) {
				case 0: // Wheel
				return(5);

				case 1: // Axle
				return(6);

				case 2: // Tongue
				return(7);
			}
		}
		return(3);
	}

	/**
	* A robber steals an item from you.
	* No way to check if you have that item. 
	* @return whichItem
	*/
	public int robber() {

		int partLoss = rand.nextInt(200);
		if(partLoss == 1){
			int whichItem = rand.nextInt(5);
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
		int temp = rand.nextInt(100);
		if(temp <= 3){return true;}
		else{return false;}
	}

	/**
	 * Husband comes back with a semi random amount of food.
	 * @param childHelp
	 * @return foodHunted
	 */
	public int foodHunted(boolean childHelp) {
		int foodHunted = rand.nextInt(70)+30;
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
		int helpMod = 0;
		if(childHelp){
			cookFinal += 2;
			helpMod = 2;
		}

		int foodGained = 0;
		System.out.println("");
		System.out.println("You roll a " + cookRoll);
		System.out.println("Plus " + "cookXP: " + cookMod);
		System.out.println("Plus" + " childMod: " + helpMod);
		System.out.println("Results in " + cookFinal + ".");

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

		int whichOne = 0;
		int temp = rand.nextInt(100)+1;

		if(temp >= 80){whichOne = rand.nextInt(3);}

		switch (whichOne) {
			case 0:
			break;

			case 1:
			System.out.println("You pass another family on the trail.");
			System.out.println("You eat together that night and share some tips and tricks.");
			System.out.println("In the morning, you go your seperate ways.");
			break;

			case 2:
			System.out.println("You hear the howl of wolves in the distance...");
			break;

			case 3:
			System.out.println("You pass a small grave site.");
			System.out.println("You pay your respects and move on.");
			break;
		}

	}

}


