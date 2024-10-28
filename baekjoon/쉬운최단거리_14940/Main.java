package 쉬운최단거리_14940;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, startY, startX;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map, answer;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        answer = new int[n][m];
        visited = new boolean[n][m];
        startY = 0;
        startX = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }

        Queue<Pos> queue = new ArrayDeque<>();
        visited[startY][startX] = true;
        queue.offer(new Pos(startY, startX));

        int distance = 0;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Pos now = queue.poll();
                answer[now.y][now.x] = distance;
                for (int d = 0; d < 4; d++) {
                    int nextY = now.y + dy[d];
                    int nextX = now.x + dx[d];
                    if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m || visited[nextY][nextX]) continue;
                    if (map[nextY][nextX] == 0) continue;
                    visited[nextY][nextX] = true;
                    queue.offer(new Pos(nextY, nextX));
                }
            }
            distance++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer[i][j] == 0 && map[i][j] != 0 && map[i][j] != 2) {
                    bw.write(-1 + "");
                } else {
                    bw.write(answer[i][j] + "");
                }
                if (j < m - 1) bw.write(" ");
                if (j == m - 1) bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Pos {
    int y, x;

    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
