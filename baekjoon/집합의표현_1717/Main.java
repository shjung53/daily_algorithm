package 집합의표현_1717;

import java.io.*;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m;
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int cal = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cal == 0) {
                union(a, b);
            } else {
                int pA = findParent(a);
                int pB = findParent(b);
                if (pA == pB) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int findParent(int num) {
        if (parent[num] == num) return num;
        return parent[num] = findParent(parent[num]);
    }

    private static void union(int a, int b) {
        int pA = findParent(a);
        int pB = findParent(b);

        if (pA == pB) return;

        if (rank[pA] < rank[pB]) {
            int temp = pA;
            pA = pB;
            pB = temp;
        }

        parent[pB] = pA;
        rank[pA]++;
    }
}
