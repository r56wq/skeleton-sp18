package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.Coordinate;


public class HallWayTest {

    public static void main(String[] args) {
        testCase0();
       // testCase1();
       // testCase2();
        //testCase3();
    }

    private static void testCase0() {
        TETile[][] world = new TETile[50][50];
        initializeWorld(world);

        Coordinate start = new Coordinate(5, 7);
        Coordinate mid1 = new Coordinate(10, 7);
        Coordinate mid2 = new Coordinate(10, 20);
        Coordinate end = new Coordinate(29, 20);

        Draw.drawHallway(0, start, mid1, mid2, end, world);
    }

    private static void testCase1() {
        TETile[][] world = new TETile[10][10];
        initializeWorld(world);

        Coordinate start = new Coordinate(2, 2);
        Coordinate mid1 = new Coordinate(4, 4);
        Coordinate mid2 = new Coordinate(6, 4);
        Coordinate end = new Coordinate(8, 4);

        Draw.drawHallway(1, start, mid1, mid2, end, world);
    }

    private static void testCase2() {
        TETile[][] world = new TETile[10][10];
        initializeWorld(world);

        Coordinate start = new Coordinate(2, 2);
        Coordinate mid1 = new Coordinate(2, 4);
        Coordinate mid2 = new Coordinate(2, 6);
        Coordinate end = new Coordinate(2, 8);

        Draw.drawHallway(2, start, mid1, mid2, end, world);
    }

    private static void testCase3() {
        TETile[][] world = new TETile[10][10];
        initializeWorld(world);

        Coordinate start = new Coordinate(2, 8);
        Coordinate mid1 = new Coordinate(2, 6);
        Coordinate mid2 = new Coordinate(2, 4);
        Coordinate end = new Coordinate(2, 2);

        Draw.drawHallway(3, start, mid1, mid2, end, world);
    }

    private static void initializeWorld(TETile[][] world) {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[0].length; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    }

