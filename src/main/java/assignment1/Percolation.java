/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {
    private boolean[] open; // blocked: false, open: true
    private boolean[] connectTop;
    private boolean[] connectBottom;
    private final int anInt; // create N-by-N grid
    private final QuickUnionUF uf;
    private boolean percolateFlag;

    private int openSites;

    public Percolation(int n) {             // create N-by-N grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("N must be bigger than 0");
        }
        this.anInt = n;
        uf = new QuickUnionUF(n * n);
        open = new boolean[n * n];
        connectTop = new boolean[n * n];
        connectBottom = new boolean[n * n];
        openSites = 0;

        for (int i = 0; i < n * n; i++) {
            open[i] = false;
            connectTop[i] = false;
            connectBottom[i] = false;
        }
        percolateFlag = false;
    }

    public void open(int i, int j) {        // open site (row i, column j) if it is not open already
        validateIJ(i, j);
        int index = xyTo1D(i, j);
        if (!open[index]) {
            open[index] = true;
            openSites++;
        }
        boolean top = false;
        boolean bottom = false;

        int currentParrent;
        int nieghbor;

        if (i < anInt && open[index + anInt]) {


            currentParrent = uf.find(index);
            nieghbor = uf.find(index + anInt);
            if (connectTop[nieghbor] || connectTop[currentParrent]) {
                top = true;
            }
            if (connectBottom[uf.find(index + anInt)] || connectBottom[uf.find(index)]) {
                bottom = true;
            }
            uf.union(index, index + anInt);
        }
        if (i > 1 && open[index - anInt]) {

            currentParrent = uf.find(index);
            nieghbor = uf.find(index - anInt);
            if (connectTop[nieghbor] || connectTop[currentParrent]) {
                top = true;
            }
            if (connectBottom[nieghbor] || connectBottom[currentParrent]) {
                bottom = true;
            }
            uf.union(index, index - anInt);
        }
        if (j < anInt && open[index + 1]) {

            currentParrent = uf.find(index);
            nieghbor = uf.find(index + 1);

            if (connectTop[nieghbor] || connectTop[currentParrent]) {
                top = true;
            }
            if (connectBottom[nieghbor] || connectBottom[currentParrent]) {
                bottom = true;
            }
            uf.union(index, index + 1);
        }
        if (j > 1 && open[index - 1]) {

            currentParrent = uf.find(index);
            nieghbor = uf.find(index - 1);

            if (connectTop[nieghbor] || connectTop[currentParrent]) {
                top = true;
            }
            if (connectBottom[nieghbor] || connectBottom[currentParrent]) {
                bottom = true;
            }
            uf.union(index, index - 1);
        }
        if (i == 1) {
            top = true;
        }
        if (i == anInt) {
            bottom = true;
        }

        currentParrent = uf.find(index);

        connectTop[currentParrent] = top;
        connectBottom[currentParrent] = bottom;
        if (connectTop[currentParrent] && connectBottom[currentParrent]) {
            percolateFlag = true;
        }
    }

    private int xyTo1D(int i, int j) {
        validateIJ(i, j);
        return j + (i - 1) * anInt - 1;
    }

    private void validateIJ(int i, int j) {
        if (!(i >= 1 && i <= anInt && j >= 1 && j <= anInt)) {
            throw new IllegalArgumentException("Index is not betwwen 1 and N");
        }
    }

    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        validateIJ(i, j);
        return open[xyTo1D(i, j)];
    }

    /* A full site is an open site that can be connected to an open site in the top row
     * via a chain of neighboring (left, right, up, down) open sites.
     */

    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        validateIJ(i, j);
        return connectTop[uf.find(xyTo1D(i, j))];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    /* Introduce 2 virtual sites (and connections to top and bottom).
     * Percolates iff virtual top site is connected to virtual bottom site.
     */
    public boolean percolates() {           // does the system percolate?
        return percolateFlag;
    }

    public static void main(String[] args) { // test client (optional)
    }
}

