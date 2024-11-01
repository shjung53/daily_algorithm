package 불_4179;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int r, c, startY, startX, answer;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Pos> fires;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        visited = new boolean[r][c];

        fires = new ArrayDeque<>();
        answer = -1;

        for (int i = 0; i < r; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < c; j++) {
                char c = line.charAt(j);
                if (c == '#') map[i][j] = 1;
                if (c == 'J') {
                    startY = i;
                    startX = j;
                }
                if (c == 'F') {
                    map[i][j] = 2;
                    fires.offer(new Pos(i, j));
                }
            }
        }

        bfs();

        if (answer < 0) bw.write("IMPOSSIBLE");
        else bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        Queue<Pos> queue = new ArrayDeque<>();

        int time = 0;

        visited[startY][startX] = true;
        queue.offer(new Pos(startY, startX));

        OUT:
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            spreadFire();
            for (int i = 0; i < queueSize; i++) {
                Pos now = queue.poll();
                if (now.y == 0 || now.x == 0 || now.y == r - 1 || now.x == c - 1) {
                    answer = time + 1;
                    break OUT;
                }
                for (int d = 0; d < 4; d++) {
                    int nextY = now.y + dy[d];
                    int nextX = now.x + dx[d];
                    if (nextY < 0 || nextX < 0 || nextY >= r || nextX >= c) continue;
                    if (visited[nextY][nextX] || map[nextY][nextX] != 0) continue;
                    visited[nextY][nextX] = true;
                    queue.offer(new Pos(nextY, nextX));
                }
            }
            time++;
        }
    }

    private static void spreadFire() {
        int queueSize = fires.size();
        for (int i = 0; i < queueSize; i++) {
            Pos fire = fires.poll();
            for (int d = 0; d < 4; d++) {
                int nextY = fire.y + dy[d];
                int nextX = fire.x + dx[d];
                if (nextY < 0 || nextX < 0 || nextY >= r || nextX >= c) continue;
                if (map[nextY][nextX] == 0) {
                    map[nextY][nextX] = 2;
                    fires.offer(new Pos(nextY, nextX));
                }
            }
        }
    }
}

class Pos {
    int y, x;

    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
