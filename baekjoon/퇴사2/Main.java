package 퇴사2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, answer;
    static Counsel[] counsels;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());

        counsels = new Counsel[n + 1];
        dp = new int[n + 1];
        answer = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            counsels[i] = new Counsel(time, cost);
        }

        for (int i = 1; i <= n; i++) {
            Counsel counsel = counsels[i];
            dp[i] = Math.max(dp[i - 1], dp[i]);
            int end = i + counsel.time - 1;
            if (end > n) continue;
            dp[end] = Math.max(dp[end], dp[i - 1] + counsel.cost);
        }

        for (int i = 1; i <= n; i++) {
            if (dp[i] > answer) answer = dp[i];
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}

class Counsel {
    int time, cost;

    public Counsel(int time, int cost) {
        this.time = time;
        this.cost = cost;
    }
}
