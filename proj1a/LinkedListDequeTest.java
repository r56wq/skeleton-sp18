import java.util.Objects;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	public static boolean checkeq(Integer expected, Integer actual){
		if (!Objects.equals(expected, actual)) {
			System.out.println("removeLast() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed.
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct,
	 * finally printing the results.
	 *
	 * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");


		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);

	}

	public static void addRemoveTest2() {

		System.out.println("Running add/remove test2.");


		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());


		lld1.addFirst(1);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		//should return 1
		passed = checkeq(1, lld1.removeLast()) && passed;

		lld1.addFirst(3);
		//should return 3
		passed = checkeq(3, lld1.removeLast()) && passed;

		//should return null
		passed = checkeq(null, lld1.removeFirst()) && passed;
		passed = checkeq(null, lld1.removeLast()) && passed;

		//get negative item
		passed = checkeq(null, lld1.get(-1)) && passed;

		//get larger than size item
		lld1.addFirst(2);
		lld1.addLast(6);
		lld1.addFirst(1);

		passed = checkeq(1, lld1.get(0)) && passed;
		passed = checkeq(1, lld1.getRecursive(0)) && passed;
		passed = checkeq(1, lld1.getRecursive(2)) && passed;
		passed = checkeq(null, lld1.getRecursive(3)) && passed;
		printTestStatus(passed);

	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		addRemoveTest2();
	}
} 