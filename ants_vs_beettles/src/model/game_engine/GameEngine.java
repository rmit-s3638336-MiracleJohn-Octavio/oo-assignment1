package model.game_engine;

import com.google.java.contract.Ensures;
import console_view.ErrorMessage;
import controller.DashboardVC;
import controller.Helper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.InsectGenerator;
import model.player.Player;

import java.io.IOException;
import java.util.List;

public class GameEngine {
    private Board board;
    private Player[] players;
    private int turn;
    private InsectGenerator insectGenerator;
    private Insect currentInsect;
    private List<Tile> currentValidTiles;
    private Mode mode;
    private ErrorMessage errorMessage;
    private DashboardVC controller;

    public GameEngine(Stage primaryStage) {
    	// UI Version
    	FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
        Parent dashboardUI = null;
        try {
            dashboardUI = dashboardLoader.load();
            controller = dashboardLoader.getController();
            controller.setGameEngine(this);

            board = new Board();

            // View
            controller.drawTiles(board.getAllTiles());
            controller.loadPanels();
            board.updateBoard();

//    	Parent dashboardUI = FXMLLoader.load(getClass().getResource("/view/DashboardView.fxml"));
            primaryStage.setTitle("Ants VS Beetle");
            primaryStage.setScene(new Scene(dashboardUI, Helper.WINDOW_W, Helper.WINDOW_H));
            primaryStage.setResizable(false);
            primaryStage.show();
            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
                primaryStage.setTitle(newVal.toString());
            });
            primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
                primaryStage.setTitle(newVal.toString());
            });
        } catch (IOException e) {
            System.out.println("File not found");
            // TODO: Exit? Popup?
        }

        players = new Player[]{new Player(), new Player()};
        turn = 0;
        insectGenerator = new InsectGenerator();
        mode = Mode.UNDEFINED;
        errorMessage = new ErrorMessage();
    }

    public void selectNewInsect(String insectType) {
        if (players[turn].reachedMaxInsects()) {
            // TODO: VIEW - display error (exception?)
            errorMessage.printError("Cannot add more insects.");
            return;
        }

        currentInsect = insectGenerator.createInsect(insectType);
        mode = Mode.PLACE;
        currentValidTiles = board.getValidPlaceTiles(turn);

        System.out.println("CurrentInsect (after selecting a new one): " + currentInsect);
        controller.drawTiles(board.getAllTiles());
    }

    public void setMode(String mode) {
        if (mode.equals("move")) {
            this.mode = Mode.MOVE;
            currentValidTiles = board.getValidMoveTiles(currentInsect);
        } else {
            this.mode = Mode.ATTACK;
            currentValidTiles = board.getValidAttackTiles(currentInsect);
        }
    }

    public void processSelectedTile(int x, int y) {
        System.out.println("CurrentInsect: " + currentInsect);
        Tile selectedTile = board.getTile(x, y);
        switch (mode) {
            case PLACE:
                placeInsectOnto(selectedTile);
                break;
            case MOVE:
                moveInsectTo(selectedTile);
                break;
            case ATTACK:
                attack(selectedTile);
                break;
            case UNDEFINED:
                setCurrentInsect(selectedTile);
        }
        
        controller.drawTiles(board.getAllTiles());
    }

    private void setCurrentInsect(Tile selectedTile) {
        if (selectedTile != null) {
            currentInsect = selectedTile.getInsect();
            board.updateBoard();
        }
    }

    private void placeInsectOnto(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            currentInsect.setTile(selectedTile);
            players[turn].placeInsect(currentInsect);
            selectedTile.setInsect(currentInsect);
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot be placed on the selected tile.");
        }

        board.updateBoard();
    }

    private void moveInsectTo(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            currentInsect.getTile().resetInsect();
            currentInsect.setTile(selectedTile);
            selectedTile.setInsect(currentInsect);
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot move to the selected tile.");
        }

        board.updateBoard();
    }

    // TODO
    private void attack(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            // TODO: some other stuff
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot attack the selected tile.");
        }

        board.updateBoard();
    }

    private boolean validTileSelection(Tile selectedTile) {
        return currentValidTiles.contains(selectedTile);
    }

    @Ensures("old(turn) != turn")
    private void toggleTurn() {
        // Reset
        currentInsect = null;
        currentValidTiles = null;
        mode = Mode.UNDEFINED;

        // TODO: checkWin() and enable/disable ants/beetles

        // Switch to the other player
        turn = (turn % 2 == 0) ? 1 : 0;
    }
}
