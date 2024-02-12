public class ArrayDeque<T> {
    private int size;
    private int nextfirst;
    private int nextlast;
    private T[] itemArray;

    private int initArraySize = 8;
    public ArrayDeque() {
        size = 0; //the starting size is 8

        nextfirst = 3; //randomly choose
        nextfirst = 4; //randomly choose
        itemArray = (T[]) new Object[initArraySize];
    }

    public void addFirst(T item) {
        int factor = 2;
        int flag = needResize();
        if (flag == 0) { //the size is ok
            itemArray[nextfirst] = item;
        } else if (flag == 1) { //the array is full
            resize(factor * itemArray.length); //multiply by factor
            itemArray[nextfirst] = item;
        }
        size++; //change the size
        nextfirst = ((nextfirst - 1) + size) % size; //change nextfirst
    }

    public void addLast(T item) {
        int factor = 2;
        int flag = needResize();
        if (flag == 0) { //the size is ok
            itemArray[nextlast] = item;
        } else if (flag == 1) { //the array is full
            resize(factor * itemArray.length); //multiply by factor
            itemArray[nextlast] = item;
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
            System.out.print(itemArray[temp].toString() + " ");
            temp = ((temp - 1) + size) % size;
        }
    }

    public T removeFirst() {
        //check valid remove
        if (size == 0) {
            return null;
        }
        int currentFirst = (nextfirst + 1 + size) % size;
        T firstItem = itemArray[currentFirst];
        itemArray[currentFirst] = null; //discard the Obj
        nextfirst = currentFirst;
        if (needResize() == 2) {
            int factor = 2;
            resize(itemArray.length / factor);
            itemArray[currentFirst] = null; //discard the Obj
        }
        size--;
        return firstItem;
    }

    public T removeLast() {
        //check valid remove
        if (size == 0) {
            return null;
        }
        int currentLast = (nextlast + 1 + size) % size;
        size--; //Put size-- here so that not divided by 0
        T lastItem = itemArray[currentLast];
        nextlast = currentLast;
        itemArray[currentLast] = null;
        if (needResize() == 2) {
            int factor = 2; //meaning half the array
            resize(itemArray.length / factor);
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
        return itemArray[temp];
    }


    /**
     * helper method to resize the array when it is full or when the usage is too low
     */
    private void resize(int capacity) {
        T[] newItem = (T[]) new Object[capacity];
        if (capacity > size) { /* A larger array is needed, so it is safe to simply copy 0~end of
        the original array, without changing nextfirst and nextlast*/
            System.arraycopy(itemArray, 0, newItem, 0, size);
            itemArray = newItem;
        }

        /*If a smaller array is needed, using a loop to copy, and nextfirst and nextlast need to
        be changed*/
        nextfirst = capacity / 2;
        nextlast = nextfirst + 1;
        for (int i = 0; i < size; i++) {
            if ((itemArray[i] != null) && (nextfirst != nextlast)) { /*If the original array is not
            null and the new one is not empty*/
                newItem[nextfirst] = itemArray[i];
                nextfirst = (nextfirst + size - 1) % size;
            }
        }
        itemArray = newItem;
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
        int l = itemArray.length;
        if (size == l) {
            flag = 1;
        } else if ((size < l / 4) && (l >= threshold)) {
            flag = 2;
        }

        return flag;
    }
}

