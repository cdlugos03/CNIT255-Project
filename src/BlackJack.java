// CNIT25501_PROJECT -- BlackJack.java
//Class Function: This class represents a BlackJack game using a graphical interface.
//It extends the Game class and uses a Deck, Player, and Dealer to manage game flow.
//The interface includes buttons for player actions and displays cards and results.

import javax.swing.*;
import java.awt.*;

//BlackJack now extends Game
public class BlackJack extends Game {
    //deck of cards used in the game
    private Deck deck;
    //player in the game is created
    private Player player;
    //dealer in the game is created which inherits from the player
    private Dealer dealer;
    //main game window is created
    private JFrame frame;
    //panel for displaying the cards and results
    private JPanel gamePanel;
    //Hit button for pulling another card
    private JButton hitButton;
    //stay button for letting the dealer pull a card
    private JButton stayButton;
    //restart button restarts the current game to play again
    private JButton restartButton;
    //quit program to close the window and the game
    private JButton quitButton;
    //Stores the result or status message for display
    private String message = "";
    //Controls whether to reveal the dealer's first card
    private boolean revealDealerCard = false;


    //This Constructor Initializes the BlackJack game with a new deck, player, and dealer and
    //sets up the graphical user interface and starts a new game.
    public BlackJack() {
        //Call Game constructor
        super();
        //creates a new deck that is shuffled
        deck = new Deck();
        //initializes a new player
        player = new Player();
        //initializes a new dealer
        dealer = new Dealer();
        //calls this function to set up the GUI
        initializeUI();
        //starts a new game instance
        startGame();
    }


    //Initializes the graphical user interface for the BlackJack game.
    //Sets up the main frame, game panel, and action buttons.
    private void initializeUI() {
        //main game window
        frame = new JFrame("Black Jack");
        //create a new panel for game area
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                //calls superclass method
                super.paintComponent(g);
                //calls method to draw game element
                drawGame(g);
            }
        };

        //Set the background color to green
        gamePanel.setBackground(new Color(53, 101, 77));

        //UI Buttons used for game interaction
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        restartButton = new JButton("Restart");
        quitButton = new JButton("Quit");

        //creates a panel for the input buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);

        //sets the window size
        frame.setSize(700, 700);
        //closes the application when the window is physically closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets the layout manager for the frame
        frame.setLayout(new BorderLayout());
        //adds the game panel to the ceneter of frame
        frame.add(gamePanel, BorderLayout.CENTER);
        //adds the button panel to the bottom of the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        //makes the frame visible
        frame.setVisible(true);
        //set up the button action
        setButtonActions();
    }

    //Defines actions for the Hit, Stay, Restart, and Quit buttons.
    private void setButtonActions() {
        //adds an actionlistener to the hitbutton to define it behavior when the user presses it
        hitButton.addActionListener(e -> {
            //player action to draw a card from the deck
            player.addCard(deck.drawCard());
            //check if the player score is greater than 21
            if (player.getScore() > 21) {
                //if the players score is above 21 the hit button disables
                hitButton.setEnabled(false);
                //the stay button is also disabled
                stayButton.setEnabled(false);
                //the message is displyaed to the screen that the player has lost
                displayMessage("Bust! You Lose!");
            }
            //updates the display
            gamePanel.repaint();
        });
        //adds an action listener to the staybutton to define its behavior
        stayButton.addActionListener(e -> {
            //disables hit button when stay button is clicked
            hitButton.setEnabled(false);
            //stay button is also disabled
            stayButton.setEnabled(false);
            //reveals the dealers first card
            revealDealerCard = true;
            //dealer draws card until their score is at least 17
            while (dealer.getScore() < 17) {
                //dealer draws a card
                dealer.addCard(deck.drawCard());
            }
            //updates the display
            gamePanel.repaint();
            //calls the method to check who won
            checkWinner();
        });
        //adds action listener to the restart button to define its behavior
        restartButton.addActionListener(e -> restartGame());
        //closes the game window when pressed
        quitButton.addActionListener(e -> frame.dispose());
    }


    //Starts a new game, shuffles the deck, clears players' hands, and deals initial cards.
    //Initializes player control buttons and resets display settings.
    @Override
    public void startGame() {
        //calls the startgame method of the superclass
        super.startGame();
        //shuffle deck
        deck.shuffle();
        //clears the players hand
        player.clearHand();
        //clears the dealers hand
        dealer.clearHand();

        //starts by dealing two cards to the player
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        //deals two cards to the dealer the first card is initially hidden
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        //enables the hit and stay button for input
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
        //sets the flag to hide the dealers first card
        revealDealerCard = false;
        //clears any message
        message = "";
        //updates the display
        gamePanel.repaint();
    }

    //Restarts the game by calling the startGame method.
    private void restartGame() {
        startGame();
    }


    //Checks for the game winner and displays the result based on player and dealer scores.
    private void checkWinner() {
        //retreives the players score
        int playerScore = player.getScore();
        //retrieves the dealers score
        int dealerScore = dealer.getScore();
        //check the outcome based on the player and dealer score
        if (playerScore > 21) {
            //player busts and loses!
            displayMessage("Bust! You Lose!");
            //checks if the dealer busts
        } else if (dealerScore > 21) {
            //displays that the player wins
            displayMessage("You Win!");
            //check if you have a higher score than the dealer
        } else if (playerScore > dealerScore) {
            displayMessage("You Win!");
            //check if its a tie
        } else if (playerScore == dealerScore) {
            displayMessage("Tie!");
            //check if the players score is lower
        } else {
            displayMessage("You Lose!");
        }
    }


    //Updates the display message and triggers a repaint of the game panel.
    private void displayMessage(String msg) {
        //sets the message to be displayed and updates the game panel display
        this.message = msg;
        gamePanel.repaint();
    }


    //Draws the game elements on the game panel, including player and dealer hands and result messages.
    private void drawGame(Graphics g) {
        //sets the width of the card images
        int cardWidth = 110;
        //sets the card height of the display
        int cardHeight = 154;
        //sets horizontal offset for card position
        int xOffset = 20;
        //vertical position for players card
        int yOffsetPlayer = 320;
        //vertical offset for dealers card
        int yOffsetDealer = 20;

        //Draw player's hand by looping through the players hand
        for (int i = 0; i < player.getHand().size(); i++) {
            //retrieves the card at the curren loop index
            Card card = player.getHand().get(i);
            //loads the image of the card based on the image path
            Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
            //displays the card image on the game panel
            g.drawImage(cardImg, xOffset + (cardWidth + 5) * i, yOffsetPlayer, cardWidth, cardHeight, null);
        }

        //loops through delaers hand to draw each card
        for (int i = 0; i < dealer.getHand().size(); i++) {
            //retrieves the card at that current index
            Card card = dealer.getHand().get(i);
            Image cardImg;
            //determines if the first card should be hidden
            if (i == 0 && !revealDealerCard) {
                //display the back of the card for the dealer's first card
                cardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
            } else {
                //loads the image of the card based on its image path
                cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
            }
            //draws the card image on the game panel
            g.drawImage(cardImg, xOffset + (cardWidth + 5) * i, yOffsetDealer, cardWidth, cardHeight, null);
        }

        // Display result message if present such as the game result
        if (!message.isEmpty()) {
            //sets the font for the message
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            //sets the color of the message text
            g.setColor(Color.white);
            //draws the message on the game panel
            g.drawString(message, 220, 250);
        }
    }
}
