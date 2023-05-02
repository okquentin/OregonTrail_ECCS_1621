import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;

/**
 * Provides ASCII output for the text-based game UI
 * 
 * File Index
 * ---------
 * (0) file1: Wagon
 * (1) file2: Plains Landscape
 * 
 * @author Quentin Osterhage
 * @version MVP
 * @since 5/2/2023
 */

 public class Ascii{
    
    int numArt = 2;
    private File directory = new File("asciiSRC"); 
    private File[] list = directory.listFiles();



    public boolean printArt(int artIndex) throws FileNotFoundException{
        if(list[artIndex].isFile())
        
        Scanner scan = new Scanner(list[artIndex]);

        return false;
    }


 