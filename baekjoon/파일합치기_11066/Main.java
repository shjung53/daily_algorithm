package 파일합치기_11066;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int t, k;
    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= t; tc++) {
            k = Integer.parseInt(br.readLine().trim());
            dp = new int[k + 1][k + 1];
            sum = new int[k + 1];
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 1; i <= k; i++) {
                sum[i] = Integer.parseInt(st.nextToken()) + sum[i - 1];
            }

            int answer = findDp(1, k);
            bw.write(String.valueOf(answer));
            if (tc < t) bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findDp(int first, int last) {
        if (dp[first][last] > 0) return dp[first][last];
        for (int i = first; i < last; i++) {
            if (dp[first][last] == 0) {
                dp[first][last] = findDp(first, i) + findDp(i + 1, last) + sum[last] - sum[first - 1];
                continue;
            }

            dp[first][last] = Math.min(dp[first][last], findDp(first, i) + findDp(i + 1, last) + sum[last] - sum[first - 1]);
        }
        return dp[first][last];
    }
}
