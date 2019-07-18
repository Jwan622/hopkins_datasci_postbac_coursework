import java.util.Scanner;

public class TemperatureConversion
{
    public static void main( String [] args )
    {
        int userChoice = 0;                           // User selection: 1, 2, 3
        float temperatureFahrenheit = 0;              // Fahrenheit temperature
        float temperatureCelsius = 0;                 // Celsius temperature

        Scanner input = new Scanner( System.in );     // Create a Scanner to obtain user input

        while( userChoice != 3 )
        {
            userChoice = obtainUserChoice(input);

            switch( userChoice )
            {
                case 1:     // Convert Fahrenheit to Celsius
                    temperatureCelsius = convertFahrenheitToCelsius(obtainTemperature("Enter a Fahrenheit temperature: ", input));
                    System.out.println( temperatureFahrenheit + " degrees Fahrenheit is " + temperatureCelsius + " degrees Celsius" );
                    break;
                case 2:     // Convert Celsius to Fahrenheit
                    temperatureFahrenheit = convertCelsiusToFahrenheit(temperatureCelsius = obtainTemperature( "Enter a Celsius temperature: ", input ));
                    System.out.println( temperatureCelsius + " degrees Celsius is " + temperatureFahrenheit + " degrees Fahrenheit" );
                    break;
                case 3:     // End Program
                    System.out.println( "Bye Bye" );
                    break;
                default:    // Invalid Data Entered
                    System.out.println( "Invalid Data: You must enter 1, 2, or 3" );
            }
        }
    }

    private static int obtainUserChoice(Scanner input) {
        System.out.print( "Enter 1 to convert F->C, 2 to convert C->F, 3 to quit: " );
        return input.nextInt();              // Read user input
    }

    private static float obtainTemperature(String message, Scanner input) {
        System.out.print( message );
        return input.nextFloat();
    }

    private static float convertFahrenheitToCelsius(float temp) {
        return 5F/9F * ( temp - 32F );
    }

    private static float convertCelsiusToFahrenheit(float temp) {
        return 9F/5F * temp + 32F;
    }
}