package 최대힙_11279;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine().trim());

            if (number == 0) {
                if (pq.isEmpty()) {
                    stb.append('0').append('\n');
                } else {
                    stb.append(pq.poll()).append('\n');
                }
            } else {
                pq.offer(number);
            }
        }

        System.out.println(stb);
    }
}
