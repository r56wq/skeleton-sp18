package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF sites;
    private final boolean[] opens; //true if open, false if not open
    private boolean[] fulls; //true if is full, false if not
    private final int length;
    public Percolation(int N) {
        if (N < 0 || N == 0) {
            throw new java.lang.IllegalArgumentException("No a valid argument");
        } else {
            sites = new WeightedQuickUnionUF(N * N);
            opens = new boolean[N];
            length = N;
        }
    }

    private int xyT1D(int r, int c) {
        return (r * length + c);
    }

    /*
    * helper method to check if nearby sites is open
     */
    private void checkNearby(WeightedQuickUnionUF sites, int row, int col) {
        if (!checkValid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Index" + row + " " + col
                    + "is out of boundary");
        }

        //look around and see if other sites are full
        if (checkValid(row, col - 1)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row, col - 1);
            sites.union(idx1, idx2);
        }

        if (checkValid(row, col + 1)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row, col + 1);
            sites.union(idx1, idx2);
        }

        if (checkValid(row - 1, col)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row - 1, col);
            sites.union(idx1, idx2);
        }

        if (checkValid(row + 1, col)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row + 1, col);
            sites.union(idx1, idx2);
        }
    }

    /*
    * helper method to check if a point is a valid point
     */
    private boolean checkValid(int row, int col) {
        return (xyT1D(row, col) < ((length * length) - 1));
    }

    /*
    * method to open a blocked
     */
    public void open(int row, int col) {
        if (!checkValid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Index" + row + " " + col
                    + "is out of boundary");
        }
        int idx = xyT1D(row, col);
        opens[idx] = true;
        checkNearby(sites, row, col);
    }

    /*
    * check if a site is open
     */
    public boolean isOpen(int row, int col) {
        if (!checkValid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Index" + row + " " + col
                    + "is out of boundary");
        }
        return (opens[xyT1D(row, col)]);
    }

    /*
    * check if a site is full
     */
    public boolean isFull(int row, int col) {
        if (!checkValid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Index" + row + " " + col
                    + "is out of boundary");
        }
        return (fulls[xyT1D(row, col)]);
    }

}
