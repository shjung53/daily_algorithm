package 무기공학_18430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visited;
    static int n, m, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        answer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int index, int count) {
        int y = index / m;
        int x = index % m;

        if (index >= n * m) {
            if (answer < count) answer = count;
            return;
        }

        for (int sub1 = 0; sub1 < 4; sub1++) {
            int sub2 = (sub1 + 1) % 4;
            int sub1Y = y + dy[sub1];
            int sub1X = x + dx[sub1];
            int sub2Y = y + dy[sub2];
            int sub2X = x + dx[sub2];
            if(visited[y][x]) continue;
            if (sub1Y < 0 || sub1X < 0 || sub1Y >= n || sub1X >= m) continue;
            if (sub2Y < 0 || sub2X < 0 || sub2Y >= n || sub2X >= m) continue;
            if (visited[sub1Y][sub1X] || visited[sub2Y][sub2X]) continue;
            visited[y][x] = true;
            visited[sub1Y][sub1X] = true;
            visited[sub2Y][sub2X] = true;
            dfs(index + 1, count + map[y][x] * 2 + map[sub1Y][sub1X] + map[sub2Y][sub2X]);
            visited[y][x] = false;
            visited[sub1Y][sub1X] = false;
            visited[sub2Y][sub2X] = false;
        }

        dfs(index + 1, count);
    }
}
