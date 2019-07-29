/**
 * This class represents a single card in the blackjack deck
 * @author jwan
 * @version 1.0
 */

public class Card {
    // Cards: The cards in the deck by ordinal rank
    private String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};

    // Suits: clubs, hearts, spades, diamonds
    private String[] suits = {"C", "H", "S", "D"};

    private int cardSuit, cardFace;
    private boolean faceUp;

    // This constructor builds a card with the given suit and face, turned face down.
    public Card(int cardSuit, int cardFace) {
        this.cardSuit = cardSuit;
        this.cardFace = cardFace;
        this.faceUp = true;
    }

    public String getSuit() {
        if(this.faceUp == true) {
            return suits[cardSuit];
        } else {
            return "Unknown because card is facedown";
        }
    }

    // This method retrieves the face (ace through king) of this card. We have to do cardFace - 1 because we initialize
    // using numbers 1-13 for the cardFace. This makes adding points together easier. To compensate, we have to subtract 1
    // when retrieving the readable value of the card (A, 2, 3).
    public String getFace() {
        if(this.faceUp == true) {
            return cards[cardFace - 1];
        } else {
            return "Unknown because card is facedown";
        }
    }

    public int getCardValue() {
        return this.cardFace;
    }

    public void turnFaceUp() {
        this.faceUp = true;
    }

    // This method records that only the back of the card should be visible.
    public void turnFaceDown() {
        this.faceUp = false;
    }
}