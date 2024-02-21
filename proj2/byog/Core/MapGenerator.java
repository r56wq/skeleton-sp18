package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.TERenderer;
import java.util.Random;

public class MapGenerator {


    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static final int MAX_ROOM_WIDTH = 10;
    private static final int MIN_ROOM_WIDTH = 5;
    private static final int MAX_ROOM_HEIGHT = 10;
    private static final int MIN_ROOM_HEIGHT = 5;
    private static final int MAX_HALLWAY_LENGTH = 15;

    private TETile[][] world;
    private Random random;

    public MapGenerator(long seed) {
        random = new Random(seed);
        world = new TETile[WIDTH][HEIGHT];
        initializeWorld();
    }

    private void initializeWorld() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] generateMap() {
        int numRooms = random.nextInt(10) + 10; // Generate between 10 to 20 rooms
        for (int i = 0; i < numRooms; i++) {
            int roomWidth = random.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
            int roomHeight = random.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;
            int x = random.nextInt(WIDTH - roomWidth - 1) + 1;
            int y = random.nextInt(HEIGHT - roomHeight - 1) + 1;
            Coordinate bottomLeft = new Coordinate(x, y);

            // Check for overlap with existing rooms
            if (!roomOverlap(bottomLeft, roomWidth, roomHeight)) {
                Draw.drawRoom(bottomLeft, roomWidth, roomHeight, world);
            }
        }
        connectRooms();
        return world;
    }

    private boolean roomOverlap(Coordinate bottomLeft, int width, int height) {
        // Check if the new room overlaps with any existing room
        for (int x = bottomLeft.x; x < bottomLeft.x + width; x++) {
            for (int y = bottomLeft.y; y < bottomLeft.y + height; y++) {
                if (world[x][y] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }


    private void connectRooms() {
        // Connect rooms with hallways
        // You need to implement this part based on your drawHallway method
        // This could involve selecting random rooms and drawing hallways between them
        // Make sure the hallways don't intersect with existing rooms
    }

    public static void main(String[] args) {
        MapGenerator mapGenerator = new MapGenerator(System.currentTimeMillis());
        TETile[][] generatedMap = mapGenerator.generateMap();
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
        renderer.renderFrame(generatedMap);
    }
}
