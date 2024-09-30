package 캠프준비_16938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, l, r, x, goalCount, answer;
    static int[] problems;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        problems = new int[n];

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(problems);

        for (int i = 1; i <= 15; i++) {
            goalCount = i;
            visited = new boolean[n];
            combination(0, 0);
        }

        System.out.println(answer);
    }

    private static void combination(int index, int count) {
        if (count >= goalCount) {
            if (check()) answer++;
            return;
        }

        if (index >= n) return;
        combination(index + 1, count);
        visited[index] = true;
        combination(index + 1, count + 1);
        visited[index] = false;
    }

    private static boolean check() {
        int levelSum = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                deque.addLast(problems[i]);
                levelSum += problems[i];
            }
        }

        int levelGap = deque.peekLast() - deque.peekFirst();
        if (levelSum < l) return false;
        if (levelSum > r) return false;
        if (levelGap < x) return false;
        return true;
    }
}
