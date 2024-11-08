package yesOrYes_25195;

import java.io.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m;
    static Node[] nodes;
    static String answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new Node[n + 1];
        answer = "Yes";

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(false);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].linkTo.add(to);
        }

        m = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < m; i++) {
            int gom = Integer.parseInt(st.nextToken());
            nodes[gom].isGom = true;
        }

        dfs(1);

        bw.write(answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index) {
        if(nodes[index].isGom) return;
        if (nodes[index].linkTo.size() == 0 && !nodes[index].isGom) answer = "yes";

        for(int next: nodes[index].linkTo) {
            if(!nodes[next].isGom) dfs(next);
        }
    }
}

class Node {
    boolean isGom;
    HashSet<Integer> linkTo;

    public Node(boolean isGom) {
        this.linkTo = new HashSet<>();
        this.isGom = isGom;
    }
}
