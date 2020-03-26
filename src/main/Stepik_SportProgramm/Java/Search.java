/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Search {

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();
        int n = in.nextInt(), q = in.nextInt();
        int[] arr = in.lineToIntArr(n);
        while (q-- != 0) {
            int l = -1, mid, r = n; //arr[l] < искомого числа; arr[r] >= искомого числа
            int k = in.nextInt();
            while (l + 1 != r) {
                mid = (l + r) / 2;
                if (arr[mid] < k) l = mid;
                else r = mid;
            }
            //System.out.println(r == n || arr[r] != k ? "NO" : "YES");
            if (r < n)
                System.out.println(arr[r]);
            else
                System.out.println("NO SOLUTION");
        }
    }

    private static final class FastInput {
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

        private String nextWord() throws Exception {
            if (st == null || !st.hasMoreElements()) st = new StringTokenizer(br.readLine(), " ");
            return st.nextToken();
        }

        private int[] lineToIntArr(int len) throws Exception {
            int[] arr = new int[len];
            for (int i = 0; i < arr.length; i++) arr[i] = nextInt();
            return arr;
        }
    }
}
