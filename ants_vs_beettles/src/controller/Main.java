package controller;

import model.board.Board;
import model.board.Coordinate;
import model.board.Tile;
import model.insect.Insect;
import model.insect.ants.Scout;
import model.insect.beetles.Finder;
import model.player.Player;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        // Init the game
        System.out.println("Ants vs Beetles");
        Board board = new Board();
        Player player1 = new Player();
        Player player2 = new Player();

        // Generate the food (which class???) - TO BE IMPLEMENTED IN THE SECOND ASSIGNMENT?

        // THE GAME
        // TODO: get user choice
        String choice;

        // Player1 chooses to add a scout on the board
        choice = "add";
        Insect insect1 = new Scout();
        ArrayList<Tile> validTiles = board.getValidTiles(1, choice);

        // Player1 places the scout on the board
        boolean validMovement = false;
        while (!validMovement) {
            // TODO: new method: Get user to input the coord
            Coordinate coordinate = new Coordinate(0,0);
            insect1.setCoordinate(coordinate);

            validMovement = player1.placeInsect(validTiles, insect1);
        }
        board.registerInsect(insect1);
    }
}