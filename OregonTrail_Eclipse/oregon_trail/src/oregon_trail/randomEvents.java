package oregon_trail;
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
	* @param pace Passed from Map, responsible for chance calculation
	* @param distanceTraveled Passed from Map, responsible for chance calculation
	* @param temperature Passed from Map, responsible for chance calculation. Uses modulo to avoid any errors in division
	* @return Returns a boolean if a part is lost or not
	*/
	public boolean partLoss(int pace, int distanceTraveled, int temperature){
	int chance = 500;
	chance -= (pace - 1) * 10;
	chance -= (distanceTraveled);
	int partLoss = rand.nextInt(100);
	if(partLoss == 2){
	return true;
	}
	else{
	return false;
	}
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
}


