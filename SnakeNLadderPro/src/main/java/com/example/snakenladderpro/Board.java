package com.example.snakenladderpro;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> snakeLadderPosition;

    public Board() {
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadderPositions();
    }

    private void populateSnakeLadderPositions() {
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i); //initially mapped each index with the same value;
        }
        //setting the locations where the ladders and snake reach manually
        snakeLadderPosition.set(2,38);
        snakeLadderPosition.set(7,14);
        snakeLadderPosition.set(8,31);
        snakeLadderPosition.set(15,26);
        snakeLadderPosition.set(16,6);
        snakeLadderPosition.set(21,42);
        snakeLadderPosition.set(28,84);
        snakeLadderPosition.set(36,44);
        snakeLadderPosition.set(46,25);
        snakeLadderPosition.set(49,11);
        snakeLadderPosition.set(51,67);
        snakeLadderPosition.set(62,19);
        snakeLadderPosition.set(64,60);
        snakeLadderPosition.set(71,91);
        snakeLadderPosition.set(74,53);
        snakeLadderPosition.set(78,98);
        snakeLadderPosition.set(87,94);
        snakeLadderPosition.set(89,68);
        snakeLadderPosition.set(92,88);
        snakeLadderPosition.set(95,75);
        snakeLadderPosition.set(99,80);
    }

    public int getNewPosition(int currentPos) {
        if (currentPos >0 && currentPos<= 100) {
            return snakeLadderPosition.get(currentPos); // if we are at a starting point of a snake or ladder it will
                                                        // to the value kept at the index in the arraylist which is
                                                        // equal to the ending point of snl
        }
        return -1;
    }

    private void populatePositionCoordinates() {
        positionCoordinates.add(new Pair<>(0, 0)); // creating dummy value
        int h = SnakeLadder.height; // extracting the height of our grid
        int w = SnakeLadder.width; // extracting width
        int tSize = SnakeLadder.tileSize; // size of each tile
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // x coordinates for each no.
                int xCord = 0;
                if (i%2 == 0) {
                    // when the row is even then the numbers are increasing
                    xCord = j*tSize + tSize/2;
                }
                else {
                    // when the row is odd then the numbers are decreasing
                    xCord = tSize*h - (j*tSize) - tSize/2;
                }
                // y coordinates for each no.
                int yCord = tSize*h - (i*tSize) - tSize/2;

                positionCoordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }

    int getXCoordinate(int position) {
        if (position >=1 && position<= 100) { //if we are in a valid position
            return positionCoordinates.get(position).getKey(); //the x coordinate for the position is stored in the Arraylist in the key
        }
        return -1;
    }

    int getYCoordinate(int position) {
        if (position >=1 && position<= 100) { //if we are in a valid position
            return positionCoordinates.get(position).getValue(); //the y coordinate for the position is stored in the Arraylist in the value
        }
        return -1;
    }

//    just for testing purpose
//    public static void main(String[] args) {
//        Board b = new Board();
//        for (int i = 0; i < b.positionCoordinates.size(); i++) {
//            System.out.println(i + " $ x :" + b.positionCoordinates.get(i).getKey() +
//                    " y : " + b.positionCoordinates.get(i).getValue()
//
//            );
//        }
//    }
}
