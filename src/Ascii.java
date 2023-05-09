import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Provides ASCII art generation for the text-based game UI
 * Reads from rtf files stored in asciiSRC
 * 
 * [Art Index]
 * (0) file0: Welcome
 * (1) file1: Wagon
 * (2) file2: Plains Landscape
 * (3) file3: Arid Landscape
 * (4) file4: River Crossing
 * (5) file5: Town Visit
 * (6) file6: Hot Weather
 * (7) file7: Cold Weather
 * (8) file8: Rainy Weather
 * 
 * [Implementation in App]
 * try {art.printArt(int artIndex);} 
 * catch (FileNotFoundException e) {e.printStackTrace();}
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 5/2/2023
 */

 public class Ascii{
    
    int numArt = 2;
    String directory = "src/asciiSRC/"; 


    public void printArt(int artIndex) throws FileNotFoundException{
        String filePath = directory+"file"+Integer.toString(artIndex)+".rtf";
        File file = new File(filePath);
        System.out.println(file);
        try (Scanner scan = new Scanner(file)) {
            while(scan.hasNextLine()){System.out.println(scan.nextLine());}
        }

    }

    public static int weatherToIndex(String weatherString){
        int index = 0;
        switch(weatherString) {
            case "Mild":
                index = 0;
                break;
            case "Hot": 
                index = 6;
                break;
            case "Cold": 
                index = 7;
                break;
            case "Rainy": 
                index = 8;
                break;
        }
        return index;
    }
 }

 