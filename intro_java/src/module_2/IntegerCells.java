/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 2
*/

import java.util.Scanner;

public class IntegerCells
{
    public static void main( String [] args )
    {
        // Define and initialize variables for values to be input
        int v1 = 0;      // First value to be input
        int v2 = 0;      // Second value to be input
        int v3 = 0;      // Third value to be input
        int v4 = 0;      // Fourth value to be input
        int v5 = 0;      // Fifth value to be input
        int v6 = 0;      // Sixth value to be input

        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        System.out.println( "\n\n" );
        System.out.print( "Enter 6 integers separated by a blank space:" );
        v1 = input.nextInt();     // Input first value
        v2 = input.nextInt();     // Input second value
        v3 = input.nextInt();     // Input third value
        v4 = input.nextInt();     // Input fourth value
        v5 = input.nextInt();     // Input fifth value
        v6 = input.nextInt();     // Input sixth value

        // Output using System.out.println()
        System.out.println( "\n\n" );
        System.out.println( "\t\t" + "Value" + "\t" + "Value" + "\t" + "Total" );
        System.out.println( "\t\t" + v1 + "\t\t" + v2 + "\t\t" + (v1 + v2));
        System.out.println( "\t\t" + v3 + "\t\t" + v4 + "\t\t" + (v3 + v4));
        System.out.println( "\t\t" + v5 + "\t\t" + v6 + "\t\t" + (v5 + v6));
        System.out.println( "\t\t" + "----" + "\t" + "----" + "\t" + "----");
        System.out.println( "Total" + "\t" + (v1 + v3 + v5) + "\t\t" + (v2 + v4 + v6) + "\t\t" + (v1 + v2 + v3 + v4 + v5 + v6));
        System.out.println( "\n\n" );
    }
}