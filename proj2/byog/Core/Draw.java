package byog.Core;
import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;

public class Draw {

    private static final HashMap<String, TETile> tilesmap; //map from string to TETILE
    static {
        tilesmap = tileMapping();
    }

    /**
     * draw a room with walls, note that the width and height here include the wall
     * */
    public static void drawRoom(Coordinate bottomLeft, int width, int height,
                         TETile[][] world) {
        //draw walls
        drawRect(bottomLeft, width, height, world, "wall");

        Coordinate bottromLeftFloor = new Coordinate(bottomLeft.x + 1, bottomLeft.y + 1);
        int floorW = width - 2;
        int floorH = height - 2;
        drawRect(bottromLeftFloor, floorW, floorH, world, "floor");
    }









    //helper method that draws the rectangle
    private static void drawRect(Coordinate bottomleft, int width, int height,
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

    //helper method that draws a horizon line between two points
    private static void drawHorizontal(Coordinate p0, Coordinate p1, TETile[][] world,
                                       String fill) {
        //check these two points are valid
        if (p0.y != p1.y) {
            throw new RuntimeException("These two points are not on the same horizontal line");
        }
        if (p0.x == p1.x) {
            throw new RuntimeException("These two pointe cannot overlap");
        }
        //decide the start point since draw from left to right
        Coordinate start = p0.x < p1.x ? p0 : p1;
        int width = Math.abs(p0.x - p1.x) + 1;
        drawRect(start, width, 1, world, fill);
    }

    // Helper method that draws a vertical line between two points
    private static void drawVertical(Coordinate p0, Coordinate p1, TETile[][] world, String fill) {
        // Check if these two points are valid
        if (p0.x != p1.x) {
            throw new RuntimeException("These two points are not on the same vertical line");
        }
        if (p0.y == p1.y) {
            throw new RuntimeException("These two points cannot overlap");
        }
        // Decide the start point since we draw from bottom to top
        Coordinate start = p0.y < p1.y ? p0 : p1;
        int height = Math.abs(p0.y - p1.y) + 1;
        drawRect(start, 1, height, world, fill);
    }


    //helper method that draws a connection, for left to right connection case
    private static void drawConnectionLR(Coordinate start, Coordinate mid1, Coordinate mid2,
                                         Coordinate end, TETile[][] world, String fill) {
        drawHorizontal(start, mid1, world, fill);
        drawVertical(mid1, mid2, world, fill);
        drawHorizontal(mid2, end, world, fill);
    }

    //helper method that draws a connection, for top to bottom connection case
    private static void drawConnectionBT(Coordinate start, Coordinate mid1, Coordinate mid2,
                                         Coordinate end, TETile[][] world, String fill) {
        drawVertical(start, mid1, world, fill);
        drawHorizontal(mid1, mid2, world, fill);
        drawVertical(mid2, end, world, fill);
    }

    //helper method that draws a hallway with walls, 4 cases
    //type 0: left-right, left.y < right.y
    //type 1: left-right, left.y > right.y
    //type 2: bottom-top, bottom.x > top.x
    //type 3: bottom-top, bottom.x < top.x
    public static void drawHallway(int type, Coordinate start, Coordinate mid1, Coordinate mid2,
                                   Coordinate end, TETile[][] world) {
        if (type == 0) {
            Coordinate twallStart = new Coordinate(start.x, start.y + 1);
            Coordinate twallMid1 = new Coordinate(mid1.x - 1, mid1.y + 1);
            Coordinate twallMid2 = new Coordinate(mid2.x - 1, mid2.y + 1);
            Coordinate twallEnd = new Coordinate(end.x, end.y + 1);
            Coordinate dwallStart = new Coordinate(start.x, start.y - 1);
            Coordinate dwallMid1 = new Coordinate(mid1.x + 1, mid1.y - 1);
            Coordinate dwallMid2 = new Coordinate(mid2.x + 1, mid2.y - 1);
            Coordinate dwallEnd = new Coordinate(end.x, end.y - 1);
            drawConnectionLR(start, mid1, mid2, end, world, "floor"); //draw way
            //draw top wall
            drawConnectionLR(twallStart, twallMid1, twallMid2, twallEnd, world, "wall");
            //draw bottom wall
            drawConnectionLR(dwallStart, dwallMid1, dwallMid2, dwallEnd, world, "wall");

        } else if (type == 1) {
            Coordinate twallStart = new Coordinate(start.x, start.y + 1);
            Coordinate twallMid1 = new Coordinate(mid1.x + 1, mid1.y + 1);
            Coordinate twallMid2 = new Coordinate(mid2.x + 1, mid2.y + 1);
            Coordinate twallEnd = new Coordinate(end.x, end.y + 1);
            Coordinate dwallStart = new Coordinate(start.x, start.y - 1);
            Coordinate dwallMid1 = new Coordinate(mid1.x - 1, mid1.y - 1);
            Coordinate dwallMid2 = new Coordinate(mid2.x - 1, mid2.y - 1);
            Coordinate dwallEnd = new Coordinate(end.x, end.y - 1);
            drawConnectionLR(start, mid1, mid2, end, world, "floor"); //draw way
            //draw top wall
            drawConnectionLR(twallStart, twallMid1, twallMid2, twallEnd, world, "wall");
            //draw bottom wall
            drawConnectionLR(dwallStart, dwallMid1, dwallMid2, dwallEnd, world, "wall");
        } else if (type == 2) {
            Coordinate lwallStart = new Coordinate(start.x - 1, start.y);
            Coordinate lwallMid1 = new Coordinate(mid1.x - 1, mid1.y - 1);
            Coordinate lwallMid2 = new Coordinate(mid2.x - 1, mid2.y - 1);
            Coordinate lwallEnd = new Coordinate(end.x - 1, end.y);
            Coordinate rwallStart = new Coordinate(start.x + 1, start.y);
            Coordinate rwallMid1 = new Coordinate(mid1.x + 1, mid1.y + 1);
            Coordinate rwallMid2 = new Coordinate(mid2.x + 1, mid2.y + 1);
            Coordinate rwallEnd = new Coordinate(end.x + 1, end.y);
            drawConnectionBT(start, mid1, mid2, end, world, "floor"); //draw way
            //draw left wall
            drawConnectionBT(lwallStart, lwallMid1, lwallMid2, lwallEnd, world, "wall");
            //draw right wall
            drawConnectionBT(rwallStart, rwallMid1, rwallMid2, rwallEnd, world, "wall");
        } else if (type == 3) {
            Coordinate lwallStart = new Coordinate(start.x - 1, start.y);
            Coordinate lwallMid1 = new Coordinate(mid1.x - 1, mid1.y + 1);
            Coordinate lwallMid2 = new Coordinate(mid2.x - 1, mid2.y + 1);
            Coordinate lwallEnd = new Coordinate(end.x - 1, end.y);
            Coordinate rwallStart = new Coordinate(start.x + 1, start.y);
            Coordinate rwallMid1 = new Coordinate(mid1.x + 1, mid1.y - 1);
            Coordinate rwallMid2 = new Coordinate(mid2.x + 1, mid2.y - 1);
            Coordinate rwallEnd = new Coordinate(end.x + 1, end.y);
            drawConnectionBT(start, mid1, mid2, end, world, "floor"); //draw way
            //draw left wall
            drawConnectionBT(lwallStart, lwallMid1, lwallMid2, lwallEnd, world, "wall");
            //draw right wall
            drawConnectionBT(rwallStart, rwallMid1, rwallMid2, rwallEnd, world, "wall");
        }
    }

}
