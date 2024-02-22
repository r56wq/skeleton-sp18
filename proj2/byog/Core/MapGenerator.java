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
        int i;
        for (i = 0; i < l - 2; i++) {
            room leftRoom;
            room rightRoom;
            int type = -1;
            room roomA = rooms.get(i);
            room roomB = rooms.get(i + 1);
            /* find left and right room */
            if (roomA.x < roomB.x) {
                leftRoom = roomA;
                rightRoom = roomB;
            } else {
                leftRoom = roomB;
                rightRoom = roomA;
            }

            //if leftRoom is higher
            if (leftRoom.y > rightRoom.y) {
                type = random.nextInt(2) + 1; // 1 or 2

            } else { //leftRoom is lower
                int flag = random.nextInt(2);
                type = ((flag == 1) ? 0 : 3); // 0 or 3
            }
            System.out.println("Hello from" + i);
            switch (type) {
                case 0: //jump to next case
                case 1: {
                    Coordinate start = new Coordinate(leftRoom.x + leftRoom.roomWidth - 1,
                            leftRoom.y + random.nextInt(2) + 2);
                    Coordinate mid1 = new Coordinate(start.x + random.nextInt(2) + 2,
                            start.y);
                    Coordinate mid2 = new Coordinate(mid1.x,
                            rightRoom.y + random.nextInt(2) + 2);
                    Coordinate end = new Coordinate(rightRoom.x, mid2.y);
                    Draw.drawHallway(type, start, mid1, mid2, end ,world);
                    break;
                }
                case 2: {
                    Coordinate start = new Coordinate(rightRoom.x + random.nextInt(2) + 2,
                            rightRoom.y);
                    Coordinate mid1 = new Coordinate(start.x, start.y + random.nextInt(2) + 2);
                    Coordinate mid2 = new Coordinate(mid1.x - random.nextInt(2) + 2, mid1.y);
                    Coordinate end = new Coordinate(mid2.x, leftRoom.y);
                    Draw.drawHallway(type, start, mid1, mid2, end, world);
                    break;
                }
                case 3: {
                    Coordinate start = new Coordinate(leftRoom.x + random.nextInt(2) + 2,
                            leftRoom.y);
                    Coordinate mid1 = new Coordinate(start.x, start.y + random.nextInt(2) + 2);
                    Coordinate mid2 = new Coordinate(mid1.x + random.nextInt(2) + 2, mid1.y);
                    Coordinate end = new Coordinate(mid2.x, rightRoom.y);
                    Draw.drawHallway(type, start, mid1, mid2, end, world);
                    break;
                }

            }
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
