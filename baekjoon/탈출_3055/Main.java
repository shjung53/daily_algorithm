package 탈출_3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visited;
    static int r, c, startY, startX, endY, endX, answer;
    static Queue<Pos> water;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        visited = new boolean[r][c];
        water = new ArrayDeque<>();
        answer = -1;

        for (int i = 0; i < r; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < c; j++) {
                char c = line.charAt(j);
                if (c == 'D') {
                    endY = i;
                    endX = j;
                    map[i][j] = 3;
                }

                if (c == 'S') {
                    startY = i;
                    startX = j;
                }

                if (c == 'X') {
                    map[i][j] = 1;
                }

                if (c == '*') {
                    map[i][j] = 2;
                    water.offer(new Pos(i, j));
                }
            }
        }

        findRoute();

        if (answer < 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    private static void findRoute() {
        Queue<Pos> hedgehog = new ArrayDeque<Pos>();
        int time = 0;
        visited[startY][startX] = true;
        hedgehog.offer(new Pos(startY, startX));

        while (!hedgehog.isEmpty()) {
            int waterSize = water.size();

            for (int i = 0; i < waterSize; i++) {
                Pos now = water.poll();
                for (int d = 0; d < 4; d++) {
                    int newY = now.y + dy[d];
                    int newX = now.x + dx[d];
                    if (newY < 0 || newX < 0 || newY >= r || newX >= c) continue;
                    if (map[newY][newX] != 0) continue;
                    map[newY][newX] = 2;
                    water.offer(new Pos(newY, newX));
                }
            }

            int hedgeSize = hedgehog.size();

            for (int i = 0; i < hedgeSize; i++) {
                Pos now = hedgehog.poll();
                if (now.y == endY && now.x == endX) {
                    answer = time;
                    hedgehog.clear();
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int newY = now.y + dy[d];
                    int newX = now.x + dx[d];
                    if (newY < 0 || newX < 0 || newY >= r || newX >= c) continue;
                    if (visited[newY][newX]) continue;
                    if (map[newY][newX] == 1) continue;
                    if (map[newY][newX] == 2) continue;
                    visited[newY][newX] = true;
                    hedgehog.offer(new Pos(newY, newX));
                }
            }

            time++;
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
