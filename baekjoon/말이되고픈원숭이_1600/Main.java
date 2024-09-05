package 말이되고픈원숭이_1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int k, w, h, answer;

    static int[][] map;
    static int[][][] countMap;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static int[] horseY = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] horseX = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        countMap = new int[h][w][k + 1];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                for (int l = 0; l < k + 1; l++) {
                    countMap[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }

        bfs();

        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<Monkey> queue = new ArrayDeque<>();
        for (int i = 0; i < k + 1; i++) {
            countMap[0][0][i] = 0;
        }
        queue.offer(new Monkey(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Monkey monkey = queue.poll();
            int nowY = monkey.y;
            int nowX = monkey.x;

            if (monkey.count > countMap[nowY][nowX][monkey.horse]) continue;

            if (nowY == h - 1 && nowX == w - 1) {
                if (answer > countMap[nowY][nowX][monkey.horse]) {
                    answer = countMap[nowY][nowX][monkey.horse];
                }
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int newY = nowY + dy[d];
                int newX = nowX + dx[d];

                if (newY < 0 || newY >= h || newX < 0 || newX >= w) continue;
                if (map[newY][newX] == 1) continue;
                if (countMap[newY][newX][monkey.horse] <= monkey.count + 1) continue;
                countMap[newY][newX][monkey.horse] = monkey.count + 1;
                queue.offer(new Monkey(newY, newX, monkey.count + 1, monkey.horse));
            }

            if (monkey.horse >= k) continue;

            for (int d = 0; d < 8; d++) {
                int newY = nowY + horseY[d];
                int newX = nowX + horseX[d];

                if (newY < 0 || newY >= h || newX < 0 || newX >= w) continue;
                if (map[newY][newX] == 1) continue;
                if (countMap[newY][newX][monkey.horse + 1] <= monkey.count + 1) continue;
                countMap[newY][newX][monkey.horse + 1] = monkey.count + 1;
                queue.offer(new Monkey(newY, newX, monkey.count + 1, monkey.horse + 1));
            }

        }
    }
}

class Monkey {
    int y, x, count, horse;

    public Monkey(int y, int x, int count, int horse) {
        this.y = y;
        this.x = x;
        this.count = count;
        this.horse = horse;
    }
}
