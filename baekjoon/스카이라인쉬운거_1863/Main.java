package 스카이라인쉬운거_1863;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        answer = 0;

        for (int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && y < deque.peekLast()) {
                answer++;
                deque.removeLast();
            }

            if (!deque.isEmpty() && y == deque.peekLast()) continue;

            deque.addLast(y);
        }

        while (!deque.isEmpty()) {
            if (deque.peekLast() > 0) answer++;
            deque.removeLast();
        }

        br.close();
        System.out.println(answer);
    }
}
