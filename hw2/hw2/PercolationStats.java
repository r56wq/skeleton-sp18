package hw2;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;



public class PercolationStats {
    private int experimentTime;
    private int [] numOpenSites; // store numbers of open site until percolate
    private double [] threshold;

    private int length;
    public PercolationStats(int N, int T, PercolationFactory pf) { // perform T
        // independent experiments on an N-by-N grid
        experimentTime = T;
        length = N;
        numOpenSites = new int[experimentTime];
        threshold = new double[experimentTime];
        doExp(pf);
        calThreshold(N);
    }

    private void doExp(PercolationFactory pf) {
        Percolation pl;
        for (int i = 0; i < experimentTime; i++) {
            //creat a N by N grid
            pl = pf.make(length);
            while (!pl.percolates()) {
                // randomly open a site
                openSite(pl);
                numOpenSites[i]++;
            }
        }
    }



    /*
    *Given a percolation object, return the coordinates of an unopen coordinates
     */
    private void openSite(Percolation pl) {
        int row;
        int col;
        do {
            row = StdRandom.uniform(length);
            col = StdRandom.uniform(length);
        } while (pl.isOpen(row, col));
        pl.open(row, col);
    }




    private void calThreshold(int n) {
        for (int i = 0; i < experimentTime; i++) {
            threshold[i] = ((double) numOpenSites[i]) / n;
        }
    }
    public double mean() {  // sample mean of percolation threshold
        return StdStats.mean(threshold);
    }
    public double stddev() {
        return StdStats.stddev(threshold);
    }                                      // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double m = mean();
        double dev = stddev();
        double con = 1.96;
        return m - (con * dev) / Math.sqrt(experimentTime);
    }                            // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double m = mean();
        double dev = stddev();
        double con = 1.96;
        return m + (con * dev) / Math.sqrt(experimentTime);
    }                                // high endpoint of 95% confidence interval
}
