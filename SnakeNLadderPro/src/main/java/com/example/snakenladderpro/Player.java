package com.example.snakenladderpro;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {
    private Circle token; // the shape of the token is circle so we will use that shape
    private int currentPos; // the current position of the player token
    private String name; //name of the player
    private static Board gameBoard = new Board(); // calling this function will create our game board of 10x10 and also populate the position coordinates for each position

    public Player(int rad, Color coinColor, String playerName) {
        token = new Circle(rad); // giving the radius of the circle
        token.setFill(coinColor);
        currentPos = 0;
        movePlayer(1); //starting position of the token
        name = playerName;
    }

    public void movePlayer(int diceValue) {
        if (currentPos + diceValue <= 100) {
            currentPos += diceValue; // we only need to move our player if the above condition is satisfied
            // for eg: we will not move if we are at 97, and we get a 6 on the diceValue
            TranslateTransition secondMove = null, firstMove =  translateAnimation(diceValue);

            //code for the case of snake or ladder
            int newPos = gameBoard.getNewPosition(currentPos);
            if (newPos != currentPos && newPos != -1) {
                // this mean that there is a snake/ladder present so update the currentPosition
                currentPos = newPos;
                secondMove = translateAnimation(6);
            }

            if (secondMove == null) {
                firstMove.play(); // there was no second move due to no snake or ladder
            }

            else {
                SequentialTransition seq = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(300)), secondMove); //sequential transition helps us to get the desired result by running both animations and also we are taking a pause in between
                seq.play();
            }
        }
//        we don't need the following now because we are handling the movement by animation
//        int x = gameBoard.getXCoordinate(currentPos);// getting the x coordinate of our new location where the token will move
//        int y = gameBoard.getYCoordinate(currentPos);// getting y coordinate for new location
//
//        // moving our token at the new position
//        token.setTranslateX(x);
//        token.setTranslateY(y);
    }

    // function to animate the token movement
    private TranslateTransition translateAnimation(int diceValue) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(200 * diceValue), token);
        animate.setToX(gameBoard.getXCoordinate(currentPos));
        animate.setToY(gameBoard.getYCoordinate(currentPos));
        animate.setAutoReverse(false); //we don't want the animation to automatically reverse
        return animate; //we need to return because we need the value, if we use void function then we can see that a person is moving directly to the final position
        // without first reaching the starting point of the snake or ladder
    }
    // starting position function
    public void startingPosition() {
        currentPos = 0;
        movePlayer(1);
    }
    //function which checks is a player has won the game
    boolean hasWon() {
        if (currentPos == 100) return true;
        return false;
    }

    // creating getters of the values so that we can get them in our snake and ladder class
    public Circle getToken() {
        return token;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public String getName() {
        return name;
    }
}
