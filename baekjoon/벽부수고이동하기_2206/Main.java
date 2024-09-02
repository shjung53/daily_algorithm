package 벽부수고이동하기_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int[][][] countMap;
    static int n, m, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        countMap = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < m; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
                countMap[i][j][0] = Integer.MAX_VALUE;
                countMap[i][j][1] = Integer.MAX_VALUE;
            }
        }

        bfs();

        answer = Math.min(countMap[n - 1][m - 1][0], countMap[n - 1][m - 1][1]);

        if (answer == Integer.MAX_VALUE) answer = -1;

        System.out.println(answer);
    }

    static void bfs() {
        Queue<Mover> queue = new ArrayDeque<>();
        queue.offer(new Mover(0, 0, 1, 0));
        countMap[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Mover now = queue.poll();

            int nowY = now.y;
            int nowX = now.x;

            for (int d = 0; d < 4; d++) {
                int newY = nowY + dy[d];
                int newX = nowX + dx[d];
                int nowCrashCount = now.crashCount;

                if (newY < 0 || newX < 0 || newY >= n || newX >= m) continue;
                if (map[newY][newX] > 0) nowCrashCount++;
                if (nowCrashCount > 1) continue;

                if (countMap[newY][newX][nowCrashCount] <= now.count + 1) continue;
                countMap[newY][newX][nowCrashCount] = now.count + 1;
                queue.offer(new Mover(newY, newX, now.count + 1, nowCrashCount));

            }
        }
    }
}

class Mover {
    int x, y, count, crashCount;

    public Mover(int y, int x, int count, int crashCount) {
        this.y = y;
        this.x = x;
        this.count = count;
        this.crashCount = crashCount;
    }
}
