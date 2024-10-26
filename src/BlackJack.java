// CNIT25501_PROJECT -- BlackJack.java
// Class Function: This class represents a BlackJack game using a graphical interface.
// It extends the Game class and uses a Deck, Player, and Dealer to manage game flow.
// The interface includes buttons for player actions and displays cards and results.

import javax.swing.*;
import java.awt.*;

public class BlackJack extends Game {  // BlackJack now extends Game
    private Deck deck;
    private Player player;
    private Dealer dealer;  // Changed to Dealer type instead of Player
    private JFrame frame;
    private JPanel gamePanel;
    private JButton hitButton;
    private JButton stayButton;
    private JButton restartButton;
    private JButton quitButton;
    private String message = "";  // Stores the result or status message for display
    private boolean revealDealerCard = false;  // Controls whether to reveal the dealer's first card


    //This Constructor Initializes the BlackJack game with a new deck, player, and dealer and
    //sets up the graphical user interface and starts a new game.
    public BlackJack() {
        super();  // Call Game constructor
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();  // Using Dealer class for dealer
        initializeUI();
        startGame();
    }


    //Initializes the graphical user interface for the BlackJack game.
    //Sets up the main frame, game panel, and action buttons.
    private void initializeUI() {
        frame = new JFrame("Black Jack");
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        // Set the background color to green
        gamePanel.setBackground(new Color(53, 101, 77)); // Dark green

        //UI Buttons used for game interaction
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        restartButton = new JButton("Restart");
        quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);

        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        setButtonActions();
    }

    //Defines actions for the Hit, Stay, Restart, and Quit buttons.
    private void setButtonActions() {
        hitButton.addActionListener(e -> {
            player.addCard(deck.drawCard());
            if (player.getScore() > 21) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);
                displayMessage("Bust! You Lose!");
            }
            gamePanel.repaint();
        });

        stayButton.addActionListener(e -> {
            hitButton.setEnabled(false);
            stayButton.setEnabled(false);
            revealDealerCard = true; // Reveal dealer's first card
            while (dealer.getScore() < 17) {
                dealer.addCard(deck.drawCard());
            }
            gamePanel.repaint();
            checkWinner();
        });

        restartButton.addActionListener(e -> restartGame());

        quitButton.addActionListener(e -> frame.dispose());
    }


    //Starts a new game, shuffles the deck, clears players' hands, and deals initial cards.
    //Initializes player control buttons and resets display settings.
    @Override
    public void startGame() {
        super.startGame();  // Calls the Game class's startGame
        deck.shuffle();
        player.clearHand();
        dealer.clearHand();

        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());  // Dealer's hidden card
        dealer.addCard(deck.drawCard());

        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
        revealDealerCard = false;  // Hide dealer's first card initially
        message = "";  // Clear message
        gamePanel.repaint();
    }


    //Restarts the game by calling the startGame method.
    private void restartGame() {
        startGame();
    }


    //Checks for the game winner and displays the result based on player and dealer scores.
    private void checkWinner() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore > 21) {
            displayMessage("Bust! You Lose!");
        } else if (dealerScore > 21) {
            displayMessage("You Win!");
        } else if (playerScore > dealerScore) {
            displayMessage("You Win!");
        } else if (playerScore == dealerScore) {
            displayMessage("Tie!");
        } else {
            displayMessage("You Lose!");
        }
    }


    //Updates the display message and triggers a repaint of the game panel.
    private void displayMessage(String msg) {
        this.message = msg;
        gamePanel.repaint(); // Trigger repaint to show message
    }


    //Draws the game elements on the game panel, including player and dealer hands and result messages.
    private void drawGame(Graphics g) {
        int cardWidth = 110;
        int cardHeight = 154;
        int xOffset = 20;
        int yOffsetPlayer = 320;
        int yOffsetDealer = 20;

        // Draw player's hand
        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
            g.drawImage(cardImg, xOffset + (cardWidth + 5) * i, yOffsetPlayer, cardWidth, cardHeight, null);
        }

        // Draw dealer's hand
        for (int i = 0; i < dealer.getHand().size(); i++) {
            Card card = dealer.getHand().get(i);
            Image cardImg;
            if (i == 0 && !revealDealerCard) {
                // Display the back of the card for the dealer's first card
                cardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
            } else {
                cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
            }
            g.drawImage(cardImg, xOffset + (cardWidth + 5) * i, yOffsetDealer, cardWidth, cardHeight, null);
        }

        // Display result message if present
        if (!message.isEmpty()) {
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.setColor(Color.white);
            g.drawString(message, 220, 250);
        }
    }
}
