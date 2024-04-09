package hw4.puzzle;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testEquals() {
        int[][] tiles1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] tiles2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board board1 = new Board(tiles1);
        Board board2 = new Board(tiles2);

        // boards with same tiles should be equal
        assertTrue(board1.equals(board2));

        // boards with different tiles should not be equal
        int[][] differentTiles = {{1, 2, 3}, {4, 6, 5}, {7, 8, 0}};
        Board board3 = new Board(differentTiles);
        assertFalse(board1.equals(board3));

        // boards with different dimensions should not be equal
        int[][] differentDimensions = {{1, 2, 3}, {4, 5, 6}};
        Board board4 = new Board(differentDimensions);
        assertFalse(board1.equals(board4));
    }

    @Test
    public void testHamming() {
        int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board board = new Board(tiles);

        // The tiles are in their correct positions, so hamming distance should be 0
        assertEquals(0, board.hamming());

        // Create a board with tiles out of order
        int[][] outOfOrderTiles = {{1, 2}, {0, 3}};
        Board outOfOrderBoard = new Board(outOfOrderTiles);

        // The hamming distance for outOfOrderBoard should be 7
        assertEquals(1, outOfOrderBoard.hamming());
    }
}

