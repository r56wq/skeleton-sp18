import static org.junit.Assert.*;
import org.junit.Test;
public class LLDTest2 {
    @Test
    public void testAddFirst() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, (int)deque.get(0));
        assertEquals(1, (int)deque.get(1));
    }

    @Test
    public void testAddLast() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("Hello");
        deque.addLast("World");
        assertEquals("Hello", deque.get(0));
        assertEquals("World", deque.get(1));
    }

    @Test
    public void testRemoveFirst() {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        deque.addLast('a');
        deque.addLast('b');
        assertEquals('a', (char)deque.removeFirst());
        assertEquals('b', (char)deque.removeFirst());
        assertNull(deque.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(2, (int)deque.removeLast());
        assertEquals(1, (int)deque.removeLast());
        assertNull(deque.removeLast());
    }

    @Test
    public void testIsEmpty() {
        LinkedListDeque<Double> deque = new LinkedListDeque<>();
        assertTrue(deque.isEmpty());
        deque.addLast(3.14);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testSize() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        assertEquals(0, deque.size());
        deque.addFirst("one");
        deque.addLast("two");
        assertEquals(2, deque.size());
        deque.removeFirst();
        assertEquals(1, deque.size());
    }

    @Test
    public void testGet() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        assertEquals(10, (int)deque.get(0));
        assertEquals(20, (int)deque.get(1));
        assertEquals(30, (int)deque.get(2));
        assertNull(deque.get(3));
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("orange");
        assertEquals("apple", deque.getRecursive(0));
        assertEquals("banana", deque.getRecursive(1));
        assertEquals("orange", deque.getRecursive(2));
        assertNull(deque.getRecursive(3));
    }
}
