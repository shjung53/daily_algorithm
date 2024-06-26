package 동전1_2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;
    static int[] coin;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coin = new int[n];
        dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine().trim());
        }

        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }

        System.out.println(dp[k]);
    }
}
