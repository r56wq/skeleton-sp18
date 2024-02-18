
package synthesizer;
import java.nio.Buffer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        first = 0;
        last = 0;
        this.capacity = capacity;
        rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {

        if (isFull()) {
            throw new RuntimeException("The buff is full");
        }
        rb[last] = x;
        fillCount = fillCount + 1;
        last = (last + 1 + capacity) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("The buff is empty");
        }
        T item = rb[first];
        first = (first + 1 + capacity) % capacity;
        fillCount--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {

        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    public class ArrayListIterator<T> implements Iterator<T> {
        /*To Support nested iteration, cannot directly point to this.rb*/
        int iteratorFirst = first;
        int iteratorCap = fillCount;

        @Override
        public boolean hasNext() {
          return !(iteratorCap == 0);
        }

        @Override
        public T next() {
            T item = (T)rb[iteratorFirst];
            iteratorFirst = (iteratorFirst + 1 + capacity) % capacity;
            iteratorCap--;
            return item;

        }
    }
}
