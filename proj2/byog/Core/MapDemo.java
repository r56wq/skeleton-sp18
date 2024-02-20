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

    }
}
