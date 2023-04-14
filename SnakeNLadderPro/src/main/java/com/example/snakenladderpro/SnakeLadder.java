package com.example.snakenladderpro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize = 70, width = 10, height = 10;

    public static final int buttonLine = height*tileSize + 100;
    public static final int infoLine = buttonLine - 60;
    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent() {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: white;");
        root.setPrefSize(width*tileSize, height*tileSize + 160); // setting the size of our pane

        // created a loop to add 100 tiles to our grid.. 10x10 grid for the snake and ladder game
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize); // creating a new tile object
                tile.setTranslateX(j*tileSize); //giving the x coordinate from where out tile begins
                tile.setTranslateY(i*tileSize); //giving the y coordinate from where our tile begins
                root.getChildren().add(tile); // adding our single tile to the pane/ layout
            }
        }

        Image img = new Image("C:\\Java Projects Acciojob\\SnakeNLadderPro\\src\\main\\snakesladders-finalboard.jpg"); // importing an image
        ImageView board = new ImageView(); //creating a new image view
        board.setImage(img); // in our imageview we are placing our imported image
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        Image strt = new Image("C:\\Java Projects Acciojob\\SnakeNLadderPro\\src\\main\\pngtree-new-start-game-png-image_2944604.png");
        ImageView img1 = new ImageView(strt);

        Button startButton = new Button(); // this button will start our game
        startButton.setGraphic(img1); // adding start image to the button
        startButton.setTranslateY(buttonLine); // the button will start from this position on the Y axis
        startButton.setTranslateX(290); //the button will start from this position on the X axis
        startButton.setMaxWidth(100);
        startButton.setMaxHeight(42);

        // creating two buttons, one for both the players
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");

        // fixing the dimensions for player one button
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(40);
        playerOneButton.setPrefSize(100, 46);
        playerOneButton.setDisable(true);

        // fixing the dimensions for player two button
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(560);
        playerTwoButton.setPrefSize(100, 46);
        playerTwoButton.setDisable(true);

        //info display
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Image snl = new Image("C:\\Java Projects Acciojob\\SnakeNLadderPro\\src\\main\\snakes ladder text.png");
        ImageView snlIV = new ImageView(snl);
        Label snlFont = new Label("", snlIV);

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(55);

        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(575);

        snlFont.setTranslateY(height*tileSize + 5);
        snlFont.setTranslateX(220);

        //Initialising our players
        playerOne = new Player(25, Color.BLACK, "P1"); //radius, color, and name
        playerTwo = new Player(20, Color.WHITE, "P2");
        playerOne.getToken().setStrokeWidth(2);
        playerOne.getToken().setStroke(Color.WHITE);
        playerTwo.getToken().setStrokeWidth(2);
        playerTwo.getToken().setStroke(Color.BLACK);

        //Setting action for our players
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) { // the button will only work when the game is running
                    if (playerOneTurn) {
                        playerTwoLabel.setText("");
                        int diceValue = dice.getRolledDiceValue();
                        playerOneLabel.setText("Dice Value: " + diceValue);
                        playerOne.movePlayer(diceValue);
                        //winning condition
                        if (playerOne.hasWon()) {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("YOU WON!");
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("Better Luck Next Time");

                            startButton.setDisable(false); //re-enabling the start button
                            gameStarted = false;

                        }
                        else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true); //after one's turn his button will be disabled

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false); //enabling the button for player two
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) {
                    if (playerTwoTurn) {
                        playerOneLabel.setText("");
                        int diceValue = dice.getRolledDiceValue();
                        playerTwoLabel.setText("Dice Value: " + diceValue);
                        playerTwo.movePlayer(diceValue);
                        if (playerTwo.hasWon()) {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("Better Luck Next Time");
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("YOU WON!");

                            startButton.setDisable(false); //re-enabling the start button
                            gameStarted = false;

                        }
                        else {
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);

                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerOne.getName());
                        }
                    }
                }
            }
        });

        // setting the action for start button
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true; //changing the state of the game
                startButton.setDisable(true); // disabling the button so that the user is not able to click it anymore
                playerOneTurn = true; //player one's turn is set true in the first place
                playerOneLabel.setText("Your Turn " + playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition(); //bringing the token to 1
                playerTwoTurn = false;
                playerTwoLabel.setText(""); //clearing the label for player two
                playerTwoButton.setDisable(true); // when it is player one's turn, button for player two cannot be pressed
                playerTwo.startingPosition();
            }
        });

        // adding all the created components to our pane
        root.getChildren().addAll(board,
                startButton, playerOneButton, playerTwoButton,
                playerOneLabel, playerTwoLabel, snlFont,
                playerOne.getToken(), playerTwo.getToken()
        );

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snakes & Ladder! Let's Play");
        Image icon = new Image("C:\\Java Projects Acciojob\\SnakeNLadderPro\\src\\main\\snl-Icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}