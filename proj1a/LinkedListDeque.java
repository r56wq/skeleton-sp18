public class LinkedListDeque<T> {

    /** Node inside the linklist **/
    public static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;
        public Node(T data) {
            item = data;
            next = null;
            prev = null;
        }
    }

    private int size;
    private Node<T> sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    public void addFirst(T item) {
        Node<T> first = new Node<>(item);
        sentinel.next.prev = first;
        first.next = sentinel.next;
        first.prev = sentinel;
        sentinel.next = first;
        size++;
    }

    public void addLast(T item) {
        Node<T> last = new Node<>(item);
        sentinel.prev.next = last;
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev = last;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> temp = sentinel.next;
        while (temp != sentinel) {
            System.out.printf("%s ", temp.item);
            temp = temp.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return firstItem;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return lastItem;
    }

    public T get(int index) {
        if ((index < 0) || (index > (size - 1))) {
            return null; // no such item
        }
        int i = 0;
        Node<T> temp = sentinel.next;
        while (i < size) {
            if (i == index) {
                break;
            }
            temp = temp.next;
            i++;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        if ((index < 0) || (index > (size - 1))) {
            return null;
        }
        return getrecursive(sentinel.next, index);
        }

    private T getrecursive(Node<T> currentNode, int index){
        if(index == 0){
            return currentNode.item;
        }
        return getrecursive(currentNode.next, index-1);
    }
    }



