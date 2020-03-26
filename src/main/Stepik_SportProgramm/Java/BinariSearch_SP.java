/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BinariSearch_SP {

    public static void main(String[] args) throws Exception {
        try (FastInput in = new FastInput()) {
            int n = in.nextInt();
            double[] coefs = in.doubleArr(n + 1);

            Func fun = x -> {
                double y = 0, currX = 1;
                for (int i = coefs.length - 1; i >= 0; i--) {
                    y += coefs[i] * currX;
                    currX *= x;
                }
                return y;
            };

            double eps = 1e-6, l = -1e10, r = 1e10, rise, f, mid;

            while (fun.f(l) * fun.f(r) > 0) {
                l *= 2;
                r *= 2;
            }

            rise = fun.f(r) >= fun.f(l) ? 1 : -1;

            while (true) {
                mid = (l + r) / 2;
                f = fun.f(mid);
                if (f > -eps && f < eps) {
                    System.out.println(mid);
                    break;
                }
                else if (f * rise > 0d) r = mid;
                else l = mid;
            }
        }
    }

    @FunctionalInterface
    private interface Func {
        double f(double x);
    }

    private static final class FastInput implements AutoCloseable {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        private String nextLine() throws Exception {
            return br.readLine();
        }

        private int nextInt() throws Exception {
            return Integer.parseInt(nextWord());
        }

        private long nextLong() throws Exception {
            return Long.parseLong(nextWord());
        }

        private double nextDouble() throws Exception {
            return Double.parseDouble(nextWord());
        }

        private String nextWord() throws Exception {
            if (st == null || !st.hasMoreElements()) st = new StringTokenizer(br.readLine(), " ");
            return st.nextToken();
        }

        private int[] intArr(int len) throws Exception {
            int[] arr = new int[len];
            for (int i = 0; i < arr.length; i++) arr[i] = nextInt();
            return arr;
        }

        private double[] doubleArr(int len) throws Exception {
            double[] arr = new double[len];
            for (int i = 0; i < arr.length; i++) arr[i] = nextDouble();
            return arr;
        }

        private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        private void write(String s) throws Exception {
            bw.write(s);
        }

        private void writeLine(String s) throws Exception {
            bw.write(s + "\n");
        }

        @Override
        public void close() throws IOException {
            br.close();
            bw.close();
        }
    }
}

