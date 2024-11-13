package 운동_1956;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int v, e, answer;
    static int[][] route;
    static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        route = new int[v + 1][v + 1];
        answer = INF;

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                route[i][j] = INF;
            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            route[from][to] = cost;
        }

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                for (int k = 1; k <= v; k++) {
                    if (j == k) continue;
                    route[i][k] = Math.min(route[i][k], route[i][j] + route[j][k]);
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            answer = Math.min(answer, route[i][i]);
        }

        if (answer == INF) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
