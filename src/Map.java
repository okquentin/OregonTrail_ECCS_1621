import java.util.Random;

/**
 * Map from Independence, MO to Ash Hollow, NE
 * Depth, Length, Distance in MILES
 * All distances are relative to Independence, MO
 * Temperature in FAHRENHEIT
 * Time is 24-Hour Clock
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 4/4/2023
 */

public class Map {
	
    	Random rand = new Random();
    	
	 	public int pace; // 0=Resting 1=Steady 2=Strenuous 3=Grueling
	    public int terrain; // 0=Plains 1=Arid 2=RiverCrossing, 3=Town
	    public int distanceTraveled;
	    public int fortsPassed;
	    public String currRiver;
	    public int riverDepth;
	    public int riverLength;
	    public boolean drown;
	    public String currTown;
	    public boolean exitTown;
	    public boolean visitShop;
	    
	    private final int maxDepth = 20; 
	    private final int maxLength = 500; 
	    private boolean crossBlue;
	    private boolean crossPlatte;
	    private boolean crossPlatte2;
	    private final int distBR = 130;
	    private final int distPR = 250;
	    private final int distPR2 = 430;

	    public int distKearny = 260;
	    public boolean visitKearny;
	    
	    public int distAH = 480;  // Ash Hollow
	    public boolean visitAH;

		public int distWV = 1000; // Willamette Valley (END OF GAME)
		public boolean visitWV;

	    /**
	     * Constructor for Map class
	     */
	    public Map() {
	    	distanceTraveled = 0; 
	    	fortsPassed = 0;
	    	pace = 1;
	    	terrain = 3;
	    	currTown = "Independence, MO";
			crossBlue = false;
			crossPlatte = false;
			crossPlatte2 = false;
			visitKearny = false;
			visitAH = false;
	    }
	    
	    /**
	     * Sets river depth and length 
	     */
	    private void riverGen() {
	    	riverDepth = rand.nextInt(maxDepth)+2;
	    	riverLength = rand.nextInt(maxLength)+50;
	    }
	    
	    /**
	     * Increments distanceTraveled based on pace
	     * and determines when landmarks, towns, and rivers are reached
	     */
	    public void travel(){
	        switch(pace){
	            case 0:
					break;
	            case 1:
	                distanceTraveled += 8;
	                break;
	            case 2:
	                distanceTraveled += 14;
	                break;
	            case 3:
	                distanceTraveled += 20;
	                break;
	       }
	        
	       if(exitTown = true) {
	    	   terrain = 0;
	       }
	        
		   if(distanceTraveled >= distKearny && visitKearny == false) {
			   visitKearny = true;
			   terrain = 3;
			   currTown = "Kearny";
			   exitTown = false;
			   fortsPassed++;
		   }
		        
	       if(distanceTraveled >= distAH && visitAH == false) {
	    	   visitAH = true;
			   terrain = 3;
			   currTown = "Ash Hollow";
			   exitTown = false;
			   fortsPassed++;
	       }
	       
	       if(distanceTraveled >= 380 && exitTown != false) {terrain = 1;} 
	   
	       if(distanceTraveled >= distBR && crossBlue == false) {
	    	   riverGen();
	    	   terrain = 2;
	    	   currRiver = "Blue River";
	    	   crossBlue = true;
	       }
	       if(distanceTraveled >= distPR && crossPlatte == false) {
	    	   riverGen();
	    	   terrain = 2;
	    	   currRiver = "Platte River";
	    	   crossPlatte = true;
	       }
	       if(distanceTraveled >= distPR2 && crossPlatte2 == false) {
	    	   riverGen();
	    	   terrain = 2;
	    	   currRiver = "Platte River";
	    	   crossPlatte2 = true;
	       }
	    }
}
