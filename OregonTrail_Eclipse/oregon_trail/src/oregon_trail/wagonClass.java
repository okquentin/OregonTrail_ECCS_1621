/**
* Wagon Class for Oregon Trail project
* Adds and subtracts items from the wagon's inventory.
* Also keeps track of money and food.
*/

/**
* @author Nathan Johnson
*/

package oregon_trail;
import java.util.ArrayList;
public class wagonClass {
	/**
	 * This array list keeps track of the wagon's inventory.
	 * Each different item has an integer attributed to it, so this keeps track of items using those numbers.
	 */
public ArrayList<Integer> inventory = new ArrayList<Integer>();
/**
* Amount of food.
* Tracked separate from the rest of the items because there will be a lot of food.
*/
public int food = 0;
/**
* Amount of money.
*/
public double money = 865;
/**
* Adds items to the array that is the wagon's inventory.
* @param itemGained
* @param amountGained
*/
public void inventoryAdder(int itemGained, int amountGained) {
	for(int i = 0; i < amountGained; i++) {
		inventory.add(itemGained);
	}
	
}
/**
* Takes items from the array that is the wagon's inventory.
* Returns a boolean to test if the player has the necessary items to be taken.
* @param itemLost
* @param amountLost
* @return test == amountLost
*/
public Boolean inventorySubtractor(int itemLost, int amountLost) {
int test = 0;
for(int i = 0; i <= amountLost; i++) {
for (int j = 0; j < inventory.size(); j++) {
if(inventory.get(j) == itemLost) {
inventory.remove(j);
j--;
test++;
break;
}
}
}
return(test == amountLost);
}
}
