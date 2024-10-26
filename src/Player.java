// CNIT25501_PROJECT -- Player.java
// Class Function: This class represents a player in a card game.
// Each Player has a hand of cards, keeps track of Aces in the hand, and calculates the score dynamically.
// The class includes methods for adding cards to the hand, adjusting scores for Aces, and clearing the hand.

import java.util.ArrayList;

public class Player {
    // Protected fields to store the player's hand of cards, count of Aces, and current score.
    protected ArrayList<Card> hand;
    protected int aceCount;
    protected int score;

    //Constructor: Initializes a new Player object with an empty hand, zero Ace count, and zero score.
    public Player() {
        hand = new ArrayList<>();
        aceCount = 0;
        score = 0;
    }


     //Adds a Card to the player's hand, updates the score based on the card's value,
     //and increments the Ace count if the card is an Ace.
    public void addCard(Card card) {
        hand.add(card);
        score += card.getValue();
        if (card.isAce()) aceCount++;
    }

    //Retrieves the player's score, adjusting it if necessary for Aces to avoid exceeding 21.
    //Returns the reduced score if applicable
    public int getScore() {
        return reduceAceIfNeeded();
    }


    //Adjusts the player's score by reducing the value of Aces from 11 to 1 if the score exceeds 21.
    //Once the score is correctly adjusted it will be returned
    private int reduceAceIfNeeded() {
        int adjustedScore = score;
        int aces = aceCount;
        while (adjustedScore > 21 && aces > 0) {
            adjustedScore -= 10;
            aces--;
        }
        return adjustedScore;
    }

    //Clears the Player's hand and resets the Ace count and score to zero.
    public void clearHand() {
        hand.clear();
        aceCount = 0;
        score = 0;
    }

    //Retreives the current hand of cards held by the player
    //and returns an arraylist of card objects that represents the player's hand.
    public ArrayList<Card> getHand() {
        return hand;
    }
}
