package hcpc_사이클없는그래프만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k, t;
    static Node[] nodes;
    static boolean[] isDeleted;
    static Queue<Integer> adjacent;
    static PriorityQueue<Integer> ascending;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = 0;
        nodes = new Node[n + 1];
        isDeleted = new boolean[n + 1];

        ascending = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nodes[o1].set.size() - nodes[o2].set.size();
            }
        });

        adjacent = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].set.add(to);
            nodes[to].set.add(from);
        }

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < k; i++) {
            adjacent.offer(Integer.parseInt(st.nextToken()));
        }

        do {
            eraseAdjacent();
            arrangeAscending();
            t++;
        }
        while (nodes[ascending.peek()].set.size() > 1);

        System.out.println(t);
    }

    static void eraseAdjacent() {
        int adjacentSize = adjacent.size();
        for (int i = 0; i < adjacentSize; i++) {
            int number = adjacent.poll();
            Node node = nodes[number];
            if (isDeleted[number]) continue;
            isDeleted[number] = true;
            for (int next : node.set) {
                nodes[next].set.remove(node.number);
                if (!isDeleted[next]) adjacent.offer(next);
            }
        }
    }

    static void arrangeAscending() {
        ascending = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nodes[o1].set.size() - nodes[o2].set.size();
            }
        });
        for (int i = 1; i <= n; i++) {
            if (!isDeleted[i]) ascending.offer(i);
        }
    }
}

class Node {
    int number;
    HashSet<Integer> set;

    public Node(int number) {
        this.number = number;
        set = new HashSet<>();
    }
}
