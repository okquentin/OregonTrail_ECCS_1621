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
		storeClass store = new storeClass();
		Player player = new Player();
		randomEvents events = new randomEvents();
		wagonClass wagon = new wagonClass();
		
		// To end the game play loop
		boolean gameEnd = false;
		boolean playerDeath = false;
		
		// Parameters to pass into methods
		int currDay, currPace, currDistance, dayChoice, currTerrain, riverDepth, riverLength, temperature, fortsPassed, currRation, playerCount;
		String currWeather, currRiver, currTown, storeInventory;
		
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
			playerCount = 1;
			
			currTown = tm.getTown();
			currWeather = tm.getWeather();
			currRiver = tm.getRiver();
			
			men.displayDay(currDay, currPace, currTerrain, currDistance, currRation, currWeather);

			player.pace = currPace;
	
			
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
				currTown = tm.getTown();
				if(currTown == "Ash Hollow") {gameEnd = true;}
				fortsPassed = tm.getFortsPassed();
				
				boolean visitShop, exitTown;
				
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
					
					men.displayInventory(store.getInventory()); // Displaying inventory before shopping
					
					// Prompting the player for purchasing items
					storeInventory = store.getStoreInventory(fortsPassed);
					men.shopPrompt(storeInventory);
					shopChoice = men.shopChoice();
					visitShop = tm.townVisit(shopChoice);
							
					if(visitShop == false) {System.out.println(""); break;} // Leaves the store
					
					// Prompting the player for choosing quantity of purchase
					men.amountPrompt();
					amountChoice = men.amountChoice();
					store.moneySpentPerItem(shopChoice, amountChoice, fortsPassed);
					store.addItemsToWagon(shopChoice, amountChoice);
					
					// Instantiating new Ox objects when buying them
					int numOx = store.getAmountOfItem(1); // See storeClass.getInventory for item index # 
					Oxen myOx[] = new Oxen[numOx];
					if(numOx > 0) {
						for(int i = 0; i < (store.getAmountOfItem(1)); i++){myOx[i] = new Oxen();}
					}
				} // End of shop visit
				
				if(townChoice ==2) {}
				else {continue;}
			} // End of town visit
			
			// Allows the game to end once Ash Hollow is reached
			
			// Checks for random event to occur
			boolean oxenDeath, partLoss, wrongTrail = false;
			oxenDeath = events.oxenDeath(currPace, currDistance);
			partLoss = events.partLoss(currPace, currDistance, temperature);
			wrongTrail = events.wrongTrail();
			
			// Oxen death (not implemented for now)
			if(oxenDeath == true) {
				int blank = 0;
			}
			
			// Part loss (not implemented for now)
			if(partLoss == true) {
				int blank  = 0;
			}
			// Wrong rail (not implemented for now)
			if(wrongTrail == true) {
				int blank = 0;
			}
			
			if(gameEnd == true) {break;}
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
			if(wagon.food == 0){playerDeath = true;}
			
			// Daily prompt for advancing the game
			men.dayPrompt();
			dayChoice = men.dayChoice();
			
			switch(dayChoice) {
				case 1:
					men.displayInventory(store.getInventory());
					break;
				case 2:
					men.pacePrompt();
					currPace = men.paceChoice();
					tm.setPace(currPace);
					break;
				case 3:
					men.rationPrompt();
					currRation = men.rationChoice();
					

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