package model.board;

import model.insect.Insect;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
	public static final int BOARD_SIZE = 12;
	private static final int PLAYER_0_VALID_PLACING_COL = 0;
	private static final int PLAYER_1_VALID_PLACING_COL = BOARD_SIZE - 1;

	private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];

	public Board() {
		initBoard();
	}
	
	public Tile[][] getAllTiles() {
		return tiles;
	}

	private void initBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				Tile tile = new Tile(row, col);
				tiles[row][col] = tile;
			}
		}
	}

	public Tile getTile(int x, int y) {
		return containsTile(x, y) ? tiles[x][y] : null;
	}

	private boolean containsTile(int x, int y) {
		return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
	}

	public ArrayList<Tile> getValidPlaceTiles(int turn) {
		// Determine which player is playing
		int validCol = (turn == 0) ? PLAYER_0_VALID_PLACING_COL : PLAYER_1_VALID_PLACING_COL;

		// Generate a list of valid tiles (side edge tiles with no insect in it) that
		// the insect can be placed on
		ArrayList<Tile> validTiles = new ArrayList<>();
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (tiles[row][validCol].getInsect() == null) {
				validTiles.add(tiles[row][validCol]);
			}
		}

		return validTiles;
	}

	public ArrayList<Tile> getValidMoveTiles(Insect insect) {
		ArrayList<Tile> validTiles = new ArrayList<>();

		// Get the insect's location and move range
		Tile insectLoc = insect.getTile();
		int x = insectLoc.getX();
		int y = insectLoc.getY();
		int range = insect.getProfile().getMoveRange();

		// Get valid tiles
		validTiles.addAll(insect.getValidMoveTiles(x, y, 1, 0, range, this));
		validTiles.addAll(insect.getValidMoveTiles(x, y, -1, 0, range, this));
		validTiles.addAll(insect.getValidMoveTiles(x, y, 0, 1, range, this));
		validTiles.addAll(insect.getValidMoveTiles(x, y, 0, -1, range, this));

		Collections.sort(validTiles);

		return validTiles;
	}

	public ArrayList<Tile> getValidAttackTiles(Insect insect) {
		// TODO

		return null;
	}
}
