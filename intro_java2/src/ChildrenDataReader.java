/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 10
*/

/**
 * simple reads from a file and outputs it line by line
 * @author Jeffrey Wan
 */

import java.io.*;

public class ChildrenDataReader {
    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0];
        String numberOfRecords = args[1];

        // used to display the input file
        printHeader(inputFilePath);

        File f = new File(inputFilePath);

        BufferedReader br = new BufferedReader(new FileReader(f));

        String record;

        // Since the tempFile is already formatted line by line, we just have to print out the file line by line
        // i don't actually use the numberOfRecords here and so it's just unused.
        while((record = br.readLine()) != null) {
            System.out.println(record);
        }
    }

    // Create the header
    public static void printHeader(String inputFilePath) {
        System.out.println("File: " + inputFilePath);
    }
}
