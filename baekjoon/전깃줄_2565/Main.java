package 전깃줄_2565;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static int[] dp, max;
    static Line[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        arr = new Line[n + 1];
        dp = new int[n + 1];
        max = new int[n + 1];

        arr[0] = new Line(0, 0);

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[i] = new Line(from, to);
            dp[i] = 1;
        }

        Arrays.sort(arr, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.from - o2.from;
            }
        });

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j].to < arr[i].to) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = 0;

        for (int i = 0; i <= n; i++) {
            if (dp[i] > answer) answer = dp[i];
        }

        System.out.println(n - answer);
    }
}

class Line {
    int from, to;

    public Line(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
