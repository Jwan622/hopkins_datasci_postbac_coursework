/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 8
*/

/**
 * This class is the runner class and plays blackjack. The rules for this blackjack game are as follows:
 * - J's are worth 11, Q's are worth 12, K's are worth 13, A's are worth 1
 * @author jwan
 * @version 1.0
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGameSimulator {
    public static void main(String args[]) {
        Player p1 = new Player();
        // the Dealer class extends Player class and adds some functionality
        Dealer dealer = new Dealer();

        // if a plae
        boolean playing = true;
        Scanner sc = new Scanner(System.in);

        // reusable method, used at start of the game and when reloading when Player has lost all money.
        getMoney(sc, p1);

        while(playing) {
            System.out.println("Let's play. Shuffling starting");

            // Deck is really an array of 52 Card objects. Shuffling rearranges them using Random util.
            Deck deck = new Deck();
            deck.shuffle();

            // the hand are the cards belonging to the Player or Dealer. Used to count total points to see who wins or
            // who has gone over 21.
            List<Card> hand = new ArrayList<Card>();
            List<Card> dealerHand = new ArrayList<Card>();
            int bet;

            System.out.println("Your bankroll is currently " + p1.getBankroll() + " dollars");
            System.out.println("How much do you want to bet?");
            bet = sc.nextInt();

            // bet cannot be > bankroll
            while(bet > p1.getBankroll()) {
                System.out.println("You can't bet more than Player bankroll");
                System.out.println("How much do you want to bet?");
                bet = sc.nextInt();
            }

            playerPlay(deck, hand, p1, sc, bet, dealerHand);

            // dealer should not play if player has 21, player auto wins in that case.
            if(!p1.busto() && !p1.has21()) {
                dealerPlay(deck, dealerHand, dealer, p1, bet);
            }

            // if Player does not bust and neither does dealer, we see who wins. In case of a tied 21, dealer wins.
            if(!p1.busto() && !dealer.busto()) {
                decideWhoWins(dealer, p1, bet);
            }

            // if the Player has run out of cash, Player needs to reload bankroll or quit. If Player has cash, option to
            // play again or quit
            if(!p1.broke()) {
                System.out.println("Play another hand? 'y' for yes or 'n' for no");
                char choice = sc.next().charAt(0);

                while(!(choice == ('y') || choice == ('n'))) {
                    System.out.println("Invalid entry, please enter 'y' or 'n'");
                    choice = sc.next().charAt(0);
                }

                if(choice == 'n') {
                    playing = false;
                } else {
                    // resets the player's points and passed attribute which is used for deciding when player has
                    // stopped hitting
                    p1.reset();
                    continue;
                }
            } else {
                System.out.println("You are out of cash. Reload? 'y' for yes or 'no' for no");
                char choice = sc.next().charAt(0);

                if(choice == 'y') {
                    getMoney(sc, p1);
                } else {
                    playing = false;
                }
            }
        }

        System.out.println("Thanks for playing!");
    }

    /**
     * This method handles the Player playing
     * @param deck The deck of cards
     * @param player the Player or participant
     * @param sc the scanner object
     * @param bet the size of the bet that is being wagered.
     * @return Nothing. Just causes side effects
     */
    private static void playerPlay(Deck deck, List<Card> hand, Player player, Scanner sc, int bet, List<Card> dealerHand) {
        System.out.println("It is the player's turn and player is wagering " + bet + " dollars");
        System.out.println("These are your cards:");
        startPlaying(deck, hand, player);

        // dealer gets dealt one card face up and one card face down only if player isn't busto and doesn't have 21.
        if(player.hitting()) {
            dealerDeal(deck, dealerHand);
        }

        // hitting is when the player has not passed, or is over 21, or has 21. If the player has 21, play automatically
        // goes to the dealer.
        while(player.hitting()) {
            System.out.println("Do you want to hit? Enter 'y' or 'n'");
            char choice = sc.next().charAt(0);

            while(!(choice == ('y') || choice == ('n'))) {
                System.out.println("Invalid entry, please enter 'y' or 'n'");
                choice = sc.next().charAt(0);
            }

            if (choice == 'y') {
                System.out.println("You have chosen to hit");
                System.out.println("This is your card: ");
                playRound(deck, hand, player, 1);
            } else {
                player.passes();
            }
        }

        // busto means that the Player is over 21 points.
        if(player.busto()) {
            playerLoses(player, bet);
        }
    }


    /**
     * This method handles the dealer playing
     * - J's are worth 11, Q's are worth 12, K's are worth 13, A's are worth 1
     * @param deck The deck of cards
     * @param player the Player or participant
     * @param bet the size of the bet that is being wagered.
     * @return Nothing. Just causes side effects
     */
    private static void dealerPlay(Deck deck, List<Card> hand, Dealer dealer, Player player, int bet) {
        System.out.println("Dealer's turn: ");
        System.out.println("Dealer turning hand faceup");

        for(Card card : hand) {
            card.turnFaceUp();
            System.out.println("Dealer has: " + card.getFace() + " of " + " Suit: " + card.getSuit());
        }

        while(dealer.getPoints() < dealer.minimumStop && !dealer.busto()) {
            playRound(deck, hand, dealer, 1);
        }

        if(dealer.busto()) {
            playerWins(player, dealer, bet);
        }
    }

    /**
     * This method handles the Player lost
     * @param player the Player or participant
     * @param bet the size of the bet that is being wagered.
     * @return Nothing. Just causes side effects
     */
    private static void playerLoses(Player player, int bet) {
        System.out.println("Player lost");
        player.loses(bet);
        System.out.println("Player now has: " + player.getBankroll() + " dollars");
    }

    private static void playerWins(Player player, Dealer dealer, int bet) {
        player.wins(bet);
        System.out.println("Player has won!");
        System.out.println("Player now has: " + player.getBankroll() + " dollars");
    }

    // used to show cards that were dealt and add to the hand. For example, 9S is the 9 of spades.
    public static void deal(Deck deck, List<Card> hand, int numberOfCardsToDeal) {
        while(numberOfCardsToDeal > 0) {
            Card card = deck.drawCard();
            hand.add(card);
            System.out.println("Drew card: " + card.getFace() + " of " + " Suit: " + card.getSuit());
            numberOfCardsToDeal--;
        }
    }

    private static void dealerDeal(Deck deck, List<Card> hand) {
        System.out.println("Dealer is drawing cards. One is facedown and unknown");
        Card card1 = deck.drawCard();
        // dealer cards should be hidden
        card1.turnFaceDown();
        hand.add(card1);
        System.out.println("Drew card: " + card1.getFace() + " of " + " Suit: " + card1.getSuit());

        Card card2 = deck.drawCard();
        hand.add(card2);
        System.out.println("Drew card: " + card2.getFace() + " of " + " Suit: " + card2.getSuit());
    }

    /**
     * This method handles calculating the points that a player has
     * Note: - J's are worth 11, Q's are worth 12, K's are worth 13, A's are worth 1
     * @param hand The listArray of cards that a Player/Dealer has
     * @param player the Player or participant
     * @return The number of points that a player has.
     */
    private static int calculateHandTotal(List<Card> hand, Player player) {
        int points = 0;

        for (Card card : hand) {
            points += card.getCardValue();

        }
        return points;
    }

    private static void startPlaying(Deck deck, List<Card> hand, Player player) {
        playRound(deck, hand, player, 2);
    }

    // add card to hand, calculate hand total, store point total
    /**
     * This method handles a round of cards.
     * a card is dealt, the new hand total is calculated, and the points are set to the player.
     * @param deck The deck of cards
     * @param hand The ListArray of Cards that a player has
     * @param player The player who is betting against the dealer.
     * @param numberOfCardsToDeal 2 cards are dealt to the Player at the start of the game, 1 card when the player wants
     *                            to hit
     * @return Nothing. Just causes side effects
     */
    private static void playRound(Deck deck, List<Card> hand, Player player, int numberOfCardsToDeal) {
        deal(deck, hand, numberOfCardsToDeal);
        int handTotal = calculateHandTotal(hand, player);
        System.out.println(player.getClass().getName() + " now has " + handTotal + " points");
        player.setPoints(handTotal);
    }

    /**
     * This method handles the end game. If both players are tied, the dealer wins. Whoever has more points wins.
     * @param dealer The Dealer
     * @param player the Player or participant
     * @param bet the size of the bet that is being wagered.
     * @return Nothing. Just causes side effects
     */
    private static void decideWhoWins(Dealer dealer, Player player, int bet) {
        if(player.getPoints() > dealer.getPoints()) {
            playerWins(player, dealer, bet);
        } else if(player.getPoints() < dealer.getPoints()) {
            playerLoses(player, bet);
        } else if(player.getPoints() == dealer.getPoints()) {
            playerLoses(player, bet);
        }
    }

    /**
     * This method handles setting the bankroll. Note: cannot enter an invalid amount or a char.
     * @return Nothing. Just causes side effects
     */
    private static void getMoney(Scanner sc, Player p1) {
        try {
            System.out.println("Welcome to blackjack tables. How much would you like to play with?");

            int bankroll = sc.nextInt();
            p1.setBankroll(bankroll);
        } catch (InputMismatchException e) {
            System.out.println("That is not a valid dollar amount. We are just going to set you with $100");
            p1.setBankroll(100);
            sc.nextLine();
        }
    }
}
