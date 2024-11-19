package 가장긴증가하는부분수열4_14002;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, max;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        dp = new int[n];
        max = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dp[i] > max) max = dp[i];
        }

        int answer = max;
        int count = answer;

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == count) {
                stack.push(arr[i]);
                count--;
            }
            if (count == 0) break;
        }

        bw.write(answer + "\n");

        while (!stack.isEmpty()) {
            bw.write(String.valueOf(stack.pop()));
            if (!stack.isEmpty()) bw.write(" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
