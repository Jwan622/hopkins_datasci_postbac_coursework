/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 8
*/

public class NullPointerExceptionThrownCatch {
    public static void main(String args[]) {
        try {
            String stringArray[] = null;
            System.out.println(stringArray.length); // this will throw a NullPointerException because you cannot access length of null as if it were an array
        } catch (NullPointerException e) {
            System.out.println("cannot access length of null array" + e);
        }
    }
}
