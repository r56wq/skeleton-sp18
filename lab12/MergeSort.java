import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {

        Queue<Queue<Item>> queues = new Queue<>(); //a queue with queue items

        if (items == null) {
            return null;
        } else if (items.isEmpty()) {
            return null;
        } else {
            for (Item i : items) {
                Queue<Item> itemQueue = new Queue<>();
                itemQueue.enqueue(i);
                queues.enqueue(itemQueue);
            }
        }
        return queues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> itemQueue = new Queue<>();
        int l = q1.size() + q2.size();
        for (int i = 0; i < l; i++) {
            itemQueue.enqueue(getMin(q1, q2));
        }
        return itemQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items == null) {
            return null;
        } else if (items.size() == 1 || items.isEmpty()) {
            return items;
        }
        Queue<Queue<Item>> queueQueue = makeSingleItemQueues(items);
        while (queueQueue.size() > 1) {
            Queue<Item> q1 = queueQueue.dequeue();
            Queue<Item> q2 = queueQueue.dequeue();
            queueQueue.enqueue(mergeSortedQueues(q1, q2));
        }

        return queueQueue.dequeue();
    }

    public static void main(String args[]) {
        Queue<Integer> students = new Queue<Integer>();
        students.enqueue(0);
        students.enqueue(2);
        students.enqueue(1);
        students.enqueue(6);
        students.enqueue(3);
        students.enqueue(4);
        students.enqueue(5);
        students.enqueue(6);
        students.enqueue(7);
        students.enqueue(8);
        Queue<Integer> sortedStudents = MergeSort.mergeSort(students);

        System.out.println("The original array is");
        //print the original array
        for (int s : students) {
            System.out.print(s + " ");
        }
        System.out.println("");


        System.out.println("The sorted array is");
        //print the sorted array
        for (int s : sortedStudents) {
            System.out.print(s + " ");
        }
        System.out.println("");
    }
}
