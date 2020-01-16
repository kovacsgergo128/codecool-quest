package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("door", new Tile(0, 9));
        tileMap.put("door_open", new Tile(2, 9));
        tileMap.put("sword", new Tile(3,28));
        tileMap.put("stairs_down", new Tile(3,6));
        tileMap.put("stairs_up", new Tile(2,6));
        tileMap.put("skull", new Tile(0,15));
        tileMap.put("web", new Tile(2,15));
        tileMap.put("turkey leg", new Tile(17,28));
        tileMap.put("riches", new Tile(9,26));
        tileMap.put("bonfire", new Tile(14, 10));
        tileMap.put("spikes", new Tile(22, 0));
        tileMap.put("boss", new Tile(24, 1));
        tileMap.put("crown", new Tile(11, 24));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
