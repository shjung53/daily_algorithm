package 이분그래프_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static int k, v, e;

    static Node[] nodes;
    static int[] color;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine().trim());
        stb = new StringBuilder();

        for (int tc = 1; tc <= k; tc++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            nodes = new Node[v + 1];
            visited = new boolean[v + 1];

            for (int i = 1; i <= v; i++) {
                nodes[i] = new Node();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                nodes[from].link.add(to);
                nodes[to].link.add(from);
            }

            boolean isBipartite = true;

            for (int i = 1; i <= v; i++) {
                if(visited[i]) continue;
                if (!bfs(i)) isBipartite = false;
            }

            if (isBipartite) {
                stb.append("YES").append('\n');
            } else {
                stb.append("NO").append('\n');
            }
        }

        System.out.println(stb);
    }

    static boolean bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        color = new int[v + 1];
        color[start] = 1;
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int nextColor;
            if (color[now] == 1) {
                nextColor = 2;
            } else {
                nextColor = 1;
            }

            for (int next : nodes[now].link) {
                if (color[next] != 0 && color[next] == color[now]) return false;
                if (visited[next]) continue;
                visited[next] = true;
                color[next] = nextColor;
                queue.offer(next);
            }
        }
        return true;
    }
}

class Node {
    HashSet<Integer> link;

    public Node() {
        link = new HashSet<>();
    }
}
