import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 단지번호붙이_2667 {
    static BufferedReader br;
    static int n, count;
    static int[][] map;
    static PriorityQueue<Integer> complex;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static StringBuilder stb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        map = new int[n][n];
        complex = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < n; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j)); // char 파싱해서 int로
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    count = 0;
                    countHouse(i, j);
                    complex.offer(count);
                }
            }
        }

        stb = new StringBuilder();

        stb.append(complex.size()).append('\n');

        while (!complex.isEmpty()) {
            stb.append(complex.poll()).append('\n');
        }

        System.out.println(stb);

    }

    private static void countHouse(int y, int x) {
        Queue<Position> queue = new ArrayDeque<>();
        map[y][x] = 0;
        queue.offer(new Position(y, x));
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            count++;
            int nowY = now.y;
            int nowX = now.x;

            for (int k = 0; k < 4; k++) {
                int newY = nowY + dy[k];
                int newX = nowX + dx[k];

                if (newY < 0 || newX < 0 || newY >= n || newX >= n || map[newY][newX] == 0) continue;
                map[newY][newX] = 0;
                queue.offer(new Position(newY, newX));
            }
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
