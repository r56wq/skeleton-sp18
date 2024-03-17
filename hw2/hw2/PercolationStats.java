package hw2;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;
public class PercolationStats {
    private int experimentTime;
    private int length;

    private Percolation pl;
    public PercolationStats(int N, int T, PercolationFactory pf) { // perform T independent experiments on an N-by-N grid
        experimentTime = T;
        length = N;
        pl = pf.make(N);
    }
    public double mean()                                           // sample mean of percolation threshold
    public double stddev()                                         // sample standard deviation of percolation threshold
    public double confidenceLow()                                  // low endpoint of 95% confidence interval
    public double confidenceHigh()                                 // high endpoint of 95% confidence interval
}
