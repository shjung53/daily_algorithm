package 레이저통신_6087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map;
    static int[][] visited;
    static int w, h, startY, startX, endY, endX, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        visited = new int[h][w];
        answer = Integer.MAX_VALUE;
        startY = -1;
        startX = -1;

        for (int i = 0; i < h; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < w; j++) {
                char c = line.charAt(j);
                if (c == '.') map[i][j] = 0;
                if (c == '*') map[i][j] = 1;
                if (c == 'C') {
                    map[i][j] = 2;
                    if(startY >= 0 && startX >= 0) {
                        endY = i;
                        endX = j;
                        visited[i][j] = Integer.MAX_VALUE;
                        continue;
                    }
                    startY = i;
                    startX = j;
                }
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        findRoute();

        System.out.println(visited[endY][endX]);
    }

    private static void findRoute() {
        PriorityQueue<Position> pq = new PriorityQueue<>();

        visited[startY][startX] = 0;
        pq.offer(new Position(startY, startX, -1, 0));

        while(!pq.isEmpty()) {
            Position now = pq.poll();

            if(visited[now.y][now.x] < now.count) continue;

            if(now.y == endY && now.x == endX) return;

            for (int d = 0; d < 4; d++) {
                int nextY = now.y + dy[d];
                int nextX = now.x + dx[d];
                if (nextY < 0 || nextX < 0 || nextY >= h || nextX >= w) continue;
                if(map[nextY][nextX] == 1) continue;

                if(now.d < 0) {
                    visited[nextY][nextX] = now.count;
                    pq.offer(new Position(nextY, nextX, d, now.count));
                    continue;
                }

                if(now.d != d) {
                    if(visited[nextY][nextX] < now.count + 1) continue;
                    visited[nextY][nextX] = now.count + 1;
                    pq.offer(new Position(nextY, nextX, d, now.count + 1));
                }else {
                    if(visited[nextY][nextX] < now.count) continue;
                    visited[nextY][nextX] = now.count;
                    pq.offer(new Position(nextY, nextX, d, now.count));
                }

            }
        }
    }
}

class Position implements Comparable<Position> {
    int y, x, d, count;

    public Position(int y, int x, int d, int count) {
        this.y = y;
        this.x = x;
        this.d = d;
        this.count = count;
    }

    @Override
    public int compareTo(Position o) {
        return this.count - o.count;
    }
}
