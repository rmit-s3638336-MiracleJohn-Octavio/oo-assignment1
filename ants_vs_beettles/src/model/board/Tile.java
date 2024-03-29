package model.board;

import model.insect.Insect;

// GRASP - High Cohesion
public class Tile implements Comparable<Tile> {
    private int x;
    private int y;
    private Insect insect = null;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(Tile tile) {
        x = tile.x;
        y = tile.y;
        if (tile.insect != null) {
            insect = tile.insect.mementoClone();
            insect.setTile(this);
            tile.insect.deParalyse();
        }
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
        insect.setTile(this);
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

    @Override
    public int compareTo(Tile tile) {
        if (this.equals(tile)) {
            return 0;
        } else if (x < tile.getX() || (x == tile.getX() && y < tile.getY())) {
            return -1;
        } else {
            return 1;
        }
    }
}
