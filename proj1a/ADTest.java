import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ADTest {
    private ArrayDeque<Integer> deque;

    @Before
    public void setUp() {
        deque = new ArrayDeque<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(deque.isEmpty());
        deque.addFirst(5);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, deque.size());
        deque.addFirst(5);
        deque.addLast(10);
        assertEquals(2, deque.size());
        deque.removeFirst();
        assertEquals(1, deque.size());
    }

    @Test
    public void testAddFirst() {
        deque.addFirst(5);
        deque.addFirst(10);
        assertEquals(10, (int) deque.get(0));
        assertEquals(5, (int) deque.get(1));
    }

    @Test
    public void testAddLast() {
        deque.addLast(5);
        deque.addLast(10);
        assertEquals(5, (int) deque.get(0));
        assertEquals(10, (int) deque.get(1));
    }

    @Test
    public void testRemoveFirst() {
        assertNull(deque.removeFirst());
        deque.addFirst(5);
        deque.addFirst(10);
        assertEquals(10, (int) deque.removeFirst());
        assertEquals(1, deque.size());
    }

    @Test
    public void testRemoveLast() {
        assertNull(deque.removeLast());
        deque.addLast(5);
        deque.addLast(10);
        assertEquals(10, (int) deque.removeLast());
        assertEquals(1, deque.size());
    }

    @Test
    public void testGet() {
        assertNull(deque.get(0));
        deque.addLast(5);
        deque.addLast(10);
        assertEquals(5, (int) deque.get(0));
        assertEquals(10, (int) deque.get(1));
    }

    @Test
    public void testResize() {
        for (int i = 0 ; i < 20; i++) {
            deque.addLast(i);
        }
        assertEquals(20, deque.size());
        for (int i = 0; i < 18; i++) {
            assertEquals(i, (int) deque.removeFirst());
        }
        assertEquals(8, deque.getArrayLength());
    }
}
