# snakeLadder
What the Application does?
- It is the virtual version of the famous snake and ladder game. In this game we have a 10x10 grid(board), numbered from 1 to 100. There are two or more players in the game. Each starts from the number 1 and whoever reaches 100 first wins the game. Their move depends on the number that they get on roll of a dice. But the things are not that simple. There are ladders to lift up the mood (well the token as well) and there are some party spoilers(snakes) who you need to be aware of. If a player reaches the tail of a ladder then his token reaches the top of the ladder but when a player reaches the mouth of a snake, then he falls down to where the snake's tail is. 

What are the technologies used? 
- Javafx and Java

What are the functionalities used for the UI?
1. First, for the overall layout, I have used the Pane class because it allows us to lay our children nodes and acts as a base layout for them. 
2. Then i created a grid of 10x10 tiles for our board. Over this grid, I have placed an image of the snake and ladder board which will show the user where they are and where the snakes and ladders are. (They cant do anything about this information though, when a snake comes it will bite them for sure).
3. For the players, they have got one token each. The circle class is used for the tokens. Each player has a token of different color.
4. Then I used three buttons, one will start the game, one is for player one which will roll the dice for him and also move his token forward the number of positions that the dice will roll and the last button is for player two.
5. There is a label for player one which will tell him that it his turn or the value of the dice roll or else that he has won the game. Similarly there is a lable for player two.
6. When any player wins the game by reaching 100, the tokens are again set to their default locations and they can start the game again.

What is happening in the backend?
1. First of all whenever the start button is clicked, its setOnFunction is called and the game is started and the button for player one is enabled and he can now press the button for his move. The start button is disabled now till any player wins the game.
2. When the player one button is pushed its respective setOnAction function is called and a random number is generated from 1 to 6 by calling the getRolledDiceValue() function in the dice class and displayed on the label for player one. Then the token for player one is moved to those many positions by calling the movePlayer() function defined in the player class. Player one's button is diabled now and player two's button gets enabled and player two's label shows your turn. 
3. When the player tow button is pushed its respective setOnAction function is called and a random number is generated from 1 to 6 and displayed on the label for player two. Then the token for player two is moved to those many positions by calling the movePlayer() function defined in the player class. Player two's button is diabled now and player one's button gets enabled and player one's label shows your turn.
4. The movePlayer() function in the player class ensures that a player's token is moved to its new position keeping in mind that the token cant be moved in case the movement takes the player to a value over 100. It should be exactly equal to 100.
5. The board class basically initialises two Arraylists. One stores the numbers from 1 to 100 in the manner that is desired in the snake and ladder game. The other stores the coordinates of the head and tail of the snakes and ladders. If a player reaches these cordinates where ladders or snakes are located they are to be moved accordingly. 
6. At every button click the winning condition is checked(player has reached 100). If the winning condition is satisfied the player who won is announced in its respective label and the start button is enabled again. 
