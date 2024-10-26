// CNIT25501_PROJECT -- Deck.java
// Class Function: This class represents a standard deck of playing cards.
// The Deck is built with 52 cards, each having a specific value and suit.
// It includes methods to build the deck, shuffle the cards, and draw a card from the deck.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    // Private field to store the list of Card objects representing the deck and a Random object for shuffling.
    private ArrayList<Card> cards;
    private Random random = new Random();

    //Constructor: Initializes a new Deck object, builds the deck with 52 cards, and shuffles it.
    public Deck() {
        buildDeck();
        shuffle();
    }

    //Builds the deck with 52 unique cards by iterating over all possible values and suits.
    //Adds each card to the ArrayList of cards.
    private void buildDeck() {
        cards = new ArrayList<>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};  // C = Clubs, D = Diamonds, H = Hearts, S = Spades

        for (String type : types) {
            for (String value : values) {
                cards.add(new Card(value, type));
            }
        }
    }

    //Shuffles the deck randomly using Collections.shuffle and the Random object.
    public void shuffle() {
        Collections.shuffle(cards, random);
    }


    //Draws a card from the top of the deck (last card in the list) and removes it from the deck.
    //Returns null if the deck is empty.
    //The drawn Card object or null if the deck is empty.
    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(cards.size() - 1);
    }
}
