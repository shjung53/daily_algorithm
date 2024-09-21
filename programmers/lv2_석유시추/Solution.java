package lv2_석유시추;

import java.util.*;

class Solution {
    static int[][] map;
    static int[] rowCount;
    static int n, m;
    static boolean[][] visited;
    static boolean[] rowVisited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public int solution(int[][] land) {
        map = land;
        int answer = 0;
        n = land.length;
        m = land[0].length;
        rowCount = new int[m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] == 0) continue;
                bfs(i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, rowCount[i]);
        }

        return answer;
    }

    public void bfs(int y, int x) {
        int count = 0;
        rowVisited = new boolean[m];
        Queue<Position> queue = new ArrayDeque<>();
        visited[y][x] = true;
        count++;
        rowVisited[x] = true;
        queue.offer(new Position(y, x));

        while (!queue.isEmpty()) {
            Position now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int newY = now.y + dy[d];
                int newX = now.x + dx[d];
                if (newY < 0 || newX < 0 || newY >= n || newX >= m) continue;
                if (map[newY][newX] == 0) continue;
                if (visited[newY][newX]) continue;
                visited[newY][newX] = true;
                count++;
                rowVisited[newX] = true;
                queue.offer(new Position(newY, newX));
            }
        }

        for (int i = 0; i < m; i++) {
            if (rowVisited[i]) rowCount[i] += count;
        }
    }
}

class Position {
    int y, x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
