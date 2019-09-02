/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 10
*/

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * This transformer reads in a file of raw data, removes non-integer values and puts the data into a buffer, and then
 * using that temp buffer aggregates the rows by state ID, and then writes that final buffer into a tempFile.txt
 * Example command when running this program: java ChildrenDataTransformer SmallAreaIncomePovertyEstData.txt tempFile.txt 13486
 * @author Jeffrey Wan
 */
public class ChildrenDataTransformer{
    public static void main(String args[]) throws IOException {
        // takes the filename from args[0] which is passed into this program via the command line
        String fileName = args[0];
        File f = new File(fileName);

        // this creates an output file which will be properly formatted
        String tempFileName = args[1];
        File outputf = new File(tempFileName);

        // numberOfRecords used for looping
        String numberOfRecords = args[2];
        int numberOfRecordsInt = Integer.valueOf(numberOfRecords);

        //writer to the output file. Using a PrintWriter to take advantage of printf
        PrintWriter outputWriter = new PrintWriter(new FileWriter(outputf));
        BufferedReader br = new BufferedReader(new FileReader(f));
        int dataBuffer[][] = new int[numberOfRecordsInt + 1][4];

        String record;
        for(int i = 0; i < numberOfRecordsInt; i++) {
            record = br.readLine();
            record = record.trim(); // remove leading and trailing whitespace
            // get rid of all the extra spaces and replace with just a single space
            record = record.replaceAll("\\s+", " ");

            // split the row up into units. At this point, there are still strings in the middle that need to be ignored
            String rowData[] = record.split(" ");
            int[] rowDataOnlyIntegers = new int[4];

            // get the first number
            for (int j = 0; j < 1; j++) {
               rowDataOnlyIntegers[j] = Integer.parseInt(rowData[j]);
            }

            // get the 3 last numbers and ignore the strings
            int newStartingIndex = 1;
            for (int j = (rowData.length - 5); j < (rowData.length - 2); j++) {
                rowDataOnlyIntegers[newStartingIndex] = Integer.parseInt(rowData[j]);
                newStartingIndex++;
            }
            dataBuffer[i] = rowDataOnlyIntegers;
        }

        // a 56 * 5 2nd dimension array used to hold the final values
        String finalBuffer[][] = new String[58][5];

        // the headers on the first row
        finalBuffer[0][0] = "State";
        finalBuffer[0][1] = "Population";
        finalBuffer[0][2] = "Child Population";
        finalBuffer[0][3] = "Child Poverty Population";
        finalBuffer[0][4] = "% Child Poverty";

        // line separators on the second row
        finalBuffer[1][0] = "-----";
        finalBuffer[1][1] = "----------";
        finalBuffer[1][2] = "--------------";
        finalBuffer[1][3] = "------------------------";
        finalBuffer[1][4] = "---------------";

        // this is used to compare the next state with the current state. if the number changes, we flush the state
        // totals to temp file
        int previousState = 1;

        int totalPop = 0;
        int childPop = 0;
        int childPovPop = 0;
        double childPovPopPer = 0.0;
        for(int i = 0; i < dataBuffer.length; i++) {
            int currentState = dataBuffer[i][0];

            // if the next row has the same state, keep aggregating totals
            if(currentState == previousState) {
                totalPop += dataBuffer[i][1];
                childPop += dataBuffer[i][2];
                childPovPop += dataBuffer[i][3];
            } else {
                // if the next state is different, calculate the child population poverty percentage and write the totals
                // to another buffer starting with the 2nd row because the first 2 rows already container header/separate
                // data. Store it in strings and format them with commas and justfication.
                childPovPopPer = (childPovPop * 1.0 / childPop) * 100;
                finalBuffer[previousState + 1][0] = Integer.toString(previousState);
                finalBuffer[previousState + 1][1] = String.format("%,d", totalPop);
                finalBuffer[previousState + 1][2] = String.format("%,d", childPop);
                finalBuffer[previousState + 1][3] = String.format("%,d", childPovPop);
                BigDecimal bd = new BigDecimal(childPovPopPer).setScale(2, RoundingMode.HALF_EVEN);
                finalBuffer[previousState + 1][4] = Double.toString(bd.doubleValue());

                //reset these values to the next state
                totalPop = dataBuffer[i][1];
                childPop = dataBuffer[i][2];
                childPovPop = dataBuffer[i][3];
                previousState++;
            }
        }


        for(String[] row : finalBuffer) {
            outputWriter.printf("%7s %12s %22s %32s %19s", row[0], row[1], row[2], row[3], row[4]);
            outputWriter.print("\n");
        }
        br.close();
        outputWriter.close();
    }
}