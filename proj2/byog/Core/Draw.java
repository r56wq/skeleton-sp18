package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;

public class Draw {

    private HashMap<String, TETile> tilesmap; //map from string to TETILE


    public Draw() {

        tilesmap = tileMapping();
    }

    /**
     * draw a room with walls, note that the width and height here include the wall
     * */
    public void drawRoom(Coordinate bottomLeft, int width, int height,
                         TETile[][] world) {
        //draw walls
        drawRect(bottomLeft, width, height, world, "wall");

        Coordinate bottromLeftFloor = new Coordinate(bottomLeft.x + 1, bottomLeft.y + 1);
        int floorW = width - 2;
        int floorH = height - 2;
        drawRect(bottromLeftFloor, floorW, floorH, world, "floor");
    }







    //helper method that draws the rectangle
    private void drawRect(Coordinate bottomleft ,int width, int height,
                          TETile[][] world, String fill) {
        int bottomleftX = bottomleft.x;
        int bottomleftY = bottomleft.y;
        TETile tile = tilesmap.get(fill);
        if (tile == null) {
            throw new RuntimeException("No such fill");
        }
        for (int j = bottomleftX; j < bottomleftX + width; j++) {
            for (int i = bottomleftY; i < bottomleftY + height; i++) {
                world[j][i] = tile;
            }
        }
    }

    //helper method that maps from String fill to a Tileset
    private static HashMap<String, TETile> tileMapping() {
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
