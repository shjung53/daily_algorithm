package 보석도둑_1202;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int n, k;
    static long answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;

        PriorityQueue<Jewelry> pq = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                if (o1.value == o2.value) return o1.weight - o2.weight;
                return o2.value - o1.value;
            }
        });

        TreeMap<Integer, Integer> bagMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            pq.offer(new Jewelry(weight, value));
        }

        for (int i = 0; i < k; i++) {
            int limit = Integer.parseInt(br.readLine().trim());
            bagMap.put(limit, bagMap.getOrDefault(limit, 0) + 1);
        }

        while (!pq.isEmpty()) {
            Jewelry jewelry = pq.poll();
            if (bagMap.keySet().isEmpty()) break;
            if (jewelry.weight > bagMap.lastKey()) continue;
            int leastWeight = bagMap.ceilingKey(jewelry.weight);
            bagMap.put(leastWeight, bagMap.get(leastWeight) - 1);
            if (bagMap.get(leastWeight) <= 0) bagMap.remove(leastWeight);
            answer += jewelry.value;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();

    }
}

class Jewelry {
    int weight, value;

    public Jewelry(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
