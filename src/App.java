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
		Time tm = new Time();
		Menus men = new Menus();
		Ascii art = new Ascii();
		Character player = new Character();
		Character husband = new Character();
		Character child1 = new Character();
		Character child2 = new Character();
		Character child3 = new Character();
		Character child4 = new Character();

		Events events = new Events();
		wagonClass wagon = new wagonClass();
		
		// To end the game play loop
		boolean gameEnd = false;
		boolean playerDeath = false;
		boolean childDeath = false;
		
		// Parameters to pass into methods
		int currDay, currPace, currDistance, dayChoice, currTerrain, riverDepth, riverLength, temperature, fortsPassed, currRation, partLoss, daysHungry = 0;
		int playerCount = 6;
		boolean badWater, littleWater, roughTrail, oxenDeath, wrongTrail, noParts, wagonStuck = false;
		double playerHealth = 0;
		String currWeather, currRiver, currTown, storeInventory;
		
		// Acii Art File List

		// Beginning of Game
		men.gameWelcome();
		tm.newDay();
		
		while(gameEnd == false) {
			
			// Updating parameters each day
			currDay = tm.getDay();
			currPace = tm.getPace();
			currDistance = tm.getDistance();
			currTerrain = tm.getTerrain();
			riverDepth = tm.getDepth();
			riverLength = tm.getLength();
			temperature = tm.getTemperature();
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
			if(currTerrain == 3) {
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
					int numOx = wagon.getAmountOfItem(1); // See storeClass.getInventory for item index # 
					Oxen myOx[] = new Oxen[numOx];
					if(numOx > 0) {
						for(int i = 0; i < (wagon.getAmountOfItem(1)); i++){myOx[i] = new Oxen();}
					}
				} // End of shop visit
				
				if(townChoice ==2) {}
				else {continue;}
			} // End of town visit
			
			// Allows the game to end once Oregon is reached
			if(gameEnd == true){break;}

			// Checks for events to occur
			oxenDeath = events.oxenDeath(currPace, currDistance);
			partLoss = events.partLoss();
			wrongTrail = events.wrongTrail();
			noParts = wagon.inventorySubtractor(partLoss, 1);
			badWater = events.badWater();
			littleWater = events.littleWater();
			roughTrail = events.roughTrail();

			// Oxen death (not implemented for now)
			if(oxenDeath == true) {
				int blank = 0;
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
				wagon.food -= 1 * playerCount;
				break;
				case 2:
				wagon.food -= 2 * playerCount;
				break;
				case 3:
				wagon.food -= 3 * playerCount;
				break;


			}

			// Starvation Mechanic
			if(wagon.food == 0){daysHungry++;}
			else{daysHungry = 0;}
			if(daysHungry > 3){
				playerDeath = true;
				gameEnd = true;
			}

			// Check for player deaths
			while(playerCount > 0){
				if(player.isDead){
					gameEnd = true;
					playerDeath = true;
				}
				if(gameEnd == true){break;}
		}
			
			
			// Daily prompt for advancing the game
			playerHealth = player.healthCheck(currPace, badWater, littleWater, roughTrail);
			men.dayPrompt();
			dayChoice = men.dayChoice();
			
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
		else {men.gameEnd();}
		
	} // End of main method
} // End of main class