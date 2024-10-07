package 출퇴근길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, s, t, answer;
    static Node[] nodes;
    static boolean[] visited;

    static HashSet<Integer> sSet;
    static HashSet<Integer> tSet;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new Node[n + 1];
        visited = new boolean[n + 1];
        sSet = new HashSet<>();
        tSet = new HashSet<>();
        answer = 0;

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].link.add(to);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        visited[t] = true;
        dfs(t, s);
        visited[s] = true;
        visited = new boolean[n];
        dfs(s, t);

        for (int sNode : sSet) {
            for (int tNode : tSet) {
                if (sNode == tNode) answer++;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int position, int goal) {
        if (position == goal) {
            for (int i = 0; i < n; i++) {
                if(i == t || i == s) continue;
                if (visited[i]) {
                    for (int node : nodes[i].link) {
                        if(node == t || node == s) continue;
                        if (nodes[node].link.contains(i)) {
                            if (goal == t) tSet.add(node);
                            if (goal == s) sSet.add(node);
                        }
                    }
                    if (goal == t) tSet.add(i);
                    if (goal == s) sSet.add(i);
                }
            }
            return;
        }

        for (int next : nodes[position].link) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next, goal);
            visited[next] = false;
        }
    }
}

class Node {
    int num;
    HashSet<Integer> link;

    public Node(int num) {
        this.num = num;
        link = new HashSet<>();
    }
}
