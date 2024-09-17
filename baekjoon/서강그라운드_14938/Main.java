package 서강그라운드_14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int n, m, r, answer;
    static City[] cities;
    static int[] distanceMap;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        cities = new City[n + 1];
        st = new StringTokenizer(br.readLine());
        answer = 0;

        for (int i = 1; i <= n; i++) {
            int item = Integer.parseInt(st.nextToken());
            cities[i] = new City(item);
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            cities[from].link.add(new Route(from, to, cost));
            cities[to].link.add(new Route(to, from, cost));
        }

        for (int i = 1; i <= n; i++) {
            bfs(i);
        }

        System.out.println(answer);

    }

    public static void bfs(int start) {
        PriorityQueue<Person> queue = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.distance - o2.distance;
            }
        });

        distanceMap = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distanceMap[i] = Integer.MAX_VALUE;
        }
        int total = 0;
        distanceMap[start] = 0;
        queue.offer(new Person(0, start));

        while (!queue.isEmpty()) {
            Person now = queue.poll();

            if(distanceMap[now.pos] < now.distance) continue;
            total += cities[now.pos].item;

            for (Route route : cities[now.pos].link) {
                if (distanceMap[route.to] <= now.distance + route.distance) continue;
                if (m < now.distance + route.distance) continue;
                distanceMap[route.to] = now.distance + route.distance;
                queue.offer(new Person(route.distance + now.distance, route.to));
            }
        }

        if (answer < total) answer = total;
    }
}

class City {
    ArrayList<Route> link;
    int item;

    public City(int item) {
        this.item = item;
        link = new ArrayList<>();
    }
}

class Route {
    int from, to, distance;

    public Route(int from, int to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}

class Person {
    int distance;
    int pos;

    public Person(int distance, int pos) {
        this.distance = distance;
        this.pos = pos;
    }
}
