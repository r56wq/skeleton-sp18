package hw2;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;



public class PercolationStats {
    private int experimentTime;
    private int numOpenSites[]; // store numbers of open site until percolate
    private double threshold[];
    public PercolationStats(int N, int T, PercolationFactory pf) { // perform T independent experiments on an N-by-N grid
        experimentTime = T;
        Percolation pl;
        pl = pf.make(N);
        numOpenSites = new int[experimentTime];
        threshold = new double[experimentTime];
        doExp(experimentTime, pl, N);
        calThreshold(N);
    }

    private void doExp(int experimentTime, Percolation pl, int N) {
        for (int i = 0; i < experimentTime; i++) {
            while (!pl.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!pl.isOpen(row, col)) {
                    pl.open(row, col);
                } else {
                    numOpenSites[i]++;
                }
            }
        }
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
