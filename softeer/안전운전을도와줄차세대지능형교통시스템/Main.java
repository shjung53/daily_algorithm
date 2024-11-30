package 안전운전을도와줄차세대지능형교통시스템;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] signals = {
            {},
            {0, 1, 3},
            {0, 2, 3},
            {1, 2, 3},
            {0, 1, 2},
            {0, 3},
            {2, 3},
            {1, 2},
            {0, 1},
            {0, 1},
            {0, 3},
            {2, 3},
            {1, 2}
    };
    static int[][] trafficLights;
    static boolean[] visited;
    static int n, t, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        trafficLights = new int[n * n][4];
        visited = new boolean[n * n];
        answer = 0;

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < 4; j++) {
                trafficLights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Move> queue = new ArrayDeque<>();
        visited[0] = true;
        queue.offer(new Move(2, 0));
        int time = 0;

        while (!queue.isEmpty() && time < t) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Move now = queue.poll();
                int nowSignal = trafficLights[now.intersectionIndex][time % 4];
                int possibleEnter = nowSignal % 4;
                if (now.enterDirection != possibleEnter) continue;
                int nowY = now.intersectionIndex / n;
                int nowX = now.intersectionIndex % n;
                for (int d : signals[nowSignal]) {
                    int nextY = nowY + dy[d];
                    int nextX = nowX + dx[d];
                    int index = nextY * n + nextX;
                    if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue;
                    int enterDirection = 0;
                    if (d == 0) enterDirection = 1;
                    if (d == 2) enterDirection = 3;
                    if (d == 3) enterDirection = 2;
                    visited[index] = true;
                    queue.offer(new Move(enterDirection, index));
                }
            }
            time++;
        }

        for (int i = 0; i < n * n; i++) {
            if (visited[i]) answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}

class Move {
    int enterDirection, intersectionIndex;

    public Move(int enterDirection, int intersectionIndex) {
        this.enterDirection = enterDirection;
        this.intersectionIndex = intersectionIndex;
    }
}
