package 이곱하기엔타일링_11727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[1] = 1;
                continue;
            }
            if (i == 2) {
                dp[2] = 3;
                continue;
            }
            dp[i] = (dp[i - 1] + (dp[i - 2] * 2)) % 10007;
        }

        System.out.println(dp[n]);
    }
}
