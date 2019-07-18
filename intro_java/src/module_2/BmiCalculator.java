/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 2
*/

import java.util.Scanner;

public class BmiCalculator {
    public static void main( String [] args) {
        int height_inches = 0;
        int weight_pounds = 0;
        double height_meters = 0;
        double weight_kilos = 0;
        final double pound_to_kilo = 0.45359237;
        final double inch_to_meter = 0.0254;
        double bmi = 0;

        Scanner input = new Scanner( System.in );
        System.out.println( "\n" );
        System.out.print( "Enter in person's height in inches:" );
        height_inches = input.nextInt();     // Input pounds
        System.out.print( "Enter in person's weight in pounds:" );
        weight_pounds = input.nextInt();     // Input weight

        height_meters = height_inches * inch_to_meter;
        weight_kilos = weight_pounds * pound_to_kilo;

        bmi = weight_kilos / (height_meters * height_meters);

        System.out.println("\n");
        System.out.println( "BMI:" + bmi);
        System.out.println("\n");

        System.out.println("Note that the following is BMI information from the Department of Health & Human\n" +
                "Services/National Institutes of Health:\n");
        System.out.println("Underweight: less than 18.5");
        System.out.println("Normal: 18.5 - 24.9");
        System.out.println("Overweight: 25 - 29.9");
        System.out.println("Obese: 30 or greater");
    }
}
