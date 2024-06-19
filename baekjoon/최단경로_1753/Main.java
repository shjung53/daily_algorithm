package 최단경로_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static Node[] nodes;
    static Edge[] edges; // 공간 복잡도를 위해서 따로 빼서 관리한다
    static int v, e, k;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine().trim());
        nodes = new Node[v + 1];
        edges = new Edge[e];
        visited = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= v; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
            nodes[from].connection.add(i);
        }

        findRoute();

        for (int i = 1; i <= v; i++) {
            int answer = visited[i];
            if (answer == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(answer);
            }
        }
    }

    private static void findRoute() {
        PriorityQueue<Move> queue = new PriorityQueue<>(new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                return o1.weight - o2.weight;
            }
        });
        visited[k] = 0;
        queue.offer(new Move(k, 0));

        while (!queue.isEmpty()) {
            Move now = queue.poll();
            int node = now.position;
            for (int edgeNumber: nodes[node].connection) {
                Edge edge = edges[edgeNumber];
                if (visited[edge.to] <= now.weight + edge.weight) continue;
                visited[edge.to] = now.weight + edge.weight;
                queue.offer(new Move(edge.to, visited[edge.to]));
            }
        }
    }
}

class Move {
    int position, weight;

    public Move(int position, int weight) {
        this.position = position;
        this.weight = weight;
    }
}

class Edge {
    int from, to, weight;
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Node {
    ArrayList<Integer> connection = new ArrayList<Integer>();

}
