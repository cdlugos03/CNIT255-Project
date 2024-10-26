// CNIT25501_PROJECT -- Game.java
// Class Function: This class provides a basic structure for a game, including start and end functionalities.
// It can be extended by specific game classes, such as BlackJack, to implement custom game behavior.

public class Game {

     //Starts the game and prints a message indicating that the game has started.
     //This method can be overridden by subclasses to provide specific start-game behavior.
    public void startGame() {
        System.out.println("Game started.");
    }


     //Ends the game and prints a message indicating that the game has ended.
     //This method can be overridden by subclasses to provide specific end-game behavior.
    public void endGame() {
        System.out.println("Game ended.");
    }
}
