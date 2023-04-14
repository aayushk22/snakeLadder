package com.example.snakenladderpro;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    // creating one tile that needs to be there in our grid
    public Tile (int tileSize) {
        setWidth(tileSize); // setting the width of a single tile
        setHeight(tileSize); // setting the height of a single tile
        setFill(Color.DODGERBLUE);
        setStroke(Color.BLACK); // color of the border
    }
}
