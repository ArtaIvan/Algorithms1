/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private static final double CONFID_96 = 1.96;

    private final double[] arrStats;
    private final double trial;
    private double meanP = -1.0;
    private double stddevP = -1.0;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        checkN(n, trials);

        arrStats = new double[trials];
        int mx;
        int raw;
        int col;


        double numOp;
        mx = n * n;
        trial = trials;

        for (int i = 0; i < trials; i++) {

            Percolation clPerc = new Percolation(n);
            // raw = StdRandom.uniform(n) + 1;
            // col = StdRandom.uniform(n) + 1;
            while (!clPerc.percolates()) {

                // while (clPerc.isOpen(raw, col)) {
                raw = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;
                // }

                clPerc.open(raw, col);

            }

            numOp = clPerc.numberOfOpenSites();
            arrStats[i] = numOp / mx;

        }
    }

    private void checkN(int i, int tr) {
        if (i <= 0)
            throw new IllegalArgumentException("index n out of bounds");
        if (tr <= 0)
            throw new IllegalArgumentException("index Trials out of bounds");

    }

    // sample mean of percolation threshold
    public double mean() {
        if (meanP < 0) meanP = StdStats.mean(arrStats);
        return meanP;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (stddevP < 0) stddevP = StdStats.stddev(arrStats);
        return stddevP;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        if (meanP < 0) meanP = StdStats.mean(arrStats);
        if (stddevP < 0) stddevP = StdStats.stddev(arrStats);
        return (meanP) - CONFID_96 * stddevP / Math.sqrt(trial);

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (meanP < 0) meanP = StdStats.mean(arrStats);
        if (stddevP < 0) stddevP = StdStats.stddev(arrStats);
        return (meanP) + CONFID_96 * stddevP / Math.sqrt(trial);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 10;
        int m = 20;

        double[] confAr = new double[2];
        PercolationStats statsPer;

        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);

        StdOut.println(n);
        StdOut.println(m);

        Stopwatch stopwatch = new Stopwatch();

        statsPer = new PercolationStats(n, m);

        double time = stopwatch.elapsedTime();

        StdOut.print("initialisation                = ");
        StdOut.println(time);

        StdOut.print("mean                      = ");
        StdOut.println(statsPer.mean());
        StdOut.print("stddev                    = ");
        StdOut.println(statsPer.stddev());
        confAr[0] = statsPer.confidenceLo();
        confAr[1] = statsPer.confidenceHi();
        StdOut.print("95% confidence interval   = ");
        StdOut.println("[" + confAr[0] + ", " + confAr[1] + "]");
    }

}
