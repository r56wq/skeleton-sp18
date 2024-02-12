public class ArrayDeque<T> {
    private int size;
    private int nextfirst;
    private int nextlast;
    private T[] Item;

    private int InitArraySize = 8;
    public ArrayDeque() {
        size = 0; //the starting size is 8

        nextfirst = 3; //randomly choose
        nextfirst = 4; //randomly choose
        Item = (T[]) new Object[InitArraySize];
    }

    public void addFirst(T item) {
        int factor = 2;
        int flag = needResize();
        if (flag == 0) { //the size is ok
            Item[nextfirst] = item;
        } else if (flag == 1) { //the array is full
            resize(factor * Item.length); //multiply by factor
            Item[nextfirst] = item;
        }
        size++; //change the size
        nextfirst = ((nextfirst - 1) + size) % size; //change nextfirst
    }

    public void addLast(T item) {
        int factor = 2;
        int flag = needResize();
        if (flag == 0) { //the size is ok
            Item[nextlast] = item;
        } else if (flag == 1) { //the array is full
            resize(factor * Item.length); //multiply by factor
            Item[nextlast] = item;
        }

        size++; //change the size
        nextlast = (nextlast + 1 + size) % size; //chane next-last
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int first = (nextfirst + 1 + size) % size;
        int last = ((nextlast - 1) + size) % size;
        int temp = first;
        while (temp != last) {
            System.out.print(Item[temp].toString() + " ");
            temp = ((temp - 1) + size) % size;
        }
    }

    public T removeFirst() {
        //check valid remove
        if (size == 0) {
            return null;
        }
        int CurrentFirst = (nextfirst + 1 + size) % size;
        T firstItem = Item[CurrentFirst];
        Item[CurrentFirst] = null; //discard the Obj
        nextfirst = CurrentFirst;
        if (needResize() == 2) {
            int factor = 2;
            resize(Item.length / factor);
            Item[CurrentFirst] = null; //discard the Obj
        }
        size--;
        return firstItem;
    }

    public T removeLast() {
        //check valid remove
        if (size == 0) {
            return null;
        }
        int CurrentLast = (nextlast + 1 + size) % size;
        size--; //Put size-- here so that not divided by 0
        T lastItem = Item[CurrentLast];
        nextlast = CurrentLast;
        Item[CurrentLast] = null;
        if (needResize() == 2) {
            int factor = 2; //meaning half the array
            resize(Item.length / factor);
        }
        size--;
        return lastItem;
    }

    public T get(int index) {
        //check valid index
        if (index > size) {
            return null;
        }

        int i = 0;
        int temp = (nextfirst + 1 + size) % size;
        while (i < index) {
            i++;
            temp = (temp - 1 + size) % size;
        }
        //now temp is the real desired index in the array
        return Item[temp];
    }


    /**
     * helper method to resize the array when it is full or when the usage is too low
     */
    private void resize(int Capacity) {
        T[] NewItem = (T[]) new Object[Capacity];
        if (Capacity > size) {/* A larger array is needed, so it is safe to simply copy 0~end of the
          original array, without changing nextfirst and nextlast*/
            System.arraycopy(Item, 0, NewItem, 0, size);
            Item = NewItem;
        }

        /*If a smaller array is needed, using a loop to copy, and nextfirst and nextlast need to be changed*/
        nextfirst = Capacity / 2;
        nextlast = nextfirst + 1;
        for (int i = 0; i < size; i++) {
            if ((Item[i] != null) && (nextfirst != nextlast)) {/*If the original array is not null and the
            new one is not empty*/
                NewItem[nextfirst] = Item[i];
                nextfirst = (nextfirst + size - 1) % size;
            }
        }
        Item = NewItem;
    }

    /**
     * helper method to check if resize is needed
     * return 0 when resize is not needed,
     * return 1 when the array is full
     * return 2 when the usage is less than 25%
     */
    private int needResize() {
        int flag = 0;
        int threshold = 16;
        int l = Item.length;
        if (size == l) {
            flag = 1;
        }
        else if ((size < l / 4) && (l >= threshold)) {
            flag = 2;
        }
        return flag;
    }
}

