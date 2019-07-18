/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 6
*/

public class TortoiseHareRace {
    public static void main(String args[]) {
        // some setup. The initial position 1 and the winning position is 51. We use the initialPosition value to initialize tortoisePosition and harePosition
        final int initialPosition = 1;
        int tortoisePosition = initialPosition;
        int harePosition = initialPosition;
        int previousOuchPosition = -1;
        int winningPosition = 51;

        Tortoise tortoise = new Tortoise();
        Hare hare = new Hare();

        // this is just an empty course for display before the game starts.
        buildCourse();
        System.out.println("AND THEY’RE OFF!!");

        while(harePosition < winningPosition && tortoisePosition < winningPosition) {
            int move = generateMove();
            tortoisePosition += tortoiseMove(move, tortoise);
            harePosition += hareMove(move, hare);

            // make sure neither the tortoise nor hare can slip past initial position
            harePosition = harePosition < initialPosition ? initialPosition : harePosition;
            tortoisePosition = tortoisePosition < initialPosition ? initialPosition : tortoisePosition;

            if (tortoisePosition == harePosition && tortoisePosition != initialPosition) {
                // when tortoisePosition == harePosition, an ouch position should be generated except for at the start of the game.
                previousOuchPosition = harePosition;
            };

            // position tracker used during development
            //            System.out.println("T: " + tortoisePosition);
            //            System.out.println("H: " + harePosition);
            //            System.out.println("PreviousOuch: " + previousOuchPosition);


            // display the course with positions of the tortoise, hare, and their previous ouch position.
            System.out.println(buildCourse(tortoisePosition, harePosition, previousOuchPosition));
        }

        displayFinalMessage(harePosition, tortoisePosition, winningPosition);
    }

    public static int generateMove() {
        // return a random number between 1 and 10
        return ((int) (Math.random()*(10 - 1))) + 1;
    }

    public static void buildCourse() {
        // initialize the course with the tortoise and hare to the left of the leftmost border of the course.
        String courseBuilder = "TH|";
        for (int i = 0; i <= 50; i++) {
            courseBuilder += "|";
        }

        System.out.println(courseBuilder);
    }

    // overloaded method to build the course once the race has started.
    public static String buildCourse(int tortoisePosition, int harePosition, int previousOuchPosition) {
        // initialize the course with the left border
        String courseBuilder = "|";
        int square = 1;

        while(square <= 50) {
            if (square == tortoisePosition && square == harePosition) {
              // the course should contain only the new ouch position and not the old one.
              courseBuilder += "OUCH!|";
            } else if (square == previousOuchPosition && harePosition != tortoisePosition) {
                // still display the previous ouch position IF the harePosition != tortoisePosition currently
              courseBuilder += "OUCH!|";
            } else if (square == tortoisePosition) {
                courseBuilder += "T|";
            } else if (square == harePosition) {
                courseBuilder += "H|";
            } else {
                courseBuilder += "|";
            }
            square++;
        }

        return courseBuilder;
    }

    public static int tortoiseMove(int move, Tortoise tortoise) {
        if (move >= 1 && move <= 5) {
            return tortoise.fastPlod();
        } else if (move >= 6 && move <= 8) {
            return tortoise.slowPlod();
        } else {
            return tortoise.slip();
        }
    }

    public static int hareMove(int move, Hare hare) {
        if (move >= 1 && move <= 2) {
            return hare.bigHop();
        } else if (move >= 3 && move <= 5) {
            return hare.smallHop();
        } else if (move == 6) {
            return hare.bigSlip();
        } else if (move == 7 || move == 8) {
            return hare.smallSlip();
        } else {
            return hare.fallAsleep();
        }
    }

    public static void displayFinalMessage(int harePosition, int tortoisePosition, int winningPosition) {
        if (harePosition >= winningPosition) {
            System.out.println("HARE WINS!");
        } else if (tortoisePosition >= winningPosition) {
            System.out.println("TORTOISE WINS!");
        } else {
            System.out.println("IT'S A TIE!");
        }
    }
}

class Tortoise {
    public int fastPlod() {
        return 3;
    }

    public int slowPlod() {
        return 1;
    }

    public int slip() {
        return -6;
    }
}

class Hare {
    public int bigHop() {
        return 9;
    }

    public int smallHop() {
        return 1;
    }

    public int bigSlip() {
        return -12;
    }

    public int smallSlip() {
        return -2;
    }

    public int fallAsleep() {
        return 0;
    }
}
