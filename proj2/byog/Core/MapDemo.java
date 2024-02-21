package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;

public class MapDemo {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;


        public static void main(String[] args) {
            // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
            TERenderer ter = new TERenderer();
            ter.initialize(WIDTH, HEIGHT);


            // initialize tiles
            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            /**
            // Test drawRect
            Draw drawer = new Draw();
            drawer.drawRect(0, 0, 5, 5, world, "water");
            ter.renderFrame(world);
             **/

            /**
             * Test drawRoom

            Draw drawer = new Draw();
            Coordinate bottomleft = new Coordinate(20, 10);
            drawer.drawRoom(bottomleft, 5, 8, world);
            ter.renderFrame(world);
             */

            /**
             * Test drawHorizontal and drawVertical

            Draw drawer = new Draw();
            Coordinate p0 = new Coordinate(5, 10);
            int length0 = 5;
            Coordinate p1 = new Coordinate(5, 14);
            int length1 = 10;
            drawer.drawVertical(p0, length0, world, "floor");
            drawer.drawHorizontal(p1, length1, world, "floor");
             */

            /**Test drawTurn
            Coordinate start = new Coordinate(25, 15);
            Coordinate mid = new Coordinate(35, 15);
            Coordinate end = new Coordinate(35, 20);
            Draw drawer = new Draw();
            drawer.drawTurn(start, mid, end, world, "floor");
            ter.renderFrame(world);
             **/

            /**Test drawConnectoinBT
            Coordinate start = new Coordinate(35, 25);
            Coordinate mid1 = new Coordinate(35, 15);
            Coordinate mid2 = new Coordinate(20, 15);
            Coordinate end = new Coordinate(20, 5);
            Draw.drawConnectionBT(start, mid1, mid2, end, world, "floor");
            ter.renderFrame(world);
            */

             /* Test drawHallway type0
             Coordinate start = new Coordinate(35, 10);
             Coordinate mid1 = new Coordinate(50, 10);
             Coordinate mid2 = new Coordinate(50, 15);
             Coordinate end = new Coordinate(55, 15);
             Draw.drawHallway(0, start, mid1, mid2, end, world);
             ter.renderFrame(world);
            */

            /**Test drawHallway type1
            Coordinate start = new Coordinate(35, 10);
            Coordinate mid1 = new Coordinate(50, 10);
            Coordinate mid2 = new Coordinate(50, 5);
            Coordinate end = new Coordinate(57, 5);
            Draw.drawHallway(1, start, mid1, mid2, end, world);
            ter.renderFrame(world);
            */




        }
}
