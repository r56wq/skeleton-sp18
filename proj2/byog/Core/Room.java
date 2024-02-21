package byog.Core;

public class Room {
    int startX;
    int startY;
    int width;
    int height;

    public Room(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "StartX: " + startX + ", StartY: " + startY + ", Width: " + width + ", Height: " + height;
    }
}
