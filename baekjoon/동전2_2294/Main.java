package 동전2_2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] coins;
    static int[] dp;
    static int n, k, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine().trim());
        }

        dp = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= k; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                if (dp[i - coin] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }

        if (dp[k] == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = dp[k];
        }

        System.out.println(answer);
    }
}
