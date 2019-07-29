/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 8
*/

public class IllegalArgumentExceptionThrowCatch {
    public static void main(String args[]) {
        callMethodWithPositiveNumber(1);
        callMethodWithPositiveNumber(-1);
    }

    public static void callMethodWithPositiveNumber(int number) {
        // this method only accepts positive numbers and will throw an error even though the compiler doesn't complain when the method is passed a negative number
        try {
            if (number < 1) {
                // Thrown to indicate that a method has been passed an illegal or inappropriate argument.
                throw new IllegalArgumentException();
            }

            System.out.println("The argument passed was positive: " + number);
        } catch (IllegalArgumentException e) {
            System.out.println("The argument passed was negative: " + number + ": " + e);
        }
    }
}
