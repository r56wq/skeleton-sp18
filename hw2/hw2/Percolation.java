package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF sites;
    private final boolean[] opens; //true if open, false if not open
    private int openNums;
    private final int virtualTop;
    private final int virtualBottom;
    private final int length;

    public Percolation(int N) {
        if (N < 0 || N == 0) {
            throw new java.lang.IllegalArgumentException("No a valid argument");
        } else {
            sites = new WeightedQuickUnionUF(N * N + 2); /*2 is left for virtual top and bottom
            index N*N  is the virtual bottom and N*N + 1 is the virtual top*/
            opens = new boolean[N * N];
            length = N;
            virtualTop = N * N + 1;
            virtualBottom = N * N;
            openNums = 0;
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
        if (checkValid(row, col - 1) && isOpen(row, col - 1)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row, col - 1);
            sites.union(idx1, idx2);
        }

        if (checkValid(row, col + 1) && isOpen(row, col + 1)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row, col + 1);
            sites.union(idx1, idx2);
        }

        if (checkValid(row - 1, col) && isOpen(row - 1, col)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row - 1, col);
            sites.union(idx1, idx2);
        }

        if (checkValid(row + 1, col) && isOpen(row + 1, col)) {
            int idx1 = xyT1D(row, col);
            int idx2 = xyT1D(row + 1, col);
            sites.union(idx1, idx2);
        }
    }

    /*
    * helper method to check if a point is a valid point
     */
    private boolean checkValid(int row, int col) {
        if ((row > 0 || row == 0) && (col > 0) || (col == 0)) {
            return (xyT1D(row, col) < (length * length)) && (xyT1D(row, col) > 0 || xyT1D(row, col) == 0);
        }
        return false;
    }

    /*
    * method to open a blocked
     */
    public void open(int row, int col) {
        if (!checkValid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Index" + row + " " + col
                    + "is out of boundary");
        }
        if (isOpen(row, col)) {
            return;
        }
        openNums++;
        int idx = xyT1D(row, col);
        opens[idx] = true;
        if (row == 0) { /* connect the first row to the virtual top */
            sites.union(idx, virtualTop);
        }
        if (row == length - 1) { /* connect the last row to the virtual bottom */
            sites.union(idx, virtualBottom);
        }
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
        if (!isOpen(row, col)) {
            return false;
        }
        /*
        //to avoid back wash
        if (row == length - 1) {
            if ((isOpen(row, col - 1)) && (checkValid(row, col - 1)) && (sites.connected(xyT1D(row, col - 1), virtualTop))) {
                return true;
            } else if ((isOpen(row, col + 1)) && (checkValid(row, col + 1)) && (sites.connected(xyT1D(row, col + 1), virtualTop))) {
                return true;
            } else {
                return ((isOpen(row - 1, col)) && checkValid(row - 1, col)) && (sites.connected(xyT1D(row - 1, col), virtualTop));
            }
        }
        */
        return sites.connected(xyT1D(row, col), virtualTop);
    }

    /*
    * return number of open sites
     */
    public int numberOfOpenSites() {
        return openNums;
    }

    public boolean percolates() {
        return sites.connected(virtualTop, virtualBottom);
    }
}
