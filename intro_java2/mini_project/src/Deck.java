/**
 * This class represents the deck. It can shuffle and return a drawn card.
 * @author jwan
 * @version 1.0
 */

import java.util.Random;

public class Deck {
    private Card[] deck;

    // used for drawing cards. Needs to be an instance method because the deck needs to retain knowledge about how
    // many cards were drawn
    private int counter = 0;

    // This constructor builds a deck of 52 cards.
    public Deck() {
        deck = new Card[52];
        int index = 0;

        for (int i = 0; i < 4; i++){
            for (int j = 1; j < 14; j++){
                deck[index] = new Card(i, j);
                index++;
            }
        }
    }

    // This method shuffles the deck (randomizes the array of cards) by looping over the cards and swapping each one
    // with another card in a random position.
    public void shuffle() {
        Random r = new Random();

        for (Card c : deck) {
            int x = r.nextInt(52);
            Card temp = c;
            c = deck[x];
            deck[x] = temp;
        }
    }

    /**
     * Draws a card, replaces the card in deck with null, and increments/decrements appropriate counters.
     * Sometimes we might draw a card that has already been selected. So, we need the while loop to keep picking
     * until a card is returned.
     * @return the current card
     */
    public Card drawCard() {
        Card current = null;
        while(current == null) {
            current = deck[counter];
            deck[counter] = null;
            counter++;
        }

        return current;
    }
}

