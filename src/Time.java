import java.util.Random;
import java.util.Scanner;


/**
 * Subclass of both Map and Weather
 * Responsible for day tracking, implementing map/weather generation, and location visits
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 4/4/2023
 */

public class Time {
	
	Scanner sc = new Scanner(System.in);
	
    Random rand = new Random();
	Map map = new Map();
	Weather wth = new Weather();
	int day = 0;
    
    /**
     * Constructor for Time
     * Sets starting temperature and rain status
    */
    public Time() {
    	wth.temperature = 70;
    	wth.precipitation = false;
    }
    
    /**
     * Runs each time the day is advanced
     * Increments day, generates weather, and travels the player along the map
    */
    public void newDay(){
    	int terrain = map.terrain;
        day++;
        wth.changeWeather(terrain);
        wth.weatherGen();
        if(day > 1) {map.travel();}
    }
    
    
    /**
     * Allows player to choose how to cross river (or travel around it)
     * @param choice 
     * @return true if cross succeeds
     * @return false otherwise
    */
    public boolean riverCross(int choice) {
		boolean crossed = false;
    	switch(choice) {
	    	case 1:
	    		int temp = rand.nextInt(10)+1;
    			for(int i = 0; i < temp; i++) {
	    			System.out.println("You are attemping to ford to the river...");
	    				
		    		if(map.riverDepth >= 10) {
		        		int tempRand = rand.nextInt(100);
		        		if(tempRand % 10 == 0){
		        			map.drown = true;
		        			crossed = false;
		        			break;
		        		}
		        		else {crossed = true;}	
		    		}
		    		else {crossed = true;}
    			}
		    	break;
		    	
	    	case 2:
	    		while(!crossed) {
	    			for(int i = 0; i < 10; i++) {
	    			System.out.println("You are caulking across the river...");

		        		int tempRand = rand.nextInt(100);
		        		
		        		if(tempRand <= 10){
		        			map.drown = true;
		        		}
		        		
		        		if(map.drown == true) {
		        			crossed = true;
		        		}
	    			}
	    			crossed = true;
	    		}
	    		break;
	    		
	    	case 3:
	    		if(!map.visitKearny) {map.distKearny += map.riverLength;}
	    		else if (!map.visitAH) {map.distAH += map.riverLength;}
	    		crossed = true;
	    		break;
	    		
	    	}
    	return crossed;
    }
    
    /**
     * Allows player to choose what to do while in town
     * @param choice 
     * @return true in cases 3 and 8 [switch(choice)]
     * @return false otherwise
    */
    public boolean townVisit(int choice) {
    	boolean visitShop = false;
    	
    	// Maybe add HP heal for resting in town?
    	switch(choice) {
    		case 1: 
    			map.pace = 0;
    			day++;
    			break;
    		case 2: 
    			map.pace = 1;
    			map.exitTown = true;
    			break;
    		case 3:
    			visitShop = true;
    			break;
    		// Choice for leaving the store
    		case 8:
    			visitShop = false;
    			break;
    	}
    	return visitShop;
    }
    
    /**
     * Setter for travel pace
     * @param paceString 
    */
    public void setPace(String paceString){
        switch(paceString){
            case "Resting": 
                map.pace = 0;
                break;
            
            case "Steady": 
                map.pace = 1;
                break;
            
            case "Strenuous":
                map.pace = 2;
                break;
            
            case "Grueling":
                map.pace = 3;
                break;
        }
    }
    
    /**
     * Setter for travel pace
     * @param pace 
    */
    public void setPace(int pace) {map.pace = pace;}
    
    /**
     * Getter for current day 
     * @return day
    */
    public int getDay() {return day;}
    
    /**
     * Getter for days traveled, up to current day 
     * @return day-1
    */
    public int getDaysTraveled(){
        return day-1;
    }
    
    /**
     * Getter for current pace 
     * @return map.Pace
    */
    public int getPace() {return map.pace;}
    
    /**
     * Getter for current distance 
     * @return map.distanceTraveled
    */
    public int getDistance() {return map.distanceTraveled;}
    
    /**
     * Getter for current day 
     * @return map.riverDepth
    */
    public int getTerrain() {return map.terrain;}
    
    /**
     * Getter for river depth 
     * @return map.riverDepth
    */
    public int getDepth() {return map.riverDepth;}
    
    /**
     * Getter for river length 
     * @return map.riverLength
    */
    public int getLength() {return map.riverLength;}
    
    /**
     * Getter for current temperature 
     * @return wth.temperature
    */
    public int getTemperature() {return wth.temperature;}
    
    /**
     * Getter for forts passed 
     * @return map.fortsPassed
    */
    public int getFortsPassed() {return map.fortsPassed;}
    
    
    /**
     * Getter for weatherState String 
     * @return map.weatherString
    */
    public String getWeather() {return wth.weatherString;}
    
    /**
     * Getter for current river name 
     * @return map.currRiver
    */
    public String getRiver() {return map.currRiver;}
    
    /**
     * Getter for current town name 
     * @return map.currTown
    */
    public String getTown() {return map.currTown;}
    
    /**
     * Getter for town exit 
     * @return map.exitTown
    */
    public boolean getExitTown() {return map.exitTown;}
}
    
