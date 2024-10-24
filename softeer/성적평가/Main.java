package 성적평가;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static int[][] scores;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        scores = new int[4][n];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += scores[i][j];
            }
            scores[3][j] = sum;
        }

        for (int order = 0; order < 4; order++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int[] sortedScore = scores[order].clone();

            Arrays.sort(sortedScore);

            for (int i=0; i<n; i++) {
                int score = sortedScore[i];
                map.put(score, i);
            }

            for (int j = 0; j < n; j++) {
                bw.write(String.valueOf(n - map.get(scores[order][j])));
                if (j != n - 1) bw.write(" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
