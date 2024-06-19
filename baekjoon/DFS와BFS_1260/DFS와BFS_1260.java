package DFS와BFS_1260;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS와BFS_1260 {

    static int n, m, v;
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static boolean[][] connection;
    static boolean[] visited;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        stb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        connection = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connection[from][to] = true;
            connection[to][from] = true;
        }

        visited = new boolean[n + 1];
        dfs(v);
        answer.append('\n');
        visited = new boolean[n + 1];
        bfs(v);
        System.out.println(answer);
    }

    private static void dfs(int now) {
        visited[now] = true;
        answer.append(now).append(' ');
        for (int next = 1; next < n + 1; next++) {
            if (connection[now][next] && !visited[next]) {
                visited[next] = true; // 방문 처리 잊지말 것
                dfs(next);
            }
        }
    }

    private static void bfs(int root) {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        visited[root] = true;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size(); // 큐 사이즈만큼 돌아야 같은 레벨에서만 돌게 된다
            for (int i = 0; i < queueSize; i++) {
                int now = queue.poll();
                answer.append(now).append(' ');
                for (int next = 1; next < n + 1; next++) {
                    if (connection[now][next] && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
    }
}
