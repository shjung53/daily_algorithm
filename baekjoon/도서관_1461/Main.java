package 도서관_1461;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, answer, far;
    static boolean isPositiveLast;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;
        isPositiveLast = false;
        far = 0;

        PriorityQueue<Integer> positive = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> negative = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            int distance = Integer.parseInt(st.nextToken());
            if (distance > 0) {
                if (distance > far) {
                    far = distance;
                    isPositiveLast = true;
                }
            } else {
                if (distance * -1 > far) {
                    far = distance * -1;
                    isPositiveLast = false;
                }
            }

            if (distance > 0) {
                positive.offer(distance);
            } else {
                negative.offer(distance * -1);
            }
        }

        if (isPositiveLast) {
            deliverNegative(negative);
            deliverPositive(positive);
        } else {
            deliverPositive(positive);
            deliverNegative(negative);
        }

        answer -= far;

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void deliverPositive(PriorityQueue<Integer> positive) {
        while (!positive.isEmpty()) {
            int max = 0;
            int delivery = 0;
            if (positive.size() > m) delivery = m;
            if (positive.size() <= m) {
                delivery = positive.size();
            }
            for (int i = 0; i < delivery; i++) {
                int pos = positive.poll();
                if (pos > max) max = pos;
            }
            answer += max * 2;
        }
    }

    private static void deliverNegative(PriorityQueue<Integer> negative) {
        while (!negative.isEmpty()) {
            int max = 0;
            int delivery = 0;
            if (negative.size() > m) delivery = m;
            if (negative.size() <= m) {
                delivery = negative.size();
            }
            for (int i = 0; i < delivery; i++) {
                int pos = negative.poll();
                if (pos > max) max = pos;
            }
            answer += max * 2;
        }
    }
}
