import java.util.Scanner;

public class NumberGuesser {
    public static void main(String args[]) {
        boolean keep_playing = true;

        while (keep_playing == true) {
            int number_of_guesses;
            int upper_limit;
            int random_number;
            boolean game_finished = false;
            int current_guess;

            Scanner scan = new Scanner(System.in);

            System.out.println("What's the upper limit of the random number?");
            upper_limit = scan.nextInt();

            System.out.println("How many guesses do you want to have?");
            number_of_guesses = scan.nextInt();

            random_number = (int) (upper_limit * Math.random()) + 1;

            while (game_finished != true && number_of_guesses > 0) {
                System.out.println("What is your guess?. You have " + number_of_guesses + " left");
                current_guess = scan.nextInt();

                if (current_guess < random_number) {
                    System.out.println("too low");
                    number_of_guesses--;
                } else if (current_guess > random_number) {
                    System.out.println("too high");
                    number_of_guesses--;
                } else {
                    System.out.println("correct");
                    game_finished = true;
                }
            }

            if (number_of_guesses == 0) {
                System.out.println("Game over. You're out of guesses. Keep playing? Enter 1 for yes, 2 for no");
                keep_playing = scan.nextInt() == 1;
            }
            else {
                System.out.println("You win");
                keep_playing = false;
            }
        }
    }
}
