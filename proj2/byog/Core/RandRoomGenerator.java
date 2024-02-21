package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandRoomGenerator {
    private static Random RANDOM;

    public RandRoomGenerator(long seed) {
        RANDOM = new Random(seed);
    }
    public static List<Room> generateRooms(int mapWidth, int mapHeight) {
        List<Room> rooms = new ArrayList<>();

        // Randomly determine the number of rows (a) and columns (b) for the blocks
        int a = RANDOM.nextInt(2) + 4;  // a ranges from 4 to 5
        int b = RANDOM.nextInt(2) + 1;  // b ranges from 1 to 2

        // Calculate the maximum size of a block
        int maxBlockSizeX = mapWidth / a;
        int maxBlockSizeY = mapHeight / b;

        // Calculate the minimum size of a block
        int minBlockSizeX = 8;
        int minBlockSizeY = 8;

        // Generate blocks
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                // Calculate random width and height for the block
                int blockWidth = RANDOM.nextInt(maxBlockSizeX - minBlockSizeX + 1) + minBlockSizeX;
                int blockHeight = RANDOM.nextInt(maxBlockSizeY - minBlockSizeY + 1) + minBlockSizeY;

                // Calculate the starting point of the block
                int startX = j * maxBlockSizeX;
                int startY = i * maxBlockSizeY;

                // Make sure the block fits within the map bounds
                if (startX + blockWidth > mapWidth) {
                    blockWidth = mapWidth - startX;
                }
                if (startY + blockHeight > mapHeight) {
                    blockHeight = mapHeight - startY;
                }

                // Generate a room within the block
                Room room = generateRoom(startX, startY, blockWidth, blockHeight, mapWidth, mapHeight);
                rooms.add(room);
            }
        }

        return rooms;
    }

    private static Room generateRoom(int startX, int startY, int blockWidth, int blockHeight,
                                     int mapWidth, int mapHeight) {
        // Calculate random width and height for the room
        int minRoomWidth = blockWidth / 2;
        int maxRoomWidth = blockWidth * 5 / 6;
        int minRoomHeight = blockHeight / 2;
        int maxRoomHeight = blockHeight * 5 / 6;
        int roomWidth = RANDOM.nextInt(maxRoomWidth - minRoomWidth) + 3;
        int roomHeight = RANDOM.nextInt(maxRoomHeight - minRoomHeight + 3) + 3;
        int roomX = startX + RANDOM.nextInt(blockWidth - roomWidth - 2) + 1;
        int roomY = startY + RANDOM.nextInt(blockHeight - roomHeight + 2) + 1;
        //make sure room fits in the bound
        if (roomX + roomWidth > mapWidth) {
             roomWidth = mapWidth - startX;
        }
        if (startY + blockHeight > mapHeight) {
            roomHeight = mapHeight - startY;
        }
        return new Room(roomX, roomY, roomWidth, roomHeight);
    }


}
