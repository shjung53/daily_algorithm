package 이차원배열의합_2167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map, dp;
    static int n, m, k, i, j, x, y;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (c == 0) {
                    dp[r][c] = map[r][c];
                    continue;
                }
                dp[r][c] = dp[r][c - 1] + map[r][c];
            }
        }

        k = Integer.parseInt(br.readLine().trim());
        StringBuilder stb = new StringBuilder();
        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine().trim());
            int sum = 0;
            i = Integer.parseInt(st.nextToken()) - 1;
            j = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;

            for (int r = i; r <= x; r++) {
                if (j == 0) {
                    sum += dp[r][y];
                    continue;
                }
                sum += dp[r][y] - dp[r][j - 1];
            }
            stb.append(sum).append('\n');
        }

        System.out.println(stb);
    }
}
