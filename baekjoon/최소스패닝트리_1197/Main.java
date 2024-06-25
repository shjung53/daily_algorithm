package 최소스패닝트리_1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int v, e, answer;
    static int[] parent;
    static PriorityQueue<Edge> priorityQueue;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        rank = new int[v + 1];
        answer = 0;
        // 코스트 작은 순으로 우선순위 큐
        priorityQueue = new PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });
        for (int i = 1; i <= v; i++) {
            parent[i] = i; // 자기 부모가 자기
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            priorityQueue.offer(new Edge(from, to, cost));
        }

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            if (union(from, to)) answer += cost;
        }

        System.out.println(answer);
    }

    private static boolean union(int v1, int v2) {
        int v1Parent = findParent(v1);
        int v2Parent = findParent(v2);

        if (v1Parent == v2Parent) return false;

        // 랭크 v1으로 몰기
        if (rank[v1Parent] < rank[v2Parent]) {
            int temp = v2Parent;
            v2Parent = v1Parent;
            v1Parent = temp;
        }

        parent[v2Parent] = v1Parent;
        rank[v1Parent]++;

        return true;
    }

// 자기 부모 찾기
    private static int findParent(int v) {
        if (parent[v] == v) return v;
        return parent[v] = findParent(parent[v]); // 경로 압축, 최종 부모를 모든 자식들에게 전달
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
