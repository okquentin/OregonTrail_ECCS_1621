import java.io.FileNotFoundException;
import java.io.File;
import java.util.Random;

/**
 * Class: App.java
 * 
 * Allows the Time, Menus, storeClass, Player, randomEvents, and Oxen classes to interact
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 4/4/2023
 */
public class App {
	public static void main(String[] args) {
		
		// Object instantiations
		Random rand = new Random();
		Time tm = new Time();
		Menus men = new Menus();
		Ascii art = new Ascii();
		Events events = new Events();
		wagonClass wagon = new wagonClass();

		// Character Instantiations (See index at Character.java)
		int numPlayer = 6;
		Character[] player = new Character[numPlayer];
		for(int i = 0; i < numPlayer; i++){
			player[i] = new Character();
			player[i].setName(i);
		}
		int[] playerHealth = new int[numPlayer];
		for(int i = 0; i < numPlayer; i++){
			playerHealth[i] = 0;
		}
		boolean[] death = new boolean[numPlayer];


		int numOx = 1;
		Oxen[] myOx = new Oxen[numOx];
		double[] oxHealth = new double[numOx];
		boolean[] oxDeath = new boolean[numOx];

		// To end the game play loop
		boolean gameEnd = false;
		boolean playerDeath = false;
		boolean tragedy = false;

		// Parameters to pass into methods
		int currDay, currPace, currDistance, dayChoice, currTerrain, riverDepth, riverLength, fortsPassed, currRation, partLoss, artIndex, daysHungry = 0;
		boolean badWater, littleWater, roughTrail, oxenDeath, wrongTrail, noParts, wagonStuck = false;
		String currWeather, currRiver, currTown, storeInventory;

		// Skill Factor
		int cookMod = 0;

		// Beginning of Game
		try {art.printArt(0);} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		
		tm.newDay();
		
		while(gameEnd == false) {
			
			// Updating parameters each day
			currDay = tm.getDay();
			currPace = tm.getPace();
			currDistance = tm.getDistance();
			currTerrain = tm.getTerrain();
			riverDepth = tm.getDepth();
			riverLength = tm.getLength();
			currRation = tm.getRation();

			// Determining when landmarks are reached
			currTown = tm.getTown();
			currWeather = tm.getWeather();
			currRiver = tm.getRiver();
	
			men.displayDay(currDay, currPace, currTerrain, currDistance, currRation, currWeather);

			// River cross check
			if(currTerrain == 2) {
				boolean cross;
				men.riverPrompt(currRiver, riverDepth, riverLength);
				int choice = men.riverChoice();
				cross = tm.riverCross(choice);
				men.crossResult(cross);
				if(cross == false) {
					gameEnd = true;
					playerDeath = true;
				}
			}
			
			// Town visit check
			else if(currTerrain == 3) {
				boolean visitShop, exitTown;

				currTown = tm.getTown();
				if(currTown == "Willamette Valley") {gameEnd = true; break;}
				fortsPassed = tm.getFortsPassed();
				
				men.townPrompt(currTown);
				int townChoice = men.townChoice();
					
				visitShop = tm.townVisit(townChoice);
				
				// Allows the town prompt to loop for each day resting in town
				if(townChoice == 1) {continue;}
				
				// Brings up the day info after leaving town, with updated pace (to change from resting, if rested)
				exitTown = tm.getExitTown();
				if(exitTown) {
					currPace = tm.getPace();
					men.displayDay(currDay, currPace, currTerrain, currDistance, currRation, currWeather);
				}
				
				// Shop visit check
				while(visitShop == true) {	
					int shopChoice, amountChoice;
					
					men.displayInventory(wagon.getInventory()); // Displaying inventory before shopping
					
					// Prompting the player for purchasing items
					storeInventory = wagon.getStoreInventory(fortsPassed);
					men.shopPrompt(storeInventory);
					shopChoice = men.shopChoice();
					visitShop = tm.townVisit(shopChoice);
							
					if(visitShop == false) {System.out.println(""); break;} // Leaves the store
					
					// Prompting the player for choosing quantity of purchase
					men.amountPrompt();
					amountChoice = men.amountChoice();
					wagon.moneySpentPerItem(shopChoice, amountChoice, fortsPassed);
					wagon.addItemsToWagon(shopChoice, amountChoice);
					
					// Instantiating new Ox objects when buying them
					numOx += wagon.getAmountOfItem(1); // See storeClass.getInventory for item index # 
					if(numOx > 0) {
						for(int i = 0; i < (wagon.getAmountOfItem(1)); i++){myOx[i] = new Oxen();}
					}

				} // End of shop visit
				
				if(townChoice ==2) {}
				else {continue;}
			} // End of town visit
			
			// Allows the game to end once Oregon is reached
			if(gameEnd == true){break;}
			
			// Hunting/Cooking Event every week
			else if(currDay % 7 == 0){
				men.huntPrompt();
				men.helpPrompt();
				boolean choice = men.helpChoice();
				if(choice){
					boolean hunt = false;
					int temp = 0;
					while(!hunt){
						temp = rand.nextInt(4)+2;
						if(!player[temp].isDead){hunt = true;}
					}
					int harm = player[temp].childHarm();
					String name = player[temp].name;

					men.displayHarm(harm, name);
				}
				int numHunted = events.foodHunted(choice);

				men.cookPrompt(numHunted);
				men.helpPrompt();
				boolean choice2 = men.helpChoice();
				events.cookingMinigame(numHunted, cookMod, choice2);
			}

			// Checks for events to occur
			oxenDeath = events.oxenDeath(currPace, currDistance);
			partLoss = events.partLoss();
			wrongTrail = events.wrongTrail();
			noParts = wagon.inventorySubtractor(partLoss, 1);
			badWater = events.badWater();
			littleWater = events.littleWater();
			roughTrail = events.roughTrail();

			// Oxen death Mechanic
			if(oxenDeath == true) {
				for(int i = 0; i < numOx; i++){myOx[i] = null;}
			}

			if(noParts){
				wagonStuck = true;
				men.stuckPrompt(partLoss);
			}
			
			// Wrong trail (not implemented for now)
			if(wrongTrail == true) {
				int blank = 0;
			}
			
			//Player Hunger
			switch(currRation){
				case 0:
				wagon.food -= 1 * numPlayer;
				break;
				case 2:
				wagon.food -= 2 * numPlayer;
				break;
				case 3:
				wagon.food -= 3 * numPlayer;
				break;
			}

			// Starvation Mechanic
			if(wagon.food == 0){daysHungry++;}
			else{daysHungry = 0;}
			if(daysHungry > 3){
				playerDeath = true;
				gameEnd = true;
			}
			
			// Health Checks for all Characters
			for(int i = 0; i < numPlayer; i++){
				playerHealth[i] = player[i].healthCheck(currPace, badWater, littleWater, roughTrail);
				System.out.println(playerHealth[i]);
			}

			// Check for player deaths
			if(player[0].isDead){
				playerDeath = true;
				gameEnd = true;
			}

			for(int i = 1; i < numPlayer; i++){
				if(player[i].isDead && !death[i]){
					men.characterDeath(i); 
					death[i] = true;
					tragedy = true;
				}
			}

			if(gameEnd == true){break;}

			// Daily prompt for advancing the game
			men.dayPrompt();
			dayChoice = men.dayChoice();

			// FOR DEBUG
			
			// Allows player to choose what to do each day
			switch(dayChoice) {
				case 1:
					men.displayInventory(wagon.getInventory());
					break;
				case 2:
					men.pacePrompt();
					currPace = men.paceChoice();
					if(wagonStuck){
						men.stuckPace();
						break;
					}
					tm.setPace(currPace);
					break;
				case 3:
					men.rationPrompt();
					currRation = men.rationChoice();
					tm.setRation(currRation);
					break;	
				case 4:
					tm.newDay();
					break;	
			}
		}
		// Chooses text display based upon game outcome 
		if(playerDeath){men.gameDeath();}
		else if(tragedy){men.tragicEnd();}
		else {men.gameEnd();}
		
	} // End of main method
} // End of main class