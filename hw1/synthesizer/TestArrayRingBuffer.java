package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(4);
        arb.enqueue(10);
        assertEquals(arb.dequeue(), (Integer) 1);
        assertEquals(arb.dequeue(), (Integer) 4);
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(4);
        arb.enqueue(10);
        for (int i : arb) {
            for (int j : arb) {
                System.out.println(i + " " + j + "");
            }
        }
    }
    @Test
    public void testPluck() {
        GuitarString GS = new GuitarString(10000);
        GS.pluck();
        GS.sample();
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
