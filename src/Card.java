// CNIT25501_PROJECT -- Card.java
// Class Function: This class represents a playing card with a value and suit
// Each Card has methods to retrieve its numerical value for gameplay,
// check if it is an Ace, and return a string for the image path.

public class Card {
    // Private fields for the card's value and type (e.g., "A", "10", "K" and "Hearts", "Diamonds").
    private String value;
    private String type;

    //This constructor initializes a Card object with a specific value and type.
    //Value represents the face value of the card (e.g., "A", "2", "10", "K").
    //type represents the suit of the card (e.g., "Hearts", "Diamonds", "Clubs", "Spades").
    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    //This converts the card to a string in the format "value-type" (e.g. "A-Hearts")
    public String toString() {
        return value + "-" + type;
    }


    //Retrieves the numerical value of the card
    //Face cards (J, Q, K) are worth 10, Ace is worth 11, and number cards are worth their numeric value.
    public int getValue() {
        if ("AJQK".contains(value)) {
            return value.equals("A") ? 11 : 10;
        }
        return Integer.parseInt(value);
    }

    //Used to determine if the card is an Ace and returns True if the card is an Ace, false otherwise.
    public boolean isAce() {
        return value.equals("A");
    }
    //Retrieves the file path of the image associated with the card
    //the image path follows the structure "./cards/value-type.png" based on the cards string representation
    public String getImagePath() {
        return "./cards/" + toString() + ".png";
    }
}
