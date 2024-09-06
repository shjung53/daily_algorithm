package 성적평균;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;

    static int[] scoreSum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        StringBuilder stb = new StringBuilder();

        scoreSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int score = Integer.parseInt(st.nextToken());
            scoreSum[i] = scoreSum[i - 1] + score;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int totalScore = scoreSum[to] - scoreSum[from - 1];

            double average = (double) totalScore / (to - from + 1);

            double answer = Math.round(average * 100) / 100.0;

            stb.append(String.format("%.2f", answer)).append('\n');
        }

        System.out.println(stb);
    }
}
