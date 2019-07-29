/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 8
*/

class A
{
    int i = 100;
}

class B extends A
{
    int j = 200;
}

class C extends B
{
    int k = 300;
}

public class ClassCastExceptionThrowCatch {
    public static void main(String[] args) {
        try {
            A a = new B();   // B type is auto up cast to A type
            B b = (B) a;     // This is allowed because we can downcast a to B because object a really is an object of class B
            C c = (C) b;    // Here, you will get class cast exception because we are trying to cast an object of class B to class C which it is not an object of.
            System.out.println(c.k);
        } catch (ClassCastException e) {
            System.out.println("You are trying to typecast an object to a class that they aren't compatiblew with" + e);
        }
    }
}
