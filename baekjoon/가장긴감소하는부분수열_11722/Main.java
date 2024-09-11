package 가장긴감소하는부분수열_11722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            int index = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    if (max < dp[j]) {
                        max = dp[j];
                        index = j;
                    }
                }
            }
            if (max == 0) {
                dp[i] = 1;
            } else {
                dp[i] = dp[index] + 1;
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > answer) answer = dp[i];
        }

        System.out.println(answer);
    }
}
