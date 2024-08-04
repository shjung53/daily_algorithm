package 안녕_1535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] life, joy, dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        life = new int[n];
        joy = new int[n];
        dp = new int[100];

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            life[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            joy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 99; j >= life[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - life[i]] + joy[i]);
            }
        }
        System.out.println(dp[99]);
    }
}
