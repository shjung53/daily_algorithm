package 평범한배낭_12865;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] values, weights, dp;
    static int n, k;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        values = new int[n + 1];
        weights = new int[n + 1];
        dp = new int[k + 1];

        PriorityQueue<Item> items = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.weight - o2.weight;
            }
        });

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items.offer(new Item(value, weight));
        }

        int index = 1;
        while (!items.isEmpty()) {
            Item item = items.poll();
            values[index] = item.value;
            weights[index] = item.weight;
            index++;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = k; j - weights[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }

        bw.write(String.valueOf(dp[k]));
        bw.flush();
        bw.close();
        br.close();
    }
}

class Item {
    int value, weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}
