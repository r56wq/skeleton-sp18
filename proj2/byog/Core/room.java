package byog.Core;

public class room {
    int x;
    int y;
    int roomWidth;
    int roomHeight;
    public room(int leftX, int leftY, int Width, int Height) {
        x = leftX;
        y = leftY;
        roomWidth = Width;
        roomHeight = Height;
    }
    public room(Coordinate bottomleft, int Width, int Height) {
        x = bottomleft.x;
        y = bottomleft.y;;
        roomWidth = Width;
        roomHeight = Height;
    }
}

