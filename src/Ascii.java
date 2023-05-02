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
 }

 