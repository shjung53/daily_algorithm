package 가장큰증가하는부분수열_11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static int[] max;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        max = new int[n];
        dp = new int[n];

        max[0] = arr[0];
        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            int m = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[j] > m) m = dp[j];
                }
            }
            dp[i] = m + arr[i];
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > answer) answer = dp[i];
        }

        System.out.println(answer);
    }
}
