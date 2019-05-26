package controller;

import javafx.scene.image.Image;

import java.util.HashMap;

public class TileBackgroundFactory {
    private HashMap<String, Image> backgrounds;

    public TileBackgroundFactory() {
        loadBackgrounds();
    }

    private void loadBackgrounds() {
        backgrounds = new HashMap<>();
        backgrounds.put("odd", new Image("/assets/tile1.png", Helper.TILE_W, Helper.TILE_H, true, true));
        backgrounds.put("even", new Image("/assets/tile2.png", Helper.TILE_W, Helper.TILE_H, true, true));
    }

    public Image getBackground(int row, int col) {
        if (Helper.isEven(row)) {
            if (Helper.isEven(col)) {
                return backgrounds.get("odd");
            } else {
                return backgrounds.get("even");
            }
        } else {
            if (Helper.isEven(col)) {
                return backgrounds.get("even");
            } else {
                return backgrounds.get("odd");
            }
        }
    }
}
