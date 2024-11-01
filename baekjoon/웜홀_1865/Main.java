package 웜홀_1865;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, w;
    static Edge[] edges;
    static long[] costs;
    static boolean[] visited;
    static String answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            answer = "NO";
            edges = new Edge[m + w];
            visited = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(from, to, cost);
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[m + i] = new Edge(from, to, cost * -1);
            }

            for (int start = 1; start <= n; start++) {
                if (visited[start]) continue;
                visited[start] = true;
                costs = new long[n + 1];
                Arrays.fill(costs, Integer.MAX_VALUE);
                costs[start] = 0;

                for (int i = 0; i < n; i++) {
                    for (Edge e : edges) {
                        if (e.cost < 0) {
                            if (costs[e.from] != Integer.MAX_VALUE) {
                                if (costs[e.to] > e.cost + costs[e.from]) costs[e.to] = e.cost + costs[e.from];
                            }
                        } else {
                            if (costs[e.from] != Integer.MAX_VALUE) {
                                if (costs[e.to] > e.cost + costs[e.from]) costs[e.to] = e.cost + costs[e.from];
                            }
                            if (costs[e.to] != Integer.MAX_VALUE) {
                                if (costs[e.from] > e.cost + costs[e.to]) costs[e.from] = e.cost + costs[e.to];
                            }
                        }
                    }
                }

                for (Edge e : edges) {
                    if (e.cost < 0) {
                        if (costs[e.from] != Integer.MAX_VALUE) {
                            if (costs[e.to] > e.cost + costs[e.from]) {
                                answer = "YES";
                                break;
                            }
                        }
                    } else {
                        if (costs[e.from] != Integer.MAX_VALUE) {
                            if (costs[e.to] > e.cost + costs[e.from]) {
                                answer = "YES";
                                break;
                            }
                        }
                        if (costs[e.to] != Integer.MAX_VALUE) {
                            if (costs[e.from] > e.cost + costs[e.to]) {
                                answer = "YES";
                                break;
                            }
                        }
                    }
                }

                for (int i = 1; i <= n; i++) {
                    if (costs[i] != Integer.MAX_VALUE) visited[i] = true;
                }
            }

            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
