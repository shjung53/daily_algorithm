package garagegame;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int[][] map, game;
    static boolean[][] visited, blockVisited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int n, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        map = new int[3 * n + 1][n];
        game = new int[n][n];
        answer = 0;

        for (int i = 1; i <= 3 * n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                game[i][j] = i + 2 * n + 1;
            }
        }

        solve(1, 0);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve(int time, int totalSize) {
        if (time > 3) {
            if (totalSize > answer) answer = totalSize;
            return;
        }
        visited = new boolean[n][n];
        boolean[][] originalVisited = new boolean[n][n];
        int[][] originalGame = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                originalGame[i][j] = game[i][j];
                originalVisited[i][j] = visited[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blockVisited = new boolean[n][n];
                if (!visited[i][j] && map[game[i][j]][j] > 0) {
                    visited[i][j] = true;
                    blockVisited = new boolean[n][n];
                    blockVisited[i][j] = true;
                    breakBlock(map[game[i][j]][j], i, j);
                    int size = countScore();
                    solve(time + 1, totalSize + size);
                    for (int k = 0; k < n; k++) {
                        System.arraycopy(originalGame[k], 0, game[k], 0, n);
                    }
                }
            }
        }
        for (int k = 0; k < n; k++) {
            System.arraycopy(originalVisited[k], 0, visited[k], 0, n);
        }
    }

    private static int countScore() {
        int maxY = -1;
        int minY = 20;
        int maxX = -1;
        int minX = 20;
        int blockCount = 0;
        for (int j = 0; j < n; j++) {
            int colCount = 0;
            int lastRow = 0;
            for (int i = 0; i < n; i++) {
                if (blockVisited[i][j]) {
                    if (i > maxY) maxY = i;
                    if (i < minY) minY = i;
                    if (j > maxX) maxX = j;
                    if (j < minX) minX = j;
                    blockCount++;
                    colCount++;
                    if (i > lastRow) lastRow = i;
                }
            }
            fallDown(colCount, lastRow, j);
        }

        return blockCount + ((Math.abs(maxY - minY) + 1) * (Math.abs(maxX - minX) + 1));
    }

    private static void fallDown(int amount, int y, int x) {
        for (int row = y; row >= 0; row--) {
            game[row][x] -= amount;
            if (game[row][x] < 0) game[row][x] = 0;
        }
    }

    private static void breakBlock(int number, int y, int x) {
        for (int d = 0; d < 4; d++) {
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue;
            if (map[game[nextY][nextX]][nextX] != number) continue;
            if (map[game[nextY][nextX]][nextX] <= 0) continue;
            if (visited[nextY][nextX]) continue;
            visited[nextY][nextX] = true;
            blockVisited[nextY][nextX] = true;
            breakBlock(number, nextY, nextX);
        }
    }
}
