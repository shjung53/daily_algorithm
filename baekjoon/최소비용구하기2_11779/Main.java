package 최소비용구하기2_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[] costMap;
    static City[] cities;
    static Move answerMove;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        m = Integer.parseInt(br.readLine().trim());

        cities = new City[n + 1];
        costMap = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cities[i] = new City();
            costMap[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            cities[from].routes.add(new Route(from, to, cost));
        }

        st = new StringTokenizer(br.readLine().trim());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bfs(start, end);

        StringBuilder stb = new StringBuilder();
        stb.append(answerMove.cost).append('\n');
        stb.append(answerMove.history.size()).append('\n');

        for(int city : answerMove.history) {
            stb.append(city).append(' ');
        }

        System.out.println(stb.toString().trim());
    }

    static void bfs(int start, int end) {
        PriorityQueue<Move> queue = new PriorityQueue<>(new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                return o1.cost - o2.cost;
            }
        });
        costMap[start] = 0;
        Move move = new Move(start, 0);
        move.history.add(start);
        queue.offer(move);

        while (!queue.isEmpty()) {
            Move now = queue.poll();

            if (now.cost > costMap[now.position]) continue;

            if (now.position == end) {
                answerMove = now;
                break;
            }

            for (Route route : cities[now.position].routes) {
                if (costMap[route.to] <= route.cost + now.cost) continue;
                costMap[route.to] = route.cost + now.cost;
                Move next = new Move(route.to, route.cost + now.cost);
                next.history.addAll(now.history);
                next.history.add(route.to);
                queue.offer(next);
            }
        }
    }
}

class City {
    ArrayList<Route> routes;

    public City() {
        routes = new ArrayList<>();
    }
}

class Route {
    int from, to, cost;

    public Route(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

class Move {
    ArrayList<Integer> history;
    int position, cost;

    public Move(int position, int cost) {
        history = new ArrayList<>();
        this.position = position;
        this.cost = cost;
    }
}
