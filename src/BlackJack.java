import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //storing cards in players hand
import java.util.Random; //used for shuffling and pulling cards
import javax.smartcardio.Card;
import javax.swing.*;


public class BlackJack {
    private static final ArrayList<Card> D = new ArrayList<Card>();

    private class Card {
        String value;
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return value + "-" + type;
        }

    }

    BlackJack() {
        startGame();
    }

    public void startGame() {
        buildDeck();
    }

    public void buildDeck() {
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (String type : types) {
            for (String value : values) {
                Card card = new Card(value, type);
                D.add(card);
            }
        }

        System.out.println("BUILD DECK:");
        System.out.println(D);
        System.out.println(D.size());
    }


}
