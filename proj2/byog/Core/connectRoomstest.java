package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
public class connectRoomstest {
    public static void main(String args[]) {
        room roomA = new room(20, 25, 2, 9);
        room roomB = new room(35, 48, 5, 7);
        List<room> rooms = new ArrayList<>();
        rooms.add(roomA);
        rooms.add(roomB);
        MapGenerator mg = new MapGenerator(1);
        //mg.connectRooms(rooms);
    }

}
