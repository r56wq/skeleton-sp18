package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;

public class Room {
    private int bottomleftX;
    private int bottomleftY;
    private int width;
    private int height;
    private int index;

    private HashMap<String, TETile> tilesmap;

    public Room(int x, int y, int w, int h, int idx) {
        bottomleftX = x;
        bottomleftY = y;
        width = w;
        height = h;
        index = idx;
        tilesmap = tileMapping();
    }

    //helper method that draws the rectangle
    private void drawRect(int bottomleftX, int bottomleftY, int width, int height,
                          TETile[][] world, String fill) {
        TETile tile = tilesmap.get(fill);
        if (tile == null) {
            throw new RuntimeException("No such fill");
        }
        for (int i = bottomleftY; i < bottomleftY + height; i++) {
            for (int j = bottomleftX; j < bottomleftX + width; j++) {
                world[i][j] = tile;
            }
        }
    }

    //helper method that maps from String fill to a Tileset
    private HashMap<String, TETile> tileMapping() {
        HashMap<String, TETile> stMap = new HashMap<>();
        stMap.put("player", Tileset.PLAYER);
        stMap.put("wall", Tileset.WALL);
        stMap.put("floor", Tileset.FLOOR);
        stMap.put("nothing", Tileset.NOTHING);
        stMap.put("grass", Tileset.GRASS);
        stMap.put("water", Tileset.WATER);
        stMap.put("flower", Tileset.FLOWER);
        stMap.put("locked door", Tileset.LOCKED_DOOR);
        stMap.put("unlocked door", Tileset.UNLOCKED_DOOR);
        stMap.put("sand", Tileset.SAND);
        stMap.put("mountain", Tileset.MOUNTAIN);

        return stMap;
    }
}
