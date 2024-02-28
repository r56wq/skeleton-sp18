package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.TERenderer;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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





    //random room generate
    private room randRoomGen() {
        int roomWidth = random.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
        int roomHeight = random.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;
        int x = random.nextInt(WIDTH - roomWidth - 1) + 1;
        int y = random.nextInt(HEIGHT - roomHeight - 1) + 1;
        return new room(x, y, roomWidth, roomHeight);
    }


    public TETile[][] generateMap() {
        int numRooms = random.nextInt(10) + 10; // Generate between 10 to 20 rooms
        List<room> rooms = new ArrayList<>();

        for (int i = 0; i < numRooms; i++) {
            int roomWidth = random.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
            int roomHeight = random.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;
            int x = random.nextInt(WIDTH - roomWidth - 1) + 1;
            int y = random.nextInt(HEIGHT - roomHeight - 1) + 1;
            Coordinate bottomLeft = new Coordinate(x, y);

            // Check for overlap with existing rooms and ensure 3x3 offset
            if (!roomOverlapWithOffset(rooms, bottomLeft, roomWidth, roomHeight)) {
                rooms.add(new room (bottomLeft, roomWidth, roomHeight));
                Draw.drawRoom(bottomLeft, roomWidth, roomHeight, world);
            }
        }
        connectRooms(rooms);
        return world;
    }

    private boolean roomOverlapWithOffset(List<room> rooms, Coordinate bottomLeft, int width, int height) {
        for (room existingRoom : rooms) {
            // Calculate the right and top edges of the new room
            int newRight = bottomLeft.x + width;
            int newTop = bottomLeft.y + height;

            // Calculate the left and bottom edges of the existing room
            int existingLeft = existingRoom.x;
            int existingBottom = existingRoom.y;

            // Check for overlap considering the 3x3 offset
            if (newRight + 5 >= existingLeft && bottomLeft.x - 5 <= existingRoom.x + existingRoom.roomWidth &&
                    newTop + 5 >= existingBottom && bottomLeft.y - 5 <= existingRoom.y + existingRoom.roomHeight) {
                return true; // Overlapping with existing room with 3x3 offset
            }
        }
        return false;
    }
    private void connectRooms(List<room> rooms) {
        int l = rooms.size();
        for (int i = 0; i < l - 1; i++) {
            room roomA = rooms.get(i);
            room roomB = rooms.get(i + 1);

            // Determine the direction of the hallway based on the positions of the rooms
            int type;
            if (roomA.x < roomB.x) {
                // Room A is to the left of Room B
                if (roomA.y < roomB.y) {
                    // Room A is below Room B
                    type = random.nextInt(2) + 2; // 2 or 3 (Top-Bottom)
                } else {
                    // Room A is above Room B
                    type = random.nextInt(2); // 0 or 1 (Bottom-Top)
                }
            } else {
                // Room A is to the right of Room B
                if (roomA.y < roomB.y) {
                    // Room A is below Room B
                    type = random.nextInt(2) + 2; // 2 or 3 (Top-Bottom)
                } else {
                    // Room A is above Room B
                    type = random.nextInt(2); // 0 or 1 (Bottom-Top)
                }
            }

            // Draw the hallway based on the chosen type
            drawHallway(type, roomA, roomB);
        }
    }






    public static void main(String[] args) {
        MapGenerator mapGenerator = new MapGenerator(13322212);
        TETile[][] generatedMap = mapGenerator.generateMap();
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
        renderer.renderFrame(generatedMap);
    }
}
