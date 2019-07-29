/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 8
*/

public class ManipulateAnimals {
    public static void main(String args[]) {
        Animal animal1 = new Animal();
        // I don't think the assignment says to actually use these, but I'm going to use these to delimit the mutatables.
        animal1.setName("animal 1");
        Animal animal2 = new Animal();
        animal2.setName("animal 2");
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("vehicle 1");
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("vehicle 2");

        // array of objects that implement a common interface assigned to the Mutatable interface
        Mutatable mutatables[] = { animal1, animal2, vehicle1, vehicle2 };

        for(Mutatable m : mutatables) {
            System.out.println("---" + m.getName() + "---");
            m.drawObject();
            m.rotateObject();
            m.playSound();
            m.resizeObject();
            System.out.println("-------------");
        }
    }
}

// all the interfaces are just going tobe used in this class, so default access modifier is fine.
interface Drawable {
    // remember that interface methods are all implicitly public
    void drawObject();
}

interface Rotatable {
    void rotateObject();
}

interface Resizable {
    void resizeObject();
}

interface Sounds {
    void playSound();
}

// this interface extends the other interfaces so that later on, I can iterate through a collection of objects that
// implement this single interface
interface Mutatable extends Sounds, Drawable, Rotatable, Resizable {
    // need to put this in one of the interfaces otherwise cannot call getName on the array of mutatables even though
    // the underlying objects has it defined.
    String getName();
}

class Animal implements Mutatable {
    // going to just use the default constructor so no constructor is needed.

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void drawObject() {
        System.out.println("Drawing an Animal");
    }

    public void rotateObject() {
        System.out.println("Rotating an Animal");
    }

    public void playSound() {
        System.out.println("Animal sound");
    }

    public void resizeObject() {
        System.out.println("Resizing an Animal");
    }
}

class Vehicle implements Mutatable {
    String name;
    double age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getAge() {
        return this.age;
    }

    public void drawObject() {
        System.out.println("Drawing a Vehicle");
    }

    public void rotateObject() {
        System.out.println("Rotating a Vehicle");
    }

    public void playSound() {
        System.out.println("Vehicle sound");
    }

    public void resizeObject() {
        System.out.println("Resizing a Vehicle");
    }
}
