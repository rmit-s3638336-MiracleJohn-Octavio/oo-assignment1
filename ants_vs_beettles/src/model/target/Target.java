package model.target;

import controller.Helper;
import model.board.Board;

import java.util.Random;

public abstract class Target {

    String coordinate = null;
    Boolean isAddedToTile = false;

    public abstract String getFullName();

    public void generateCoordinate() {
        if (this.coordinate == null) {
            int row = new Random().nextInt(Board.BOARD_SIZE);
            int col = new Random().nextInt(Board.BOARD_SIZE);

            this.coordinate = Integer.toString(row) + "-" + Integer.toString(col);
        }
    }

    public String getCoordinate() {
        return coordinate;
    }

    public Boolean getAddedToTile() {
        return isAddedToTile;
    }

    public void setAddedToTile(Boolean addedToTile) {
        isAddedToTile = addedToTile;
    }
}
