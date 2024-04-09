package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState {
    private int[][] board;
    private int boardSize;
    private int length;
    private int blank = 0;
    public Board(int[][] tiles) {
        // Initialize the board array with the same dimensions as tiles
        boardSize = 0;
        board = new int[tiles.length][tiles[0].length];
        length = tiles.length;
        // Copy the contents of tiles to board
        for (int i = 0; i < tiles.length; i++) {
            System.arraycopy(tiles[i], 0, board[i], 0, tiles[i].length);
            boardSize++;
        }
    }

    /*
    return the value at row i col j
     */
    public int tileAt(int i, int j) {
        int size = board.length;
        if (i < 0 || j < 0) {
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        }

        if ((j > size) || (i > size) || (j == size) || (i == size)) {
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        }

        return board[i][j];
    }

    public int size() {
        if (boardSize == -1) {
            for (int[] ints : board) {
                for (int anInt : ints) {
                    if (anInt != blank) {
                        boardSize++;
                    }
                }
            }
        }

        return boardSize;
    }


    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == blank) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = blank;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = blank;
                }
            }
        }
        return neighbors;
    }



    private static class coordination {
        int x;
        int y;
        private coordination(int corX, int corY) {
            x = corX;
            y = corY;
        }
    }


    /*
    Given a number, find the corresponding coordination in the standard diagram
     */
    private coordination findCor(int digit) {
        int row = (digit - 1) / length;
        int col = digit - length * row - 1;
        return new coordination(row, col);
    }


    /*
    Calculate hamming distance
     */
    public int hamming() {
        int ham = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (tileAt(i, j) == blank) {
                    continue;
                }
                int expect = board[i].length * i + j + 1;
                coordination expectCor = findCor(expect);
                ham = ham + Math.abs(expectCor.x - i);
            }
        }
        return ham;
    }

    public int manhattan() {
        int manhat = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == blank) {
                    continue;
                }
                coordination expectCor = findCor(board[i][j]);
                manhat = manhat + Math.abs(expectCor.x - i) + Math.abs(expectCor.y - j);
            }
        }
        return manhat;
    }


    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (!y.getClass().equals(board.getClass())) {
            return false;
        }
        int cols = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < cols; j++) {
                if (!(this.tileAt(i, j) == ((Board) y).tileAt(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        int cols = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < cols; j++) {
                result = prime * result + this.tileAt(i, j);
            }
        }
        return result;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
