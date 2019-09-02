/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 11
*/

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class builds a print queue and then adds to the queue using FIFO ordering. The queue is implemented using a
 * Linked List.
 * @author Jeffrey Wan
 * @version 1.0
 */
public class PrintQueue {
    public static void main(String args[]) {
        int userOption = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like to do?");

        LinkedList<String> printJobs = new LinkedList<String>();

        do {
            userOption = displayOptions(input);
            evaluateOption(userOption, input);
            switch (userOption) {
                case 1:
                    addPrintJob(printJobs);
                    break;
                case 2:
                    printJobs(printJobs);
                    break;
            }
        } while ((userOption == 1));
    }

    /**
     * This method evaluates user input and displays the options again if the user input is out of bounds
     * @param option the user select option
     * @param input The scanner input
     */
    private static void evaluateOption(int option, Scanner input) {
        while(!(option == 1 || option == 2)) {
            System.out.println("Invalid option, please try again.");
            displayOptions(input);
        }
    }

    /**
     * This method displays the options to the user entering in print jobs.
     * @param input The scanner input
     * @return The user selection.
     */
    private static int displayOptions(Scanner input) {
        System.out.println("1) add a job");
        System.out.println("2) quit");
        return Integer.parseInt(input.nextLine());
    }

    private static void addPrintJob(LinkedList<String> printJobs) {
        Job job = new Job(printJobs);
        printJobs.add("Job# : " + job.getId() + ", " + "PrintTime: " + job.getSecondsToPrint() + " secs");
        System.out.println("Added job number " + job.getId() + " to the print queue.");
    }

    private static void printJobs(LinkedList<String> printJobs) {
        for(String job : printJobs) {
            System.out.println(job);
        }
    }
}

class Job {
    private int id;
    private int secondsToPrint;

    Job(LinkedList<String> printJobs) {
        this.id = printJobs.size() + 1;
        this.secondsToPrint = generatePrintTime();
    }

    private int generatePrintTime() {
        Random rnGenerator = new Random(); // creates a Random object
        return rnGenerator.nextInt( 1000 - 10 ) + 10; // next int in range 10-1000
    }

    public int getSecondsToPrint() {
        return this.secondsToPrint;
    }

    public int getId() {
        return this.id;
    }
}
