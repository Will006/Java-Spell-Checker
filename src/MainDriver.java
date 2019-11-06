/**
 * MainDriver.java used to boot up the GUI interface handle the main operations
 * with MatrixGraph.java
 * William Pickering, Xavier Finney, and Leo Beltran
 * Last Edit: 5/20/18
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MainDriver
{

    static final String TXT_FILE_OF_SENTENCES="src/tempSentences";
    //static final String TXT_FILE_OF_SENTENCES="src/FileToHoI see hellewldSentences";
    public static void main(String[] args)
    {
        GUI_Interface gui = new GUI_Interface();
        gui.setVisible(true);

    }

}
