public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        int flag1 = x - y;
        int flag2 = y - x;
        return flag1 == 1 || flag2 == 1;
    }
}
