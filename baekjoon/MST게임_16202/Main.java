package MST게임_16202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] parent;
    static int[] rank;
    static Line[] lines;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        lines = new Line[m];
        boolean zeroBefore = false;
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = i + 1;
            lines[i] = new Line(from, to, weight);
        }

        for (int turn = 1; turn <= k; turn++) {
            boolean complete = true;
            if (zeroBefore) {
                stb.append(0).append(' ');
                continue;
            }
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            rank = new int[n + 1];
            int value = calculateMST(turn);
            int root = findParent(parent[1]);
            for (int i = 1; i <= n; i++) {
                if (findParent(parent[i]) != root) {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                stb.append(value).append(' ');
            } else {
                zeroBefore = true;
                stb.append(0).append(' ');
            }
        }

        System.out.println(stb);
    }

    private static int calculateMST(int turn) {
        int sum = 0;

        for (int i = turn - 1; i < m; i++) {
            Line line = lines[i];
            if (tryUnion(line.from, line.to)) {
                sum += line.weight;
            }
        }
        return sum;
    }

    private static boolean tryUnion(int from, int to) {
        int fromParent = findParent(from);
        int toParent = findParent(to);
        if (fromParent == toParent) return false;

        if (rank[fromParent] < rank[toParent]) {
            int temp = fromParent;
            fromParent = toParent;
            toParent = temp;
        }

        parent[toParent] = fromParent;
        rank[fromParent]++;
        return true;
    }

    private static int findParent(int number) {
        if (parent[number] == number) return number;
        return parent[number] = findParent(parent[number]);
    }
}

class Line {
    int from, to;
    int weight;

    public Line(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
