import java.util.Scanner;

/**
 * Provides console I/O for the text-based game UI
 * Implemented using the Scanner class
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 4/4/2023
 */


public class Menus {
	
	Scanner sc = new Scanner(System.in);
	
    /**
     * Welcomes the player to the game
    */
	public void gameWelcome() {
		System.out.println("Welcome to the Oregon Trail, Hattie Campbell!");
		System.out.println("");
	}
	
    /**
     * Displays world/player status each day
     * @param day
     * @param pace
     * @param terrain
     * @param distance
     * @param weather
	 * @param ration
    */
	public void displayDay(int day, int pace, int terrain, int distance, int ration, String weather) {
		
		String paceString = null;
		String terrString = null;
		String rationString = null;
		
		System.out.println("");
		
		System.out.println("Today is day " + day);
		
			switch(pace) { 
				case 0:
					System.out.println("Your caravan is resting");
					break;
				case 1:
					paceString = "steady";
					System.out.println("Your pace is " + paceString);
					break;
				case 2:
					paceString = "strenuous";
					System.out.println("Your pace is " + paceString);
					break;
				case 3:
					paceString = "grueling";
					System.out.println("Your pace is " + paceString);
					break;
			}
			//Prints out the current ration size
			switch(ration) { 
				case 0:
					rationString = "Bare Bones";
					System.out.println("Your ration size is " + rationString);
					break;
				case 1:
					rationString = "Meager";
					System.out.println("Your ration size is " + rationString);
					break;
				case 2:
					rationString = "Filling";
					System.out.println("Your ration size is " + rationString);
					break;
			}
			
			switch(terrain) {
				case 0:
					terrString = "grassy";
					System.out.println("The terrain is " + terrString);
					break;
				case 1:
					terrString = "arid";
					System.out.println("The terrain is " + terrString);
					break;
				case 2:
					System.out.println("You are at a river crossing");
		}
			
		
		System.out.println("You have traveled " + distance + " miles");
		
		System.out.println("The weather is " + weather);
	}
	
    /**
     * Prompts the player to make daily choices
    */
	public void dayPrompt() {
		System.out.println("What would you like to do?");
		System.out.println("");
		
		System.out.println("[1] View Inventory");
		System.out.println("[2] Change Pace");
		System.out.println("[3] Set Rations");
		System.out.println("[4] Continue to tomorrow");
	}
	
    /**
     * Prompts the player to change pace
    */
	public void pacePrompt() {
		System.out.println("");
		
		System.out.println("What pace would you like to set?");
		System.out.println("");
		System.out.println("[0] Resting");
		System.out.println("[1] Steady");
		System.out.println("[2] Strenuous");
		System.out.println("[3] Grueling");
	}
	    /**
     * Prompts the player to change ration size
	 * Author: Anthony DiMaria
    */
	public void rationPrompt() {
		System.out.println("");
		
		System.out.println("What ration size would you like to set?");
		System.out.println("");
		System.out.println("[0] Bare Bones");
		System.out.println("[1] Meager");
		System.out.println("[2] Filling");
	}
	
    /**
     * Prompts the player to choose how to cross/avoid rivers
     * @param currRiver
     * @param riverDepth
     * @param riverLength
    */
	public void riverPrompt(String currRiver, int riverDepth, int riverLength) {
		System.out.println("");
		
		System.out.println("    " + currRiver + " Crossing" + "    ");
		System.out.println("----------------------------");
		System.out.println("River depth: " + riverDepth);
		System.out.println("River length: " + riverLength);
		
		System.out.println("");
		System.out.println("What would you like to do?");
		System.out.println("");
		
		System.out.println("[1] Attempt to ford river");
		System.out.println("[2] Caulk wagon and float across");
		System.out.println("[3] Travel around the river");
	}
	
    /**
     * Welcomes to town
     * @param Town
    */
	public void townPrompt(String Town){
		
		System.out.println("");
		
		System.out.println("Welcome to " + Town + "!");
		System.out.println("----------------------------");
		
		System.out.println("What would you like to do?");
		System.out.println("");
		
		System.out.println("[1] Rest");
		System.out.println("[2] Leave Town");
		System.out.println("[3] Visit local shop");
	}
	
