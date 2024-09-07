package 나무조경;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int[][] map;
    static boolean[][] visited;

    static int n, answer;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        map = new int[n][n];
        visited = new boolean[n][n];
        answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkTree(0, 0, 0);

        System.out.println(answer);

    }

    static void checkTree(int index, int count, int beauty) {
        int y = index / n;
        int x = index % n;

        if (count >= 4 || index >= n * n) {
            if (beauty > answer) {
                answer = beauty;
                if(beauty == 28) {
                    System.out.println(answer);
                }
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int newY = y + dy[d];
            int newX = x + dx[d];

            if (newY < 0 || newX < 0 || newY >= n || newX >= n) continue;
            if(visited[newY][newX] || visited[y][x]) continue;
            visited[y][x] = true;
            visited[newY][newX] = true;
            checkTree(index + 1, count + 1, beauty + map[y][x] + map[newY][newX]);
            visited[y][x] = false;
            visited[newY][newX] = false;
        }

        checkTree(index + 1, count, beauty);
    }
}
