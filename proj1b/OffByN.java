public class OffByN {
    private int offSet;
    public OffByN(int N) {
        offSet = N;
    }

    public boolean equalchars(char x, char y) {
        return (x - y == offSet) || (y - x == offSet);
    }
}
