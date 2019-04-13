package model.board;

import model.insect.Insect;

public class Tile {
	private int x;
	private int y;
	private Insect insect = null;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Insect getInsect() {
		return insect;
	}

	public void setInsect(Insect insect) {
		this.insect = insect;
	}

	public void resetInsect() {
		insect = null;
	}

	@Override
	public String toString() {
		return insect != null ? insect.toString() : " ";
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Tile) {
			return x == ((Tile) object).x && y == ((Tile) object).y;
		}

		return false;
	}
}
