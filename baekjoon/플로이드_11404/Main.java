package 플로이드_11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static int n, m;
    static final int INF = 9999999;
    static int[][] link;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        m = Integer.parseInt(br.readLine().trim());
        stb = new StringBuilder();
        link = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                link[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (link[from][to] > cost) link[from][to] = cost;
        }

        /* 플로이드 워셜
         * 모든 노드간 최단 경로를 구할 수 있다. n^3의 시간 복잡도를 가짐
         * 중간 노드, 출발 노드, 도착 노드를 순회해서 모든 노드간 경로를 찾는다.*/
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        link[i][j] = 0;
                        continue;
                    }
                    if (link[i][j] > link[i][k] + link[k][j]) link[i][j] = link[i][k] + link[k][j];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (link[i][j] > INF) link[i][j] = 0;
                stb.append(link[i][j]).append(' ');
            }
            stb.append('\n');
        }
        System.out.println(stb);
    }
}
