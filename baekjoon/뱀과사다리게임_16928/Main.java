package 뱀과사다리게임_16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, answer;
    static HashMap<Integer, Integer> ladder;
    static HashMap<Integer, Integer> snake;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ladder = new HashMap<>();
        snake = new HashMap<>();
        answer = 0;
        visited = new boolean[101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder.put(a, b);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            snake.put(a, b);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        visited[1] = true;
        queue.offer(1);

        EXIT:
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int now = queue.poll();

                if (now == 100) {
                    queue.clear();
                    break  EXIT;
                }

                for (int dice = 1; dice <= 6; dice++) {
                    int next = now + dice;
                    if (ladder.containsKey(next)) {
                        next = ladder.get(next);
                    } else if (snake.containsKey(next)) next = snake.get(next);
                    if (next < 1 || next > 100) continue;
                    if (visited[next]) continue;
                    visited[next] = true;
                    queue.offer(next);
                }
            }

            answer++;
        }

        System.out.println(answer);

    }
}
