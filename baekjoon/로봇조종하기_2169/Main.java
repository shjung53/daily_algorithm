package 로봇조종하기_2169;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, answer;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int j = 1; j <= m; j++) {
            dp[1][j] = dp[1][j - 1] + map[1][j];
        }

        for (int i = 2; i <= n; i++) {
            int[] left = new int[m + 1];
            int[] right = new int[m + 1];

            left[m] = dp[i - 1][m] + map[i][m];
            for (int j = m - 1; j >= 1; j--) {
                left[j] = Math.max(dp[i - 1][j] + map[i][j], left[j + 1] + map[i][j]);
            }

            right[1] = dp[i - 1][1] + map[i][1];
            for (int j = 2; j <= m; j++) {
                right[j] = Math.max(dp[i - 1][j] + map[i][j], right[j - 1] + map[i][j]);
            }

            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        answer = dp[n][m];

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
