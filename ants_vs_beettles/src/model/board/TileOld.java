package model.board;

import model.insect.Insect;

public class TileOld {
    private Coordinate coordinate;
    private Insect insect = null;

    public TileOld(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Insect getInsect() {
        return insect;
    }

    public void setInsect(Insect insect) {
        this.insect = insect;
    }

    @Override
    public String toString() {
        return insect != null ? insect.toString() : " ";
    }

    @Override
    public boolean equals(Object object) {
        return coordinate.equals(((TileOld) object).getCoordinate());
    }
}
