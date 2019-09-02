/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 11
*/


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * This class builds a contact list in an in-memory TreeMap (used for ordering purposes) and then writes to a file
 * when prompted by the user. The class can add contacts, display contacts, and remove contacts and write to file.f
 * @author Jeffrey Wan
 * @version 1.0
 */
public class ContactList {
    public static void main(String args[]) throws IOException {
        int userOption = 0;
        Scanner input = new Scanner( System.in );
        System.out.print("Please enter the name of the Contact List file:");
        String fileName = input.nextLine(); // This is where the contact list will be written to eventually.

        TreeMap<String, List<String>> contactMap = new TreeMap<String, List<String>>(new LastNameComparator());

        // the main loop where the user can pick from options. Using a do-while so that this runs once and the user
        // will see the options at least one time.
        do {
            userOption = displayOptions(input);
            evaluateOption(userOption, input);
            switch (userOption) {
                case 1:
                    display(contactMap);
                    break;
                case 2:
                    addContact(contactMap, input);
                    break;
                case 3:
                    removeContact(contactMap, input);
                    break;
                case 4:
                    saveContactList(contactMap, fileName);
                    break;
            }
        } while ((userOption >= 1 && userOption < 4));
    }

    /**
     * This method displays the options to the user entering in contacts.
     * @param input The scanner input
     * @return The user selection.
     */
    private static int displayOptions(Scanner input) {
        System.out.println("1) Display Contact List");
        System.out.println("2) Add a Contact");
        System.out.println("3) Remove a Contact");
        System.out.println("4) Save Contact list and Exit");
        return Integer.parseInt(input.nextLine());
    }

    /**
     * This method evaluates user input and displays the options again if the user input is out of bounds
     * @param option the user select option
     * @param input The scanner input
     */
    private static void evaluateOption(int option, Scanner input) {
        while(!(option >= 1 && option <= 4)) {
            System.out.println("Invalid option, please try again.");
            displayOptions(input);
        }
    }

    /**
     * This method displays the in memory contact list
     * @param contactMap the contact list in memory
     */
    private static void display(TreeMap contactMap) {
        Set<Map.Entry<String, String[]>> contactSet = contactMap.entrySet();

        // only display the contact list if there are actual contacts.
        if(!contactSet.isEmpty()) {
            for (Map.Entry<String, String[]> contact : contactSet) {
                System.out.print(contact.getKey() + ": ");
                System.out.println(Arrays.toString(contact.getValue()));
            }
        } else {
            System.out.println("No contacts to display");
        }
    }


    /**
     * This method adds a contact to the in memory contact Map. the structure of the contact looks like this:
     * <firstName lastName> : [<contactInfo]
     * @param contactMap the contact list
     * @param input The scanner input
     */
    private static void addContact(TreeMap contactMap, Scanner input) {
        System.out.println("Enter in first name");
        String firstName = input.nextLine();
        System.out.println("Enter in last name");
        String lastName = input.nextLine();
        System.out.println("Enter in email");
        String email = input.nextLine();
        System.out.println("Enter in phone number");
        String phoneNumber = input.nextLine();
        String[] contactValue = {firstName, lastName, email, phoneNumber};
        contactMap.put(firstName + " " + lastName, contactValue);
    }

    private static void removeContact(TreeMap contactMap, Scanner input) {
        System.out.println("Remove contact from list. Enter in first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter in last name");
        String lastName = input.nextLine();
        Object removedContact = contactMap.remove(firstName + " " + lastName);
        if(removedContact != null) {
            System.out.println("Removing: " + removedContact);
        }
    }

    private static void saveContactList(TreeMap contactMap, String fileName) throws IOException {
        File f = new File(fileName);
        //writer to the output file. Using a PrintWriter so that the output is in string format.
        PrintWriter outputWriter = new PrintWriter(new FileWriter(f));
        Set<Map.Entry<String, String[]>> contactSet = contactMap.entrySet();
        for (Map.Entry<String, String[]> contact : contactSet) {
            outputWriter.print(contact.getKey() + ": ");
            for (String value : contact.getValue()) {
                outputWriter.print(value + " ");
            }
            outputWriter.println();
        }
        outputWriter.close();
    }
}


/**
 * this class is the comparator used for sorting the TreeMap by last name and if the last names are equal, by first name
 */
class LastNameComparator implements Comparator<String> {
    public int compare(String firstString, String secondString) {
        int i, j, k;

        // Find the index of the last name
        i = firstString.lastIndexOf(' ');
        j = secondString.lastIndexOf(' ');

        k = firstString.substring(i).compareToIgnoreCase(secondString.substring(j));
        if(k==0)
            return firstString.compareToIgnoreCase(secondString);
        else
            return k;
    }
}