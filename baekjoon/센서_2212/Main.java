package 센서_2212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, answer;

    static int count;

    static HashSet<Integer> numbers;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine().trim());
        answer = 0;

        numbers = new HashSet<>();

        for(int i=0; i<n; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int number: numbers) {
            pq.offer(number);
        }
        PriorityQueue<Integer> gapPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int before = -1;

        while(!pq.isEmpty()) {
            int number = pq.poll();
            if(before == -1) {
                before = number;
                continue;
            }
            gapPQ.offer(number - before);
            before = number;
        }

        for(int i=0; i<k-1; i++) {
            gapPQ.poll();
        }

        while(!gapPQ.isEmpty()) {
            answer  += gapPQ.poll();
        }

        System.out.println(answer);
    }
}
