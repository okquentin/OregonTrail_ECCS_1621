
import java.util.Random;

/**
 * Provides weather generation and tracking functionality
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 4/4/2023
 */


public class Weather{
	
    Random rand = new Random();
    public int temperature;
    public boolean precipitation;

    public boolean newWeather;
    public boolean weatherChange = false;
    public int weatherState; // 0=mild, 1=hot, 2=cold, 3=rainy
    public String weatherString;
    
    
    /**
     * Sets weatherString based on weatherState
     */
	private void setString() {
		       switch(weatherState) {
	            case 0: 
	                weatherString = "Mild";
	                break;
	            case 1: 
	                weatherString = "Hot";
	                break;
	            case 2: 
	            	weatherString = "Cold";
	            	break;
	            case 3: 
	            	weatherString = "Rainy";
	            	break;
	        }
		}
	
    /**
     * Changes temperature based on RNG output
     * @param tempRand
     */
    private void tempChance(int tempRand){
        if(tempRand < 25) {temperature += 10;}
        if(tempRand >= 25 && tempRand < 50) {temperature += 20;}
        if(tempRand >= 50 && tempRand < 75) {temperature -= 10;}
        if(tempRand >= 75) {temperature -= 20;}
    }

    /**
     * Sets rain status based on RNG output
     * @param rainRand
    */
    private void rainChance(int rainRand){
        if(rainRand % 3 == 0){precipitation = true;}
        else{precipitation = false;}
    }
    
    /**
     * Changes weather based on 50% chance
     * Weather change chances depend upon terrain
     * @param terrain
    */
    public void changeWeather(int terrain, int oldWeather){
        int newRand = rand.nextInt();

        if(newRand % 2 == 0) {newWeather = true;}
        else{newWeather = false;}

        if(newWeather) {
            int tempRand = rand.nextInt(100);
            tempChance(tempRand);
            
            if(terrain == 1) {precipitation = false;}
            else {
	            int rainRand = rand.nextInt(6);
	            rainChance(rainRand);
            }
            weatherGen(oldWeather);
        }
    }

    /**
     * Sets weatherState based on temperature/precipitation
    */
    public void weatherGen(int oldWeather){
        if(temperature < 80 && temperature > 40){weatherState = 0;}
        if(temperature > 80){weatherState = 1;}
        if(temperature < 40){weatherState = 2;}
        if(precipitation){weatherState = 3;}

        if(oldWeather != weatherState){weatherChange = true;}
        else{weatherChange = false;}
        setString();
    }


}