    /**
     * Allows the player to browse the shop
     * @param storeInventory
    */
	public void shopPrompt(String storeInventory){
		
		System.out.println("");
		
		System.out.println("Welcome to the shop");
		System.out.println("----------------------------");
		
		System.out.println("What would you like to buy?");
		System.out.println("");
		
		System.out.println(storeInventory);
		System.out.println("");
		System.out.println("[8] Quit Shop");
		
	}
	
    /**
     * Prompts the player to choose quantity of a store purchase
    */
	public void amountPrompt() {
		System.out.println("");
		System.out.println("How much do you want to buy?");
	}
	
    /**
     * Allows the player to view their inventory
     * @param inventory
    */
	public void displayInventory(String inventory) {
		System.out.println("");
		System.out.println("Current Inventory");
		System.out.println("----------------------------");
		System.out.println("");
		System.out.println(inventory);
		System.out.println("");
		System.out.println("----------------------------");
	}
	
    /**
     * Displays the success/failure of river crossing
     * @param cross
    */
	public void crossResult(boolean cross) {
		if(cross == true) {System.out.println("You have crossed the river successfully.");}
		else {System.out.println("You drowned while attemting to cross the river.");}
	}
	
    /**
     * Displays game end in case of survival
    */
	public void gameEnd() {
		System.out.println("");
		System.out.println("You survived the grueling journey...");
		System.out.println("");
		System.out.println("Thanks for traveling the Oregon Trail!");
	}
		
    /**
     * Displays game end in case of death
    */
	public void gameDeath() {
		System.out.println("");
		System.out.println("You died upon the hollowed trail...");
		System.out.println("");
		System.out.println("Thanks for playing!");
	}
	
    /**
     * Allows the player to make a choice from dayPrompt
     * @return choice
    */
	public int dayChoice() {
		 int choice = sc.nextInt();
		 while(choice > 4 || choice < 1) {
			System.out.println("Invalid input.");
			System.out.println("");
			choice = sc.nextInt();
		 }
		 return choice;
		 
	}
			
    /**
     * Allows the player to make a choice from riverPrompt
     * @return choice
    */
	public int riverChoice() {
		int choice = sc.nextInt();
		while(choice >= 4 || choice < 1) {
			System.out.println("Invalid input.");
			System.out.println("");
			choice = sc.nextInt();
		}
		return choice;
	}
	
    /**
     * Allows the player to make a choice from pacePrompt
     * @return choice
    */
	public int paceChoice() {
		 int choice = sc.nextInt();

		 while(choice > 3 || choice < 0) {
			System.out.println("Invalid input.");
			System.out.println("");
			choice = sc.nextInt();
		 }

		 return choice;
	}
	    /**
     * Allows the player to make a choice from rationPrompt
     * @return choice
    */
	public int rationChoice() {
		int choice = sc.nextInt();

		while(choice > 2 || choice < 0) {
		   System.out.println("Invalid input.");
		   System.out.println("");
		   choice = sc.nextInt();
		}

		return choice;
   }
    /**
     * Allows the player to make a choice from townPrompt
     * @return choice
    */
	public int townChoice() {
		int choice = sc.nextInt();
		

		while(choice > 3 || choice < 1) {
			System.out.println("Invalid input.");
			System.out.println("");
			choice = sc.nextInt();
		 }
		 return choice;
	}
		
    /**
     * Allows the player to make a choice from shopPrompt
     * @return choice
    */
	public int shopChoice() {
		int choice = sc.nextInt();
		

		while(choice > 8 || choice < 1) {
			System.out.println("Invalid input.");
			System.out.println("");
			choice = sc.nextInt();
		 }
		 return choice;
		
	}
	
    /**
     * Allows the player to make a choice from amountPrompt
     * @return choice
    */
	public int amountChoice() {
		int choice = sc.nextInt();
		return choice;
	}

	public void stuckPrompt(int itemType) {

		switch(itemType) {
			case 5:
			System.out.println("Your wagon's wheel broke.");
			break;
			case 6:
			System.out.println("Your wagon's axle broke.");
			break;
			case 7:
			System.out.println("Your wagon's tongue broke.");
			break;
		}

		System.out.println("You don't have the necessary part to fix it!");

	}

	public void stuckPace() {

		System.out.println("Your wagon is broken! You're stuck.");
		System.out.println("You have to wait for someone to come along.");
		
	}


    /**
     * Allows the player to choose to exit inventory prompt
     * @return exit
    */
	public boolean inventoryExit() {
		boolean exit = false;
		if(sc.nextInt() == 1){exit = true;}
		else{}
		return exit;
	}
}