package 미세먼지안녕_17144;

import sun.misc.Signal;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    static StringTokenizer st;
    static int r, c, t, upY, upX, downY, downX;
    static int[][] map;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        upY = -1;
        upX = -1;
        downY = -1;
        downX = -1;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (upY < 0) {
                        upY = i;
                        upX = j;
                    } else {
                        downY = i;
                        downX = j;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            spreadDust();
            cleanUpAir();
            cleanDownAir();
        }

        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] < 0) continue;
                sum += map[i][j];
            }
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
        br.close();

    }

    private static void spreadDust() {
        int[][] spreadMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] <= 0) continue;
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nextY = i + dy[d];
                    int nextX = j + dx[d];
                    if (nextY < 0 || nextX < 0 || nextY >= r || nextX >= c) continue;
                    if (map[nextY][nextX] < 0) continue;
                    count++;
                }

                int spreadAmount = map[i][j] / 5;

                spreadMap[i][j] += spreadAmount * count * -1;

                for (int d = 0; d < 4; d++) {
                    int nextY = i + dy[d];
                    int nextX = j + dx[d];
                    if (nextY < 0 || nextX < 0 || nextY >= r || nextX >= c) continue;
                    if (map[nextY][nextX] < 0) continue;
                    spreadMap[nextY][nextX] += spreadAmount;
                }

            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += spreadMap[i][j];
            }
        }
    }

    private static void cleanUpAir() {
        int y = upY;
        int x = upX;

        while (y >= 0) {
            if (map[y][x] == -1) {
                y--;
                continue;
            }

            if (map[y + 1][x] == -1) {
                y--;
                continue;
            }

            map[y + 1][x] = map[y][x];
            map[y][x] = 0;
            y--;
        }

        y = 0;

        while (x <= c - 1) {
            if (x == 0) {
                x++;
                continue;
            }

            if (map[y][x - 1] == -1) {
                x++;
                continue;
            }

            map[y][x - 1] = map[y][x];
            map[y][x] = 0;
            x++;
        }

        x = c - 1;

        while (y <= upY) {

            if (y == 0) {
                y++;
                continue;
            }

            map[y - 1][x] = map[y][x];
            map[y][x] = 0;
            y++;
        }

        y = upY;

        while (x >= 0) {
            if (map[y][x] == -1) {
                x--;
                continue;
            }

            if (x == c - 1) {
                x--;
                continue;
            }

            map[y][x + 1] = map[y][x];
            map[y][x] = 0;
            x--;
        }
    }

    private static void cleanDownAir() {
        int y = downY;
        int x = downX;

        while (y <= r - 1) {

            if (map[y][x] == -1) {
                y++;
                continue;
            }

            if (map[y - 1][x] == -1) {
                y++;
                continue;
            }

            map[y - 1][x] = map[y][x];
            map[y][x] = 0;
            y++;
        }

        y = r - 1;

//        --> 잡아먹기
        while (x <= c - 1) {
            if (x == 0) {
                x++;
                continue;
            }

            if (map[y][x - 1] == -1) {
                x++;
                continue;
            }

            map[y][x - 1] = map[y][x];
            map[y][x] = 0;
            x++;
        }

        x = c - 1;

        while (y >= downY) {
            if (y == r - 1) {
                y--;
                continue;
            }

            if (map[y + 1][x] == -1) {
                y--;
                continue;
            }

            map[y + 1][x] = map[y][x];
            map[y][x] = 0;
            y--;
        }

        y = downY;


        while (x >= 0) {
            if (x == c - 1) {
                x--;
                continue;
            }

            if (map[y][x] == -1) {
                x--;
                continue;
            }

            map[y][x + 1] = map[y][x];
            map[y][x] = 0;
            x--;
        }
    }
}
