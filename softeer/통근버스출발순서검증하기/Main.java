package 통근버스출발순서검증하기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static long answer;
    static int[] bus;
    static int[][] dp;

//    dp[i][j]는 bus[i] 기준으로 bus[j] 포함해서 왼쪽에 작은 버스 개수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        bus = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        answer = 0;
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            bus[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (bus[j] < bus[i]) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (bus[i] < bus[j]) {
                    answer += dp[i][n - 1] - dp[i][j];
                }
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
