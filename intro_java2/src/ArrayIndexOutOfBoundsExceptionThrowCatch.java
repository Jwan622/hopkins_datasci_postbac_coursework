/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 8
*/

public class ArrayIndexOutOfBoundsExceptionThrowCatch {
    public static void main(String args[]) {
        try {
            int[] array = new int[5];
            for (int index = 1; index <= array.length; index++)
            // when index == 5, array[index] will throw an arrayIndexOutofBoundsError
            {
                System.out.println(array[index]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("trying to access index 5 of a 4 index array" + e);
        }
    }
}
