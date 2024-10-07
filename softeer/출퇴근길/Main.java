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
    static boolean[] visited, toT, toTR, toS, toSR;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new Node[n + 1];
        visited = new boolean[n + 1];
        toT = new boolean[n + 1];
        toTR = new boolean[n + 1];
        toS = new boolean[n + 1];
        toSR = new boolean[n + 1];
        answer = 0;

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].out.add(to);
            nodes[to].in.add(from);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        visited[s] = true;
        dfs(s, t);
        visited = new boolean[n + 1];
        visited[t] = true;
        dfsR(t, t);

        visited = new boolean[n + 1];
        visited[t] = true;
        dfs(t, s);
        visited = new boolean[n + 1];
        visited[s] = true;
        dfsR(s, s);

        for (int i = 1; i <= n; i++) {
            if (i == s || i == t) continue;
            if (toSR[i] && toTR[i] && toT[i] && toS[i]) answer++;
        }

        System.out.println(answer);
    }

    private static void dfs(int position, int goal) {
        if (position == goal) return;
        for (int next : nodes[position].out) {
            if (visited[next]) continue;
            visited[next] = true;
            if (goal == s) toS[next] = true;
            if (goal == t) toT[next] = true;
            dfs(next, goal);
        }
    }

    private static void dfsR(int position, int from) {
        for (int before : nodes[position].in) {
            if (visited[before]) continue;
            if (from == s) toSR[before] = true;
            if (from == t) toTR[before] = true;
            visited[before] = true;
            dfsR(before, from);
        }
    }
}

class Node {
    int num;
    HashSet<Integer> out;
    HashSet<Integer> in;

    public Node(int num) {
        this.num = num;
        out = new HashSet<>();
        in = new HashSet<>();
    }
}
