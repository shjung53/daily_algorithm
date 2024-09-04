package 녹색옷입은애가젤다지_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static int n, problem;
    static int[][] map, countMap;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        stb = new StringBuilder();
        problem = 1;

        do {
            n = Integer.parseInt(br.readLine().trim());
            if (n <= 0) break;
            map = new int[n][n];
            countMap = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    countMap[i][j] = Integer.MAX_VALUE;
                }
            }

            bfs();

            int answer = countMap[n - 1][n - 1];
            stb.append("Problem ").append(problem).append(": ").append(answer).append('\n');
            problem++;
        } while (n > 0);

        System.out.println(stb);
    }


    static void bfs() {
        Queue<Link> queue = new ArrayDeque<>();
        countMap[0][0] = map[0][0];
        queue.offer(new Link(0, 0, map[0][0]));

        while (!queue.isEmpty()) {
            Link link = queue.poll();
            int nowY = link.y;
            int nowX = link.x;

            for (int d = 0; d < 4; d++) {
                int newY = nowY + dy[d];
                int newX = nowX + dx[d];
                if (newY < 0 || newX < 0 || newY >= n || newX >= n) continue;
                if (link.rupee + map[newY][newX] >= countMap[newY][newX]) continue;
                countMap[newY][newX] = link.rupee + map[newY][newX];
                queue.offer(new Link(newY, newX, link.rupee + map[newY][newX]));
            }
        }
    }
}

class Link {
    int y, x, rupee;

    public Link(int y, int x, int rupee) {
        this.y = y;
        this.x = x;
        this.rupee = rupee;
    }
}
